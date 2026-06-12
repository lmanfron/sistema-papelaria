package papelaria.view;

import papelaria.model.Funcionario;
import java.util.List;
import java.util.Scanner;

public class FuncionarioView {

    private Scanner scanner;

    public FuncionarioView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int mostrarMenu() {
        System.out.println("\n==================================");
        System.out.println("     GERENCIAR FUNCIONARIOS");
        System.out.println("==================================");
        System.out.println("1 - Cadastrar funcionario");
        System.out.println("2 - Listar todos os funcionarios");
        System.out.println("3 - Buscar funcionario por matricula");
        System.out.println("4 - Alterar funcionario");
        System.out.println("5 - Deletar funcionario");
        System.out.println("0 - Voltar ao menu principal");
        System.out.print("Escolha: ");
        return lerInteiro();
    }

    public Funcionario lerNovoFuncionario() {
        System.out.println("\n--- CADASTRAR FUNCIONARIO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Matricula: ");
        String matricula = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Salario: ");
        double salario = lerDouble();
        return new Funcionario(nome, matricula, telefone, cargo, salario);
    }

    public String lerMatricula() {
        System.out.print("Digite a matricula do funcionario: ");
        return scanner.nextLine();
    }

    public String[] lerAlteracao() {
        System.out.print("Novo telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Novo cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Novo salario: ");
        String salario = scanner.nextLine();
        return new String[]{telefone, cargo, salario};
    }

    public void listarFuncionarios(List<Funcionario> funcionarios) {
        if (funcionarios.isEmpty()) {
            System.out.println("\nNenhum funcionario cadastrado.");
        } else {
            System.out.println("\n--- FUNCIONARIOS CADASTRADOS (" + funcionarios.size() + ") ---");
            for (Funcionario f : funcionarios) {
                System.out.println(f);
            }
        }
    }

    public void mostrarFuncionario(Funcionario f) {
        if (f == null) {
            System.out.println("Funcionario nao encontrado.");
        } else {
            System.out.println("\n" + f);
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

    private double lerDouble() {
        try {
            return Double.parseDouble(scanner.nextLine().replace(",", "."));
        } catch (NumberFormatException erro) {
            return -1;
        }
    }
}
