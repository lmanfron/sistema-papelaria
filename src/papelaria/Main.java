package papelaria;

import java.util.Scanner;

import papelaria.controller.CategoriaController;
import papelaria.controller.ClienteController;
import papelaria.controller.FuncionarioController;
import papelaria.controller.PedidoController;
import papelaria.controller.VendaController;
import papelaria.controller.PagamentoController;
import papelaria.controller.ProdutoController;
import papelaria.controller.RelatorioController;
import papelaria.view.FornecedorView;
import papelaria.view.EstoqueView;

public class Main {
    public Main() {
    }

    public static void main(String[] var0) {
        Scanner var1 = new Scanner(System.in);
        ClienteController var2 = new ClienteController(var1);
        FuncionarioController var3 = new FuncionarioController(var1);
        CategoriaController var4 = new CategoriaController(var1);
        ProdutoController var5 = new ProdutoController(var1, var4);
        PedidoController var6 = new PedidoController(var1, var5);
        RelatorioController var7 = new RelatorioController(var1, var2, var5, var6);
        VendaController var8 = new VendaController(var1, var2, var3, var5);
        PagamentoController var9 = new PagamentoController();
        FornecedorView var10 = new FornecedorView(var1);
        EstoqueView var11 = new EstoqueView(var5, var1);

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
            System.out.println("5 - Gerenciar Fornecedores");
            System.out.println("6 - Gerenciar Estoque");
            System.out.println("7 - Gerenciar Vendas");
            System.out.println("8 - Gerenciar Pagamentos");
            System.out.println("9 - Gerenciar Pedidos");
            System.out.println("10 - Relatorios");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = lerOpcao(var1);
            switch (opcao) {
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                case 1:
                    var2.iniciar();
                    break;
                case 2:
                    var3.iniciar();
                    break;
                case 3:
                    var5.iniciar();
                    break;
                case 4:
                    var4.iniciar();
                    break;
                case 5:
                    var10.menu();
                    break;
                case 6:
                    var11.menu();
                    break;
                case 7:
                    var8.iniciar();
                    break;
                case 8:
                    var9.iniciar();
                    break;
                case 9:
                    var6.iniciar();
                    break;
                case 10:
                    var7.iniciar();
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        } while(opcao != 0);

        var1.close();
    }

    private static int lerOpcao(Scanner var0) {
        try {
            return Integer.parseInt(var0.nextLine());
        } catch (NumberFormatException var2) {
            return -1;
        }
    }
}
