package UI;

import javax.swing.*;
import java.awt.*;

public class EnseigPage  extends JFrame {

    CardLayout cardLayout;
    JPanel rightPanel;

    public EnseigPage() {
        setTitle("Page Enseignent");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Split layout: Left menu and Right content
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(400); // width of sidebar
        splitPane.setEnabled(false); // disable manual resize

        // LEFT: Sidebar panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(58, 146, 165)); // Dark Blue
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Page d'Enseignent");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBorder(BorderFactory.createEmptyBorder(30, 1, 30, 30));

        leftPanel.add(title);

        JButton btnEtudiants = createSidebarButton(" Consulter les étudiants");
        JButton btnMatieres = createSidebarButton(" Consulter les matières");

        leftPanel.add(btnEtudiants);
        leftPanel.add(btnMatieres);
        leftPanel.add(Box.createVerticalGlue());

        // RIGHT: Main content panel
        cardLayout = new CardLayout();
        rightPanel = new JPanel(cardLayout);

        // Pages
        rightPanel.add(createPlaceholderPanel("Liste des étudiants affichée ici"), "etudiants");
        rightPanel.add(createPlaceholderPanel("Liste des matières affichée ici"), "matieres");

        // Action listeners
        btnEtudiants.addActionListener(e -> cardLayout.show(rightPanel, "etudiants"));
        btnMatieres.addActionListener(e -> cardLayout.show(rightPanel, "matieres"));

        // Combine panels
        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(rightPanel);

        add(splitPane);
        setVisible(true);
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(300, 70));
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
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }





}
