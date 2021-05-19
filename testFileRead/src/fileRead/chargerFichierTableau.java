package fileRead;

import java.io.FileReader;
import java.util.Scanner;

public class chargerFichierTableau {

	static char[][] chargerTableau (String lienFichier, int xtab, int ytab) throws Exception {
		
		
		FileReader fr = new FileReader(lienFichier);    
        int i;    
        char[][] tab = new char [ytab][xtab];
        
        /*while((i=fr.read())!=-1) {    
      	  System.out.print((char)i);
      	  
        }
        */
                  
        for(int j = 0; j < tab.length; j++) {
      		for(int k = 0; k < tab[j].length; k++) {
      			i=fr.read();
      			if(i=='\n') {System.out.print("retour chariot"); }
      			else{tab[j][k] = (char)i;}
      			System.out.print(tab[j][k]);
      		}
      		System.out.println();
      	} 
        //fr.close();
		return tab;
		
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Saisir lien : ");
		String lienFichier = sc.nextLine();
		
		System.out.print("Saisir xtab : ");
		int x = Integer.parseInt(sc.nextLine());
		
		System.out.print("Saisir ytab : ");
		int y = Integer.parseInt(sc.nextLine());
		
		chargerTableau(lienFichier, x, y);
			
		sc.close();
	}	
}


