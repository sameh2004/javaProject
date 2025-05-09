package dao;
import model.Affectation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public  class AffectationDAO implements IDAO<Affectation> {



    @Override
    public boolean add(Affectation affectation) throws SQLException {
        String sql = "INSERT INTO AffectationMatEns (matiere_id, enseignant_id, nbr_heures) VALUES (?, ?, ?)";

        try (Connection cx=SingletonConnection.getInstance();
             PreparedStatement pstmt = cx.prepareStatement(sql)) {

            pstmt.setInt(1, affectation.getMatiereId());
            pstmt.setInt(2, affectation.getEnseignantId());
            pstmt.setInt(3, affectation.getNbrHeures());

            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();

            return rowsAffected > 0;

        } catch (SQLException e) {
            throw e; // ou loguer + traiter selon ton besoin
        }
    }
    @Override
    public List<Affectation> getAll() throws SQLException {
        return null;
    }

    @Override
    public Affectation getByID(Object id) throws SQLException {
        return null;
    }

    @Override
    public void update(Affectation object) throws SQLException {

    }

    @Override
    public void delete(Affectation object) throws SQLException {

    }
}
