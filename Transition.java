public class Transition {
	
	// Attributs
	private String etatInit;
	private String etatFinal;
	private String entree;
	private String sortie;
	
	// Constructeurs
	public Transition(String init, String entree, String fin, String sortie) {
		
		this.etatInit = init;
		this.entree = entree;
		this.etatFinal = fin;
		this.sortie  = sortie;
	}

	public Transition(String init, String entree, String fin) {
		
		this.etatInit = init;
		this.entree = entree;
		this.etatFinal = fin;
		this.sortie = "#";
	}
	
	// Getters/Setters
	public String getEtatInit() { return this.etatInit;	}
	public String getEntree() {	return this.entree;	}
	public String getEtatFinal() { return this.etatFinal; }
	public String getSortie() {	return this.sortie;	}

	public void setEtatInit(String etatInit) { this.etatInit = etatInit; }
	public void setEntree(String entree) { this.entree = entree; }
	public void setEtatFinal(String etatFinal) { this.etatFinal = etatFinal; }
	public void setSortie(String sortie) { this.sortie = sortie; }
}
