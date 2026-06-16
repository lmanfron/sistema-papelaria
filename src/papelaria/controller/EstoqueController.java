package papelaria.controller;

import papelaria.exception.EstoqueInsuficienteException;
import papelaria.exception.ProdutoNaoEncontradoException;
import papelaria.exception.ProdutoJaCadastradoException;
import papelaria.exception.QuantidadeInvalidaException;
import papelaria.model.Estoque;
import papelaria.model.Produto;
import papelaria.util.ArquivoUtil;

import java.util.ArrayList;

public class EstoqueController {

    private final Estoque estoque;

    public EstoqueController() {

        this.estoque = new Estoque();
    }

    public void adicionarProdutoAoEstoque(Produto produto) throws ProdutoJaCadastradoException {

        estoque.adicionarProduto(produto);

        ArquivoUtil.log("Produto adicionado ao estoque: " + produto.getNome());
    }

    public void removerProdutoDoEstoque(int codigo) throws ProdutoNaoEncontradoException {

        estoque.removerProduto(codigo);
    }

    public Produto buscarItemEstoque(int codigo) throws ProdutoNaoEncontradoException {

        return estoque.buscarProduto(codigo);
    }

    public ArrayList<Produto> listarItensEstoque() {

        return estoque.listarProdutos();
    }

    public void registrarEntradaEstoque(int codigo, int quantidade) throws ProdutoNaoEncontradoException, QuantidadeInvalidaException {

        estoque.registrarEntrada(codigo, quantidade);

        ArquivoUtil.log("Entrada de estoque - Código: " + codigo + " Quantidade: " + quantidade);
    }


    public void registrarSaidaEstoque(int codigo, int quantidade) throws QuantidadeInvalidaException, ProdutoNaoEncontradoException, EstoqueInsuficienteException {

        estoque.registrarSaida(codigo, quantidade);

        ArquivoUtil.log("Saída de estoque - Código: " + codigo + " Quantidade: " + quantidade);
    }

}
