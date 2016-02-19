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
}
