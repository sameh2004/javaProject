package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Etudiant;
import model.Matiere;

public class MatiereDAO implements IDAO<Matiere> {

	@Override
	public void add(Matiere M) throws SQLException {
		Connection cx=SingletonConnection.getInstance();
		String req = "insert into matiere (id,nom,type) values(?,?,?)";
		PreparedStatement ps;
		ps=cx.prepareStatement(req);
		ps.setInt(1,M.getID());
		ps.setString(2,M.getNom());
		ps.setString(3,M.getType());
		
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public List<Matiere> getAll() throws SQLException {
		Connection cx = SingletonConnection.getInstance();
        List<Matiere> Matieres = new ArrayList<>();
        String req = "SELECT * FROM matiere ;";
        PreparedStatement ps = cx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
        	Matiere e = new Matiere();
            e.setID(rs.getInt("id"));
            e.setNom(rs.getString("nom"));
            
            e.setNom(rs.getString("type"));
            
            Matieres.add(e);
        }
        rs.close();
        ps.close();
        return  Matieres;
	}

	@Override
	public Matiere getByID(Object id) throws SQLException {
		Matiere e = null;
	        String req = "SELECT * FROM matiere WHERE id = ?";
	        Connection cx = SingletonConnection.getInstance();
	        PreparedStatement ps = cx.prepareStatement(req);
	        ps.setInt(1, (int) id);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            e = new Matiere();
	            e.setID(rs.getInt("id"));
	            e.setNom(rs.getString("nom"));
	            e.setType(rs.getString("type"));
	          
	        }

	        rs.close();
	        ps.close();
	        return e;
	}

	@Override
	public void update(Matiere m) throws SQLException {
		 Connection cx = SingletonConnection.getInstance();
	        String req = "UPDATE matiere SET nom = ?, type = ?  WHERE id = ?";
	        PreparedStatement ps = cx.prepareStatement(req);
	        ps.setString(1, m.getNom());
	        ps.setString(2, m.getType());
	        
	        ps.setInt(3, m.getID());
	        ps.executeUpdate();
	        ps.close();
	}

	@Override
	public void delete(Matiere m) throws SQLException {
		Connection cx = SingletonConnection.getInstance();
        String req = "DELETE FROM matiere WHERE id = ?";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setInt(1, m.getID());
        ps.executeUpdate();
        ps.close();
		
	}

}
