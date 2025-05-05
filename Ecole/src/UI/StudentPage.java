package UI;

import javax.swing.*;
import java.awt.*;


public class StudentPage extends JFrame {

    private final JPanel panelContenu;

    public StudentPage() {
        setTitle("Page Étudiant");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Menu à gauche
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(30, 30, 60));
        panelMenu.setPreferredSize(new Dimension(400, getHeight()));
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));

        // Titre
        JLabel labelTitre = new JLabel("Page Étudiant");
        labelTitre.setForeground(Color.WHITE);
        labelTitre.setFont(new Font("SansSerif", Font.BOLD, 18));
        labelTitre.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitre.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panelMenu.add(labelTitre);

        // Boutons
        JButton btnEtudiants = createSidebarButton("Consulter les étudiants");
        JButton btnMatieres = createSidebarButton("Consulter les matières");
        JButton btnEnseignants = createSidebarButton("Consulter les enseignants");

        panelMenu.add(btnEtudiants);
        panelMenu.add(Box.createVerticalStrut(10));
        panelMenu.add(btnMatieres);
        panelMenu.add(Box.createVerticalStrut(10));
        panelMenu.add(btnEnseignants);

        // Zone de contenu
        panelContenu = new JPanel(new BorderLayout());
        panelContenu.setBackground(Color.LIGHT_GRAY);
        JLabel labelDefaut = new JLabel("Contenu affiché ici", JLabel.CENTER);
        labelDefaut.setFont(new Font("SansSerif", Font.PLAIN, 18));
        panelContenu.add(labelDefaut, BorderLayout.CENTER);

        // Ajout à la fenêtre
        add(panelMenu, BorderLayout.WEST);
        add(panelContenu, BorderLayout.CENTER);

        // Actions
        btnEtudiants.addActionListener(e -> afficherMessage("Liste des étudiants affichée ici"));
        btnMatieres.addActionListener(e -> afficherMessage("Liste des matières affichée ici"));
        btnEnseignants.addActionListener(e -> afficherMessage("Liste des enseignants affichée ici"));
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(240, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(52, 58, 82));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void afficherMessage(String message) {
        panelContenu.removeAll();
        JLabel label = new JLabel(message, JLabel.CENTER);
        label.setFont(new Font("SansSerif", Font.PLAIN, 18));
        panelContenu.add(label, BorderLayout.CENTER);
        panelContenu.revalidate();
        panelContenu.repaint();
    }

    public void showPage() {
        this.setVisible(true);
    }


}
