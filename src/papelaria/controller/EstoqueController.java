package papelaria.controller;

import papelaria.exception.EstoqueInsuficienteException;
import papelaria.exception.ProdutoNaoEncontradoException;
import papelaria.exception.ProdutoJaCadastradoException;
import papelaria.exception.QuantidadeInvalidaException;
import papelaria.model.Estoque;
import papelaria.model.Produto;
import papelaria.util.ArquivoUtil;

import java.util.ArrayList;
import java.util.List;

public class EstoqueController {

    private static final String ARQUIVO = "estoque.txt";
    private final Estoque estoque;
    private final ProdutoController produtoController;

    public EstoqueController(ProdutoController produtoController) {
        this.estoque = new Estoque();
        this.produtoController = produtoController;
        carregarDoArquivo();
    }

    public void adicionarProdutoAoEstoque(Produto produto) throws ProdutoJaCadastradoException {

        estoque.adicionarProduto(produto);
        salvarNoArquivo();

        ArquivoUtil.log("Produto adicionado ao estoque: " + produto.getNome());
    }

    public void removerProdutoDoEstoque(int codigo) throws ProdutoNaoEncontradoException {

        Produto produto = buscarItemEstoque(codigo);

        estoque.removerProduto(codigo);
        salvarNoArquivo();

        ArquivoUtil.log("Produto removido do estoque: " + produto.getNome());
    }

    public Produto buscarItemEstoque(int codigo) throws ProdutoNaoEncontradoException {

        return estoque.buscarProduto(codigo);
    }

    public ArrayList<Produto> listarItensEstoque() {

        return estoque.listarProdutos();
    }

    public void registrarEntradaEstoque(int codigo, int quantidade) throws ProdutoNaoEncontradoException, QuantidadeInvalidaException {

        estoque.registrarEntrada(codigo, quantidade);
        produtoController.atualizarArquivo();

        ArquivoUtil.log("Entrada de estoque - Código: " + codigo + " Quantidade: " + quantidade);
    }


    public void registrarSaidaEstoque(int codigo, int quantidade) throws QuantidadeInvalidaException, ProdutoNaoEncontradoException, EstoqueInsuficienteException {

        estoque.registrarSaida(codigo, quantidade);
        produtoController.atualizarArquivo();

        ArquivoUtil.log("Saída de estoque - Código: " + codigo + " Quantidade: " + quantidade);
    }

    private void salvarNoArquivo() {
        List<String> linhas = new ArrayList<>();

        for (Produto produto : estoque.listarProdutos()) {
            linhas.add(String.valueOf(produto.getCodigo()));
        }

        ArquivoUtil.salvar(ARQUIVO, linhas);
    }

    private void carregarDoArquivo() {
        List<String> linhas = ArquivoUtil.carregar(ARQUIVO);

        for (String linha : linhas) {
            if (linha.trim().isEmpty()) {
                continue;
            }

            try {
                int codigo = Integer.parseInt(linha.trim());
                Produto produto = produtoController.buscarPorCodigo(codigo);

                if (produto != null) {
                    estoque.adicionarProduto(produto);
                }
            } catch (Exception erro) {
                ArquivoUtil.log("Erro ao carregar item do estoque: " + linha + " - " + erro.getMessage());
            }
        }
    }
}
