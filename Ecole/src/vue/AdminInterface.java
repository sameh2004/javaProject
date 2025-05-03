package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminInterface extends JFrame {

    public AdminInterface() {
        setTitle("Interface Administrateur");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Liste des étudiants (exemple avec un modèle vide pour l'instant)
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> studentList = new JList<>(model);
        JScrollPane scroll = new JScrollPane(studentList);

        // Boutons
        JPanel buttonPanel = new JPanel();
        JButton btnAjouter = new JButton("Ajouter");
        JButton btnModifier = new JButton("Modifier");
        JButton btnSupprimer = new JButton("Supprimer");
        JButton btnEnregistrer = new JButton("Enregistrer");
        JButton btnFermer = new JButton("Fermer");

        buttonPanel.add(btnAjouter);
        buttonPanel.add(btnModifier);
        buttonPanel.add(btnSupprimer);
        buttonPanel.add(btnEnregistrer);
        buttonPanel.add(btnFermer);

        add(new JLabel("Liste des étudiants :"), BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Fermer
        btnFermer.addActionListener(e -> dispose());

        setVisible(true);
    }
}
