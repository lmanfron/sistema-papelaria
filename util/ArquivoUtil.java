package papelaria.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArquivoUtil {

    private static final String PASTA = "dados/";

    public static void salvar(String nomeArquivo, List<String> linhas) {
        new File(PASTA).mkdir();
        try (BufferedWriter w = new BufferedWriter(new FileWriter(PASTA + nomeArquivo))) {
            for (String linha : linhas) {
                w.write(linha);
                w.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar " + nomeArquivo);
        }
    }

    public static List<String> carregar(String nomeArquivo) {
        List<String> linhas = new ArrayList<>();
        File arquivo = new File(PASTA + nomeArquivo);
        if (!arquivo.exists()) return linhas;
        try (BufferedReader r = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = r.readLine()) != null) linhas.add(linha);
        } catch (IOException e) {
            System.out.println("Erro ao carregar " + nomeArquivo);
        }
        return linhas;
    }

    public static void log(String mensagem) {
        new File(PASTA).mkdir();
        try (BufferedWriter w = new BufferedWriter(new FileWriter(PASTA + "log.txt", true))) {
            w.write("[" + new Date() + "] " + mensagem);
            w.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar log");
        }
    }
}
