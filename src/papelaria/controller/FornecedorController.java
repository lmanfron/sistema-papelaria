package papelaria.controller;

import papelaria.exception.*;
import papelaria.model.Fornecedor;
import papelaria.util.ArquivoUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FornecedorController {

    private final Map<String, Fornecedor> fornecedores;

    public FornecedorController() {

        this.fornecedores = new HashMap<>();
    }

    public void cadastrarFornecedor(Fornecedor novo_fornecedor) throws FornecedorJaCadastradoException {

        if (fornecedores.containsKey(novo_fornecedor.getCnpj())) {
            ArquivoUtil.log("Tentativa de cadastro duplicado: " + novo_fornecedor.getCnpj());

            throw new FornecedorJaCadastradoException("Fornecedor já cadastrado");
        }

        fornecedores.put(novo_fornecedor.getCnpj(), novo_fornecedor);

        ArquivoUtil.log("Fornecedor cadastrado: " + novo_fornecedor.getNome());
    }

    public void removerFornecedor(String cnpj) throws FornecedorNaoEncontrado {

        Fornecedor fornecedor = buscarFornecedorCnpj(cnpj);

        fornecedores.remove(cnpj);

        ArquivoUtil.log("Fornecedor removido: " + fornecedor.getNome());
    }

    public Fornecedor buscarFornecedorCnpj(String cnpj) throws FornecedorNaoEncontrado {

        Fornecedor fornecedor = fornecedores.get(cnpj);
        if (fornecedor == null) {
            throw new FornecedorNaoEncontrado("Fornecedor não encontrado");
        }
        return fornecedor;
    }

    public Fornecedor buscarFornecedorNome(String nome) throws FornecedorNaoEncontrado {

        for (Fornecedor fornecedor : fornecedores.values()) {
            if (fornecedor.getNome().toLowerCase().contains(nome.toLowerCase())) {
                return fornecedor;
            }
        }
        throw new FornecedorNaoEncontrado("Fornecedor não encontrado.");
    }

    public ArrayList<Fornecedor> listarFornecedores() {
        return new ArrayList<>(fornecedores.values());
    }
}
