package model;

public class Utilisateur {
	private int log;
	private String pass;
	private String nom;
	private String prenom;
	private String role;
	public Utilisateur(int log,String pass,String nom,String prenom,String role) {
		this.log=log;
		this.pass=pass;
		this.nom=nom;
		this.prenom=prenom;
		this.role=role;
	}
	public Utilisateur() {
		
	}

	public Utilisateur(int id) {
	}

	public int getLog() {
		return log;
	}
	public void setLog(int log) {
		this.log = log;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Utilisateur [log=" + log + ", pass=" + pass + ", nom=" + nom + ", prenom=" + prenom + ", role=" + role
				+ "]";
	}
	
}
