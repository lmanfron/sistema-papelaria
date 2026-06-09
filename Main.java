package papelaria;

import papelaria.controller.ClienteController;
import papelaria.controller.FuncionarioController;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ClienteController clienteController = new ClienteController(scanner);
        FuncionarioController funcionarioController = new FuncionarioController(scanner);

        int opcao;
        do {
            System.out.println("\n==================================");
            System.out.println("  SISTEMA DE GERENCIAMENTO");
            System.out.println("        DE PAPELARIA");
            System.out.println("==================================");
            System.out.println("1 - Gerenciar Clientes");
            System.out.println("2 - Gerenciar Funcionarios");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    clienteController.iniciar();
                    break;
                case 2:
                    funcionarioController.iniciar();
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
}
