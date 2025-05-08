package UI;

import javax.swing.*;
import java.awt.event.*;

public class AdminePage extends JFrame {

    public AdminePage() {
        setTitle("Interface Administrateur");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton btnEtudiants = new JButton("Gestion des Étudiants");
        btnEtudiants.setBounds(100, 30, 200, 40);
        add(btnEtudiants);

        JButton btnEnseignants = new JButton("Gestion des Enseignants");
        btnEnseignants.setBounds(100, 90, 200, 40);
        add(btnEnseignants);

        JButton btnAffectation = new JButton("Affectation des Matières");
        btnAffectation.setBounds(100, 150, 200, 40);
        add(btnAffectation);

        // Actions pour chaque bouton
        btnEtudiants.addActionListener(e -> new GestionEtudiantsFrame());
        btnEnseignants.addActionListener(e -> new GestionEnseignantsFrame());
        btnAffectation.addActionListener(e -> new AffectationMatiereFrame());

        setVisible(true);
    }

    
}
