package armazenamento;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementação da interface GerenciaJogadores que utiliza um ArrayList para armazenar o ranking de jogadores.
 * @author André
 */
public class GerenciaJogadoresArrayList implements GerenciaJogadores {
    private Map<String, Integer> jogadores;

    /**
     * Construtor que inicializa o mapa de jogadores.
     */
    public GerenciaJogadoresArrayList() {
        jogadores = new HashMap<>();
    }

    /**
     * Registra a vitória de um jogador, atualizando o número de vitórias no mapa.
     * @param nomeJogador Nome do jogador que venceu.
     */
    @Override
    public void registrarVitoria(String nomeJogador) {
        jogadores.put(nomeJogador, jogadores.getOrDefault(nomeJogador, 0) + 1);
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
}
