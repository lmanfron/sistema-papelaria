package papelaria.controller;

import papelaria.model.Cliente;
import papelaria.model.Funcionario;
import papelaria.model.Produto;
import papelaria.model.Venda;
import papelaria.view.VendaView;
import papelaria.util.ArquivoUtil;
import java.util.List;

import java.util.ArrayList;
import java.util.Scanner;

public class VendaController {

    private static final String ARQUIVO = "vendas.txt";

    private ArrayList<Venda> vendas;
    private VendaView view;

    private ClienteController clienteController;
    private FuncionarioController funcionarioController;
    private ProdutoController produtoController;

    private int proximoId;

    public VendaController(Scanner scanner, ClienteController clienteController, FuncionarioController funcionarioController, ProdutoController produtoController) {

        this.vendas = new ArrayList<>();
        this.view = new VendaView(scanner);
        this.clienteController = clienteController;
        this.funcionarioController = funcionarioController;
        this.produtoController = produtoController;
        this.proximoId = 1;
        carregarDoArquivo();
    }

    public void iniciar() {

        int opcao;

        do {

            opcao = view.mostrarMenu();

            switch (opcao) {

                case 1:
                    cadastrarVenda();
                    break;

                case 2:
                    listarVendas();
                    break;

                case 3:
                    buscarVenda();
                    break;

                case 4:
                    alterarVenda();
                    break;

                case 5:
                    excluirVenda();
                    break;

                case 0:
                    view.mostrarMensagem("Voltando...");
                    break;

                default:
                    view.mostrarMensagem("Opcao invalida.");
            }

        } while (opcao != 0);
    }

    private void cadastrarVenda() {

        Cliente cliente = clienteController.buscarPorCpf(view.lerCpfCliente());

        if (cliente == null) {
            view.mostrarMensagem("Cliente nao encontrado.");
            return;
        }

        Funcionario funcionario = funcionarioController.buscarPorMatricula(view.lerMatriculaFuncionario());

        if (funcionario == null) {
            view.mostrarMensagem("Funcionario nao encontrado.");
            return;
        }

        ArrayList<Produto> produtos = new ArrayList<>();

        int quantidade = view.lerQuantidadeProdutos();

        for (int i = 0; i < quantidade; i++) {

            Produto produto = produtoController.buscarPorCodigo(view.lerCodigoProduto());

            if (produto != null) {
                produtos.add(produto);
            }
        }

        Venda venda = new Venda(proximoId, cliente, funcionario, produtos);

        vendas.add(venda);
        salvarNoArquivo();
        ArquivoUtil.log("Venda cadastrada: " + venda.getId());

        proximoId++;

        view.mostrarMensagem("Venda cadastrada com sucesso.");
    }

    private void listarVendas() {

        if (vendas.isEmpty()) {
            view.mostrarMensagem("Nenhuma venda cadastrada.");
            return;
        }

        for (Venda venda : vendas) {
            System.out.println(venda);
        }
    }

    private void buscarVenda() {

        Venda venda = buscarPorId(view.lerIdVenda());

        if (venda == null) {
            view.mostrarMensagem("Venda nao encontrada.");
        } else {
            System.out.println(venda);
        }
    }

    private void excluirVenda() {

        Venda venda = buscarPorId(view.lerIdVenda());

        if (venda == null) {
            view.mostrarMensagem("Venda nao encontrada.");
            return;
        }

        vendas.remove(venda);
        salvarNoArquivo();
        ArquivoUtil.log("Venda removida: " + venda.getId());

        view.mostrarMensagem("Venda removida com sucesso.");
    }

    private void alterarVenda() {

        Venda venda = buscarPorId(view.lerIdVenda());

        if (venda == null) {
            view.mostrarMensagem("Venda nao encontrada.");
            return;
        }

        vendas.remove(venda);

        cadastrarVenda();

        view.mostrarMensagem("Venda alterada com sucesso.");
    }

    public Venda buscarPorId(int id) {

        for (Venda venda : vendas) {

            if (venda.getId() == id) {
                return venda;
            }
        }

        return null;
    }

    public ArrayList<Venda> getVendas() {
        return vendas;
    }
    private void salvarNoArquivo() {

        List<String> linhas = new ArrayList<>();

        for (Venda venda : vendas) {

            StringBuilder produtos = new StringBuilder();

            for (Produto produto : venda.getProdutos()) {

                produtos.append(produto.getCodigo()).append(",");
            }

            linhas.add(venda.getId() + ";" + venda.getCliente().getCpf() + ";" + venda.getFuncionario().getMatricula() + ";" + produtos
            );
        }

        ArquivoUtil.salvar(ARQUIVO, linhas);
    }
    private void carregarDoArquivo() {

        List<String> linhas = ArquivoUtil.carregar(ARQUIVO);

        vendas.clear();

        int maiorId = 0;

        for (String linha : linhas) {

            if (linha.trim().isEmpty()) {
                continue;
            }

            String[] partes = linha.split(";");

            try {

                int id = Integer.parseInt(partes[0]);

                Cliente cliente = clienteController.buscarPorCpf(partes[1]);

                Funcionario funcionario = funcionarioController.buscarPorMatricula(partes[2]);

                ArrayList<Produto> produtos = new ArrayList<>();

                String[] codigos = partes[3].split(",");

                for (String codigo : codigos) {

                    if (!codigo.isEmpty()) {

                        Produto produto = produtoController.buscarPorCodigo(Integer.parseInt(codigo));

                        if (produto != null) {
                            produtos.add(produto);
                        }
                    }
                }

                if (cliente != null && funcionario != null) {

                    vendas.add(new Venda(id, cliente, funcionario, produtos));

                    if (id > maiorId) {
                        maiorId = id;
                    }
                }

            } catch (Exception erro) {

                ArquivoUtil.log("Erro ao carregar venda: " + erro.getMessage()
                );
            }
        }

        proximoId = maiorId + 1;
    }
}
