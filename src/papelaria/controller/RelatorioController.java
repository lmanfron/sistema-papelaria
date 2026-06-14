package papelaria.controller;

import papelaria.model.Produto;
import papelaria.view.RelatorioView;

import java.util.ArrayList;
import java.util.Scanner;

public class RelatorioController {
    private RelatorioView view;
    private ClienteController clienteController;
    private ProdutoController produtoController;
    private PedidoController pedidoController;

    public RelatorioController(Scanner scanner, ClienteController clienteController,
                               ProdutoController produtoController, PedidoController pedidoController) {
        this.view = new RelatorioView(scanner);
        this.clienteController = clienteController;
        this.produtoController = produtoController;
        this.pedidoController = pedidoController;
    }

    public void iniciar() {
        int opcao;
        do {
            opcao = view.mostrarMenu();
            switch (opcao) {
                case 1: mostrarTotais(); break;
                case 2: mostrarEstoqueBaixo(); break;
                case 0: view.mostrarMensagem("Voltando ao menu principal..."); break;
                default: view.mostrarMensagem("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    public void mostrarTotais() {
        int totalClientes = clienteController.getTodos().size();
        int totalProdutos = produtoController.getProdutos().size();
        int totalPedidos = pedidoController.getPedidos().size();
        double valorEstoque = 0;

        for (Produto produto : produtoController.getProdutos()) {
            valorEstoque += produto.getPreco() * produto.getQuantidade();
        }

        view.mostrarTotais(totalClientes, totalProdutos, totalPedidos, valorEstoque);
    }

    public void mostrarEstoqueBaixo() {
        int quantidadeMinima = view.lerQuantidadeMinima();
        ArrayList<Produto> estoqueBaixo = new ArrayList<>();

        for (Produto produto : produtoController.getProdutos()) {
            if (produto.estaComEstoqueBaixo(quantidadeMinima)) {
                estoqueBaixo.add(produto);
            }
        }

        view.listarProdutosEstoqueBaixo(estoqueBaixo);
    }
}
