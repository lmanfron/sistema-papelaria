package papelaria.view;

import papelaria.controller.PagamentoController;
import papelaria.model.Pagamento;

import java.util.Scanner;

public class PagamentoView {

    private Scanner sc = new Scanner(System.in);
    private PagamentoController controller;

    public PagamentoView(PagamentoController controller) {
        this.controller = controller;
    }

    public int mostrarMenu() {

        System.out.println("\n--- MVC 8 - Pagamentos ---");
        System.out.println("1 - Registrar Pagamento Pix");
        System.out.println("2 - Registrar Pagamento Cartao");
        System.out.println("3 - Listar Pagamentos");
        System.out.println("4 - Buscar Pagamento");
        System.out.println("5 - Total Recebido");
        System.out.println("0 - Voltar");

        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void registrarPagamentoPix() {

        System.out.print("Valor: ");
        double valor = sc.nextDouble();
        sc.nextLine();

        System.out.print("Status: ");
        String status = sc.nextLine();

        System.out.print("Chave Pix: ");
        String chavePix = sc.nextLine();

        try {

            controller.registrarPagamento(valor, status, chavePix);

            System.out.println("Pagamento Pix registrado com sucesso!");

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());
        }
    }

    public void registrarPagamentoCartao() {
        System.out.println("valor: ");
        double valor = sc.nextDouble();
        sc.nextLine();

        System.out.println("Status: ");
        String status = sc.nextLine();

        System.out.println("Parcelas: ");
        int parcelas = sc.nextInt();
        sc.nextLine();

        try {

            controller.registrarPagamento(valor, status, parcelas);

            System.out.println("Pagamento cartao registrado com sucesso!");

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());
        }
    }

    public void listarPagamentos() {
        controller.listarPagamentos();
    }

    public void buscarPagamento() {

        System.out.print("ID do pagamento: ");
        int id = sc.nextInt();
        sc.nextLine();

        Pagamento pagamento = controller.buscarPagamento(id);

        if(pagamento != null) {
            System.out.println(pagamento);
        } else {
            System.out.println("Pagamento nao encontrado.");
        }
    }
    public void mostrarTotalRecebido() {

        double total = controller.calcularTotalRecebido();

        System.out.println("Total recebido: " + total);
    }
}
