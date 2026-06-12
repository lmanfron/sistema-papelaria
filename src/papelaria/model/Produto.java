package papelaria.model;

import papelaria.interfaces.Identificavel;

public class Produto implements Identificavel {
    private int codigo;
    private String nome;
    private double preco;
    private int quantidade;
    private Categoria categoria;

    public Produto(int codigo, String nome, double preco, int quantidade, Categoria categoria) {
        setCodigo(codigo);
        setNome(nome);
        setPreco(preco);
        setQuantidade(quantidade);
        setCategoria(categoria);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("Codigo deve ser maior que zero.");
        }
        this.codigo = codigo;
    }

    @Override
    public String getId() {
        return String.valueOf(codigo);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto e obrigatorio.");
        }
        this.nome = nome.trim();
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("Preco nao pode ser negativo.");
        }
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade nao pode ser negativa.");
        }
        this.quantidade = quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria do produto e obrigatoria.");
        }
        this.categoria = categoria;
    }

    public void adicionarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        this.quantidade += quantidade;
    }

    public void removerEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        if (quantidade > this.quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente.");
        }
        this.quantidade -= quantidade;
    }

    public boolean estaComEstoqueBaixo(int quantidadeMinima) {
        return quantidade <= quantidadeMinima;
    }

    public String paraArquivo() {
        return codigo + ";" + nome + ";" + preco + ";" + quantidade + ";" + categoria.getNome();
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo
                + " | Nome: " + nome
                + " | Preco: R$ " + String.format("%.2f", preco)
                + " | Quantidade: " + quantidade
                + " | Categoria: " + categoria.getNome();
    }
}
