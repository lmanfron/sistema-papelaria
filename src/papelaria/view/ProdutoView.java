package papelaria.view;

import papelaria.model.Categoria;
import papelaria.model.Produto;

import java.util.ArrayList;
import java.util.Scanner;

public class ProdutoView {
    private Scanner scanner;

    public ProdutoView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int mostrarMenu() {
        System.out.println("\n--- MVC 3 - Produtos ---");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Buscar produto por codigo");
        System.out.println("4 - Alterar produto");
        System.out.println("5 - Deletar produto");
        System.out.println("6 - Listar produtos com estoque baixo");
        System.out.println("0 - Voltar ao menu principal");
        System.out.print("Escolha: ");
        return lerInteiro();
    }

    public int lerCodigo() {
        System.out.print("Codigo do produto: ");
        return lerInteiro();
    }

    public String lerNome() {
        System.out.print("Nome do produto: ");
        return scanner.nextLine();
    }

    public double lerPreco() {
        System.out.print("Preco do produto: ");
        return lerDouble();
    }

    public int lerQuantidade() {
        System.out.print("Quantidade em estoque: ");
        return lerInteiro();
    }

    public String lerNomeCategoria() {
        System.out.print("Nome da categoria do produto: ");
        return scanner.nextLine();
    }

    public int lerQuantidadeMinima() {
        System.out.print("Quantidade minima para estoque baixo: ");
        return lerInteiro();
    }

    public void listarProdutos(ArrayList<Produto> produtos) {
        System.out.println("\nProdutos cadastrados:");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }

    public void listarCategorias(ArrayList<Categoria> categorias) {
        System.out.println("\nCategorias disponiveis:");
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
            return;
        }

        for (Categoria categoria : categorias) {
            System.out.println("- " + categoria.getNome());
        }
    }

    public void mostrarProduto(Produto produto) {
        if (produto == null) {
            mostrarMensagem("Produto nao encontrado.");
            return;
        }
        System.out.println(produto);
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

    private double lerDouble() {
        try {
            return Double.parseDouble(scanner.nextLine().replace(",", "."));
        } catch (NumberFormatException erro) {
            return -1;
        }
    }
}
