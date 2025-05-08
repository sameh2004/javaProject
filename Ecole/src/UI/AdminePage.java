package UI;
import dao.MatiereDAO;
import dao.UtilisateurDAO;
import model.Matiere;
import model.Utilisateur;

import javax.swing.*;
import java.awt.*;

import java.sql.SQLException;

public class AdminePage extends JFrame {

    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public AdminePage() {
        frame = new JFrame("Page d'Admine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout());
        frame.setContentPane(contentPane);

        // Sidebar Panel
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(400, 0));
        sidebar.setBackground(new Color(58, 146, 165));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        String[] buttons = {"Ajouter", "Modifier", "Supprimer", "Affectation"};
        for (String btnText : buttons) {
            JButton btn = new JButton(btnText);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(280, 60));
            btn.setFocusPainted(false);
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(5, 51, 71));
            btn.setBorderPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            sidebar.add(Box.createRigidArea(new Dimension(0, 15)));
            sidebar.add(btn);

            btn.addActionListener(e -> cardLayout.show(cardPanel, btnText));
        }

        contentPane.add(sidebar, BorderLayout.WEST);

        // Card Panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Use the same names as the button texts
        cardPanel.add(buildAjouterPanel(), "Ajouter");
        cardPanel.add(buildModifierPanel(), "Modifier");
        cardPanel.add(buildSupprimerPanel(), "Supprimer");
        cardPanel.add(buildAffectationPanel(), "Affectation");

        contentPane.add(cardPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public JPanel buildAjouterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel[] labels = {
                new JLabel("Login:"), new JLabel("Mot de passe:"),
                new JLabel("Nom:"), new JLabel("Prénom:"), new JLabel("Rôle:")
        };
        JTextField[] fields = {
                new JTextField(40), new JTextField(40),
                new JTextField(40), new JTextField(40), new JTextField(40)
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; gbc.gridy = i;
            panel.add(labels[i], gbc);
            gbc.gridx = 1;
            panel.add(fields[i], gbc);
        }

        JButton btnSave = new JButton("Enregistrer");
        gbc.gridx = 1;
        gbc.gridy = labels.length;
        panel.add(btnSave, gbc);

        btnSave.addActionListener(e -> {
            try {
                int log = Integer.parseInt(fields[0].getText());
                String pass = fields[1].getText();
                String nom = fields[2].getText();
                String prenom = fields[3].getText();
                String role = fields[4].getText();

                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setLog(log);
                utilisateur.setPass(pass);
                utilisateur.setNom(nom);
                utilisateur.setPrenom(prenom);
                utilisateur.setRole(role);

                new UtilisateurDAO().add(utilisateur);

                JOptionPane.showMessageDialog(panel, "Utilisateur ajouté !");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel,
                        "Le login doit être un nombre.",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel,
                        "Erreur SQL : " + ex.getMessage(),
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    public JPanel buildModifierPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Modifier - Entrez ID et nouveaux détails (à implémenter)"));
        return panel;
    }

    public JPanel buildSupprimerPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("ID à supprimer:"), gbc);
        gbc.gridx = 1;
        JTextField txtId = new JTextField(20);
        panel.add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> comboType = new JComboBox<>(new String[]{"Étudiant", "Matière", "Enseignant"});
        panel.add(comboType, gbc);

        JButton btnDelete = new JButton("Supprimer");
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(btnDelete, gbc);

        btnDelete.addActionListener(e -> {
            String idText = txtId.getText();
            String selectedType = (String) comboType.getSelectedItem();

            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(panel,
                        "Veuillez entrer un ID.",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(idText);

                // Suppression selon le type
                UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

                if ("Matière".equals(selectedType)) {
                    // Supprime depuis la table matière
                    MatiereDAO matiereDAO = new MatiereDAO();
                    matiereDAO.delete(new Matiere(id)); // ou matiereDAO.deleteById(id)
                    JOptionPane.showMessageDialog(panel, "Matière supprimée.");
                } else {
                    // Supprime l'utilisateur (étudiant ou enseignant) depuis la table utilisateur
                    utilisateurDAO.delete(new Utilisateur(id)); // ou utilisateurDAO.deleteById(id)
                    JOptionPane.showMessageDialog(panel,
                            (selectedType.equals("Étudiant") ? "Étudiant" : "Enseignant") + " supprimé.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel,
                        "L'ID doit être un nombre entier.",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel,
                        "Erreur lors de la suppression : " + ex.getMessage(),
                        "Erreur SQL",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;

    }

    public JPanel buildAffectationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Matière Section
        JPanel matPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JLabel lblMat = new JLabel("ID Matière:");
        lblMat.setFont(lblMat.getFont().deriveFont(20f));
        JTextField txtMat = new JTextField(15);  // Smaller width
        txtMat.setFont(txtMat.getFont().deriveFont(20f));
        matPanel.add(lblMat);
        matPanel.add(txtMat);

        // Enseignant Section
        JPanel ensPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JLabel lblEns = new JLabel("ID Enseignant:");
        lblEns.setFont(lblEns.getFont().deriveFont(20f));
        JTextField txtEns = new JTextField(15);  // Smaller width
        txtEns.setFont(txtEns.getFont().deriveFont(20f));
        ensPanel.add(lblEns);
        ensPanel.add(txtEns);

        // Button Section
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        JButton btnAffecter = new JButton("Affecter");
        btnAffecter.setFont(btnAffecter.getFont().deriveFont(12f));
        btnAffecter.setPreferredSize(new Dimension(90, 25));  // Smaller button
        btnPanel.add(btnAffecter);

        // Add components to main panel
        panel.add(matPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(ensPanel);
        panel.add(Box.createVerticalGlue());
        panel.add(btnPanel);

        return panel;
    }




}