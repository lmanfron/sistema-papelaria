package papelaria.view;

import papelaria.model.Pedido;
import papelaria.model.Produto;

import java.util.ArrayList;
import java.util.Scanner;

public class PedidoView {
    private Scanner scanner;

    public PedidoView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int mostrarMenu() {
        System.out.println("\n--- MVC 9 - Pedidos ---");
        System.out.println("1 - Criar pedido");
        System.out.println("2 - Listar pedidos");
        System.out.println("3 - Buscar pedido por fornecedor");
        System.out.println("4 - Alterar status do pedido");
        System.out.println("5 - Cancelar pedido");
        System.out.println("0 - Voltar ao menu principal");
        System.out.print("Escolha: ");
        return lerInteiro();
    }

    public String lerFornecedor() {
        System.out.print("Fornecedor: ");
        return scanner.nextLine();
    }

    public int lerCodigoProduto() {
        System.out.print("Codigo do produto: ");
        return lerInteiro();
    }

    public int lerQuantidadeSolicitada() {
        System.out.print("Quantidade solicitada: ");
        return lerInteiro();
    }

    public int lerIdPedido() {
        System.out.print("Id do pedido: ");
        return lerInteiro();
    }

    public String lerStatus() {
        System.out.print("Novo status: ");
        return scanner.nextLine();
    }

    public void listarProdutos(ArrayList<Produto> produtos) {
        System.out.println("\nProdutos disponiveis:");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }

    public void listarPedidos(ArrayList<Pedido> pedidos) {
        System.out.println("\nPedidos cadastrados:");
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
            return;
        }
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    private int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException erro) {
            return -1;
        }
    }
}
