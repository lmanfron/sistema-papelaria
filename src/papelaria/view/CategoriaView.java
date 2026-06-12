package papelaria.view;

import papelaria.model.Categoria;

import java.util.ArrayList;
import java.util.Scanner;

public class CategoriaView {
    private Scanner scanner;

    public CategoriaView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int mostrarMenu() {
        System.out.println("\n--- MVC 4 - Categorias ---");
        System.out.println("1 - Cadastrar categoria");
        System.out.println("2 - Listar categorias");
        System.out.println("3 - Buscar categoria por nome");
        System.out.println("4 - Alterar categoria");
        System.out.println("5 - Deletar categoria");
        System.out.println("0 - Voltar ao menu principal");
        System.out.print("Escolha: ");
        return lerInteiro();
    }

    public String lerNome() {
        System.out.print("Nome da categoria: ");
        return scanner.nextLine();
    }

    public String lerDescricao() {
        System.out.print("Descricao da categoria: ");
        return scanner.nextLine();
    }

    public void listarCategorias(ArrayList<Categoria> categorias) {
        System.out.println("\nCategorias cadastradas:");
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
            return;
        }

        for (Categoria categoria : categorias) {
            System.out.println(categoria);
        }
    }

    public void mostrarCategoria(Categoria categoria) {
        if (categoria == null) {
            mostrarMensagem("Categoria nao encontrada.");
            return;
        }
        System.out.println(categoria);
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
