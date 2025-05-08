package UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.*;

import dao.EtudiantDAO;
import model.Etudiant;

public class GestionEtudiantsFrame extends JFrame {

    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public GestionEtudiantsFrame() {
        frame = new JFrame("Gestion Etudiants");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout());
        frame.setContentPane(contentPane);

        // Sidebar Panel
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(400, 0));
        sidebar.setBackground(new Color(0, 12, 56));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        String[] buttons = {"Ajouter", "Modifier", "Supprimer","Fermer"};
        for (String btnText : buttons) {
            JButton btn = new JButton(btnText);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(280, 60));
            btn.setFocusPainted(false);
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(63, 72, 135));
            btn.setBorderPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            sidebar.add(Box.createRigidArea(new Dimension(0, 15)));
            sidebar.add(btn);

            if (btnText.equals("Fermer")) {
                btn.addActionListener(e -> frame.dispose());
            } else {
                btn.addActionListener(e -> cardLayout.show(cardPanel, btnText));
            }

        }

        contentPane.add(sidebar, BorderLayout.WEST);

        // Card Panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Use the same names as the button texts
        cardPanel.add(buildAjouterPanel(), "Ajouter");
        cardPanel.add(buildModifierPanel(), "Modifier");
        cardPanel.add(buildSupprimerPanel(), "Supprimer");
       

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
            new JLabel("Nom:"), new JLabel("Prénom:"), new JLabel("Niveau:"), new JLabel("Spécialité:")
        };
        
        // Déclare les champs pour les récupérer
        JTextField txtLogin = new JTextField(40);
        JTextField txtPass = new JTextField(40);
        JTextField txtNom = new JTextField(40);
        JTextField txtPrenom = new JTextField(40);
        JTextField txtNiveau = new JTextField(40);
        JTextField txtSpecialite = new JTextField(40);

        JTextField[] fields = {
            txtLogin, txtPass, txtNom, txtPrenom, txtNiveau, txtSpecialite
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            panel.add(labels[i], gbc);
            gbc.gridx = 1;
            panel.add(fields[i], gbc);
        }

        JButton btnSave = new JButton("Enregistrer");
        gbc.gridx = 1;
        gbc.gridy = labels.length;
        panel.add(btnSave, gbc);

        // Action du bouton
        btnSave.addActionListener(e -> {
            try {
                Etudiant etu = new Etudiant();
                etu.setLog(Integer.parseInt(txtLogin.getText()));
                etu.setPass(txtPass.getText());
                etu.setNom(txtNom.getText());
                etu.setPrenom(txtPrenom.getText());
                etu.setNiveau(Integer.parseInt(txtNiveau.getText()));
                etu.setSpecialitéEtudiant(txtSpecialite.getText());

                new EtudiantDAO().add(etu);

                JOptionPane.showMessageDialog(panel, "Étudiant ajouté avec succès !");
                
                // Nettoyage des champs
                for (JTextField field : fields) {
                    field.setText("");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Login et niveau doivent être des entiers.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erreur SQL : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }


    public JPanel buildModifierPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel[] labels = {
            new JLabel("Login à modifier :"), new JLabel("Nouveau mot de passe :"),
            new JLabel("Nouveau nom :"), new JLabel("Nouveau prénom :"),
            new JLabel("Nouveau niveau :"), new JLabel("Nouvelle spécialité :")
        };

        JTextField txtLogin = new JTextField(40);
        JTextField txtPass = new JTextField(40);
        JTextField txtNom = new JTextField(40);
        JTextField txtPrenom = new JTextField(40);
        JTextField txtNiveau = new JTextField(40);
        JTextField txtSpecialite = new JTextField(40);

        JTextField[] fields = {txtLogin, txtPass, txtNom, txtPrenom, txtNiveau, txtSpecialite};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            panel.add(labels[i], gbc);
            gbc.gridx = 1;
            panel.add(fields[i], gbc);
        }

        JButton btnModifier = new JButton("Modifier");
        gbc.gridx = 1;
        gbc.gridy = labels.length;
        panel.add(btnModifier, gbc);

        btnModifier.addActionListener(e -> {
            try {
                int log = Integer.parseInt(txtLogin.getText());
                EtudiantDAO dao = new EtudiantDAO();
                Etudiant etu = dao.getByID(log);

                if (etu == null) {
                    JOptionPane.showMessageDialog(panel, "Aucun étudiant trouvé avec ce login.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Mettre à jour les informations
                etu.setPass(txtPass.getText());
                etu.setNom(txtNom.getText());
                etu.setPrenom(txtPrenom.getText());
                etu.setNiveau(Integer.parseInt(txtNiveau.getText()));
                etu.setSpecialitéEtudiant(txtSpecialite.getText());

                dao.update(etu);
                JOptionPane.showMessageDialog(panel, "Étudiant modifié avec succès !");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Login et niveau doivent être des entiers.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erreur SQL : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }


    public JPanel buildSupprimerPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("ID à supprimer:"), gbc);

        gbc.gridx = 1;
        JTextField txtId = new JTextField(20);
        panel.add(txtId, gbc);

        JButton btnDelete = new JButton("Supprimer");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(btnDelete, gbc);

        btnDelete.addActionListener(e -> {
            String idText = txtId.getText();

            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Veuillez entrer un ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(idText);
                EtudiantDAO etudiantDAO = new EtudiantDAO();
                Etudiant etudiant = etudiantDAO.getByID(id);

                if (etudiant != null) {
                    etudiantDAO.delete(etudiant);
                    JOptionPane.showMessageDialog(panel, "Étudiant supprimé avec succès.");
                } else {
                    JOptionPane.showMessageDialog(panel, "Aucun étudiant trouvé avec cet ID.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "ID invalide (doit être un nombre).", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Erreur lors de la suppression : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }




}
