package application.tools;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader {

	public static void main(String[] args) throws Exception {
	        
			
			int [][] tableau = chargerTableau("img/minishMAP.json").clone();
			
			for(int j = 0; j < tableau.length; j++) {
          		for(int k = 0; k < tableau[j].length; k++) {
          			System.out.print(tableau[j][k]+":");
          		}
          		System.out.println();
          	}		        
	}

	
	public static int[][] chargerTableau (String lienFichier) throws Exception {
		
            Path filePath = Paths.get(lienFichier);
            String fileContent = new String(Files.readAllBytes(filePath));
            
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(fileContent);
            JSONArray layers = (JSONArray) root.get("layers");
            JSONArray data = (JSONArray) ((JSONObject) layers.get(0)).get("data");
            
            
            int[][] tab = new int [ Integer.parseInt(root.get("width").toString())][Integer.parseInt(root.get("height").toString())];
            
            /*
            for (int i = 0; i < data.size(); i++) {
                System.out.println(data.get(i));
            }
            */
            
            int i = 0;
            for(int j = 0; j < tab.length; j++) {
          		for(int k = 0; k < tab[j].length; k++) {
          			
          			tab[j][k] =  Integer.parseInt(data.get(i).toString());
          			i++;
          			//System.out.print(tab[j][k]+";");
          		}
          		System.out.println();
          	}
            return tab;
    }		
}
	

