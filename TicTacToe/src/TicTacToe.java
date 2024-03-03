import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] button = new JButton[9];
    boolean player1_turn;

    TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(102, 51, 153));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("DRALLZ TIC-TOE");
        textfield.setOpaque(true);

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            button[i] = new JButton();
            button[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            button[i].setFocusable(false);
            button[i].addActionListener(this);
            button_panel.add(button[i]);
        }

        frame.add(button_panel);

        frame.setVisible(true);

        firstTurn();
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == button[i]) {
                if (player1_turn) {
                    if (button[i].getText().equals("")) {
                        button[i].setForeground(new Color(255, 0, 0));
                        button[i].setText("X");
                        player1_turn = false;
                        textfield.setText("TIC-TAC-TOE");
                        check();
                    }
                } else {
                    if (button[i].getText().equals("")) {
                        button[i].setForeground(new Color(0, 0, 255));
                        button[i].setText("O");
                        player1_turn = true;
                        textfield.setText("X's Turn");
                        check();
                    }
                }
            }
        }
    }

    public void firstTurn() {
        if (random.nextInt(2) == 0) {
            player1_turn = true;
            textfield.setText("X's Turn");
        } else {
            player1_turn = false;
            textfield.setText("O's Turn");
        }
    }

    public void check() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (button[i * 3].getText().equals(button[i * 3 + 1].getText()) &&
                    button[i * 3].getText().equals(button[i * 3 + 2].getText()) &&
                    !button[i * 3].getText().equals("")) {
                gameOver(i * 3, i * 3 + 1, i * 3 + 2);
                return;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (button[i].getText().equals(button[i + 3].getText()) &&
                    button[i].getText().equals(button[i + 6].getText()) &&
                    !button[i].getText().equals("")) {
                gameOver(i, i + 3, i + 6);
                return;
            }
        }
        // Check diagonals
        if (button[0].getText().equals(button[4].getText()) &&
                button[0].getText().equals(button[8].getText()) &&
                !button[0].getText().equals("")) {
            gameOver(0, 4, 8);
            return;
        }
        if (button[2].getText().equals(button[4].getText()) &&
                button[2].getText().equals(button[6].getText()) &&
                !button[2].getText().equals("")) {
            gameOver(2, 4, 6);
            return;
        }
        // Check for tie
        boolean tie = true;
        for (int i = 0; i < 9; i++) {
            if (button[i].getText().equals("")) {
                tie = false;
                break;
            }
        }
        if (tie) {
            textfield.setText("It's a Tie!");
            for (int i = 0; i < 9; i++) {
                button[i].setEnabled(false);
            }
        }
    }

    public void gameOver(int a, int b, int c) {
        button[a].setBackground(Color.GREEN);
        button[b].setBackground(Color.GREEN);
        button[c].setBackground(Color.GREEN);
        for (int i = 0; i < 9; i++) {
            button[i].setEnabled(false);
        }
        if (button[a].getText().equals("X")) {
            textfield.setText("X wins!");
        } else {
            textfield.setText("O wins!");
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
