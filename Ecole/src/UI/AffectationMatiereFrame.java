package UI;

import dao.MatiereDAO;
import dao.UtilisateurDAO;

import model.Affectation;
import model.Matiere;
import model.Utilisateur;
import dao.AffectationDAO;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AffectationMatiereFrame extends JFrame {
     private JFrame frame;
     private JPanel cardPanel;
     private CardLayout cardLayout;

    public AffectationMatiereFrame() {


            frame = new JFrame("Gestion Etudiants");
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

            String[] buttons = {"Ajouter", "Affectation", "Supprimer","Fermer"};
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

                if (btnText.equals("Fermer")) {
                    btn.addActionListener(e -> {
                        // 1. Ouvrir la page Admin
                        new AdminePage().setVisible(true);

                        // 2. Fermer la fenêtre actuelle
                        ((JFrame) SwingUtilities.getWindowAncestor(btn)).dispose();
                    });
                } else {
                    btn.addActionListener(e -> cardLayout.show(cardPanel, btnText));
                }

            }

            contentPane.add(sidebar, BorderLayout.WEST);


            cardLayout = new CardLayout();
            cardPanel = new JPanel(cardLayout);


            cardPanel.add(buildAjouterPanel(), "Ajouter");
            cardPanel.add(buildAffectationPanel(), "Affectation");
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
                new JLabel("ID:"), new JLabel("Type"),
                new JLabel("Nom:")
        };

        // Déclare les champs pour les récupérer
        JTextField txtID = new JTextField(40);
        JTextField txtType = new JTextField(40);
        JTextField txtNom = new JTextField(40);


        JTextField[] fields = {
                txtID, txtType, txtNom
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
                Matiere  mat = new Matiere();
                mat.setID(Integer.parseInt(txtID.getText()));
                mat.setNom(txtNom.getText());
                mat.setType(txtNom.getText());


                new MatiereDAO().add(mat);


                JOptionPane.showMessageDialog(panel, "Matiere  ajouté avec succès !");

                // Nettoyage des champs
                for (JTextField field : fields) {
                    field.setText("");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "ID  doivent être des entiers.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erreur SQL : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;}

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
                    MatiereDAO matiereDAO = new MatiereDAO();
                  Matiere matiere  = matiereDAO.getByID(id);
                    UtilisateurDAO ud=new UtilisateurDAO();
                    Utilisateur u=ud.getByID(id);
                    if (matiere != null) {
                        matiereDAO.delete(matiere);
                        ud.delete(u);
                        JOptionPane.showMessageDialog(panel, "Matiere  supprimé avec succès.");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Aucune Matiere  trouvé avec cet ID.");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "ID invalide (doit être un nombre).", "Erreur", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Erreur lors de la suppression : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            });

            return panel;
        }

    public JPanel buildAffectationPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels pour les champs
        JLabel[] labels = {
                new JLabel("ID Matière:"),
                new JLabel("ID Enseignant:"),
                new JLabel("Nombre d'heures:")
        };

        // Champs de saisie
        JTextField txtMatiereId = new JTextField(40);
        JTextField txtEnseignantId = new JTextField(40);
        JTextField txtHeures = new JTextField(40);

        JTextField[] fields = {txtMatiereId, txtEnseignantId, txtHeures};
        // Ajout des labels et champs au panel
        JButton btnSave = null;
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            panel.add(labels[i], gbc);

            gbc.gridx = 1;
            panel.add(fields[i], gbc);}


            // Bouton Enregistrer
            btnSave = new JButton("Enregistrer");
            gbc.gridx = 1;
            gbc.gridy = labels.length;
            panel.add(btnSave, gbc);


        btnSave.addActionListener(e -> {
            String matiereIdText = txtMatiereId.getText();
            String enseignantIdText = txtEnseignantId.getText();
            String heuresText = txtHeures.getText();

            try {
                if (matiereIdText.isEmpty() || enseignantIdText.isEmpty() || heuresText.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Tous les champs sont obligatoires.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int matiereId = Integer.parseInt(matiereIdText);
                int enseignantId = Integer.parseInt(enseignantIdText);
                int heures = Integer.parseInt(heuresText);

                Affectation affectation = new Affectation(matiereId, enseignantId, heures);
                // Assurez-vous que AffectationDAO.add() retourne un boolean pertinent ou gère les exceptions
                if (new AffectationDAO().add(affectation)) {
                    JOptionPane.showMessageDialog(panel, "Affectation ajoutée avec succès !");
                    for (JTextField field : fields) {
                        field.setText(""); // Nettoyer les champs
                    }
                } else {
                    // Ce message peut être redondant si add() lance une SQLException gérée ci-dessous
                    JOptionPane.showMessageDialog(panel, "Échec de l'ajout de l'affectation (vérifiez les logs ou la base de données).", "Erreur d'ajout", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Veuillez entrer des valeurs numériques valides pour les IDs et le nombre d'heures.", "Erreur de Format", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) { // MODIFIÉ: Gestion de SQLException
                JOptionPane.showMessageDialog(panel, "Erreur SQL lors de l'ajout de l'affectation : " + ex.getMessage(), "Erreur SQL", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace(); // Important pour le débogage
            } catch (Exception ex) { // Catch générique pour autres erreurs
                JOptionPane.showMessageDialog(panel, "Une erreur inattendue est survenue : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
        return panel;
    }


}
























