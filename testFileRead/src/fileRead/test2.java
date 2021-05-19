package fileRead;
import java.io.FileReader;  

public class test2 {
	
    public static void main(String args[])throws Exception{    
          FileReader fr = new FileReader("D:\\Programmes\\Eclipse\\Eclipse_WorkSpace\\testFileRead\\src\\fileRead\\fichier.txt");    
          int i;    
          char[][] tab = new char [5][5];
          
          /*while((i=fr.read())!=-1) {    
        	  System.out.print((char)i);
        	  
          }
          */
                    
          for(int j = 0; j < tab.length; j++) {
        		for(int k = 0; k < tab[j].length; k++) {
        			i=fr.read();
        			tab[j][k] = (char)i;
        			System.out.print(tab[j][k]);
        		}
        		System.out.println();
        	} 
          fr.close();    
    }        
}




