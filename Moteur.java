import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

public class Moteur {

	private char meta; 										// meta caractere pour arreter la saisie
	private String commentaire;								// commentaires
	private ArrayList<Character> alphabetEntree; 			// liste du vocabulaire d'entree
	private ArrayList<Character> sorties; 					// liste du vocabulaire de sortie
	private char nbEtats; 									// nombre d'etats dans l'AEF
	private ArrayList<Character> etatInit; 					// liste des etats init de l'AEF
	private ArrayList<Character> etatsAcceptants; 			// liste des etats acceptant
	private ArrayList<Transition> transitions; 				// liste des transitions
	private ArrayList<Etat> etats;
	
	// Constructeur
	public Moteur(String nomFichier) {
		
		try {
			this.transitions = new ArrayList<Transition>();
			String ligne;
			BufferedReader str = new BufferedReader(new FileReader(nomFichier));
			ligne = str.readLine();
			
			// Gestion de l'etat initial par defaut
			this.etatInit = new ArrayList<Character>();
			etatInit.add('0');
			meta ='#';
			int i = 0;

			while (ligne != null) {
				
				if (ligne.charAt(0) == 'C' && i < ligne.length()) {
					commentaire="";
					while (i < ligne.length()) {
								
						if (ligne.charAt(i) != '\'' && i>2 ) {
							commentaire+=(ligne.charAt(i));
						}
						i++;
					}
				}// If C
				
				else if (ligne.charAt(0) == 'M' && i < ligne.length()) {
					while (i < ligne.length()) {

						if (ligne.charAt(i) != ' ' && i > 0) {
							meta=ligne.charAt(i);
						}	 
						i++;
					}
				}// If M
				
				else if (ligne.charAt(0) == 'V' && i < ligne.length()) {
					this.alphabetEntree = new ArrayList<Character>();

					while (i < ligne.length()) {

						if (ligne.charAt(i) != ' ' && ligne.charAt(i) != '"'
								&& i > 0) {

							alphabetEntree.add(ligne.charAt(i));

						}
						i++;
					}
				}// If V

				else if (ligne.charAt(0) == 'O' && i < ligne.length()) {
					this.sorties = new ArrayList<Character>();

					while (i < ligne.length()) {

						if (ligne.charAt(i) != ' ' && ligne.charAt(i) != '"'
								&& i > 0) {

							sorties.add(ligne.charAt(i));

						}
						i++;
					}
				}// If O

				else if (ligne.charAt(0) == 'E' && i < ligne.length()) {

					if (ligne.charAt(i) != ' ' && i > 0) {
						nbEtats = (ligne.charAt(i));

					}
					i++;
				}// If E

				else if (ligne.charAt(0) == 'I' && i < ligne.length()) {
					this.etatInit = new ArrayList<Character>();
					while (i < ligne.length()) {

						if (ligne.charAt(i) != ' ' && ligne.charAt(i) != '"'
								&& i > 0) {

							etatInit.add(ligne.charAt(i));

						}
						i++;
					}
				}// If I

				else if (ligne.charAt(0) == 'F' && i < ligne.length()) {
					this.etatsAcceptants = new ArrayList<Character>();
					while (i < ligne.length()) {

						if (ligne.charAt(i) != ' ' && i > 0) {

							etatsAcceptants.add(ligne.charAt(i));

						}
						i++;
					}
				}// If F
				
				else if (ligne.charAt(0) == 'T' && i < ligne.length()) {
								//sans lamba transition
								if(ligne.length() == 9){
									Transition tr = new Transition(ligne.charAt(2),ligne.charAt(5),ligne.charAt(8));
									transitions.add(tr);
									//Etat etat= new Etat(String.valueOf(ligne.charAt(5)));
									//etats.add(etat);
									i = 0;
									ligne = str.readLine();
								}
								else if(ligne.length() ==13){
									Transition tr = new Transition(ligne.charAt(2),ligne.charAt(5),ligne.charAt(8),ligne.charAt(11));
									transitions.add(tr);
									//Etat etat= new Etat(String.valueOf(ligne.charAt(5)));
									//etats.add(etat);
									i = 0;
									ligne = str.readLine();
								}
								//avec lambda transition
								else if(ligne.length() ==10){
									Transition tr = new Transition(ligne.charAt(2),ligne.charAt(6),ligne.charAt(9));
									transitions.add(tr);
									//Etat etat= new Etat(String.valueOf(ligne.charAt(5)));
									//etats.add(etat);
									i = 0;
									ligne = str.readLine();
								}
								else if(ligne.length() ==14){
									Transition tr = new Transition(ligne.charAt(2),ligne.charAt(6),ligne.charAt(9),ligne.charAt(12));
									transitions.add(tr);
									//Etat etat= new Etat(String.valueOf(ligne.charAt(5)));
									//etats.add(etat);
									i = 0;
									ligne = str.readLine();
								}
					
				}// If T

				// read the next line
				else if (i == ligne.length()) {
					i = 0;
					ligne = str.readLine();
				}

			}// while

			str.close();
		
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Ensemble des methodes d'affichage des elements constituant l'AEF
	public void afficherCommentaire(){
		try{
			if(!this.getCommentaire().isEmpty())
				System.out.println("Commentaire : "+this.getCommentaire());
		}
		catch(NullPointerException e){
			System.out.println("Il n'y a pas de commentaire dans ce .descr.");
		}
	}
	
	public void afficheMetaChar(){
		
			if(this.getMeta() !='#'){
				System.out.println("Meta caractere : " + this.getMeta());
				
			}
		
		
			else{
			System.out.println("Pas de meta caractere dans ce .descr. Par defaut : " + this.getMeta());
		}
			
	}

	public void afficheAlphabetEntree() {
		try{
			String str ="";
			str += this.getAlphabetEntree().get(0);
			for (int i = 1; i < this.getAlphabetEntree().size(); i++) {
				str += ", ";
				str += this.getAlphabetEntree().get(i);
			}
			System.out.println("Alphabet d'entree : " + str + ".");
		}catch(NullPointerException e){
			System.out.println("Il n'y a pas d'alphabet d'entree dans ce .descr.");
		}
	}
	
	public void afficheAlphabetSortie(){
		try{
			String str ="";
			str += this.getSorties().get(0);
			for (int i = 1; i < this.getSorties().size(); i++) {
				str += ", ";
				str += this.getSorties().get(i);
			}
			System.out.println("Alphabet de sortie : " + str + ".");
		}catch(NullPointerException e){
			System.out.println("Il n'y a pas d'alphabet de sortie dans ce .descr.");
		}
	}

	public void afficheNombreEtat() { 
		if (this.getNbEtats() != 0 ) {
			System.out.println("Il y a : " + this.getNbEtats() + " etats."); 
		}else{
			System.out.println("Il n'y a pas d'etats.");
		}
	}
	
	public void afficherEtats(){
		this.etats = new ArrayList<Etat>();
		if(this.getNbEtats() != 0){
			int i = this.getNbEtats() - '0' - 1;
			String str = "";
			while(i >= 0){
				if(i != this.getNbEtats() - '0' - 1){
					Etat e = new Etat((char) (i + '0'));
					this.getEtats().add(e);
					str += ", " + i ;
				}else{
					Etat e = new Etat((char) (i + '0'));
					this.getEtats().add(e);
					str += i;
				}
				i--;
			}
			System.out.println("Les differents etats sont les suivants : " + str + ".");
		}else{
			System.out.println("Il n'y a pas d'etats a enumerer.");
		}
	}
	
	public void afficheEtatInit() {
		try{
			String str ="";
			str += this.getEtatInit().get(0);
			for (int i = 1; i < this.getEtatInit().size(); i++) {
				str += ", ";
				str += this.getEtatInit().get(i);
			}
			System.out.println("Etat initial/initiaux : " + str + ".");
		}catch(NullPointerException e){
			System.out.println("Il n'y a pas d'etat(s) initial/initiaux dans ce .descr.");
		}
	}
	
	public void afficheEtatsAcceptants(){
		try{
			String str ="";
			str += this.getEtatsAcceptants().get(0);
			for (int i = 1; i < this.getEtatsAcceptants().size(); i++) {
				str += ", ";
				str += this.getEtatsAcceptants().get(i);
			}
			System.out.println("Etat(s) acceptant(s) : " + str + ".");
		}catch(NullPointerException e){
			System.out.println("Il n'y a pas d'etats acceptants dans ce .descr.");
		}
	}
	
	public void afficheTransitions(){
		try{
			String str ="";
			str += this.getTransitions().get(0).getEtatInit();
			str += this.getTransitions().get(0).getEntree();
			str += this.getTransitions().get(0).getEtatFinal();
			str += this.getTransitions().get(0).getSortie();
			for (int i = 1; i < this.getTransitions().size(); i++) {
				str += "\n";
				str += this.getTransitions().get(i).getEtatInit();
				str += this.getTransitions().get(i).getEntree();
				str += this.getTransitions().get(i).getEtatFinal();
				str += this.getTransitions().get(i).getSortie();
			}
			System.out.println("Transition(s) : \n" + str);
		}catch(IndexOutOfBoundsException e){
			System.out.println("Il n'y a pas de transitions dans ce .descr.");
		}
	}

	// Ensemble des getters sur les variables d'instance du moteur AEF
	public String getCommentaire() { return this.commentaire; }
	public ArrayList<Character> getAlphabetEntree() { return this.alphabetEntree; }
	public char getMeta() {	return this.meta; }
	public ArrayList<Character> getSorties() { return this.sorties; }
	public char getNbEtats() { return this.nbEtats; }
	public ArrayList<Etat> getEtats() { return this.etats; }
	public ArrayList<Character> getEtatInit() {	return this.etatInit; }
	public ArrayList<Character> getEtatsAcceptants() { return this.etatsAcceptants; }
	public ArrayList<Transition> getTransitions() { return this.transitions; }
	
	
	/**
	 * 
	 * Partie Traitement des entrees
	 * 
	 */
	public void traitementEntrees(String entrees) {
		
		int i = 0, j = 0, cptDiese = 0;
		char cEntree, etatCourant = this.getEtatInit().get(0);
		String phraseSortie = "";
		
		System.out.println("\nTraitement des phrases lues :");
		
		while ((i < entrees.length()) && (cptDiese < 1)) {
			
			// On recupere la prochaine entree
			cEntree = entrees.charAt(i);
			
			if (cEntree == '#' && i == 0) {
				
				System.out.println("Aucune phrase n'a ete entree");
				cptDiese++;
			} else if (this.getTransitions().size() == 0) {
				
				System.out.println("Il n'existe pas de transitions");
				cptDiese++;
			} else {
			
				// Si l'entree est valide
				if (this.getAlphabetEntree().contains(cEntree)) {
					
					j = 0;
					
					// On parcourt les transitions pour cette entree
					while (j < this.getTransitions().size()) {
						
						// Si l'on trouve une transition dont l'entree et l'etat courant correspondent
						if ((this.getTransitions().get(j).getEntree() == cEntree) && (this.getTransitions().get(j).getEtatInit() == etatCourant)) {
							
							etatCourant = this.getTransitions().get(j).getEtatFinal();
							System.out.print("Etat Courant : " +this.getTransitions().get(j).getEtatInit()+ ", ");
							System.out.print("Entree : " +cEntree);
							
							// S'il existe une sortie
							if ((this.getTransitions().get(j).getSortie() != '#')) {
								
								System.out.print(", Sortie : " +this.getTransitions().get(j).getSortie());
								phraseSortie += this.getTransitions().get(j).getSortie();
							}
							
							System.out.println(" - Transition trouvee");
							
							// Evite de chercher d'autres transitions quand une est trouvee
							j = this.getTransitions().size();
						} else {
							
							//System.out.println("Pas de transition trouvee pour l'entree : " +cEntree);
						}
						
						j++;
					}
					
				// Si l'on arrive a une fin de phrase
				} else if ((cEntree == ' ') || (cEntree == '\n')) {
					
					// S'il s'agit du premier caractere
					if (cEntree == entrees.charAt(0)) {
						
						System.out.println("Aucune phrase n'a ete entree");
						System.out.println("-- Fin de phrase --");
						cptDiese++;
						
					// Si l'etat courant fait partie des etats acceptants
					} else if (this.getEtatsAcceptants().contains(etatCourant)) {
						
						System.out.println("Etat Courant : " +etatCourant+ " - Fin de chaine");
						System.out.println("Entree acceptante");
						System.out.println("La sortie de cette phrase est : " +phraseSortie);
						System.out.println("-- Fin de phrase --");
					} else {
						
						System.out.println("Etat Courant : " +etatCourant+ " - Fin de chaine");
						System.out.println("Entree non-acceptante");
						System.out.println("La sortie de cette phrase est : " +phraseSortie);
						System.out.println("-- Fin de phrase --");
					}
					
					phraseSortie = "";
					etatCourant = this.getEtatInit().get(0);
					
				// Si l'on arrive a la fin des phrases
				} else if (cEntree == '#') {
					
					// Si l'etat courant fait partie des etats acceptants
					if (this.getEtatsAcceptants().contains(etatCourant)) {
						
						System.out.println("Etat Courant : " +etatCourant+ " - Fin de chaine");
						System.out.println("Entree acceptante");
						System.out.println("La sortie de cette phrase est : " +phraseSortie);
						System.out.println("-- Fin de phrase --");
					} else {
						
						System.out.println("Etat Courant : " +etatCourant+ " - Fin de chaine");
						System.out.println("Entree non-acceptante");
						System.out.println("La sortie de cette phrase est : " +phraseSortie);
						System.out.println("-- Fin de phrase --");
					}
					
					cptDiese++;
				
				// Si l'entree est un autre caractere
				} else {
					
					if (cEntree == entrees.charAt(0)) {
						
						System.out.println("Le caractere ne fait pas partie des entrees acceptables");
						System.out.println("-- Fin de phrase --");
						cptDiese++;
					} else {
					
						System.out.println("Le caractere ne fait pas partie des entrees acceptables");
						System.out.println("La sortie de cette phrase est : " +phraseSortie);
						System.out.println("-- Fin de phrase --");
					}
				}
			}
			
			i++;
		}
		
		System.out.println("Fin de traitement");
	}	
	
	//methode qui implemente la fonction transiter en vue de la determinisation
	public void transiter(Character etat, Character c){
		for (int i = 0; i < this.getTransitions().size(); i++) {
			if(this.getTransitions().get(i).getEtatInit().equals(etat) && this.getTransitions().get(i).getEntree().equals(c)){
				System.out.println("transiter(" + etat + ", " + c + ") = " + this.getTransitions().get(i).getEtatFinal());
			}
		}
	}
	
	public void afficheTransiter(){
		for (int i = 0; i < this.getEtats().size(); i++) {
			for (int j = 0; j < this.getAlphabetEntree().size(); j++) {
				Character etat = new Character(this.getEtats().get(i).getNomEtat());
				Character lettre = new Character(this.getAlphabetEntree().get(j));
				transiter(etat, lettre);
			}
		}
	}
	
	/**
	 * 
	 * Partie exportation en .dot
	 * 
	 */
	public void exportationDot() {
		
		
		
		
		
		
		
	}
}
