package papelaria.controller;

import papelaria.model.Categoria;
import papelaria.model.Produto;
import papelaria.util.ArquivoUtil;
import papelaria.view.ProdutoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProdutoController {
    private static final String ARQUIVO = "produtos.txt";

    private ArrayList<Produto> produtos;
    private ProdutoView view;
    private CategoriaController categoriaController;

    public ProdutoController(Scanner scanner, CategoriaController categoriaController) {
        produtos = new ArrayList<>();
        view = new ProdutoView(scanner);
        this.categoriaController = categoriaController;
        carregarDoArquivo();
    }

    public void iniciar() {
        int opcao;
        do {
            opcao = view.mostrarMenu();

            switch (opcao) {
                case 1:
                    cadastrar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    alterar();
                    break;
                case 5:
                    deletar();
                    break;
                case 6:
                    listarEstoqueBaixo();
                    break;
                case 0:
                    view.mostrarMensagem("Voltando ao menu principal...");
                    break;
                default:
                    view.mostrarMensagem("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    public void cadastrar() {
        if (categoriaController.getCategorias().isEmpty()) {
            view.mostrarMensagem("Cadastre uma categoria antes de cadastrar produtos.");
            return;
        }

        try {
            int codigo = view.lerCodigo();

            if (buscarPorCodigo(codigo) != null) {
                view.mostrarMensagem("Ja existe um produto com esse codigo.");
                return;
            }

            String nome = view.lerNome();
            double preco = view.lerPreco();
            int quantidade = view.lerQuantidade();

            view.listarCategorias(categoriaController.getCategorias());
            Categoria categoria = categoriaController.buscarPorNome(view.lerNomeCategoria());
            if (categoria == null) {
                view.mostrarMensagem("Categoria nao encontrada.");
                return;
            }

            Produto produto = new Produto(codigo, nome, preco, quantidade, categoria);
            produtos.add(produto);
            salvarNoArquivo();
            ArquivoUtil.log("Produto cadastrado: " + produto.getNome());
            view.mostrarMensagem("Produto cadastrado com sucesso.");
        } catch (IllegalArgumentException erro) {
            view.mostrarMensagem("Erro: " + erro.getMessage());
            ArquivoUtil.log("Erro ao cadastrar produto: " + erro.getMessage());
        }
    }

    public void listar() {
        view.listarProdutos(produtos);
    }

    public void buscar() {
        int codigo = view.lerCodigo();
        view.mostrarProduto(buscarPorCodigo(codigo));
    }

    public void alterar() {
        int codigo = view.lerCodigo();
        Produto produto = buscarPorCodigo(codigo);

        if (produto == null) {
            view.mostrarMensagem("Produto nao encontrado.");
            return;
        }

        try {
            produto.setNome(view.lerNome());
            produto.setPreco(view.lerPreco());
            produto.setQuantidade(view.lerQuantidade());

            view.listarCategorias(categoriaController.getCategorias());
            Categoria categoria = categoriaController.buscarPorNome(view.lerNomeCategoria());
            if (categoria == null) {
                view.mostrarMensagem("Categoria nao encontrada.");
                return;
            }

            produto.setCategoria(categoria);
            salvarNoArquivo();
            ArquivoUtil.log("Produto alterado: " + produto.getNome());
            view.mostrarMensagem("Produto alterado com sucesso.");
        } catch (IllegalArgumentException erro) {
            view.mostrarMensagem("Erro: " + erro.getMessage());
            ArquivoUtil.log("Erro ao alterar produto: " + erro.getMessage());
        }
    }

    public void deletar() {
        int codigo = view.lerCodigo();
        Produto produto = buscarPorCodigo(codigo);

        if (produto == null) {
            view.mostrarMensagem("Produto nao encontrado.");
            return;
        }

        produtos.remove(produto);
        salvarNoArquivo();
        ArquivoUtil.log("Produto deletado: " + produto.getNome());
        view.mostrarMensagem("Produto deletado com sucesso.");
    }

    public void listarEstoqueBaixo() {
        int quantidadeMinima = view.lerQuantidadeMinima();
        ArrayList<Produto> produtosBaixos = new ArrayList<>();

        for (Produto produto : produtos) {
            if (produto.estaComEstoqueBaixo(quantidadeMinima)) {
                produtosBaixos.add(produto);
            }
        }

        view.listarProdutos(produtosBaixos);
    }

    public Produto buscarPorCodigo(int codigo) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        return null;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    private void salvarNoArquivo() {
        List<String> linhas = new ArrayList<>();
        for (Produto produto : produtos) {
            linhas.add(produto.paraArquivo());
        }

        ArquivoUtil.salvar(ARQUIVO, linhas);
    }

    private void carregarDoArquivo() {
        List<String> linhas = ArquivoUtil.carregar(ARQUIVO);
        produtos.clear();

        for (String linha : linhas) {
            if (linha.trim().isEmpty()) {
                continue;
            }

            String[] partes = linha.split(";", -1);
            if (partes.length >= 5) {
                int codigo = Integer.parseInt(partes[0]);
                String nome = partes[1];
                double preco = Double.parseDouble(partes[2]);
                int quantidade = Integer.parseInt(partes[3]);
                Categoria categoria = categoriaController.buscarPorNome(partes[4]);

                if (categoria == null) {
                    categoria = new Categoria(partes[4], "Categoria carregada pelo produto");
                }

                produtos.add(new Produto(codigo, nome, preco, quantidade, categoria));
            }
        }
    }
}
