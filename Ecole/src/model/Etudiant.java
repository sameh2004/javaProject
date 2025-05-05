package model;

public class Etudiant extends Utilisateur {
	private int niveau;
	private String specialitéEtudiant;
	public Etudiant(int log,String pass,String nom,String prenom,int niveau,String specialitéEtudiant) {
		super(log,pass,nom,prenom,"");
		this.niveau=niveau;
		this.specialitéEtudiant=specialitéEtudiant;
	}
	public Etudiant() {
		
	}
	

	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	public String getSpecialiteEtudiant() {
		return specialitéEtudiant;
	}
	public void setSpecialiteEtudiant(String specialitéEtudiant) {
		this.specialitéEtudiant = specialitéEtudiant;
	}
	@Override
	public String toString() {
		return" Etudiant[log=" + super.getLog() + ", pass=" + super.getPass() + ", nom=" + super.getNom() + ", prenom=" + super.getPrenom() +  ", niveau=" + niveau + " specialitéEtudiant=" + specialitéEtudiant + "]";
	}
	
	

	
}
