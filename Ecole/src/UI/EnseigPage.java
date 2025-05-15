package UI;

import javax.swing.*;

import dao.EtudiantDAO;
import dao.MatiereDAO;
import model.Etudiant;
import model.Matiere;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class EnseigPage extends JFrame {

    CardLayout cardLayout;
    JPanel rightPanel;

    public EnseigPage() {
        setTitle("Page Enseignant");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(300);
        splitPane.setEnabled(false);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(58, 146, 165));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Espace Enseignant");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnEtudiants = createSidebarButton(" Consulter les étudiants");
        JButton btnMatieres = createSidebarButton(" Consulter les matières");
        JButton btnLogout = createSidebarButton(" Se déconnecter");

        leftPanel.add(title);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(btnEtudiants);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(btnMatieres);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(btnLogout);
        leftPanel.add(Box.createVerticalStrut(30));

        cardLayout = new CardLayout();
        rightPanel = new JPanel(cardLayout);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel headerLabel = new JLabel("Bienvenue dans votre espace enseignant !");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerPanel.add(headerLabel);

        JPanel accueilPanel = new JPanel(new BorderLayout());
        accueilPanel.add(headerPanel, BorderLayout.NORTH);
        accueilPanel.add(createPlaceholderPanel("Veuillez choisir une action à gauche."), BorderLayout.CENTER);

        rightPanel.add(accueilPanel, "accueil");
        rightPanel.add(createListeEtudiantsPanel(), "etudiants");
        rightPanel.add(createListeMatieresPanel(), "matieres");


        btnEtudiants.addActionListener(e -> cardLayout.show(rightPanel, "etudiants"));
        btnMatieres.addActionListener(e -> cardLayout.show(rightPanel, "matieres"));
        btnLogout.addActionListener(e -> {
            dispose();
            new Login();
        });

        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(rightPanel);

        add(splitPane);
        cardLayout.show(rightPanel, "accueil");
        setVisible(true);
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

    private JPanel createPlaceholderPanel(String text) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }
    private JPanel createListeEtudiantsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        try {
            EtudiantDAO dao = new EtudiantDAO();
            List<Etudiant> etudiants = dao.getAll();

            if (etudiants.isEmpty()) {
                return createPlaceholderPanel("Aucun étudiant trouvé.");
            }

            String[] colonnes = {"ID", "Nom", "Prénom", "Email"};
            Object[][] data = new Object[etudiants.size()][4];

            for (int i = 0; i < etudiants.size(); i++) {
                Etudiant e = etudiants.get(i);
                data[i][0] = e.getLog();
                data[i][1] = e.getNom();
                data[i][2] = e.getPrenom();
               
            }

            JTable table = new JTable(data, colonnes);
            table.setRowHeight(25);
            table.setFont(new Font("SansSerif", Font.PLAIN, 14));

            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane, BorderLayout.CENTER);

        } catch (SQLException e) {
            return createPlaceholderPanel("Erreur lors du chargement des étudiants.");
        }

        return panel;
    }

    private JPanel createListeMatieresPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        try {
            MatiereDAO dao = new MatiereDAO();
            List matieres = (List) dao.getAll();

            if (((List<Matiere>) matieres).isEmpty()) {
                return createPlaceholderPanel("Aucune matière trouvée.");
            }

            String[] colonnes = {"ID", "Nom", "Type"};
            Object[][] data = new Object[matieres.size()][3];

            for (int i = 0; i < matieres.size(); i++) {
                Matiere m = (Matiere) matieres.get(i);
                data[i][0] = m.getID();
                data[i][1] = m.getNom();
                data[i][2] = m.getType();
            }

            JTable table = new JTable(data, colonnes);
            table.setRowHeight(25);
            table.setFont(new Font("SansSerif", Font.PLAIN, 14));

            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane, BorderLayout.CENTER);

        } catch (SQLException e) {
            return createPlaceholderPanel("Erreur lors du chargement des matières.");
        }

        return panel;
    }
 
}
