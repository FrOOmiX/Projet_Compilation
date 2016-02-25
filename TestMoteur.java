import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class TestMoteur {

	public static void main(String[] args) throws IOException {
		
		// Creation moteur de base
		Moteur moteur = null;
		
		if (verificationFichier("ND03.descr")) {
			
			moteur = new Moteur("ND03.descr");
			
			System.out.println("----- Moteur de base -----\n");
			moteur.afficherCommentaire();
			moteur.afficheMetaChar();
			moteur.afficheAlphabetEntree();
			moteur.afficheEtatInit();
			moteur.afficheNombreEtat();
			moteur.setEtats();
			moteur.afficherEtats();
			moteur.afficheEtatsAcceptants();
			moteur.afficheAlphabetSortie();
			moteur.afficheTransitions();
		}
		
		/**
		 * Traitement des entrees :
		 * 
		 * Seulement fonctionnel pour les fichier C0, S0, S1, S2, S4 et Mini
		 */
		//moteur.traitementEntrees("abbbbbbbbbcaaabccccccc###");
		
		
		// Determinisation
		System.out.println("\nDeterminisation : ");
		moteur.determinisation();
		
		// Exportation .descr & .dot
		Moteur determinise = null;
		
		if (verificationFichier("ExportationDescr.descr")) {
			
			determinise = new Moteur("ExportationDescr.descr");
			
			System.out.println("\n----- Moteur determinise -----\n");
			determinise.afficherCommentaire();
			determinise.afficheMetaChar();
			determinise.afficheAlphabetEntree();
			determinise.afficheEtatInit();
			determinise.afficheNombreEtat();
			determinise.setEtats();
			determinise.afficherEtats();
			determinise.afficheEtatsAcceptants();
			determinise.afficheAlphabetSortie();
			determinise.afficheTransitions();
			
			determinise.exportationDot();
		}
	}
	
	
	/**
	 * 
	 * Methode de verification du format du fichier .descr.
	 * 
	 * Utilisation de la class Pattern qui permet le verification de chaque ligne du fichier
	 * suivant la premiere de la ligne. Si la ligne correspond à l'un des patterns ci-dessous alors la fonction renvoie true sinon false.
	 * 
	 * @return true si la ligne match, sinon false.
	 */
	public static boolean verificationFichier(String nomFichier) throws IOException {

		boolean fichierOk = false;

		try {

			String ligne;
			BufferedReader str;
			str = new BufferedReader(new FileReader(nomFichier));
			ligne = str.readLine();
			String meta="#";
			boolean erreur =false;
			
			while (ligne != null && erreur==false) {
				
				if(Pattern.matches("C[\\s]\'.*\'", ligne) 
				|| Pattern.matches("C[\\s]\".*\"", ligne) 
				|| Pattern.matches("M[\\s]\'"+meta+"\'", ligne) 
				|| Pattern.matches("V[\\s]\"[\\p{Lower}\\p{Digit}]*\"", ligne) 
				|| Pattern.matches("O[\\s]\"[\\p{Alpha}\\p{Digit}]*\"", ligne) 
				|| Pattern.matches("E[\\s][\\p{Digit}]*", ligne) 
				|| Pattern.matches("I[[\\s][\\p{Digit}\\p{Alpha}]]*", ligne) 
				|| Pattern.matches("F[[\\s][\\p{Digit}]]*", ligne) 
				|| Pattern.matches("T[\\s][\\p{Digit}\\p{Lower}]*[\\s]\'[\\p{Digit}\\p{Lower}]*\'[\\s][\\p{Digit}\\p{Lower}]*[\\s]\'[\\p{Alpha}\\p{Digit}]*\'", ligne)
				|| Pattern.matches("T[\\s][\\p{Digit}\\p{Lower}]*[\\s]\'[\\p{Digit}\\p{Lower}]*\'[\\s][\\p{Digit}\\p{Lower}]*[\\s]\'#\'", ligne) 
				|| Pattern.matches("T[\\s][\\p{Digit}\\p{Lower}]*[\\s]\'[\\p{Digit}\\p{Lower}]*\'[\\s][\\p{Digit}\\p{Lower}]*", ligne)
				|| Pattern.matches("T[\\s][\\p{Digit}\\p{Lower}]*[\\s]\'"+meta+"\'[\\s][\\p{Digit}\\p{Lower}]*[\\s]\'[\\p{Alpha}\\p{Digit}]*\'", ligne)
				|| Pattern.matches("T[\\s][\\p{Digit}\\p{Lower}]*[\\s]\'"+meta+"\'[\\s][\\p{Digit}\\p{Lower}]*[\\s]\'#\'", ligne) 
				|| Pattern.matches("T[\\s][\\p{Digit}\\p{Lower}]*[\\s]\'"+meta+"\'[\\s][\\p{Digit}\\p{Lower}]*", ligne)
				|| Pattern.matches("T[\\s][\\p{Digit}\\p{Lower}]*[\\s]\'#\'[\\s][\\p{Digit}\\p{Lower}]*[\\s]\'[\\p{Alpha}\\p{Digit}]*\'", ligne)
				|| Pattern.matches("T[\\s][\\p{Digit}\\p{Lower}]*[\\s]\'#\'[\\s][\\p{Digit}\\p{Lower}]*[\\s]\'#\'", ligne) 
				|| Pattern.matches("T[\\s][\\p{Digit}\\p{Lower}]*[\\s]\'#\'[\\s][\\p{Digit}\\p{Lower}]*", ligne)
				){
					fichierOk=true;
				} else {
					
					System.out.println("Erreur de syntaxe : "+ligne+"\nmerci de respecter le format .descr.");
					erreur=true;
					fichierOk=false;
				}	//else
				
				ligne = str.readLine();
			}	//while
			
			str.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}	//catch
		
		
		return fichierOk;
	}	//verificationFichier
}
