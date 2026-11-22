package util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerSistema {

    private static final String ARQUIVO = "log_execucao.txt";

    private static final DateTimeFormatter FORMATO =
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static void registrar(String tipo, String mensagem) {

        String dataHora = LocalDateTime.now().format(FORMATO);

        String linha = "[" + dataHora + "] [" + tipo + "] " + mensagem;

        try (FileWriter arquivo = new FileWriter(ARQUIVO, true)) {

            arquivo.write(linha + System.lineSeparator());

        } catch (IOException e) {

            System.out.println("Erro ao registrar log: " + e.getMessage());
        }
    }

    public static void info(String mensagem) {
        registrar("INFO", mensagem);
    }

    public static void erro(String mensagem) {
        registrar("ERRO", mensagem);
    }
}