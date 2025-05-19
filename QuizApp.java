import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

public class QuizApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}

// Login Frame – accepts any input
class LoginFrame extends JFrame {
    JTextField emailField;
    JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        // 💡 Remove any validation — accept any login
        loginButton.addActionListener(e -> {
            // No check — login always succeeds
            dispose(); // Close login screen
            new QuizFrame(); // Open quiz
        });

        add(panel);
        setVisible(true);
    }
}

// Quiz Frame
class QuizFrame extends JFrame {
    String[] questions = {
        "1. What does JVM stand for?",
        "2. Which data type is used to store text?",
        "3. How to declare an int variable?",
        "4. Which keyword creates a class in Java?",
        "5. How do you create a method in Java?",
        "6. Which operator compares two values?",
        "7. What is the default value of boolean?",
        "8. What is used to inherit a class?",
        "9. Which loop is used for fixed iteration?",
        "10. What is used to define constants?"
    };

    String[][] options = {
        {"Java Virtual Machine", "Java Version Manager", "Java Variable Mode", "Just Virtual Mode"},
        {"int", "float", "String", "boolean"},
        {"int num;", "num int;", "int = num;", "num = int;"},
        {"class", "Class", "define", "create"},
        {"method()", "void method()", "function()", "def method()"},
        {"=", "==", "!=", "equals()"},
        {"true", "false", "null", "0"},
        {"extend", "extends", "implement", "inherits"},
        {"while", "do-while", "for", "switch"},
        {"const", "define", "final", "static"}
    };

    int[] correctAnswers = {0, 2, 0, 0, 1, 1, 1, 1, 2, 2};
    ButtonGroup[] groups = new ButtonGroup[10];

    public QuizFrame() {
        setTitle("Beginner Java Quiz");
        setSize(700, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < questions.length; i++) {
            JPanel qPanel = new JPanel();
            qPanel.setLayout(new BoxLayout(qPanel, BoxLayout.Y_AXIS));
            qPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            qPanel.setBackground(Color.WHITE);
            qPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel questionLabel = new JLabel(questions[i]);
            questionLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            qPanel.add(questionLabel);

            ButtonGroup bg = new ButtonGroup();
            groups[i] = bg;

            for (int j = 0; j < 4; j++) {
                JRadioButton option = new JRadioButton(options[i][j]);
                option.setBackground(Color.WHITE);
                bg.add(option);
                qPanel.add(option);
            }

            panel.add(Box.createVerticalStrut(10));
            panel.add(qPanel);
        }

        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(e -> {
            int score = 0;
            for (int i = 0; i < groups.length; i++) {
                int selected = getSelectedIndex(groups[i]);
                if (selected == correctAnswers[i]) {
                    score++;
                }
            }
            JOptionPane.showMessageDialog(this, "Your Score: " + score + "/10");
        });

        panel.add(Box.createVerticalStrut(20));
        panel.add(submitButton);
        panel.add(Box.createVerticalStrut(20));

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
        setVisible(true);
    }

    private int getSelectedIndex(ButtonGroup bg) {
        int index = 0;
        for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements(); index++) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return index;
            }
        }
        return -1;
    }
}
