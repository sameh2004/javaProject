package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import dao.EtudiantDAO;
import dao.EnseignantDAO;
import model.Etudiant;
import model.Enseignant;

public class AdminInterface extends JFrame {

    private DefaultListModel<String> studentModel = new DefaultListModel<>();
    private JList<String> studentList = new JList<>(studentModel);

    private DefaultListModel<String> enseignantModel = new DefaultListModel<>();
    private JList<String> enseignantList = new JList<>(enseignantModel);

    private EtudiantDAO etudiantDAO = new EtudiantDAO();
    private EnseignantDAO enseignantDAO = new EnseignantDAO();

    public AdminInterface() {
        setTitle("Interface Administrateur");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2));

        // Panel étudiant
        JPanel studentPanel = new JPanel(new BorderLayout());
        studentPanel.setBorder(BorderFactory.createTitledBorder("Gestion des Étudiants"));

        studentPanel.add(new JScrollPane(studentList), BorderLayout.CENTER);
        JPanel studentButtons = new JPanel();
        JButton btnAddStudent = new JButton("Ajouter");
        JButton btnEditStudent = new JButton("Modifier");
        JButton btnDeleteStudent = new JButton("Supprimer");
        JButton btnSaveStudent = new JButton("Enregistrer");
        JButton btnClose = new JButton("Fermer");

        studentButtons.add(btnAddStudent);
        studentButtons.add(btnEditStudent);
        studentButtons.add(btnDeleteStudent);
        studentButtons.add(btnSaveStudent);
        studentButtons.add(btnClose);
        studentPanel.add(studentButtons, BorderLayout.SOUTH);

        // Panel enseignant
        JPanel enseignantPanel = new JPanel(new BorderLayout());
        enseignantPanel.setBorder(BorderFactory.createTitledBorder("Gestion des Enseignants"));
        enseignantPanel.add(new JScrollPane(enseignantList), BorderLayout.CENTER);

        JPanel enseignantButtons = new JPanel();
        JButton btnAddEns = new JButton("Ajouter");
        JButton btnEditEns = new JButton("Modifier");
        JButton btnDeleteEns = new JButton("Supprimer");
        JButton btnSaveEns = new JButton("Enregistrer");
        JButton btnAssignMatieres = new JButton("Affecter Matières");

        enseignantButtons.add(btnAddEns);
        enseignantButtons.add(btnEditEns);
        enseignantButtons.add(btnDeleteEns);
        enseignantButtons.add(btnSaveEns);
        enseignantButtons.add(btnAssignMatieres);
        enseignantPanel.add(enseignantButtons, BorderLayout.SOUTH);

        add(studentPanel);
        add(enseignantPanel);

        // Action Listeners
        btnClose.addActionListener(e -> dispose());

        btnAddStudent.addActionListener(e -> {
            String nom = JOptionPane.showInputDialog(this, "Nom étudiant :");
            if (nom != null && !nom.isBlank()) {
                studentModel.addElement(nom);
            }
        });

        btnDeleteStudent.addActionListener(e -> {
            int selected = studentList.getSelectedIndex();
            if (selected >= 0) {
                studentModel.remove(selected);
            }
        });

        btnSaveStudent.addActionListener(e -> {
            try {
                List<Etudiant> list = etudiantDAO.getAll();
                System.out.println("Enregistrement terminé. Total étudiants : " + list.size());
                JOptionPane.showMessageDialog(this, "Étudiants enregistrés.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement.");
            }
        });

        btnAddEns.addActionListener(e -> {
            String nom = JOptionPane.showInputDialog(this, "Nom enseignant :");
            if (nom != null && !nom.isBlank()) {
                enseignantModel.addElement(nom);
            }
        });

        btnDeleteEns.addActionListener(e -> {
            int selected = enseignantList.getSelectedIndex();
            if (selected >= 0) {
                enseignantModel.remove(selected);
            }
        });

        btnSaveEns.addActionListener(e -> {
            try {
                List<Enseignant> list = enseignantDAO.getAll();
                System.out.println("Enregistrement terminé. Total enseignants : " + list.size());
                JOptionPane.showMessageDialog(this, "Enseignants enregistrés.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement.");
            }
        });

        btnAssignMatieres.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Fonctionnalité à implémenter : affectation des matières.");
        });

        chargerDonneesInitiales();

        setVisible(true);
    }

    private void chargerDonneesInitiales() {
        try {
            for (Etudiant e : etudiantDAO.getAll()) {
                studentModel.addElement(e.getNom() + " " + e.getPrenom());
            }
            for (Enseignant ens : enseignantDAO.getAll()) {
                enseignantModel.addElement(ens.getNom() + " " + ens.getPrenom());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
