package UI;

import dao.EnseignantDAO;
import dao.MatiereDAO;
import model.Enseignant;
import model.Matiere;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;


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
        panelMenu.setBackground(new Color(58, 146, 165));
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

        JButton btnMatieres = createSidebarButton("Consulter les matières");
        JButton btnEnseignants = createSidebarButton("Consulter les enseignants");

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

        btnMatieres.addActionListener(e -> afficherMatieres());
        btnEnseignants.addActionListener(e -> afficherEnseignants());
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(240, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(5, 51, 71));
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


    private void afficherMatieres() {


        try {
            MatiereDAO matr = new MatiereDAO();

            List<Matiere> matieres = matr.getAll();
            afficherListeMatieres(matieres);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Erreur lors de la récupération des matières: " + ex.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void afficherListeMatieres(List<Matiere> matieres) {
        panelContenu.removeAll();

        if (matieres.isEmpty()) {
            afficherMessage("Aucune matière trouvée");
            return;
        }

        // Create table model
        String[] columnNames = {"ID", "Nom", "Type"};
        Object[][] data = new Object[matieres.size()][3];

        for (int i = 0; i < matieres.size(); i++) {
            Matiere m = matieres.get(i);
            data[i][0] = m.getID();
            data[i][1] = m.getNom();
            data[i][2] = m.getType();
        }

        // Create table
        JTable table = new JTable(data, columnNames);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(25);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        panelContenu.add(scrollPane, BorderLayout.CENTER);

        panelContenu.revalidate();
        panelContenu.repaint();
    }

    private void afficherEnseignants() {
        try {
            EnseignantDAO dao = new EnseignantDAO();
            List<Enseignant> enseignants = dao.getAll();

            afficherListeEtDetailsEnseignants(enseignants);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Erreur lors de la récupération des enseignants: " + ex.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void afficherListeEtDetailsEnseignants(List<Enseignant> liste) {
        panelContenu.removeAll();
        if (liste.isEmpty()) {
            panelContenu.removeAll();
            JLabel label = new JLabel("Aucun enseignant trouvé", JLabel.CENTER);
            label.setFont(new Font("SansSerif", Font.PLAIN, 18));
            panelContenu.add(label, BorderLayout.CENTER);
            panelContenu.revalidate();
            panelContenu.repaint();
            return;
        }

        // --- Gauche : Liste des noms/prénoms ---
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Enseignant e : liste) {
            listModel.addElement(e.getNom() + " " + e.getPrenom());
        }
        
        JList<String> listeNoms = new JList<>(listModel);
        listeNoms.setFont(new Font("SansSerif", Font.PLAIN, 16));
        JScrollPane scrollListe = new JScrollPane(listeNoms);

        // --- Droite : Détails de l'enseignant ---
        JPanel panelDetails = new JPanel();
        panelDetails.setBorder(BorderFactory.createTitledBorder("Détails de l'enseignant"));
        panelDetails.setLayout(new BoxLayout(panelDetails, BoxLayout.Y_AXIS));

        // --- Mise à jour automatique au clic ---
        listeNoms.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int index = listeNoms.getSelectedIndex();
                if (index >= 0 && index < liste.size()) {
                    Enseignant selected = liste.get(index);
                    panelDetails.removeAll();

                    panelDetails.add(new JLabel("Nom : " + selected.getNom()));
                    panelDetails.add(new JLabel("Prénom : " + selected.getPrenom()));
                    panelDetails.add(new JLabel("Spécialité : " + selected.getSpecialitéEnseign()));
                    
                    panelDetails.revalidate();
                    panelDetails.repaint();
                }
            }
        });

        // --- Assemblage global ---
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollListe, panelDetails);
        splitPane.setDividerLocation(250);

        panelContenu.add(splitPane, BorderLayout.CENTER);
        panelContenu.revalidate();
        panelContenu.repaint();
    }


    public void showPage() {
        this.setVisible(true);
    }


}