package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Enseignant;

public class EnseignantDAO implements IDAO<Enseignant> {

    @Override
    public boolean add(Enseignant e) throws SQLException {
        Connection cx = SingletonConnection.getInstance();
        String req = "INSERT INTO enseignant (log,  nom, prenom,  specialité, pass) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setInt(1, e.getLog());
        
        
        ps.setString(2, e.getNom());
        ps.setString(3, e.getPrenom());
        ps.setString(4, e.getSpecialitéEnseign());
        ps.setString(5, e.getPass());
        ps.executeUpdate();
        ps.close();
        return false;
    }

    @Override
    public List<Enseignant> getAll() throws SQLException {
        Connection cx = SingletonConnection.getInstance();
        List<Enseignant> enseignants = new ArrayList<>();
        String req = "SELECT * FROM enseignant";
        PreparedStatement ps = cx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Enseignant e = new Enseignant();
            e.setLog(rs.getInt("log"));
            e.setPass(rs.getString("pass"));
            
            e.setNom(rs.getString("nom"));
            e.setPrenom(rs.getString("prenom"));
            e.setSpecialitéEnseign(rs.getString("specialité"));
            enseignants.add(e);
        }

        rs.close();
        ps.close();
        return enseignants;
    }

    public Enseignant getByID(Object log) throws SQLException {
        Enseignant e = null;
        String req = "SELECT * FROM enseignant WHERE log = ?";
        Connection cx = SingletonConnection.getInstance();
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setInt(1, (int) log);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            e = new Enseignant();
            e.setLog(rs.getInt("log"));
            e.setPass(rs.getString("pass"));
            
            e.setNom(rs.getString("nom"));
            e.setPrenom(rs.getString("prenom"));
            e.setSpecialitéEnseign(rs.getString("specialité"));
        }

        rs.close();
        ps.close();
        return e;
    }

    @Override
    public void update(Enseignant e) throws SQLException {
        Connection cx = SingletonConnection.getInstance();
        String req = "UPDATE enseignant SET  nom = ?, prenom = ?, specialité = ? ,pass = ? WHERE log = ?";
        PreparedStatement ps = cx.prepareStatement(req);
        
        
        ps.setString(1, e.getNom());
        ps.setString(2, e.getPrenom());
        ps.setString(3, e.getSpecialitéEnseign());
        ps.setString(4, e.getPass());
        ps.setInt(5, e.getLog());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(Enseignant e) throws SQLException {
        Connection cx = SingletonConnection.getInstance();
        String req = "DELETE FROM enseignant WHERE log = ?";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setInt(1, e.getLog());
        ps.executeUpdate();
        ps.close();
    }
}
