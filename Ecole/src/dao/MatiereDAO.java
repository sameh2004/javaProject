package dao;

import java.sql.SQLException;
import java.util.List;

import model.Matiere;

public class MatiereDAO implements IDAO<Matiere> {

	@Override
	public void add(Matiere m) throws SQLException {
		{
			Connection cx = SingletonConnection.getInstance();
			String req = "INSERT INTO Matiere (id,type,nom) VALUES ( ?, ?, ?)";
			PreparedStatement ps = cx.prepareStatement(req);
			ps.setInt(1, m.getID());
			ps.setString(2, m.getType());

			ps.setString(3, m.getNom());

			ps.executeUpdate();
			ps.close();
		}



		
	}

	@Override
	public List<Matiere> getAll() throws SQLException {
		Connection cx = SingletonConnection.getInstance();
		String req="SELECT * FROM Matiere";
		PreparedStatement ps = cx.prepareStatement(req);
		ResultSet rs = ps.executeQuery();
		List<Matiere> matieres = new ArrayList<Matiere>();
		while (rs.next()) {
			Matiere m = new Matiere(rs.getInt("id"),
					rs.getString("type"),
					rs.getString("nom"));

		matieres.add(m);
		}
		rs.close();
		ps.close();
		return matieres;
	}

	@Override
	public Matiere getByID(Object id) throws SQLException {
		Matiere m = null;
		String req = "SELECT * FROM Matiere WHERE id = ?";
		Connection cx = SingletonConnection.getInstance();
		PreparedStatement ps = cx.prepareStatement(req);
		ps.setInt(1, (int) id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			m = new Matiere(	rs.getInt("id"),
					rs.getString("type"),
					rs.getString("nom"));
		}

		rs.close();
		ps.close();
		return m;
	}

	@Override
	public void update(Matiere object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Matiere object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
