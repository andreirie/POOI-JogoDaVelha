package armazenamento;

/**
 * Interface para gerenciamento de jogadores e suas vitórias.
 * @author André
 */
public interface GerenciaJogadores {
    /**
     * Registra a vitória de um jogador pelo seu nome.
     * @param nomeJogador Nome do jogador que venceu.
     */
    void registrarVitoria(String nomeJogador);

    /**
     * Retorna uma representação em formato de texto do ranking de jogadores,
     * ordenados por número de vitórias.
     * @return String representando o ranking dos jogadores.
     */
    String mostrarRanking();
}
