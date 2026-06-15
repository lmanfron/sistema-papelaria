package papelaria.model;

import java.util.ArrayList;

public class Venda {

    private int id;
    private Cliente cliente;
    private Funcionario funcionario;
    private ArrayList<Produto> produtos;
    private double valorTotal;

    public Venda(int id, Cliente cliente, Funcionario funcionario, ArrayList<Produto> produtos) {
        this.id = id;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.produtos = produtos;
        this.valorTotal = calcularTotal();
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    private double calcularTotal() {
        double total = 0;

        for (Produto produto : produtos) {
            total += produto.getPreco();
        }

        return total;
    }

    @Override
    public String toString() {
        return "Venda ID: " + id
                + " | Cliente: " + cliente.getNome()
                + " | Funcionario: " + funcionario.getNome()
                + " | Total: R$ " + String.format("%.2f", valorTotal);
    }
}
