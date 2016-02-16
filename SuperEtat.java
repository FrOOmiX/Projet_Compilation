import java.util.ArrayList;

public class SuperEtat {
	
	private ArrayList<Etat> superEtats;
	
	// Constructeur
	public SuperEtat(){
		
		this.superEtats = new ArrayList<Etat>();
	}

	// Getters/Setters
	public ArrayList<Etat> getSuperEtats() { return this.superEtats; }
	public void setSuperEtats(ArrayList<Etat> superEtats) { this.superEtats = superEtats; }
	
	// Methode d'ajout d'etats
	public void addEtat(Etat e) {
		
		this.superEtats.add(e);
	}
	
	//redefinition toString pour l'affichage
	public String toString() {
		
		String str = "Super etats : { ";
		
		for (int i = 0; i < this.getSuperEtats().size(); i++) {
			
			str += this.getSuperEtats().get(i).getNomEtat()+ " ";
		}
		
		str += "}";
		return str;
	}
}
