package entradadados;

import jogodavelha.Jogo;
import jogodavelha.Jogador;
import jogodavelha.Tabuleiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Responsável por controlar a interface gráfica do jogo da velha.
 * @author André e Herich
 */
public class Console {
    private Jogo jogo;
    private JFrame frame;
    private JButton[][] buttons;

    /**
     * Construtor da classe Console.
     * @param jogo Instância do jogo da velha que será controlada pela interface gráfica.
     */
    public Console(Jogo jogo) {
        this.jogo = jogo;
    }

    /**
     * Inicializa a interface gráfica do jogo da velha.
     */
    public void iniciar() {
        frame = new JFrame("Jogo da Velha");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("-");
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Jogador jogadorAtual = jogo.getJogadorAtual();
                        if (jogo.getTabuleiro().getTabuleiro(row, col) == '-') {
                            jogo.getTabuleiro().setTabuleiro(row, col, jogadorAtual.getSimbolo());
                            buttons[row][col].setText(String.valueOf(jogadorAtual.getSimbolo()));
                            int resultado = jogo.verificaGanhador();
                            if (resultado == 0) {
                                jogo.trocaVez();
                            } else {
                                exibirResultado(resultado);
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "Jogada inválida! Tente novamente.");
                        }
                    }
                });
                frame.add(buttons[i][j]);
            }
        }

        frame.setVisible(true);
    }

    /**
     * Exibe o resultado do jogo após seu término e permite ao usuário escolher as próximas ações.
     * @param resultado Resultado do jogo: 1 para jogador 1 venceu, 2 para jogador 2 venceu, -1 para empate.
     */
    private void exibirResultado(int resultado) {
        if (resultado == -1) {
            JOptionPane.showMessageDialog(frame, "Empate!");
        } else {
            Jogador ganhador = jogo.getJogadorPorResultado(resultado);
            JOptionPane.showMessageDialog(frame, "O ganhador é: " + ganhador.getNome());
            jogo.getGerenciaJogadores().registrarVitoria(ganhador.getNome());
        }

        String[] options = {"Jogar Novamente", "Trocar Jogadores", "Ver Ranking", "Sair"};
        int escolha = JOptionPane.showOptionDialog(frame, "O que você deseja fazer?", "Fim de Jogo",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (escolha) {
            case 0: // Jogar Novamente
                jogo.getTabuleiro().reset();
                atualizarTabuleiro();
                break;
            case 1: // Trocar Jogadores
                String nomePlayer1 = JOptionPane.showInputDialog("Digite o nome do Jogador 1:");
                String nomePlayer2 = JOptionPane.showInputDialog("Digite o nome do Jogador 2:");
                if (nomePlayer1 != null && nomePlayer2 != null) {
                    jogo.getJogador1().setNome(nomePlayer1);
                    jogo.getJogador2().setNome(nomePlayer2);
                    jogo.getTabuleiro().reset();
                    atualizarTabuleiro();
                }
                break;
            case 2: // Ver Ranking
                String ranking = jogo.getGerenciaJogadores().mostrarRanking();
                JOptionPane.showMessageDialog(frame, ranking, "Ranking", JOptionPane.INFORMATION_MESSAGE);
                exibirResultado(resultado); // Voltar ao menu de opções
                break;
            case 3: // Sair
                System.exit(0);
                break;
        }
    }

    /**
     * Atualiza a representação gráfica do tabuleiro com base no estado atual do jogo.
     */
    private void atualizarTabuleiro() {
        char[][] tabuleiro = jogo.getTabuleiro().getTabuleiro();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(String.valueOf(tabuleiro[i][j]));
            }
        }
    }
}
