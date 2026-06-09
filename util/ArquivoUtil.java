package papelaria.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArquivoUtil {

    private static final String PASTA = "dados/";

    private static void criarPasta() {
        File pasta = new File(PASTA);
        if (!pasta.exists()) {
            pasta.mkdir();
        }
    }

    public static void salvar(String nomeArquivo, List<String> linhas) {
        criarPasta();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PASTA + nomeArquivo));

            for (String linha : linhas) {
                writer.write(linha);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar " + nomeArquivo);
        }
    }

    public static List<String> carregar(String nomeArquivo) {
        List<String> linhas = new ArrayList<>();
        File arquivo = new File(PASTA + nomeArquivo);

        if (!arquivo.exists()) {
            return linhas;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha = reader.readLine();

            while (linha != null) {
                linhas.add(linha);
                linha = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao carregar " + nomeArquivo);
        }

        return linhas;
    }

    public static void log(String mensagem) {
        criarPasta();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PASTA + "log.txt", true));
            writer.write("[" + new Date() + "] " + mensagem);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar log");
        }
    }
}