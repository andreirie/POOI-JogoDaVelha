package jogodavelha;

/**
 * Representa um jogador em um jogo da velha.
 * @author André
 */
public class Jogador {
    private char simbolo;
    private String nome;

    /**
     * Inicializa um jogador com um símbolo específico.
     * @param simbolo O símbolo (X ou O) que o jogador utilizará.
     */
    public Jogador(char simbolo) {
        this.simbolo = simbolo;
    }

    /**
     * Inicializa um jogador com um símbolo específico e um nome.
     * @param simbolo O símbolo (X ou O) que o jogador utilizará.
     * @param nome O nome do jogador.
     */
    public Jogador(char simbolo, String nome) {
        this.simbolo = simbolo;
        this.nome = nome;
    }

    /**
     * Obtém o símbolo (X ou O) do jogador.
     * @return O símbolo do jogador.
     */
    public char getSimbolo() {
        return simbolo;
    }

    /**
     * Define o símbolo (X ou O) do jogador.
     * @param simbolo O símbolo do jogador.
     */
    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

    /**
     * Obtém o nome do jogador.
     * @return O nome do jogador.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do jogador.
     * @param nome O nome do jogador.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
