package UI;
import dao.UtilisateurDAO;
import model.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login  extends JFrame {



        public Login() {
            // Set up full screen
            setTitle("Login Page");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
            setLayout(null);

            // Panel (twice size: 800x400)
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setBackground(Color.WHITE);
            panel.setBounds(560, 200, 800, 400); // You might adjust x=560 for centering on other screens
            panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

            // Title
            JLabel title = new JLabel("Login");
            title.setFont(new Font("Arial", Font.BOLD, 36));
            title.setBounds(350, 20, 200, 40);
            panel.add(title);

            // ID
            JLabel idLabel = new JLabel("ID:");
            idLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            idLabel.setBounds(200, 90, 100, 30);
            panel.add(idLabel);

            JTextField idField = new JTextField();
            idField.setFont(new Font("Arial", Font.PLAIN, 24));
            idField.setBounds(320, 90, 300, 35);
            panel.add(idField);

            // Password
            JLabel passwordLabel = new JLabel(" Mot de passe :");
            passwordLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            passwordLabel.setBounds(65, 150, 200, 30);
            panel.add(passwordLabel);

            JPasswordField passwordField = new JPasswordField();
            passwordField.setFont(new Font("Arial", Font.PLAIN, 24));
            passwordField.setBounds(320, 150, 300, 35);
            panel.add(passwordField);


            // Login Button
            JButton loginButton = new JButton("Login");
            loginButton.setFont(new Font("Arial", Font.BOLD, 24));
            loginButton.setBounds(320, 280, 300, 45);
            loginButton.setBackground(new Color(5, 51, 71));
            loginButton.setForeground(Color.WHITE);
            panel.add(loginButton);


            add(panel);
            setVisible(true);

            loginButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String id = idField.getText();
                    String password = new String(passwordField.getPassword());

                    UtilisateurDAO utilistaeur = new UtilisateurDAO();
                    Utilisateur u = null;
                    try {
                        u = utilistaeur.SeConnecter(Integer.parseInt(id), password);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    if (u != null) {
                        dispose(); // Close login window
                        switch (u.getRole()) {
                            case "personnel":
                                new AdminePage();
                                break;
                            case "enseignat":
                                new EnseigPage();
                                break;
                            case "etudiant ":
                                new StudentPage();
                                break;

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid ID or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
}






