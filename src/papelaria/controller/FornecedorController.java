package papelaria.controller;

import papelaria.enums.StatusFornecedor;
import papelaria.exception.*;
import papelaria.model.Fornecedor;
import papelaria.util.ArquivoUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FornecedorController {

    private static final String ARQUIVO = "fornecedores.txt";
    private final Map<String, Fornecedor> fornecedores;

    public FornecedorController() {
        this.fornecedores = new HashMap<>();
        carregarDoArquivo();
    }

    public void cadastrarFornecedor(Fornecedor novo_fornecedor) throws FornecedorJaCadastradoException {

        if (fornecedores.containsKey(novo_fornecedor.getCnpj())) {
            ArquivoUtil.log("Tentativa de cadastro duplicado: " + novo_fornecedor.getCnpj());
            throw new FornecedorJaCadastradoException("Fornecedor já cadastrado");
        }

        fornecedores.put(novo_fornecedor.getCnpj(), novo_fornecedor);
        salvarNoArquivo();

        ArquivoUtil.log("Fornecedor cadastrado: " + novo_fornecedor.getNome());
    }

    public void removerFornecedor(String cnpj) throws FornecedorNaoEncontrado {

        Fornecedor fornecedor = buscarFornecedorCnpj(cnpj);

        fornecedores.remove(cnpj);
        salvarNoArquivo();

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

    public void salvarNoArquivo() {
        List<String> linhas = new ArrayList<>();

        for (Fornecedor fornecedor : fornecedores.values()) {
            linhas.add(fornecedor.paraArquivo());
        }

        ArquivoUtil.salvar(ARQUIVO, linhas);
    }

    private void carregarDoArquivo() {
        List<String> linhas = ArquivoUtil.carregar(ARQUIVO);
        fornecedores.clear();

        for (String linha : linhas) {
            if (linha.trim().isEmpty()) {
                continue;
            }

            try {
                String[] partes = linha.split(";", -1);

                if (partes.length >= 6) {
                    int id = Integer.parseInt(partes[0]);
                    String nome = partes[1];
                    String cnpj = partes[2];
                    String telefone = partes[3];
                    String email = partes[4];
                    StatusFornecedor status = StatusFornecedor.valueOf(partes[5]);

                    Fornecedor fornecedor = new Fornecedor(id, nome, cnpj, telefone, email);
                    if (status == StatusFornecedor.INATIVO) {
                        fornecedor.desativar();
                    }

                    fornecedores.put(fornecedor.getCnpj(), fornecedor);
                }
            } catch (Exception erro) {
                ArquivoUtil.log("Erro ao carregar fornecedor: " + linha + " - " + erro.getMessage());
            }
        }
    }

    public ArrayList<Fornecedor> listarFornecedores() {
        return new ArrayList<>(fornecedores.values());
    }
}
