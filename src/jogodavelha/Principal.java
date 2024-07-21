package jogodavelha;

import javax.swing.SwingUtilities;

/**
 * Inicia a aplicação do jogo da velha.
 * @author Herich
 */
public class Principal {
    /**
     * Inicia a interface gráfica do jogo da velha.
     * @param args Argumentos da linha de comando (não utilizado neste contexto).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuInicial();
            }
        });
    }
}
