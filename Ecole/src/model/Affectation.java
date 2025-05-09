package model;

public class Affectation {
    private int matiereId;
    private int enseignantId;
    private int nbrHeures;

    public Affectation(int matiereId, int enseignantId, int nbrHeures) {
        this.matiereId = matiereId;
        this.enseignantId = enseignantId;
        this.nbrHeures = nbrHeures;
    }

    // Getters
    public int getMatiereId() { return matiereId; }
    public int getEnseignantId() { return enseignantId; }
    public int getNbrHeures() { return nbrHeures; }

    public void setNbrHeures(int nbrHeures) {
        this.nbrHeures = nbrHeures;
    }

    public void setEnseignantId(int enseignantId) {
        this.enseignantId = enseignantId;
    }
    public void setMatiereId(int matiereId) {
        this.matiereId = matiereId;
    }
}
