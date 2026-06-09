package papelaria.controller;

import papelaria.model.Cliente;
import papelaria.view.ClienteView;
import papelaria.util.ArquivoUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteController {

    private ArrayList<Cliente> clientes;
    private ClienteView view;
    private static final String ARQUIVO = "clientes.txt";

    public ClienteController(Scanner scanner) {
        this.clientes = new ArrayList<>();
        this.view = new ClienteView(scanner);
        carregarDoArquivo();
    }

    public void iniciar() {
        int opcao;
        do {
            opcao = view.mostrarMenu();
            switch (opcao) {
                case 1: cadastrar();
                break;
                case 2: listar();
                break;
                case 3: buscar();
                break;
                case 4: alterar();
                break;
                case 5: deletar();
                break;
                case 0: view.mostrarMensagem("Voltando ao menu principal...");
                break;
                default: view.mostrarMensagem("Opcao invalida!");
            }
        } while (opcao != 0);
    }

    public void cadastrar() {
        try {
            Cliente c = view.lerNovoCliente();
            clientes.add(c);
            salvarNoArquivo();
            ArquivoUtil.log("Cliente cadastrado: " + c.getNome() + " (CPF: " + c.getCpf() + ")");
            view.mostrarMensagem("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            view.mostrarMensagem("Erro ao cadastrar: " + e.getMessage());
            ArquivoUtil.log("ERRO ao cadastrar cliente: " + e.getMessage());
        }
    }

    public void listar() {
        view.listarClientes(clientes);
    }

    public void buscar() {
        String cpf = view.lerCpf();
        Cliente encontrado = buscarPorCpf(cpf);
        view.mostrarCliente(encontrado);
    }

    public void alterar() {
        String cpf = view.lerCpf();
        Cliente c = buscarPorCpf(cpf);

        if (c == null) {
            view.mostrarMensagem("Cliente nao encontrado.");
            return;
        }

        String[] novos = view.lerAlteracao();
        c.setTelefone(novos[0]);
        c.setEmail(novos[1]);
        salvarNoArquivo();
        ArquivoUtil.log("Cliente alterado: " + c.getNome() + " (CPF: " + cpf + ")");
        view.mostrarMensagem("Cliente alterado com sucesso!");
    }

    public void deletar() {
        String cpf = view.lerCpf();
        Cliente c = buscarPorCpf(cpf);

        if (c == null) {
            view.mostrarMensagem("Cliente nao encontrado.");
            return;
        }

        clientes.remove(c);
        salvarNoArquivo();
        ArquivoUtil.log("Cliente removido: " + c.getNome() + " (CPF: " + cpf + ")");
        view.mostrarMensagem("Cliente removido com sucesso!");
    }

    public Cliente buscarPorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equalsIgnoreCase(cpf)) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Cliente> getTodos() {
        return clientes;
    }

    private void salvarNoArquivo() {
        List<String> linhas = new ArrayList<>();
        for (Cliente c : clientes) {
            linhas.add(c.getNome() + ";" + c.getCpf() + ";" + c.getTelefone() + ";" + c.getEmail());
        }

        ArquivoUtil.salvar(ARQUIVO, linhas);
    }

    private void carregarDoArquivo() {
        List<String> linhas = ArquivoUtil.carregar(ARQUIVO);
        clientes.clear();

        for (String linha : linhas) {
            if (linha.trim().isEmpty())
                continue;

            String[] partes = linha.split(";");
            if (partes.length >= 4) {
                clientes.add(new Cliente(partes[0], partes[1], partes[2], partes[3]));
            }
        }
    }
}
