
	package vue;

	import javax.swing.*;
	import java.awt.*;
	import model.Utilisateur;
	import dao.UtilisateurDAO;

	public class LoginInterface extends JFrame {

	    private JTextField usernameField;
	    private JPasswordField passwordField;

	    public LoginInterface() {
	        setTitle("Système de Gestion d'École - Connexion");
	        setSize(1000, 500);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setLayout(null);
	        setLocationRelativeTo(null);

	        JLabel userLabel = new JLabel("login :");
	        userLabel.setBounds(150, 50, 150, 25);
	        add(userLabel);

	        JLabel passLabel = new JLabel("Mot de passe:");
	        passLabel.setBounds(150, 90, 120, 25);
	        add(passLabel);

	        usernameField = new JTextField();
	        usernameField.setBounds(300, 50, 150, 25);
	        add(usernameField);

	        passwordField = new JPasswordField();
	        passwordField.setBounds(300, 90, 150, 25);
	        add(passwordField);

	        JButton loginButton = new JButton("Se connecter");
	        loginButton.setBounds(330, 180, 120, 30);
	        add(loginButton);

	        loginButton.addActionListener(e -> {
	            try {
	                int log = Integer.parseInt(usernameField.getText());
	                String pass = new String(passwordField.getPassword());

	                UtilisateurDAO dao = new UtilisateurDAO();
	                Utilisateur u = dao.SeConnecter(log, pass);

	                if (u != null) {
	                   // JOptionPane.showMessageDialog(this, "Bienvenue " + u.getPrenom() + " (" + u.getRole() + ")");
	                    
	                    switch (u.getRole().toLowerCase()) {
	                    case "administrateur":
	                        new AdminInterface();
	                        break;
	                    case "enseignant":
	                        new EnseignantInterface();
	                        break;
	                    case "etudiant":
	                        new EtudiantInterface();
	                        break;
	                }

	                    

	                    dispose(); // Ferme la fenêtre actuelle
	                } else {
	                    JOptionPane.showMessageDialog(this, "Identifiants incorrects.");
	                }

	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(this, "Le login doit être un identifiant numérique.");
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(this, "Erreur de connexion.");
	            }
	        });

	        setVisible(true);
	    }

	   
	}

