package jogodavelha;

/**
 * Representa o tabuleiro do jogo da velha.
 * @author Herich
 */
public class Tabuleiro {
    private char[][] tabuleiro;

    /**
     * Inicializa o tabuleiro com dimensões 3x3 e todos os espaços preenchidos com '-'.
     */
    public Tabuleiro() {
        this.tabuleiro = new char[3][3]; // inicializa o tabuleiro com 3 linhas e 3 colunas
        reset(); // Inicializa todas as posições com '-'
    }

    /**
     * Reinicia o tabuleiro, preenchendo todas as posições com '-'.
     */
    public void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = '-';
            }
        }
    }

    /**
     * Obtém o estado atual do tabuleiro.
     * @return Matriz de caracteres representando o tabuleiro.
     */
    public char[][] getTabuleiro() {
        return tabuleiro;
    }

    /**
     * Obtém o símbolo na posição especificada do tabuleiro.
     * @param linha Linha da posição desejada.
     * @param coluna Coluna da posição desejada.
     * @return Caractere na posição especificada do tabuleiro.
     */
    public char getTabuleiro(int linha, int coluna) {
        return tabuleiro[linha][coluna];
    }

    /**
     * Define o símbolo na posição especificada do tabuleiro.
     * @param linha Linha da posição desejada.
     * @param coluna Coluna da posição desejada.
     * @param simbolo Símbolo a ser colocado na posição especificada.
     */
    public void setTabuleiro(int linha, int coluna, char simbolo) {
        this.tabuleiro[linha][coluna] = simbolo;
    }

    /**
     * Mostra o tabuleiro no console.
     */
    public void mostraTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }
}
