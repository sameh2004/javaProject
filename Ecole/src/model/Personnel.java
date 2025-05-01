package model;

public class Personnel extends Utilisateur {
private int id;
private String poste;
public Personnel(String poste) {
	super();
	this.poste=poste;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getPoste() {
	return poste;
}
public void setPoste(String poste) {
	this.poste = poste;
}
}
