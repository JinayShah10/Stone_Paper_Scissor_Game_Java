import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class StonePaperScissorsGUI extends JFrame implements ActionListener {

    private JButton stoneButton, paperButton, scissorsButton, restartButton;
    private JLabel playerChoiceLabel, computerChoiceLabel, resultLabel, scoreLabel;

    private int playerScore = 0;
    private int computerScore = 0;
    private int draws = 0;

    private final String[] options = {"Stone", "Paper", "Scissors"};
    private final Random random = new Random();

    public StonePaperScissorsGUI() {
        setTitle("Stone Paper Scissors Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Choose your move:", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        stoneButton = new JButton("🪨 Stone");
        paperButton = new JButton("📄 Paper");
        scissorsButton = new JButton("✂️ Scissors");

        stoneButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);

        buttonPanel.add(stoneButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Status + Score
        JPanel statusPanel = new JPanel(new GridLayout(5, 1));
        playerChoiceLabel = new JLabel("Your choice: ", SwingConstants.CENTER);
        computerChoiceLabel = new JLabel("Computer's choice: ", SwingConstants.CENTER);
        resultLabel = new JLabel("Result: ", SwingConstants.CENTER);
        scoreLabel = new JLabel("Score - You: 0 | Computer: 0 | Draws: 0", SwingConstants.CENTER);

        // Restart Button
        restartButton = new JButton("🔄 Restart Game");
        restartButton.addActionListener(e -> resetGame());

        statusPanel.add(playerChoiceLabel);
        statusPanel.add(computerChoiceLabel);
        statusPanel.add(resultLabel);
        statusPanel.add(scoreLabel);
        statusPanel.add(restartButton);

        add(statusPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String playerMove = "";
        if (e.getSource() == stoneButton) playerMove = "Stone";
        else if (e.getSource() == paperButton) playerMove = "Paper";
        else if (e.getSource() == scissorsButton) playerMove = "Scissors";

        String computerMove = options[random.nextInt(3)];

        playerChoiceLabel.setText("Your choice: " + playerMove);
        computerChoiceLabel.setText("Computer's choice: " + computerMove);

        if (playerMove.equals(computerMove)) {
            resultLabel.setText("Result: It's a draw! 🤝");
            draws++;
        } else if (
                (playerMove.equals("Stone") && computerMove.equals("Scissors")) ||
                (playerMove.equals("Paper") && computerMove.equals("Stone")) ||
                (playerMove.equals("Scissors") && computerMove.equals("Paper"))
        ) {
            resultLabel.setText("Result: You win this round! 🎉");
            playerScore++;
        } else {
            resultLabel.setText("Result: Computer wins this round! 💻");
            computerScore++;
        }

        scoreLabel.setText("Score - You: " + playerScore + " | Computer: " + computerScore + " | Draws: " + draws);
    }

    private void resetGame() {
        playerScore = 0;
        computerScore = 0;
        draws = 0;

        playerChoiceLabel.setText("Your choice: ");
        computerChoiceLabel.setText("Computer's choice: ");
        resultLabel.setText("Result: ");
        scoreLabel.setText("Score - You: 0 | Computer: 0 | Draws: 0");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StonePaperScissorsGUI game = new StonePaperScissorsGUI();
            game.setVisible(true);
        });
    }
}
