import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class TestMoteur {

	public static void main(String[] args) throws IOException {
		
		Moteur moteur = null;
		
		if(verificationFichier("S4.descr")) {
			
			moteur = new Moteur("S4.descr");
			moteur.afficherCommentaire();
			moteur.afficheMetaChar();
			moteur.afficheAlphabetEntree();
			moteur.afficheEtatInit();
			moteur.afficheNombreEtat();
			moteur.afficherEtats();
			moteur.afficheEtatsAcceptants();
			moteur.afficheAlphabetSortie();
			moteur.afficheTransitions();
		}
		
		
		// Traitement des entrees
		//moteur.traitementEntrees("abab###");
		
		//test de la fonction transiter sur un fichier testTransiter.descr
		//System.out.println("\nTest de la fonction transiter :");
		//moteur.afficheTransiter();
		
		// Exportation en .dot
		moteur.exportationDot();
	}
	
	public static boolean verificationFichier(String nomFichier) throws IOException{
		boolean fichierOk=false;
		try {
			String ligne;
			BufferedReader str;
			str = new BufferedReader(new FileReader(nomFichier));
			ligne = str.readLine();
			String meta="¬µ";
			boolean erreur =false;
			while(ligne != null && erreur==false){
				if(Pattern.matches("C[\\s]\'[[\\p{Alnum}]*[\\s][\\p{Punct}]*[\\s]]*\'", ligne) 
				|| Pattern.matches("M[\\s]"+meta, ligne) || Pattern.matches("V[\\s]\"[\\p{Lower}\\p{Digit}]*\"", ligne) 
				|| Pattern.matches("O[\\s]\"[\\p{Alpha}\\p{Digit}]*\"", ligne) 
				|| Pattern.matches("E[\\s][\\p{Digit}]", ligne) 
				|| Pattern.matches("I[[\\s][\\p{Digit}\\p{Alpha}]]*", ligne) 
				|| Pattern.matches("F[[\\s][\\p{Digit}]]*", ligne) 
				|| Pattern.matches("T[\\s][\\p{Digit}\\p{Lower}][\\s]\'[\\p{Digit}\\p{Lower}]\'[\\s][\\p{Digit}\\p{Lower}][\\s]\'[\\p{Alpha}\\p{Digit}]\'", ligne)
				|| Pattern.matches("T[\\s][\\p{Digit}\\p{Lower}][\\s]\'[\\p{Digit}\\p{Lower}]\'[\\s][\\p{Digit}\\p{Lower}][\\s]\'#\'", ligne) 
				|| Pattern.matches("T[\\s][\\p{Digit}\\p{Lower}][\\s]\'[\\p{Digit}\\p{Lower}]\'[\\s][\\p{Digit}\\p{Lower}]", ligne)
				|| Pattern.matches("T[\\s][\\p{Digit}\\p{Lower}][\\s]\'"+meta+"\'[\\s][\\p{Digit}\\p{Lower}][\\s]\'[\\p{Alpha}\\p{Digit}]\'", ligne)
				|| Pattern.matches("T[\\s][\\p{Digit}\\p{Lower}][\\s]\'"+meta+"\'[\\s][\\p{Digit}\\p{Lower}][\\s]\'#\'", ligne) 
				|| Pattern.matches("T[\\s][\\p{Digit}\\p{Lower}][\\s]\'"+meta+"\'[\\s][\\p{Digit}\\p{Lower}]", ligne)
				|| Pattern.matches("T[\\s][\\p{Digit}\\p{Lower}][\\s]\'#\'[\\s][\\p{Digit}\\p{Lower}][\\s]\'[\\p{Alpha}\\p{Digit}]\'", ligne)
				|| Pattern.matches("T[\\s][\\p{Digit}\\p{Lower}][\\s]\'#\'[\\s][\\p{Digit}\\p{Lower}][\\s]\'#\'", ligne) 
				|| Pattern.matches("T[\\s][\\p{Digit}\\p{Lower}][\\s]\'#\'[\\s][\\p{Digit}\\p{Lower}]", ligne)
				){
					fichierOk=true;
				}
				else{
					System.out.println("Erreur de syntaxe : "+ligne+"\nmerci de respecter le format .descr.");
					erreur=true;
					fichierOk=false;
				}
				ligne = str.readLine();
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fichierOk;
		
	
	}
	
}
