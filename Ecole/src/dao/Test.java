package dao;


import java.sql.SQLException;
import java.util.List;


import model.Utilisateur;



public class Test {

    public static void main(String[] args) {
        try {
        	
        	
        	UtilisateurDAO dao=new UtilisateurDAO();
        	Utilisateur u = dao.getByID(10555);
        	
        	dao.delete(u);
        	
        	
        	List<Utilisateur> l;
        	l=dao.getAll();
        	for (Utilisateur c : l) {
                System.out.println(c);
            }
        } catch (SQLException e) {
        	System.out.println(e);
        }
    }
}
