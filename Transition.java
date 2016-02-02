public class Transition {
	
	private Character etatInit;
	private Character etatFinal;
	private Character entree;
	private Character sortie;
	
	public Transition(Character init, Character entree, Character fin, Character sortie) {
		
		this.etatInit = init;
		this.entree = entree;
		this.etatFinal = fin;
		this.sortie  = sortie;
	}

	public Transition(Character init, Character entree, Character fin) {
		
		this.etatInit = init;
		this.entree = entree;
		this.etatFinal = fin;
		this.sortie = '#';
	}
	
	public Character getEtatInit() {
		return this.etatInit;
	}

	public void setEtatInit(Character etatInit) {
		this.etatInit = etatInit;
	}

	public Character getEtatFinal() {
		return this.etatFinal;
	}

	public void setEtatFinal(Character etatFinal) {
		this.etatFinal = etatFinal;
	}

	public Character getEntree() {
		return this.entree;
	}

	public void setEntree(Character entree) {
		this.entree = entree;
	}

	public Character getSortie() {
		return this.sortie;
	}

	public void setSortie(Character sortie) {
		this.sortie = sortie;
	}
}
