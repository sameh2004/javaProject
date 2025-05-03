package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.Utilisateur;


public class UtilisateurDAO implements IDAO<Utilisateur>{

	@Override
	public void add(Utilisateur C) throws SQLException {
		Connection cx=SingletonConnection.getInstance();
		String req = "insert into utilisateur (log,pass,user_role,nom,prenom) values(?,?,?,?,?)";
		PreparedStatement ps;
		ps=cx.prepareStatement(req);
		ps.setInt(1,C.getLog());
		ps.setString(2,C.getPass());
		ps.setString(3,C.getRole());
		ps.setString(4,C.getNom());
		ps.setString(5,C.getPrenom());
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public List<Utilisateur> getAll() throws SQLException {
		Connection cx=SingletonConnection.getInstance();
		List<Utilisateur> Utilisateurs =new ArrayList<>();
		PreparedStatement ps=cx.prepareStatement("select * from utilisateur");
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			Utilisateur u=new Utilisateur();
			u.setLog(rs.getInt("log"));
			u.setPass(rs.getString("pass"));
			u.setRole(rs.getString("user_role"));
			u.setNom(rs.getString("nom"));
			u.setPrenom(rs.getString("prenom"));
			Utilisateurs.add(u);
		}
		ps.close();
		return Utilisateurs;
	}

	@Override
	public Utilisateur getByID(Object log) throws SQLException{
		Utilisateur c = null;
		    String req = "SELECT * FROM utilisateur WHERE log = ?";

		    Connection cx = SingletonConnection.getInstance();
		         PreparedStatement ps = cx.prepareStatement(req) ;

		        ps.setInt(1, (int) log);
		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {
		            c = new Utilisateur();
		            c.setLog(rs.getInt("log"));
		            c.setPass(rs.getString("pass"));
		            c.setNom(rs.getString("nom"));
		            c.setPrenom(rs.getString("prenom"));
		            c.setRole(rs.getString("user_role"));
		            ps.close();
		            rs.close();
		        }

		    
		    
		    return c;
	}

	@Override
	public void update(Utilisateur u) throws SQLException {
	    Connection cx = SingletonConnection.getInstance();
	    String req = "UPDATE utilisateur SET pass = ?, user_role = ?, nom = ?, prenom = ? WHERE log = ?";
	    PreparedStatement ps = cx.prepareStatement(req);
	    ps.setString(1, u.getPass());
	    ps.setString(2, u.getRole());
	    ps.setString(3, u.getNom());
	    ps.setString(4, u.getPrenom());
	    ps.setInt(5, u.getLog()); 
	    ps.executeUpdate();
	    ps.close();
	}

	@Override
	
	public void delete(Utilisateur u) throws SQLException {
	    String req = "DELETE FROM utilisateur WHERE log = ?";
	    Connection cx = SingletonConnection.getInstance();
	         PreparedStatement ps = cx.prepareStatement(req);

	        ps.setInt(1, u.getLog());
	        ps.executeUpdate();

	  
	}
	public Utilisateur SeConnecter(int log, String pass) {
	    Utilisateur u = null;
	    String req = "SELECT * FROM utilisateur WHERE log = ? AND pass = ?";

	    try {
	        Connection cx = SingletonConnection.getInstance();
	        PreparedStatement ps = cx.prepareStatement(req);
	        ps.setInt(1, log);
	        ps.setString(2, pass);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            u = new Utilisateur();
	            u.setLog(rs.getInt("log"));
	            u.setPass(rs.getString("pass"));
	            u.setRole(rs.getString("user_role"));
	            u.setNom(rs.getString("nom"));
	            u.setPrenom(rs.getString("prenom"));
	        }

	        rs.close();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return u; // null si non trouvé
	}


}
