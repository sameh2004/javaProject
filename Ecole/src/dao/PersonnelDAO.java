package dao;


	

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;

	import model.Personnel;

	public class PersonnelDAO implements IDAO<Personnel> {

	    @Override
	    public void add(Personnel p) throws SQLException {
	        Connection cx = SingletonConnection.getInstance();
	        String req = "INSERT INTO personnel (log, pass,  nom, prenom,  poste) VALUES ( ?, ?, ?, ?, ?)";
	        PreparedStatement ps = cx.prepareStatement(req);
	        ps.setInt(1, p.getLog());
	        ps.setString(2, p.getPass());
	        ps.setString(3, p.getNom());
	        ps.setString(4, p.getPrenom());
	        ps.setString(5, p.getPoste());
	        ps.executeUpdate();
	        ps.close();
	    }

	    @Override
	    public List<Personnel> getAll() throws SQLException {
	        Connection cx = SingletonConnection.getInstance();
	        List<Personnel> Personnels = new ArrayList<>();
	        String req = "SELECT * FROM personnel";
	        PreparedStatement ps = cx.prepareStatement(req);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Personnel e = new Personnel();
	            e.setLog(rs.getInt("log"));
	            e.setPass(rs.getString("pass"));
	            
	            e.setNom(rs.getString("nom"));
	            e.setPrenom(rs.getString("prenom"));
	            e.setPoste(rs.getString("poste"));
	            Personnels.add(e);
	        }

	        rs.close();
	        ps.close();
	        return Personnels;
	    }

	    @Override
	    public Personnel getByID(Object log) throws SQLException {
	    	Personnel p = null;
	        String req = "SELECT * FROM personnel WHERE log = ?";
	        Connection cx = SingletonConnection.getInstance();
	        PreparedStatement ps = cx.prepareStatement(req);
	        ps.setInt(1, (int) log);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            p = new Personnel();
	            p.setLog(rs.getInt("log"));
	            p.setPass(rs.getString("pass"));
	            
	            p.setNom(rs.getString("nom"));
	            p.setPrenom(rs.getString("prenom"));
	            p.setPoste(rs.getString("poste"));
	        }

	        rs.close();
	        ps.close();
	        return p;
	    }

	    @Override
	    public void update(Personnel p) throws SQLException {
	        Connection cx = SingletonConnection.getInstance();
	        String req = "UPDATE personnel SET pass = ?, nom = ?, prenom = ?, niveau = ?, poste = ? WHERE log = ?";
	        PreparedStatement ps = cx.prepareStatement(req);
	        ps.setString(1, p.getPass());
	        
	        ps.setString(2, p.getNom());
	        ps.setString(3, p.getPrenom());
	        ps.setString(4, p.getPoste());
	        ps.setInt(5, p.getLog());
	        ps.executeUpdate();
	        ps.close();
	    }

	    @Override
	    public void delete(Personnel e) throws SQLException {
	        Connection cx = SingletonConnection.getInstance();
	        String req = "DELETE FROM personnel WHERE log = ?";
	        PreparedStatement ps = cx.prepareStatement(req);
	        ps.setInt(1, e.getLog());
	        ps.executeUpdate();
	        ps.close();
	    }
	}


