package papelaria.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {

    private static final String PASTA_DADOS = "dados";

    public static void salvar(String nomeArquivo, List<String> linhas) {
        criarPastaDados();

        try (BufferedWriter w = new BufferedWriter(new FileWriter(PASTA_DADOS + "/" + nomeArquivo))) {
            for (String linha : linhas) {
                w.write(linha);
                w.newLine();
            }
        } catch (IOException erro) {
            System.out.println("Erro ao salvar " + nomeArquivo);
            log("Erro ao salvar " + nomeArquivo + ": " + erro.getMessage());
        }
    }

    public static List<String> carregar(String nomeArquivo) {
        List<String> linhas = new ArrayList<>();
        File arquivo = new File(PASTA_DADOS + "/" + nomeArquivo);

        if (!arquivo.exists()) {
            return linhas;
        }

        try (BufferedReader r = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = r.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException erro) {
            System.out.println("Erro ao carregar " + nomeArquivo);
            log("Erro ao carregar " + nomeArquivo + ": " + erro.getMessage());
        }
        return linhas;
    }

    public static void log(String mensagem) {
        criarPastaDados();

        try (BufferedWriter w = new BufferedWriter(new FileWriter(PASTA_DADOS + "/log.txt", true))) {
            w.write(LocalDateTime.now() + " - " + mensagem);
            w.newLine();
        } catch (IOException erro) {
            System.out.println("Erro ao salvar log");
        }
    }

    private static void criarPastaDados() {
        new File(PASTA_DADOS).mkdirs();
    }
}
