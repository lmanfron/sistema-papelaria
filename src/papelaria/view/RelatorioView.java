package papelaria.view;

import papelaria.model.Produto;

import java.util.ArrayList;
import java.util.Scanner;

public class RelatorioView {
    private Scanner scanner;

    public RelatorioView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int mostrarMenu() {
        System.out.println("\n--- MVC 10 - Relatorios ---");
        System.out.println("1 - Mostrar totais do sistema");
        System.out.println("2 - Mostrar produtos com estoque baixo");
        System.out.println("0 - Voltar ao menu principal");
        System.out.print("Escolha: ");
        return lerInteiro();
    }

    public int lerQuantidadeMinima() {
        System.out.print("Quantidade minima para estoque baixo: ");
        return lerInteiro();
    }

    public void mostrarTotais(int totalClientes, int totalProdutos, int totalPedidos, double valorEstoque) {
        System.out.println("\n--- Totais do Sistema ---");
        System.out.println("Clientes cadastrados: " + totalClientes);
        System.out.println("Produtos cadastrados: " + totalProdutos);
        System.out.println("Pedidos cadastrados: " + totalPedidos);
        System.out.println("Valor total em estoque: R$ " + String.format("%.2f", valorEstoque));
    }

    public void listarProdutosEstoqueBaixo(ArrayList<Produto> produtos) {
        System.out.println("\nProdutos com estoque baixo:");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto com estoque baixo.");
            return;
        }
        for (Produto produto : produtos) {
            System.out.println(produto);
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
