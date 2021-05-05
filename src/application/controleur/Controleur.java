package application.controleur;

import java.awt.Button;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.geom.Rectangle;

import application.Main;
import application.modele.Environnement;
import application.modele.Link;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Controleur implements Initializable {
	
	
	private Environnement word;
	private Link link;
	private Circle linkVue;

	
	@FXML
    private TilePane TileMap;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		word = new Environnement(50, 50);
		

		
		
		link = new Link(15, 15, "A");//crea link modele
		
		linkVue = new Circle(10); //créa link vue
		
		
		
		linkVue.setId(link.getId());
		linkVue.setFill(Color.BLACK);
		
		linkVue.translateXProperty().bind(link.getxProporty());
		linkVue.translateYProperty().bind(link.getyProporty());
		
		TileMap.getChildren().add(linkVue);//add du link dans la map
		
		TileMap.setOnMousePressed(e -> System.out.println("hello"));
		
		
		
		TileMap.setOnKeyPressed(e -> { 
			
			System.out.println("LOL");
			
			
			if(e.getCode() == KeyCode.Z) {
				System.out.println("Z");
				link.setY(link.getY()-1);
			}
			else if (e.getCode() == KeyCode.S){
				System.out.println("S");
				link.setY(link.getY()+1);
			}
			else if (e.getCode() == KeyCode.D){
				System.out.println("D");
				link.setX(link.getX()+1);
			}
			else if (e.getCode() == KeyCode.Q){
				System.out.println("Q");
				link.setX(link.getX()-1);
			}
			
			
		});
		
		System.out.println(TileMap.getScene()+ "lol");
		
	}
	
	public void creaLink(Link link){
		Rectangle r = new Rectangle();
	}

}
