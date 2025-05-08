package model;

public class Matiere {
	private int ID;
	private String type;
	private String nom;
	public Matiere(int id,String nom,String type) {
		this.ID=id;
		this.nom=nom;
		this.type=type;
	}
	public Matiere() {}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
