package vue;

import javax.swing.*;
import java.awt.*;

public class EnseignantInterface extends JFrame {

    public EnseignantInterface() {
        setTitle("Interface Enseignant");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1, 10, 10));

        JButton btnEtudiants = new JButton("Consulter la liste des étudiants");
        JButton btnMatieres = new JButton("Consulter la liste des matières");

        add(btnEtudiants);
        add(btnMatieres);

        setVisible(true);
    }
}
