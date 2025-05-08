package UI;

import dao.EtudiantDAO;
import dao.MatiereDAO;
import model.Etudiant;
import model.Matiere;

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

            String[] buttons = {"Ajouter", "Modifier", "Supprimer","Fermer"};
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
            cardPanel.add(buildSupprimerPanel(), "Fermer");


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

            JTextField txtId = new JTextField(40);
            JTextField txtType = new JTextField(40);
            JTextField txtNom = new JTextField(40);


            JTextField[] fields = {txtId, txtType, txtNom };

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
                    int ID = Integer.parseInt(txtId.getText());
                   MatiereDAO dao = new MatiereDAO();
                   Matiere matiere= dao.getByID(ID);

                    if (matiere  == null) {
                        JOptionPane.showMessageDialog(panel, "Aucun Matiere trouvé avec ce ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Mettre à jour les informations
                    matiere.setID(Integer.parseInt(txtId.getText()));
                    matiere.setNom(txtNom.getText());
                    matiere.setType(txtType.getText());

                    dao.update(matiere);
                    JOptionPane.showMessageDialog(panel, "Matiere  modifié avec succès !");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "ID doit être des entiers.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "Erreur SQL : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            });

            return panel;
        }











    }

    }



    // Ajoute ici une JComboBox d'enseignants + une JComboBox de matières, et un bouton "Affecter"
    }

