package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminePage extends JFrame {

    public AdminePage() {

            // Configuration de base
            setTitle("Interface Administrateur");
            setSize(1000, 800);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(null);



            getContentPane().setBackground(new Color(255, 255, 255));


            // Charger une image au centre
            JLabel labelImage = new JLabel(new ImageIcon("Ecole/src/images/unnamed.png"));
            labelImage.setHorizontalAlignment(SwingConstants.CENTER);
            labelImage.setBounds(0, 100, 1000, 500);
            add(labelImage);

            // Panel pour les boutons en bas
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
            buttonPanel.setBounds(0, 650, 1000, 80);
            buttonPanel.setBackground(getContentPane().getBackground());

            // Créer les boutons stylés
            JButton btnEtudiants = createStyledButton("Gestion des Étudiants");
            JButton btnEnseignants = createStyledButton("Gestion des Enseignants");
            JButton btnAffectation = createStyledButton("Affectation des Matières");


            btnEtudiants.addActionListener(e -> {new GestionEtudiantsFrame();dispose();});
            btnEnseignants.addActionListener(e -> {new GestionEnseignantsFrame(); dispose();});
            btnAffectation.addActionListener(e -> {new AffectationMatiereFrame(); dispose();});


            buttonPanel.add(btnEtudiants);
            buttonPanel.add(btnEnseignants);
            buttonPanel.add(btnAffectation);


            add(buttonPanel);

            setVisible(true);
        }


        private JButton createStyledButton (String text){
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(5, 51, 71)); // Bleu foncé
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.setPreferredSize(new Dimension(220, 50));
            return button;
        }



    }
