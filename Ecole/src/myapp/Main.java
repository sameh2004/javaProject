package myapp;

import UI.AdminePage;
import UI.Login;
import UI.EnseigPage;
import UI.StudentPage;
import vue.AdminInterface;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
       // SwingUtilities.invokeLater(() -> {
         //  new AdminePage();
        //});
        new Login();
        //new StudentPage().setVisible(true);
        //new EnseigPage();
    }
}
