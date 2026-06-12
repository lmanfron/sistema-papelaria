package papelaria.controller;

import papelaria.model.Categoria;
import papelaria.util.ArquivoUtil;
import papelaria.view.CategoriaView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoriaController {
    private static final String ARQUIVO = "categorias.txt";

    private ArrayList<Categoria> categorias;
    private CategoriaView view;

    public CategoriaController(Scanner scanner) {
        categorias = new ArrayList<>();
        view = new CategoriaView(scanner);
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
                case 0:
                    view.mostrarMensagem("Voltando ao menu principal...");
                    break;
                default:
                    view.mostrarMensagem("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    public void cadastrar() {
        try {
            String nome = view.lerNome();
            String descricao = view.lerDescricao();

            if (buscarPorNome(nome) != null) {
                view.mostrarMensagem("Ja existe uma categoria com esse nome.");
                return;
            }

            Categoria categoria = new Categoria(nome, descricao);
            categorias.add(categoria);
            salvarNoArquivo();
            ArquivoUtil.log("Categoria cadastrada: " + categoria.getNome());
            view.mostrarMensagem("Categoria cadastrada com sucesso.");
        } catch (IllegalArgumentException erro) {
            view.mostrarMensagem("Erro: " + erro.getMessage());
            ArquivoUtil.log("Erro ao cadastrar categoria: " + erro.getMessage());
        }
    }

    public void listar() {
        view.listarCategorias(categorias);
    }

    public void buscar() {
        String nome = view.lerNome();
        view.mostrarCategoria(buscarPorNome(nome));
    }

    public void alterar() {
        String nome = view.lerNome();
        Categoria categoria = buscarPorNome(nome);

        if (categoria == null) {
            view.mostrarMensagem("Categoria nao encontrada.");
            return;
        }

        try {
            String novaDescricao = view.lerDescricao();
            categoria.setDescricao(novaDescricao);
            salvarNoArquivo();
            ArquivoUtil.log("Categoria alterada: " + categoria.getNome());
            view.mostrarMensagem("Categoria alterada com sucesso.");
        } catch (IllegalArgumentException erro) {
            view.mostrarMensagem("Erro: " + erro.getMessage());
            ArquivoUtil.log("Erro ao alterar categoria: " + erro.getMessage());
        }
    }

    public void deletar() {
        String nome = view.lerNome();
        Categoria categoria = buscarPorNome(nome);

        if (categoria == null) {
            view.mostrarMensagem("Categoria nao encontrada.");
            return;
        }

        categorias.remove(categoria);
        salvarNoArquivo();
        ArquivoUtil.log("Categoria deletada: " + categoria.getNome());
        view.mostrarMensagem("Categoria deletada com sucesso.");
    }

    public Categoria buscarPorNome(String nome) {
        for (Categoria categoria : categorias) {
            if (categoria.getNome().equalsIgnoreCase(nome)) {
                return categoria;
            }
        }
        return null;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    private void salvarNoArquivo() {
        List<String> linhas = new ArrayList<>();
        for (Categoria categoria : categorias) {
            linhas.add(categoria.paraArquivo());
        }

        ArquivoUtil.salvar(ARQUIVO, linhas);
    }

    private void carregarDoArquivo() {
        List<String> linhas = ArquivoUtil.carregar(ARQUIVO);
        categorias.clear();

        for (String linha : linhas) {
            if (linha.trim().isEmpty()) {
                continue;
            }

            String[] partes = linha.split(";", -1);
            if (partes.length >= 2) {
                categorias.add(new Categoria(partes[0], partes[1]));
            }
        }
    }
}
