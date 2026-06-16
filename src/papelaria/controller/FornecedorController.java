package papelaria.controller;

import papelaria.exception.*;
import papelaria.model.Fornecedor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FornecedorController {

    private final Map<String, Fornecedor> fornecedores;

    public FornecedorController() {

        this.fornecedores = new HashMap<>();
    }

    public void cadastrarFornecedor(Fornecedor novo_fornecedor) throws FornecedorJaCadastradoException, CnpjInvalidoException, IdInvalidoException, TelefoneInvalidoException {

        if (fornecedores.containsKey(novo_fornecedor.getCnpj())) {
            throw new FornecedorJaCadastradoException("Fornecedor já cadastrado");
        }

        fornecedores.put(novo_fornecedor.getCnpj(), novo_fornecedor);
    }

    public void removerFornecedor(String cnpj) throws FornecedorNaoEncontrado {

        buscarFornecedorCnpj(cnpj);
        fornecedores.remove(cnpj);
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
