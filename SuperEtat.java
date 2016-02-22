import java.util.ArrayList;

public class SuperEtat {
	
	private String nom;
	private ArrayList<Etat> superEtats;
	
	// Constructeur
	public SuperEtat(String leNom){
		
		this.nom = leNom;
		this.superEtats = new ArrayList<Etat>();
	}

	// Getters/Setters
	public ArrayList<Etat> getSuperEtats() { return this.superEtats; }
	public String getNom() { return this.nom; }
	
	public void setNom(String nom) { this.nom = nom; }
	public void setSuperEtats(ArrayList<Etat> superEtats) { this.superEtats = superEtats; }
	
	// Methode d'ajout d'etats
	public void addEtat(Etat e) {
		
		this.superEtats.add(e);
	}
	
	//redefinition toString pour l'affichage
	public String toString() {
		
		String str = "Super etats : { ";
		
		for (int i = 0; i < this.getSuperEtats().size(); i++) {
			
			str += this.getSuperEtats().get(i).getNom()+ " ";
		}
		
		str += "}";
		return str;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((superEtats == null) ? 0 : superEtats.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SuperEtat other = (SuperEtat) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (superEtats == null) {
			if (other.superEtats != null)
				return false;
		} else if (!superEtats.equals(other.superEtats))
			return false;
		return true;
	}
}
