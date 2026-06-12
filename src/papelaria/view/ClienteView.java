package papelaria.view;

import papelaria.model.Cliente;
import java.util.List;
import java.util.Scanner;

public class ClienteView {

    private Scanner scanner;

    public ClienteView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int mostrarMenu() {
        System.out.println("\n==================================");
        System.out.println("       GERENCIAR CLIENTES");
        System.out.println("==================================");
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Listar todos os clientes");
        System.out.println("3 - Buscar cliente por CPF");
        System.out.println("4 - Alterar cliente");
        System.out.println("5 - Deletar cliente");
        System.out.println("0 - Voltar ao menu principal");
        System.out.print("Escolha: ");
        return lerInteiro();
    }

    public Cliente lerNovoCliente() {
        System.out.println("\n--- CADASTRAR CLIENTE ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        return new Cliente(nome, cpf, telefone, email);
    }

    public String lerCpf() {
        System.out.print("Digite o CPF do cliente: ");
        return scanner.nextLine();
    }

    public String[] lerAlteracao() {
        System.out.print("Novo telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Novo email: ");
        String email = scanner.nextLine();
        return new String[]{telefone, email};
    }

    public void listarClientes(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("\nNenhum cliente cadastrado.");
        } else {
            System.out.println("\n--- CLIENTES CADASTRADOS (" + clientes.size() + ") ---");
            for (Cliente c : clientes) {
                System.out.println(c);
            }
        }
    }

    public void mostrarCliente(Cliente c) {
        if (c == null) {
            System.out.println("Cliente nao encontrado.");
        } else {
            System.out.println("\n" + c);
        }
    }

    public void mostrarMensagem(String msg) {
        System.out.println(msg);
    }

    private int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException erro) {
            return -1;
        }
    }
}
