package jogodavelha;

import armazenamento.GerenciaJogadores;
import javax.swing.JOptionPane;

/**
 * Representa a lógica principal do jogo da velha, incluindo regras de jogo,
 * controle de jogadores e interação com o tabuleiro.
 * @author Herich
 */
public class Jogo {
    private Tabuleiro board;
    private Jogador player1;
    private Jogador player2;
    private int vez; // 1 para player1, 2 para player2
    private GerenciaJogadores gerenciaJogadores;

    /**
     * Construtor da classe Jogo.
     * @param player1 Jogador 1.
     * @param player2 Jogador 2.
     * @param gerenciaJogadores Objeto para gerenciar jogadores (registro de vitórias, etc.).
     */
    public Jogo(Jogador player1, Jogador player2, GerenciaJogadores gerenciaJogadores) {
        this.board = new Tabuleiro();
        this.player1 = player1;
        this.player2 = player2;
        this.vez = 1; // Player 1 começa
        this.gerenciaJogadores = gerenciaJogadores;
    }

    /**
     * Obtém o tabuleiro atual do jogo.
     * @return O tabuleiro do jogo.
     */
    public Tabuleiro getTabuleiro() {
        return board;
    }

    /**
     * Obtém o jogador 1.
     * @return Jogador 1.
     */
    public Jogador getJogador1() {
        return player1;
    }

    /**
     * Obtém o jogador 2.
     * @return Jogador 2.
     */
    public Jogador getJogador2() {
        return player2;
    }

    /**
     * Obtém o objeto para gerenciar jogadores.
     * @return Objeto para gerenciar jogadores.
     */
    public GerenciaJogadores getGerenciaJogadores() {
        return gerenciaJogadores;
    }

    /**
     * Obtém o jogador atual, com base na vez de jogada.
     * @return Jogador atual.
     */
    public Jogador getJogadorAtual() {
        return vez == 1 ? player1 : player2;
    }

    /**
     * Alterna a vez de jogada entre os jogadores.
     */
    public void trocaVez() {
        vez = (vez == 1) ? 2 : 1;
    }

    /**
     * Verifica se há um vencedor no jogo da velha.
     * @return 1 se o jogador 1 ganhou, 2 se o jogador 2 ganhou, -1 se houve empate, 0 se o jogo continua.
     */
    public int verificaGanhador() {
        char[][] tabuleiro = board.getTabuleiro();

        // Verificar linhas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2] && tabuleiro[i][0] != '-') {
                return tabuleiro[i][0] == player1.getSimbolo() ? 1 : 2;
            }
        }

        // Verificar colunas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i] && tabuleiro[0][i] != '-') {
                return tabuleiro[0][i] == player1.getSimbolo() ? 1 : 2;
            }
        }

        // Verificar diagonais
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] && tabuleiro[0][0] != '-') {
            return tabuleiro[0][0] == player1.getSimbolo() ? 1 : 2;
        }

        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0] && tabuleiro[0][2] != '-') {
            return tabuleiro[0][2] == player1.getSimbolo() ? 1 : 2;
        }

        // Verificar se há espaços vazios
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == '-') {
                    return 0; // Jogo continua
                }
            }
        }

        // Empate
        return -1;
    }

    /**
     * Obtém o jogador com base no resultado do jogo.
     * @param resultado Resultado do jogo (1 para jogador 1 ganhou, 2 para jogador 2 ganhou, -1 para empate).
     * @return Jogador correspondente ao resultado, ou null se nenhum jogador ganhou ainda.
     */
    public Jogador getJogadorPorResultado(int resultado) {
        if (resultado == 1) {
            return player1;
        } else if (resultado == 2) {
            return player2;
        } else {
            return null;
        }
    }

    /**
     * Inicia e controla o jogo da velha.
     */
    public void jogar() {
        int resultado = 0;
        while (resultado == 0) {
            board.mostraTabuleiro();
            Jogada.pedeJogada(board, vez == 1 ? player1 : player2);
            resultado = verificaGanhador();
            if (resultado == 0) {
                trocaVez();
            }
        }
        board.mostraTabuleiro();
        if (resultado == -1) {
            System.out.println("Empate!");
        } else {
            Jogador ganhador = (resultado == 1 ? player1 : player2);
            System.out.println("O ganhador é: " + ganhador.getNome());
            gerenciaJogadores.registrarVitoria(ganhador.getNome());
        }
        perguntarContinuacao();
    }

    /**
     * Exibe um menu para perguntar ao usuário se deseja jogar novamente,
     * trocar jogadores, ver o ranking ou sair do jogo.
     */
    private void perguntarContinuacao() {
        String[] options = {"Jogar Novamente", "Trocar Jogadores", "Ver Ranking", "Sair"};
        int escolha = JOptionPane.showOptionDialog(null, "O que você deseja fazer?", "Fim de Jogo",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (escolha) {
            case 0: // Jogar Novamente
                board.reset();
                jogar();
                break;
            case 1: // Trocar Jogadores
                String nomePlayer1 = JOptionPane.showInputDialog("Digite o nome do Jogador 1:");
                String nomePlayer2 = JOptionPane.showInputDialog("Digite o nome do Jogador 2:");
                if (nomePlayer1 != null && nomePlayer2 != null) {
                    player1.setNome(nomePlayer1);
                    player2.setNome(nomePlayer2);
                    board.reset();
                    jogar();
                }
                break;
            case 2: // Ver Ranking
                String ranking = gerenciaJogadores.mostrarRanking();
                JOptionPane.showMessageDialog(null, ranking, "Ranking", JOptionPane.INFORMATION_MESSAGE);
                perguntarContinuacao(); // Voltar ao menu de opções
                break;
            case 3: // Sair
                System.exit(0);
                break;
        }
    }
}
