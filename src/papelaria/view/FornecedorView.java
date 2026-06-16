package papelaria.view;

import papelaria.controller.FornecedorController;
import papelaria.exception.*;
import papelaria.model.Fornecedor;

import java.util.ArrayList;
import java.util.Scanner;

public class FornecedorView {

    private final Scanner sc;
    private final FornecedorController controller;

    public FornecedorView(Scanner sc) {

        this.sc = sc;
        this.controller = new FornecedorController();
    }

    public void menu() {


        int user_choice;
        do {
            mostrarMensagem("""
                    1.) Cadastrar Fornecedor
                    2.) Remover Fornecedor
                    3.) Verificar Status Fornecedor
                    4.) Ativar Fornecedor
                    5.) Desativar Fornecedor
                    6.) Buscar Fornecedor por CNPJ
                    7.) Buscar Fornecedor por nome
                    8.) Listar Fornecedores
                    0.) Sair""");

            user_choice = sc.nextInt();
            buffer();

            switch (user_choice) {

                case 1:
                    cadastrarFornecedor();
                    break;
                case 2:
                    removerFornecedor();
                    break;
                case 3:
                    verificarStatus();
                    break;
                case 4:
                    ativarFornecedor();
                    break;
                case 5:
                    desativarFornecedor();
                    break;
                case 6:
                    buscarCnpj();
                    break;
                case 7:
                    buscarNome();
                    break;
                case 8:
                    listarFornecedores();
                    break;
                case 0:
                    mostrarMensagem("Saindo...");
                    break;
                default:
                    mostrarMensagem("Opção inválida");
                    break;
            }

        } while (user_choice != 0);

    }

    public void cadastrarFornecedor() {

        try {
            mostrarMensagem("ID:");
            int id = sc.nextInt();
            buffer();
            mostrarMensagem("Nome:");
            String nome = sc.nextLine();
            mostrarMensagem("CNPJ:");
            String cnpj = sc.nextLine();
            mostrarMensagem("Número de telefone:");
            String numero = sc.nextLine();
            mostrarMensagem("E-mail:");
            String email = sc.nextLine();

            Fornecedor fornecedor = new Fornecedor(id, nome, cnpj, numero, email);
            controller.cadastrarFornecedor(fornecedor);

        } catch (FornecedorJaCadastradoException | CnpjInvalidoException | IdInvalidoException e) {
            mostrarMensagem(e.getMessage() + "\n");
        }
    }

    public void removerFornecedor() {
        try {
            mostrarMensagem("CNPJ do Fornecedor à Remover:");
            controller.removerFornecedor(sc.nextLine());
            mostrarMensagem("Fornecedor removido...\n");

        } catch (FornecedorNaoEncontrado e) {
            mostrarMensagem(e.getMessage() + "\n");
        }
    }

    public void verificarStatus() {
        try {
            Fornecedor fornecedor = buscarObjetoFornecedor();

            boolean status = fornecedor.estaAtivo();
            if (status) {
                mostrarMensagem("ATIVO...\n");
            } else {
                mostrarMensagem("INATIVO...\n");
            }

        } catch (FornecedorNaoEncontrado e) {
            mostrarMensagem(e.getMessage() + "\n");
        }
    }

    public void ativarFornecedor() {
        try {
            Fornecedor fornecedor = buscarObjetoFornecedor();
            fornecedor.ativar();
            mostrarMensagem("Fornecedor Ativado...\n");

        } catch (FornecedorNaoEncontrado e) {
            mostrarMensagem(e.getMessage() + "\n");
        }
    }

    public void desativarFornecedor() {
        try {
            Fornecedor fornecedor = buscarObjetoFornecedor();
            fornecedor.destivar();
            mostrarMensagem("Fornecedor Desativado \n");

        } catch (FornecedorNaoEncontrado e) {
            mostrarMensagem(e.getMessage() + "\n");
        }
    }

    public void buscarCnpj() {
        try {
            mostrarMensagem(buscarObjetoFornecedor().toString());

        } catch (FornecedorNaoEncontrado e) {
            mostrarMensagem(e.getMessage() + "\n");
        }
    }

    public void buscarNome() {
        try {
            System.out.println("Digite o nome do distribuidor");
            Fornecedor fornecedor = controller.buscarFornecedorNome(sc.nextLine());
            mostrarMensagem(fornecedor.toString());

        } catch (FornecedorNaoEncontrado e) {
            mostrarMensagem(e.getMessage() + "\n");
        }
    }

    public void listarFornecedores() {
        ArrayList<Fornecedor> fornecedores = controller.listarFornecedores();
        for (Fornecedor f : fornecedores) {
            mostrarMensagem(f.toString());
        }
    }

    public void buffer() {
        sc.nextLine();
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public Fornecedor buscarObjetoFornecedor() throws FornecedorNaoEncontrado {
        mostrarMensagem("CNPJ Fornecedor: ");
        String cnpj = sc.nextLine();
        return controller.buscarFornecedorCnpj(cnpj);
    }
}
