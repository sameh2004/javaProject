package dao;


import java.sql.SQLException;
import java.util.List;

import model.Enseignant;





public class Test {

    public static void main(String[] args) {
        try {
        	
        	
        	EnseignantDAO dao=new EnseignantDAO();
        	Enseignant  p=new Enseignant(102564,"ldfl3f52","ali","malek","math");
        	dao.add(p);
        	List<Enseignant> l;
        	l=dao.getAll();
        	for (Enseignant c : l) {
                System.out.println(c);
            }
        } catch (SQLException e) {
        	System.out.println(e);
        }
    }
}
