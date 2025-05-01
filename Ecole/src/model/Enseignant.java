package model;

public class Enseignant  extends Utilisateur {
	private String specialitéEnseign;

	public Enseignant(int log,String pass,String nom,String prenom,String spec) {
		super(log,pass,nom,prenom,"");
		this.specialitéEnseign=spec;
	}
	public Enseignant() {
		
	}
	public String getSpecialitéEnseign() {
		return specialitéEnseign;
	}

	public void setSpecialitéEnseign(String specialitéEnseign) {
		this.specialitéEnseign = specialitéEnseign;
	}
	@Override
	public String toString() {
		return" Enseignant[log=" + super.getLog() + ", pass=" + super.getPass() + ", nom=" + super.getNom() + ", prenom=" + super.getPrenom() +  
 "specialitéEnseign=" + specialitéEnseign + "]";
	}

	
	
	
}
