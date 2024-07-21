package armazenamento;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementação da interface GerenciaJogadores que utiliza um arquivo para armazenar o ranking de jogadores.
 * @author André
 */
public class GerenciaJogadoresArquivo implements GerenciaJogadores {
    private static final String ARQUIVO_RANKING = "ranking.txt";
    private Map<String, Integer> jogadores;

    /**
     * Inicializa o mapa de jogadores e carrega o ranking do arquivo.
     */
    public GerenciaJogadoresArquivo() {
        jogadores = new HashMap<>();
        carregarRanking();
    }

    /**
     * Registra a vitória de um jogador, atualizando o número de vitórias no mapa e salvando no arquivo.
     * @param nomeJogador Nome do jogador que venceu.
     */
    @Override
    public void registrarVitoria(String nomeJogador) {
        jogadores.put(nomeJogador, jogadores.getOrDefault(nomeJogador, 0) + 1);
        salvarRanking();
    }

    /**
     * Retorna uma representação em formato de texto do ranking de jogadores, lendo os dados do mapa.
     * @return String representando o ranking dos jogadores.
     */
    @Override
    public String mostrarRanking() {
        StringBuilder ranking = new StringBuilder("Ranking:\n");
        for (Map.Entry<String, Integer> entry : jogadores.entrySet()) {
            ranking.append(entry.getKey()).append(": ").append(entry.getValue()).append(" vitórias\n");
        }
        return ranking.toString();
    }

    /**
     * Carrega o ranking de jogadores a partir de um arquivo de texto.
     */
    private void carregarRanking() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_RANKING))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(": ");
                if (partes.length == 2) {
                    String nome = partes[0];
                    int vitorias = Integer.parseInt(partes[1]);
                    jogadores.put(nome, vitorias);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o ranking: " + e.getMessage());
        }
    }

    /**
     * Salva o ranking de jogadores no arquivo de texto.
     */
    private void salvarRanking() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_RANKING))) {
            for (Map.Entry<String, Integer> entry : jogadores.entrySet()) {
                bw.write(entry.getKey() + ": " + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o ranking: " + e.getMessage());
        }
    }
}
