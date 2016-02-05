public class Etat {
	
	String nomEtat;
	char nom;
	
	public Etat(String nom) {
		
		this.nomEtat = nom;
	}

	public Etat(char nom) {
		this.nom = nom;
	}

	public String getNomEtat() { return this.nomEtat; }

	public void setNomEtat(String nomEtat) { this.nomEtat = nomEtat; }
}
