package papelaria.model;

import papelaria.exception.EstoqueInsuficienteException;
import papelaria.exception.ProdutoNaoEncontradoException;
import papelaria.exception.QuantidadeInvalidaException;
import papelaria.exception.ProdutoJaCadastradoException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Estoque {

    private final Map<Integer, Produto> itensEstoque;

    public Estoque() {

        this.itensEstoque = new HashMap<>();
    }

    public void adicionarProduto(Produto produto) throws ProdutoJaCadastradoException {

    if(produto == null) {
        throw new IllegalArgumentException("Produto não pode ser nulo");
    }

    if(itensEstoque.containsKey(produto.getCodigo())) {
        throw new ProdutoJaCadastradoException("Produto já cadastrado no estoque");
    }

        itensEstoque.put(produto.getCodigo(), produto);
    }

    public void removerProduto(int codigo) throws ProdutoNaoEncontradoException {

        buscarProduto(codigo);
        itensEstoque.remove(codigo);
    }

    public Produto buscarProduto(int codigo)
            throws ProdutoNaoEncontradoException {

        Produto produto = itensEstoque.get(codigo);

        if (produto == null) {
            throw new ProdutoNaoEncontradoException("Produto não encontrado");
        }

        return produto;
    }

    public ArrayList<Produto> listarProdutos() {

        return new ArrayList<>(itensEstoque.values());
    }

    public void registrarEntrada(int codigo, int quantidade) throws QuantidadeInvalidaException, ProdutoNaoEncontradoException {

        if(quantidade <= 0) {
            throw new QuantidadeInvalidaException("A quantidade de entrada deve ser maior que 0");
        }

        Produto produto = buscarProduto(codigo);

        produto.setQuantidade(produto.getQuantidade() + quantidade);
    }

    public void registrarSaida(int codigo, int quantidade) throws QuantidadeInvalidaException, ProdutoNaoEncontradoException, EstoqueInsuficienteException {

        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("A quantidade de saida deve ser maior que 0");
        }

        Produto produto = buscarProduto(codigo);

        if(produto.getQuantidade() < quantidade) {
            throw new EstoqueInsuficienteException("Estoque insuficiente para essa saida");
        }

        produto.setQuantidade(produto.getQuantidade() - quantidade);
    }
}
