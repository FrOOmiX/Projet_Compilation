import java.util.ArrayList;

public class SuperEtat { //plus tard tester avec extends Etat pour renommer le super Etat (ex : on a super : {0,2,9}
						//et on renomme en super : 0
	private ArrayList<Etat> superEtats;
	
	public SuperEtat(){
		this.superEtats = new ArrayList<Etat>();
	}

	//assesseurs
	public ArrayList<Etat> getSuperEtats() {
		return superEtats;
	}

	public void setSuperEtats(ArrayList<Etat> superEtats) {
		this.superEtats = superEtats;
	}
	
	//redefinition toString pour l'affichage
	public String toString(){
		String str = "Super etats : {";
		str += this.getSuperEtats().get(0).getNomEtat();
		for (int i = 0; i < this.getSuperEtats().size(); i++) {
			str += ", ";
			str += this.getSuperEtats().get(i).getNomEtat();
		}
		return str;
	}
	
}
