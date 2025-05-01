package model;

public class Etudiant extends Utilisateur {
	private int id;
	public Etudiant(int id) {
		super();
		this.id=id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
}
