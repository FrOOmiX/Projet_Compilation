public class Etat {
	
	// Attributs
	private String nom;
	
	// Constructeur
	public Etat(String leNom) {
		
		this.nom = leNom;
	}

	// Getters/Setters
	public String getNom() { return this.nom; }

	public void setNom(String nom) { this.nom = nom; }

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etat other = (Etat) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
}
