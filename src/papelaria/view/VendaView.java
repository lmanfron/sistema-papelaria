package papelaria.view;

import java.util.Scanner;

public class VendaView {

    private Scanner scanner;

    public VendaView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int mostrarMenu() {

        System.out.println("\n--- MVC 7 - Vendas ---");
        System.out.println("1 - Cadastrar venda");
        System.out.println("2 - Listar vendas");
        System.out.println("3 - Buscar venda");
        System.out.println("4 - Alterar venda");
        System.out.println("5 - Excluir venda");
        System.out.println("0 - Voltar");

        return lerInteiro();
    }

    public String lerCpfCliente() {
        System.out.print("CPF do cliente: ");
        return scanner.nextLine();
    }

    public String lerMatriculaFuncionario() {
        System.out.print("Matricula do funcionario: ");
        return scanner.nextLine();
    }

    public int lerCodigoProduto() {
        System.out.print("Codigo do produto: ");
        return lerInteiro();
    }

    public int lerQuantidadeProdutos() {
        System.out.print("Quantidade de produtos na venda: ");
        return lerInteiro();
    }

    public int lerIdVenda() {
        System.out.print("ID da venda: ");
        return lerInteiro();
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
