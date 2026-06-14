package papelaria.controller;

import papelaria.model.Pedido;
import papelaria.model.Produto;
import papelaria.util.ArquivoUtil;
import papelaria.view.PedidoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoController {
    private static final String ARQUIVO = "pedidos.txt";

    private ArrayList<Pedido> pedidos;
    private PedidoView view;
    private ProdutoController produtoController;
    private int proximoId;

    public PedidoController(Scanner scanner, ProdutoController produtoController) {
        this.pedidos = new ArrayList<>();
        this.view = new PedidoView(scanner);
        this.produtoController = produtoController;
        this.proximoId = 1;
        carregarDoArquivo();
    }

    public void iniciar() {
        int opcao;
        do {
            opcao = view.mostrarMenu();
            switch (opcao) {
                case 1: criarPedido(); break;
                case 2: listar(); break;
                case 3: buscarPorFornecedor(); break;
                case 4: alterarStatus(); break;
                case 5: cancelarPedido(); break;
                case 0: view.mostrarMensagem("Voltando ao menu principal..."); break;
                default: view.mostrarMensagem("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    public void criarPedido() {
        if (produtoController.getProdutos().isEmpty()) {
            view.mostrarMensagem("Cadastre um produto antes de criar pedidos.");
            return;
        }

        try {
            String fornecedor = view.lerFornecedor();
            view.listarProdutos(produtoController.getProdutos());
            Produto produto = produtoController.buscarPorCodigo(view.lerCodigoProduto());

            if (produto == null) {
                view.mostrarMensagem("Produto nao encontrado.");
                return;
            }

            int quantidade = view.lerQuantidadeSolicitada();
            Pedido pedido = new Pedido(proximoId, fornecedor, produto, quantidade, "Aberto");
            pedidos.add(pedido);
            proximoId++;
            salvarNoArquivo();
            ArquivoUtil.log("Pedido criado: " + pedido.getId());
            view.mostrarMensagem("Pedido criado com sucesso.");
        } catch (IllegalArgumentException erro) {
            view.mostrarMensagem("Erro: " + erro.getMessage());
            ArquivoUtil.log("Erro ao criar pedido: " + erro.getMessage());
        }
    }

    public void listar() {
        view.listarPedidos(pedidos);
    }

    public void buscarPorFornecedor() {
        String fornecedor = view.lerFornecedor();
        ArrayList<Pedido> encontrados = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getFornecedor().equalsIgnoreCase(fornecedor)) {
                encontrados.add(pedido);
            }
        }
        view.listarPedidos(encontrados);
    }

    public void alterarStatus() {
        Pedido pedido = buscarPorId(view.lerIdPedido());
        if (pedido == null) {
            view.mostrarMensagem("Pedido nao encontrado.");
            return;
        }

        try {
            pedido.setStatus(view.lerStatus());
            salvarNoArquivo();
            ArquivoUtil.log("Status do pedido alterado: " + pedido.getId());
            view.mostrarMensagem("Status alterado com sucesso.");
        } catch (IllegalArgumentException erro) {
            view.mostrarMensagem("Erro: " + erro.getMessage());
        }
    }

    public void cancelarPedido() {
        Pedido pedido = buscarPorId(view.lerIdPedido());
        if (pedido == null) {
            view.mostrarMensagem("Pedido nao encontrado.");
            return;
        }
        pedido.setStatus("Cancelado");
        salvarNoArquivo();
        ArquivoUtil.log("Pedido cancelado: " + pedido.getId());
        view.mostrarMensagem("Pedido cancelado com sucesso.");
    }

    public Pedido buscarPorId(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) return pedido;
        }
        return null;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    private void salvarNoArquivo() {
        List<String> linhas = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            linhas.add(pedido.paraArquivo());
        }
        ArquivoUtil.salvar(ARQUIVO, linhas);
    }

    private void carregarDoArquivo() {
        List<String> linhas = ArquivoUtil.carregar(ARQUIVO);
        pedidos.clear();
        int maiorId = 0;

        for (String linha : linhas) {
            if (linha.trim().isEmpty()) continue;

            String[] partes = linha.split(";", -1);
            if (partes.length >= 5) {
                try {
                    int id = Integer.parseInt(partes[0]);
                    String fornecedor = partes[1];
                    int codigoProduto = Integer.parseInt(partes[2]);
                    int quantidade = Integer.parseInt(partes[3]);
                    String status = partes[4];
                    Produto produto = produtoController.buscarPorCodigo(codigoProduto);

                    if (produto != null) {
                        pedidos.add(new Pedido(id, fornecedor, produto, quantidade, status));
                        if (id > maiorId) maiorId = id;
                    }
                } catch (IllegalArgumentException erro) {
                    ArquivoUtil.log("Erro ao carregar pedido: " + erro.getMessage());
                }
            }
        }
        proximoId = maiorId + 1;
    }
}
