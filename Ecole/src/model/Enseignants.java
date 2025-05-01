package model;

public class Enseignants  extends Utilisateur {
	private int id;

	public Enseignants(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
