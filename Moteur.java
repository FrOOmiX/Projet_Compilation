import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Moteur {

	private char meta; 										// meta caractere pour arreter la saisie
	private String commentaire;								// commentaires
	private ArrayList<String> alphabetEntree; 			// liste du vocabulaire d'entree
	private ArrayList<String> sorties; 					// liste du vocabulaire de sortie
	private char nbEtats; 									// nombre d'etats dans l'AEF
	private ArrayList<String> etatInit; 					// liste des etats init de l'AEF
	private ArrayList<String> etatsAcceptants; 			// liste des etats acceptant
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
			this.etatInit = new ArrayList<String>();
			etatInit.add("0");
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
					this.alphabetEntree = new ArrayList<String>();

					while (i < ligne.length()) {

						if (ligne.charAt(i) != ' ' && ligne.charAt(i) != '"'
								&& i > 0) {

							alphabetEntree.add(String.valueOf(ligne.charAt(i)));

						}
						i++;
					}
				}// If V

				else if (ligne.charAt(0) == 'O' && i < ligne.length()) {
					this.sorties = new ArrayList<String>();

					while (i < ligne.length()) {

						if (ligne.charAt(i) != ' ' && ligne.charAt(i) != '"'
								&& i > 0) {

							sorties.add(String.valueOf(ligne.charAt(i)));

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
					this.etatInit = new ArrayList<String>();
					while (i < ligne.length()) {

						if (ligne.charAt(i) != ' ' && ligne.charAt(i) != '"'
								&& i > 0) {

							etatInit.add(String.valueOf(ligne.charAt(i)));

						}
						i++;
					}
				}// If I

				else if (ligne.charAt(0) == 'F' && i < ligne.length()) {
					this.etatsAcceptants = new ArrayList<String>();
					while (i < ligne.length()) {

						if (ligne.charAt(i) != ' ' && i > 0) {

							etatsAcceptants.add(String.valueOf(ligne.charAt(i)));

						}
						i++;
					}
				}// If F
				
				else if (ligne.charAt(0) == 'T' && i < ligne.length()) {
								String s [] = ligne.split("\\s");
								if(s.length == 4){
									Transition tr = new Transition(s[1],s[2].substring(1, 2),s[3]);
									transitions.add(tr);
								}
								else{
									Transition tr = new Transition(s[1],s[2].substring(1, 2),s[3],s[4].substring(1, 2));
									transitions.add(tr);
								}
								ligne = str.readLine();
								
					
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
		
			if(this.getMeta() =='#'){
				
				System.out.println("Meta caractere : " + this.getMeta());
				
			} else {
				
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
	
	public void definirEtats() {
		
		this.etats = new ArrayList<Etat>();
		
		if (this.getNbEtats() != 0) {
			int i = this.getNbEtats() - '0' - 1;
			while (i >= 0) {
				
				if (i != this.getNbEtats() - '0' - 1) {
					
					Etat e = new Etat((char) (i + '0'));
					this.getEtats().add(e);
					
				} else {
					
					Etat e = new Etat((char) (i + '0'));
					this.getEtats().add(e);
				}
				i--;
			}
		}	
	}
	
	public void afficherEtats(){
		String str = "Les etats sont : ";
		for (int i = 0; i < this.getEtats().size(); i++) {
			str += this.getEtats().get(i).getNomEtat();
		}
		System.out.println(str);
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
	
	public void afficheTransitions() {
		
		try{
			String str ="";
			str += this.getTransitions().get(0).getEtatInit()+" ";
			str += this.getTransitions().get(0).getEntree()+" ";
			str += this.getTransitions().get(0).getEtatFinal()+" ";
			str += this.getTransitions().get(0).getSortie()+" ";
			for (int i = 1; i < this.getTransitions().size(); i++) {
				str += "\n";
				str += this.getTransitions().get(i).getEtatInit()+" ";
				str += this.getTransitions().get(i).getEntree()+" ";
				str += this.getTransitions().get(i).getEtatFinal()+" ";
				str += this.getTransitions().get(i).getSortie()+" ";
			}
			System.out.println("Transition(s) : \n" + str);
		}catch(IndexOutOfBoundsException e){
			System.out.println("Il n'y a pas de transitions dans ce .descr.");
		}
	}

	// Ensemble des getters sur les variables d'instance du moteur AEF
	public String getCommentaire() { return this.commentaire; }
	public ArrayList<String> getAlphabetEntree() { return this.alphabetEntree; }
	public char getMeta() {	return this.meta; }
	public ArrayList<String> getSorties() { return this.sorties; }
	public char getNbEtats() { return this.nbEtats; }
	public ArrayList<Etat> getEtats() { return this.etats; }
	public ArrayList<String> getEtatInit() {	return this.etatInit; }
	public ArrayList<String> getEtatsAcceptants() { return this.etatsAcceptants; }
	public ArrayList<Transition> getTransitions() { return this.transitions; }
	
	
	/**
	 * 
	 * Partie Traitement des entrees
	 * 
	 */
	public void traitementEntrees(String entrees) {
		
		int i = 0, j = 0, cptDiese = 0;
		String cEntree, etatCourant = String.valueOf(this.getEtatInit().get(0));
		String phraseSortie = "";
		
		System.out.println("\nTraitement des phrases lues :");
		
		while ((i < entrees.length()) && (cptDiese < 1)) {
			
			// On recupere la prochaine entree
			cEntree = String.valueOf(entrees.charAt(i));
			
			if (cEntree == "#" && i == 0) {
				
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
						if ((this.getTransitions().get(j).getEntree() == String.valueOf(cEntree)) && (this.getTransitions().get(j).getEtatInit() == String.valueOf(etatCourant))) {
							
							etatCourant = this.getTransitions().get(j).getEtatFinal();
							System.out.print("Etat Courant : " +this.getTransitions().get(j).getEtatInit()+ ", ");
							System.out.print("Entree : " +cEntree);
							
							// S'il existe une sortie
							if ((this.getTransitions().get(j).getSortie() != "#")) {
								
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
				} else if ((cEntree == " ") || (cEntree == "\n")) {
					
					// S'il s'agit du premier caractere
					if (cEntree == String.valueOf(entrees.charAt(0))) {
						
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
					etatCourant = String.valueOf(this.getEtatInit().get(0));
					
				// Si l'on arrive a la fin des phrases
				} else if (cEntree == "#") {
					
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
					
					if (cEntree == String.valueOf(entrees.charAt(0))) {
						
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
	/*public void transiter(Character etat, Character c){
		for (int i = 0; i < this.getTransitions().size(); i++) {
			if(this.getTransitions().get(i).getEtatInit().equals(etat) && this.getTransitions().get(i).getEntree().equals(c)){
				System.out.println("transiter(" + etat + ", " + c + ") = " + this.getTransitions().get(i).getEtatFinal());
			}
		}
	}*/
	
	
	/**
	 * 
	 * Partie determinisation
	 *
	 */
	public ArrayList<Etat> transiter(Etat etat, String string) {
		
		ArrayList<Etat> e = new ArrayList<Etat>();
		
		for (int i = 0; i < this.getTransitions().size(); i++) {
			
			if (this.getTransitions().get(i).getEtatInit().equals(etat.getNomEtat()) && this.getTransitions().get(i).getEntree().equals(string)) {
				
				Etat state = new Etat(this.getTransitions().get(i).getEtatFinal());
				e.add(state);
				//System.out.println("transiter(" + etat.getNomEtat() + ", " + c + ") = " + this.getTransitions().get(i).getEtatFinal());
			}
		}
		
		return e;
	}
	
	/*public void afficheTransiter(){
		for (int i = 0; i < this.getEtats().size(); i++) {
			for (int j = 0; j < this.getAlphabetEntree().size(); j++) {
				Character etat = new Character(this.getEtats().get(i).getNomEtat());
				Character lettre = new Character(this.getAlphabetEntree().get(j));
				transiter(etat, lettre);
			}
		}
	}*/
	public void afficheTransiter(ArrayList<Etat> e) {
		
		for (int i = 0; i < e.size(); i++) {
			
			System.out.println("transiter = " + e.get(i).getNomEtat());
		}
	}
	
	//methode qui implemente le calcul des lambda fermeture
	public ArrayList<Etat> calculLambdaFermeture(ArrayList<Etat> T) { //etats resultant de transiter
		
		ArrayList<Etat> F=new ArrayList<Etat>();
		ArrayList<Etat> p=new ArrayList<Etat>();
		Etat e = new Etat(null);

		for (Etat etat : T) {
			
			if (p.isEmpty()) {
				p.add(etat);
			} else {
				if (!p.contains(etat))
					p.add(etat);
			}
		}
		
		int i=0;
		
		while (!p.isEmpty()) {
			
			if (!F.contains(p.get(i)))
				F.add(p.get(i));
			
			for (Transition t: this.getTransitions()) {
				
				if (t.getEtatInit() == p.get(i).getNomEtat() && t.getEntree()=="#") {
					
					if (!p.contains(t.getSortie()))
						e.setNomEtat(t.getSortie());
						p.add(e);
				}
			}
			
			p.remove(i);
		}
		
		return F;
	}
	
	public void afficheLambdaFermeture(ArrayList<Etat> e) {
		
		for (int i = 0; i < e.size(); i++) {
			
			System.out.println(e.get(i).getNomEtat());
		}
	}
	
	public void determinisation() {
		
		ArrayList<Etat> superEtats = new ArrayList<Etat>();
		
		for (int i = 0; i < this.getEtats().size(); i++) {
			
			for (int j = 0; j < this.getAlphabetEntree().size(); j++) {
				
				superEtats = this.transiter(this.getEtats().get(i), this.getAlphabetEntree().get(j));
			}
		}
		
		// Non fini...
	}
	
	
	/**
	 * 
	 * Partie exportation en .dot
	 * 
	 * 
	 */
	public void exportationDot() throws IOException {
		
		File f = new File("Exportation.dot");
		FileWriter fw = new FileWriter(f);
		
		// Squelette du fichier .dot
		String squelette = "digraph G {\n\n";
		
		// Ecriture du squelette
		fw.write(squelette);
		
		// Ecriture etat initial et labels
		fw.write("\t\"\" -> " +this.getEtatInit().get(0));
		fw.write("\n\t" +this.getEtatInit().get(0)+ " -> " +this.getTransitions().get(0).getEtatFinal());
		fw.write(" [label=\"" +this.getTransitions().get(0).getEntree()+ "/" +this.getTransitions().get(0).getSortie()+ "\"]");
		
		// Ecriture autres etats et labels
		for (int i = 1; i < this.getTransitions().size(); i++) {
			
			fw.write("\n\t" +this.getTransitions().get(i).getEtatInit()+ " -> " +this.getTransitions().get(i).getEtatFinal());
			fw.write(" [label=\"" +this.getTransitions().get(i).getEntree()+ "/" +this.getTransitions().get(i).getSortie()+ "\"]");
		}
		
		// Marquage etat initial
		fw.write("\n\t\"\" [shape=none]");
		
		// Marquage etats acceptants
		for (int i = 0; i < this.getEtatsAcceptants().size(); i++) {
			
			fw.write("\n\t" +this.getEtatsAcceptants().get(i)+ " [shape=doublecircle]");
		}
		
		// Fermeture fichier
		fw.write("\n}");
		fw.close();
	}
}
