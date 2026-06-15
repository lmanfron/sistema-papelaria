package papelaria.controller;

import papelaria.util.ArquivoUtil;
import papelaria.model.Pagamento;
import papelaria.model.PagamentoCartao;
import papelaria.model.PagamentoPix;
import papelaria.view.PagamentoView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PagamentoController {

    private static final String ARQUIVO = "pagamentos.txt";

    public PagamentoController() {

        this.view = new PagamentoView(this);
        carregarDoArquivo();
    }

    private Map<Integer, Pagamento> pagamentos = new HashMap<>();
    private int proximoId = 1;
    private PagamentoView view;

    public void iniciar() {

        int opcao;

        do {

            opcao = view.mostrarMenu();

            switch (opcao) {

                case 1:
                    view.registrarPagamentoPix();
                    break;

                case 2:
                    view.registrarPagamentoCartao();
                    break;

                case 3:
                    view.listarPagamentos();
                    break;

                case 4:
                    view.buscarPagamento();
                    break;

                case 5:
                    view.mostrarTotalRecebido();
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while (opcao != 0);
    }

    public void registrarPagamento(double valor, String status, String chavePix) {

        if(valor <= 0){
            throw new IllegalArgumentException(
                    "Valor deve ser maior que zero");
        }

        Pagamento pagamento = new PagamentoPix(proximoId,
                valor,
                status,
                chavePix);

        pagamentos.put(proximoId, pagamento);

        salvarNoArquivo();
        ArquivoUtil.log("Pagamento Pix cadastrado. ID: " + proximoId);

        proximoId++;
    }

    public void registrarPagamento(double valor, String status, int parcelas) {

        if(valor <= 0){
            throw new IllegalArgumentException(
                    "Valor deve ser maior que zero");
        }

        Pagamento pagamento = new PagamentoCartao(
                proximoId,
                valor,
                status,
                parcelas);

        pagamentos.put(proximoId, pagamento);

        salvarNoArquivo();
        ArquivoUtil.log("Pagamento Cartao cadastrado. ID: " + proximoId);

        proximoId++;
    }

    public void listarPagamentos() {

        for(Pagamento pagamento : pagamentos.values()) {
            System.out.println(pagamento);
        }
    }

    public Pagamento buscarPagamento(int id) {

        ArquivoUtil.log("Busca de pagamento. ID: " + id);

        return pagamentos.get(id);
    }

    public double calcularTotalRecebido(){

        double total = 0;

        for(Pagamento pagamento : pagamentos.values()){
            total += pagamento.getValor();
        }
        ArquivoUtil.log("Total recebido consultado");
        return total;
    }

    private void salvarNoArquivo() {

        List<String> linhas = new ArrayList<>();

        for(Pagamento pagamento : pagamentos.values()) {

            if(pagamento instanceof PagamentoPix) {

                PagamentoPix pix = (PagamentoPix) pagamento;
                linhas.add("PIX;" +
                        pix.getId() + ";" +
                        pix.getValor() + ";" +
                        pix.getStatus() + ";" +
                        pix.getChavePix()
                );
            }
            else if(pagamento instanceof PagamentoCartao) {

                PagamentoCartao cartao = (PagamentoCartao) pagamento;
                linhas.add("CARTAO;" +
                        cartao.getId() + ";" +
                        cartao.getValor() + ";" +
                        cartao.getStatus() + ";" +
                        cartao.getParcelas()
                );
            }
        }
        ArquivoUtil.salvar(ARQUIVO, linhas);
    }

    private void carregarDoArquivo() {
        List<String> linhas = ArquivoUtil.carregar(ARQUIVO);

        pagamentos.clear();

        for(String linha : linhas) {
            String[] partes = linha.split(";");
            if(partes[0].equals("PIX")) {
                int id = Integer.parseInt(partes[1]);
                double valor = Double.parseDouble(partes[2]);
                String status = partes[3];
                String chavePix = partes[4];

                Pagamento pagamento =
                        new PagamentoPix(
                                id,
                                valor,
                                status,
                                chavePix);

                pagamentos.put(id, pagamento);
            }
            else if(partes[0].equals("CARTAO")) {
                int id = Integer.parseInt(partes[1]);
                double valor = Double.parseDouble(partes[2]);
                String status = partes[3];
                int parcelas = Integer.parseInt(partes[4]);

                Pagamento pagamento =
                        new PagamentoCartao(
                                id,
                                valor,
                                status,
                                parcelas);

                pagamentos.put(id, pagamento);
            }
        }
        if(!pagamentos.isEmpty()) {

            proximoId =
                    pagamentos.keySet()
                            .stream()
                            .max(Integer::compareTo)
                            .get() + 1;
        }
    }
}
