package model;

public class Personnel extends Utilisateur {

    private String poste;

    public Personnel(int log, String pass, String nom, String prenom, String poste) {
        super(log, pass, nom, prenom, "");
        this.poste = poste;
    }

    public Personnel() {}

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    @Override
    public String toString() {
        return "Personnel[log=" + getLog() + ", pass=" + getPass() + ", nom=" + getNom() +
               ", prenom=" + getPrenom() + ", role=" + getRole() + ", poste=" + poste + "]";
    }
}
