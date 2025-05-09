package UI;

import javax.swing.*;

import dao.EnseignantDAO;
import dao.EtudiantDAO;
import dao.UtilisateurDAO;
import model.Enseignant;
import model.Etudiant;
import model.Utilisateur;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class GestionEnseignantsFrame extends JFrame {



	

	

	    private JFrame frame;
	    private JPanel cardPanel;
	    private CardLayout cardLayout;

	    public GestionEnseignantsFrame() {
	        frame = new JFrame("Gestion Enseignant");
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
	            new JLabel("Nom:"), new JLabel("Prénom:"), new JLabel("Spécialité:")
	        };
	        
	        // Déclare les champs pour les récupérer
	        JTextField txtLogin = new JTextField(40);
	        JTextField txtPass = new JTextField(40);
	        JTextField txtNom = new JTextField(40);
	        JTextField txtPrenom = new JTextField(40);
	        JTextField txtSpecialite = new JTextField(40);

	        JTextField[] fields = {
	            txtLogin, txtPass, txtNom, txtPrenom, txtSpecialite
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
	            	Enseignant etu = new Enseignant();
	                etu.setLog(Integer.parseInt(txtLogin.getText()));
	                etu.setPass(txtPass.getText());
	                etu.setNom(txtNom.getText());
	                etu.setPrenom(txtPrenom.getText());
	                etu.setSpecialitéEnseign(txtSpecialite.getText());

	                new EnseignantDAO().add(etu);
	                Utilisateur en=new Utilisateur();
	                en.setLog(Integer.parseInt(txtLogin.getText()));
	                en.setPass(txtPass.getText());
	                en.setNom(txtNom.getText());
	                en.setPrenom(txtPrenom.getText());
	                en.setRole("enseignant");
	                new UtilisateurDAO().add(en);
	                JOptionPane.showMessageDialog(panel, "Enseignant ajouté avec succès !");
	                
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
	             new JLabel("Nouvelle spécialité :")
	        };

	        JTextField txtLogin = new JTextField(40);
	        JTextField txtPass = new JTextField(40);
	        JTextField txtNom = new JTextField(40);
	        JTextField txtPrenom = new JTextField(40);
	        JTextField txtSpecialite = new JTextField(40);

	        JTextField[] fields = {txtLogin, txtPass, txtNom, txtPrenom, txtSpecialite};

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
	                EnseignantDAO dao = new EnseignantDAO();
	                Enseignant etu = dao.getByID(log);
	                UtilisateurDAO ud=new UtilisateurDAO();
	                Utilisateur u=ud.getByID(log);
	                if (etu == null) {
	                    JOptionPane.showMessageDialog(panel, "Aucun étudiant trouvé avec ce login.", "Erreur", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }

	                // Mettre à jour les informations
	                if (!txtPass.getText().isEmpty()) {
	                    etu.setPass(txtPass.getText());
	                    u.setPass(txtPass.getText());
	                }
	                if(!txtNom.getText().isEmpty()) {
	                	etu.setNom(txtNom.getText());
	                	u.setNom(txtNom.getText());}
	                if(!txtPrenom.getText().isEmpty()) {
	                	u.setPrenom(txtPrenom.getText());
	                	etu.setPrenom(txtPrenom.getText());}
	                if(!txtSpecialite.getText().isEmpty())
	                	etu.setSpecialitéEnseign(txtSpecialite.getText());
	                u.setRole("enseignant");
	                dao.update(etu);
	                ud.update(u);
	                JOptionPane.showMessageDialog(panel, "Enseignant modifié avec succès !");
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
	                EnseignantDAO enseignantDAO = new EnseignantDAO();
	                Enseignant enseignant = enseignantDAO.getByID(id);
	                UtilisateurDAO ud=new UtilisateurDAO();
	                Utilisateur u=ud.getByID(id);
	                if (enseignant != null) {
	                	enseignantDAO.delete(enseignant);
	                	ud.delete(u);
	                    JOptionPane.showMessageDialog(panel, "Enseignant supprimé avec succès.");
	                } else {
	                    JOptionPane.showMessageDialog(panel, "Aucun Enseignant trouvé avec cet ID.");
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

   


