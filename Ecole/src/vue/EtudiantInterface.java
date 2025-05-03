package vue;

import javax.swing.*;
import java.awt.*;

public class EtudiantInterface extends JFrame {

    public EtudiantInterface() {
        setTitle("Interface Étudiant");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1, 10, 10));

        JButton btnMatieres = new JButton("Consulter la liste des matières");
        JButton btnEnseignants = new JButton("Consulter la liste des enseignants");

        add(btnMatieres);
        add(btnEnseignants);
        
        setVisible(true);
    }
}
