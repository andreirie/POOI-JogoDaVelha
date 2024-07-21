package jogodavelha;

import java.util.Scanner;

/**
 * Responsável por lidar com a realização de jogadas dos jogadores no jogo da velha.
 * @author André
 */
public class Jogada {

    /**
     * Solicita e valida uma jogada de um jogador.
     * @param tabuleiro O tabuleiro atual do jogo da velha.
     * @param jogador O jogador que está realizando a jogada.
     */
    public static void pedeJogada(Tabuleiro tabuleiro, Jogador jogador) {
        Scanner scanner = new Scanner(System.in);
        int linha, coluna;
        boolean jogadaValida = false;

        while (!jogadaValida) {
            System.out.println("Jogador " + jogador.getNome() + " (" + jogador.getSimbolo() + "), digite a linha e a coluna (0, 1 ou 2):");
            linha = scanner.nextInt();
            coluna = scanner.nextInt();

            if (linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && tabuleiro.getTabuleiro(linha, coluna) == '-') {
                tabuleiro.setTabuleiro(linha, coluna, jogador.getSimbolo());
                jogadaValida = true;
            } else {
                System.out.println("Jogada inválida! Tente novamente.");
            }
        }
    }
}
