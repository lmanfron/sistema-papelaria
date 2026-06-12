package papelaria.controller;

import papelaria.model.Funcionario;
import papelaria.view.FuncionarioView;
import papelaria.util.ArquivoUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FuncionarioController {

    private ArrayList<Funcionario> funcionarios;
    private FuncionarioView view;
    private static final String ARQUIVO = "funcionarios.txt";

    public FuncionarioController(Scanner scanner) {
        this.funcionarios = new ArrayList<>();
        this.view = new FuncionarioView(scanner);
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
            Funcionario f = view.lerNovoFuncionario();
            if (buscarPorMatricula(f.getMatricula()) != null) {
                view.mostrarMensagem("Ja existe um funcionario com essa matricula.");
                return;
            }
            funcionarios.add(f);
            salvarNoArquivo();
            ArquivoUtil.log("Funcionario cadastrado: " + f.getNome() + " (Matricula: " + f.getMatricula() + ")");
            view.mostrarMensagem("Funcionario cadastrado com sucesso!");
        } catch (Exception e) {
            view.mostrarMensagem("Erro ao cadastrar: " + e.getMessage());
            ArquivoUtil.log("ERRO ao cadastrar funcionario: " + e.getMessage());
        }
    }

    public void listar() {
        view.listarFuncionarios(funcionarios);
    }

    public void buscar() {
        String matricula = view.lerMatricula();
        Funcionario encontrado = buscarPorMatricula(matricula);
        view.mostrarFuncionario(encontrado);
    }

    public void alterar() {
        String matricula = view.lerMatricula();
        Funcionario f = buscarPorMatricula(matricula);

        if (f == null) {
            view.mostrarMensagem("Funcionario nao encontrado.");
            return;
        }

        try {
            String[] novos = view.lerAlteracao();
            f.setTelefone(novos[0]);
            f.setCargo(novos[1]);
            f.setSalario(Double.parseDouble(novos[2].replace(",", ".")));
            salvarNoArquivo();
            ArquivoUtil.log("Funcionario alterado: " + f.getNome() + " (Matricula: " + matricula + ")");
            view.mostrarMensagem("Funcionario alterado com sucesso!");
        } catch (NumberFormatException erro) {
            view.mostrarMensagem("Salario invalido. Alteracao cancelada.");
            ArquivoUtil.log("ERRO ao alterar funcionario: salario invalido");
        } catch (IllegalArgumentException erro) {
            view.mostrarMensagem("Erro ao alterar: " + erro.getMessage());
            ArquivoUtil.log("ERRO ao alterar funcionario: " + erro.getMessage());
            return;
        }
    }

    public void deletar() {
        String matricula = view.lerMatricula();
        Funcionario f = buscarPorMatricula(matricula);

        if (f == null) {
            view.mostrarMensagem("Funcionario nao encontrado.");
            return;
        }

        funcionarios.remove(f);
        salvarNoArquivo();
        ArquivoUtil.log("Funcionario removido: " + f.getNome() + " (Matricula: " + matricula + ")");
        view.mostrarMensagem("Funcionario removido com sucesso!");
    }

    public Funcionario buscarPorMatricula(String matricula) {
        for (Funcionario f : funcionarios) {
            if (f.getMatricula().equalsIgnoreCase(matricula)) {
                return f;
            }
        }
        return null;
    }

    public ArrayList<Funcionario> getTodos() {
        return funcionarios;
    }

    private void salvarNoArquivo() {
        List<String> linhas = new ArrayList<>();
        for (Funcionario f : funcionarios) {
            linhas.add(f.getNome() + ";" + f.getMatricula() + ";" + f.getTelefone() + ";" + f.getCargo() + ";" + f.getSalario());
        }
        ArquivoUtil.salvar(ARQUIVO, linhas);
    }

    private void carregarDoArquivo() {
        List<String> linhas = ArquivoUtil.carregar(ARQUIVO);
        funcionarios.clear();

        for (String linha : linhas) {
            if (linha.trim().isEmpty())
                continue;

            String[] partes = linha.split(";", -1);
            if (partes.length >= 5) {
                try {
                    funcionarios.add(new Funcionario(
                            partes[0], partes[1], partes[2], partes[3], Double.parseDouble(partes[4])));
                } catch (IllegalArgumentException erro) {
                    ArquivoUtil.log("ERRO ao carregar funcionario: " + erro.getMessage());
                }
            }
        }
    }
}
