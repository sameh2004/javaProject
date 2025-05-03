package dao;


	

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;

import model.Etudiant;


	public class EtudiantDAO implements IDAO<Etudiant>{


		@Override
		public void add(Etudiant E) throws SQLException {
			Connection cx=SingletonConnection.getInstance();
			String req = "insert into etudiant (log,pass,nom,prenom,niveau,specialité) values(?,?,?,?,?,?)";
			PreparedStatement ps;
			ps=cx.prepareStatement(req);
			ps.setInt(1,E.getLog());
			ps.setString(2,E.getPass());
			ps.setString(3,E.getNom());
			ps.setString(4,E.getPrenom());
			ps.setInt(5, E.getNiveau());
			ps.setString(6,E.getSpecialitéEtudiant());
			ps.executeUpdate();
			ps.close();
		}

		@Override
		public List<Etudiant> getAll() throws SQLException {
	        Connection cx = SingletonConnection.getInstance();
	        List<Etudiant> etudiants = new ArrayList<>();
	        String req = "SELECT * FROM etudiant ;";
	        PreparedStatement ps = cx.prepareStatement(req);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Etudiant e = new Etudiant();
	            e.setLog(rs.getInt("log"));
	            e.setPass(rs.getString("pass"));
	            
	            e.setNom(rs.getString("nom"));
	            e.setPrenom(rs.getString("prenom"));
	            e.setNiveau(rs.getInt("niveau"));
	            e.setSpecialitéEtudiant(rs.getString("specialité"));
	            etudiants.add(e);
	        }
	        rs.close();
	        ps.close();
	        return etudiants;
	    }
		@Override
		  public Etudiant getByID(Object log) throws SQLException {
	        Etudiant e = null;
	        String req = "SELECT * FROM etudiant WHERE log = ?";
	        Connection cx = SingletonConnection.getInstance();
	        PreparedStatement ps = cx.prepareStatement(req);
	        ps.setInt(1, (int) log);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            e = new Etudiant();
	            e.setLog(rs.getInt("log"));
	            e.setPass(rs.getString("pass"));
	            e.setNom(rs.getString("nom"));
	            e.setPrenom(rs.getString("prenom"));
	           
	            e.setNiveau(rs.getInt("niveau"));
	            e.setSpecialitéEtudiant(rs.getString("specialité"));
	        }

	        rs.close();
	        ps.close();
	        return e;
	    }
		  @Override
		    public void update(Etudiant e) throws SQLException {
		        Connection cx = SingletonConnection.getInstance();
		        String req = "UPDATE etudiant SET pass = ?, nom = ?, prenom = ?, niveau = ?, specialité = ? WHERE log = ?";
		        PreparedStatement ps = cx.prepareStatement(req);
		        ps.setString(1, e.getPass());
		        ps.setString(3, e.getNom());
		        ps.setString(4, e.getPrenom());
		        ps.setInt(5, e.getNiveau());
		        ps.setString(6, e.getSpecialitéEtudiant());
		        ps.setInt(7, e.getLog());
		        ps.executeUpdate();
		        ps.close();
		    }

		    @Override
		    public void delete(Etudiant e) throws SQLException {
		        Connection cx = SingletonConnection.getInstance();
		        String req = "DELETE FROM etudiant WHERE log = ?";
		        PreparedStatement ps = cx.prepareStatement(req);
		        ps.setInt(1, e.getLog());
		        ps.executeUpdate();
		        ps.close();
		    }
		}