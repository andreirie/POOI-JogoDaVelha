package jogodavelha;

import javax.swing.*;
import armazenamento.GerenciaJogadores;
import armazenamento.GerenciaJogadoresArquivo;
import armazenamento.GerenciaJogadoresArrayList;
import entradadados.Console;

/**
 * Representa o menu inicial do jogo da velha.
 * Permite ao usuário iniciar o jogo, ver o ranking de jogadores e interagir com o sistema.
 * @author Herich
 */
public class MenuInicial extends JFrame {
    private GerenciaJogadores gerenciaJogadores;

    /**
     * Inicializa o menu e define os botões para jogar e ver o ranking.
     */
    public MenuInicial() {
        // Alternar entre GerenciaJogadoresArquivo e GerenciaJogadoresArrayList conforme necessário
        gerenciaJogadores = new GerenciaJogadoresArquivo(); // Ou GerenciaJogadoresArrayList

        setTitle("Menu Inicial");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JButton btnJogar = new JButton("Jogar");
        JButton btnRanking = new JButton("Ver Ranking");

        btnJogar.addActionListener(e -> pedirNomesJogadores());
        btnRanking.addActionListener(e -> mostrarRanking());

        add(btnJogar);
        add(btnRanking);

        setVisible(true);
    }

    /**
     * Solicita o nome dos jogadores, inicia o jogo e fecha o menu inicial.
     */
    private void pedirNomesJogadores() {
        String nomePlayer1 = JOptionPane.showInputDialog(this, "Digite o nome do Jogador 1:");
        String nomePlayer2 = JOptionPane.showInputDialog(this, "Digite o nome do Jogador 2:");

        if (nomePlayer1 != null && nomePlayer2 != null && !nomePlayer1.trim().isEmpty() && !nomePlayer2.trim().isEmpty()) {
            Jogador player1 = new Jogador('X', nomePlayer1);
            Jogador player2 = new Jogador('O', nomePlayer2);

            Jogo jogo = new Jogo(player1, player2, gerenciaJogadores);
            Console console = new Console(jogo);
            console.iniciar();

            // Fechar o menu inicial após o início do jogo
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Os nomes dos jogadores não podem ser vazios.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Mostra o ranking de jogadores utilizando a classe GerenciaJogadores.
     */
    private void mostrarRanking() {
        String ranking = gerenciaJogadores.mostrarRanking();
        JOptionPane.showMessageDialog(this, ranking, "Ranking", JOptionPane.INFORMATION_MESSAGE);
    }
}
