public class Transition {
	
	private String etatInit;
	private String etatFinal;
	private String entree;
	private String sortie;
	
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
	
	public String getEtatInit() {
		return this.etatInit;
	}

	public void setEtatInit(String etatInit) {
		this.etatInit = etatInit;
	}

	public String getEtatFinal() {
		return this.etatFinal;
	}

	public void setEtatFinal(String etatFinal) {
		this.etatFinal = etatFinal;
	}

	public String getEntree() {
		return this.entree;
	}

	public void setEntree(String entree) {
		this.entree = entree;
	}

	public String getSortie() {
		return this.sortie;
	}

	public void setSortie(String sortie) {
		this.sortie = sortie;
	}
}
