package papelaria.view;

import papelaria.exception.*;
import papelaria.controller.EstoqueController;
import papelaria.controller.ProdutoController;
import papelaria.model.Produto;

import java.util.Scanner;
import java.util.ArrayList;

public class EstoqueView {

    private final Scanner sc;
    private final EstoqueController estoqueController;
    private final ProdutoController produtoController;

    public EstoqueView(EstoqueController estoqueController, ProdutoController produtoController) {

        this.sc = new Scanner(System.in);
        this.estoqueController = estoqueController;
        this.produtoController = produtoController;

    }

    public void menu() {

        int user_choice;
        do {
            mostrarMensagem("""
                    1.) Adicionar Produto
                    2.) Remover Produto
                    3.) Buscar Item
                    4.) Listar itens
                    5.) Registrar Entrada
                    6.) Registrar Saida
                    0.) Sair""");

            user_choice = sc.nextInt();
            buffer();

            switch (user_choice) {

                case 1:
                    adicionarProduto();
                    break;
                case 2:
                    removerProduto();
                    break;
                case 3:
                    buscarProduto();
                    break;
                case 4:
                    listarItens();
                    break;
                case 5:
                    registrarEntrada();
                    break;
                case 6:
                    registrarSaida();
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

    public void adicionarProduto() {
        try {
            mostrarMensagem("Digite o código do produto a adicionar no estoque:");
            int codigo = sc.nextInt();
            buffer();

            Produto produto = produtoController.buscarPorCodigo(codigo);

            if (produto == null) {
                mostrarMensagem("Produto não encontrado.");
                return;
            }

            estoqueController.adicionarProdutoAoEstoque(produto);

            mostrarMensagem("Produto adicionado ao estoque com sucesso.");

        } catch (ProdutoJaCadastradoException e) {
            mostrarMensagem(e.getMessage());
        }
    }

    public void removerProduto() {
        try {
            mostrarMensagem("Digite o código do produto a remover do estoque:");
            int codigo = sc.nextInt();
            buffer();

            estoqueController.removerProdutoDoEstoque(codigo);

            mostrarMensagem("Produto removido do estoque com sucesso.");

        } catch (ProdutoNaoEncontradoException e) {
            mostrarMensagem(e.getMessage());
        }
    }

    public void buscarProduto() {
        try {
            mostrarMensagem("Digite o código do produto: ");
            int codigo = sc.nextInt();
            buffer();

            Produto produto =
                    estoqueController.buscarItemEstoque(codigo);

            mostrarMensagem(produto.toString());

        } catch (ProdutoNaoEncontradoException e) {
            mostrarMensagem(e.getMessage() + "\n");
        }
    }

    public void listarItens() {

        ArrayList<Produto> produtos =
                estoqueController.listarItensEstoque();

        if (produtos.isEmpty()) {
            mostrarMensagem("Nenhum produto cadastrado no estoque.");
            return;
        }

        for (Produto p : produtos) {
            mostrarMensagem(p.toString());
        }
    }

    public void registrarEntrada() {
        try {

            mostrarMensagem("Digite o código do produto:");
            int codigo = sc.nextInt();
            buffer();

            mostrarMensagem("Digite a quantidade de entrada:");
            int quantidade = sc.nextInt();
            buffer();

            estoqueController.registrarEntradaEstoque(codigo, quantidade);

            mostrarMensagem("Entrada registrada com sucesso.");

        } catch (ProdutoNaoEncontradoException | QuantidadeInvalidaException e) {
            mostrarMensagem(e.getMessage());
        }
    }

    public void registrarSaida() {
        try {

            mostrarMensagem("Digite o código do produto:");
            int codigo = sc.nextInt();
            buffer();

            mostrarMensagem("Digite a quantidade de saida:");
            int quantidade = sc.nextInt();
            buffer();

            estoqueController.registrarSaidaEstoque(codigo, quantidade);

            mostrarMensagem("Saida registrada com sucesso.");

        } catch (ProdutoNaoEncontradoException | QuantidadeInvalidaException | EstoqueInsuficienteException e) {
            mostrarMensagem(e.getMessage());
        }
    }


    public void mostrarMensagem(String mensagem) {

        System.out.println(mensagem);
    }

    public void buffer() {

        sc.nextLine();
    }
}
