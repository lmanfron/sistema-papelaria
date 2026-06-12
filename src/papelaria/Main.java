package papelaria;

import papelaria.controller.CategoriaController;
import papelaria.controller.ClienteController;
import papelaria.controller.FuncionarioController;
import papelaria.controller.ProdutoController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ClienteController clienteController = new ClienteController(scanner);
        FuncionarioController funcionarioController = new FuncionarioController(scanner);
        CategoriaController categoriaController = new CategoriaController(scanner);
        ProdutoController produtoController = new ProdutoController(scanner, categoriaController);

        int opcao;
        do {
            System.out.println("\n==================================");
            System.out.println("  SISTEMA DE GERENCIAMENTO");
            System.out.println("        DE PAPELARIA");
            System.out.println("==================================");
            System.out.println("1 - Gerenciar Clientes");
            System.out.println("2 - Gerenciar Funcionarios");
            System.out.println("3 - Gerenciar Produtos");
            System.out.println("4 - Gerenciar Categorias");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = lerOpcao(scanner);

            switch (opcao) {
                case 1:
                    clienteController.iniciar();
                    break;
                case 2:
                    funcionarioController.iniciar();
                    break;
                case 3:
                    produtoController.iniciar();
                    break;
                case 4:
                    categoriaController.iniciar();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static int lerOpcao(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException erro) {
            return -1;
        }
    }
}
