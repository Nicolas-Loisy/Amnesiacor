package application.controleur;

import java.awt.Button;
import java.net.URL;
import java.util.ResourceBundle;

//import com.sun.javafx.geom.Rectangle;
import com.sun.scenario.effect.impl.state.LinearConvolveKernel;

import application.Main;
import application.modele.Environnement;
import application.modele.Link;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.paint.ImagePattern;

public class Controleur implements Initializable {
	
	
	private Environnement word;
	private Link link;
	private Rectangle linkVue;

	
	@FXML
    private TilePane TileMap;

	//handlerScene
	private static final String linkURL = "file:img/1.png";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		Image imgLink = new Image(linkURL);
		
		word = new Environnement(50, 50);
		
		
		
		link = new Link(32, 32, "A");//crea link modele
		
		linkVue = new Rectangle(32, 42); //créa link vue
		
		
		
		linkVue.setId(link.getId());
		linkVue.setFill(new ImagePattern(imgLink, 0, 0, 1, 1, true));
		
		linkVue.translateXProperty().bind(link.getxProporty());
		linkVue.translateYProperty().bind(link.getyProporty());
		
		TileMap.getChildren().add(linkVue);//add du link dans la map

		
		
	}
	
	@FXML
	void moveHandle(MouseEvent event) {
		System.out.println("heree");
		TileMap.getScene().setOnKeyPressed(e->{
			
			if(e.getCode() == KeyCode.Z) {
				link.setY(link.getY()-10);
			}
			else if (e.getCode() == KeyCode.S){
				link.setY(link.getY()+10);
			}
			else if (e.getCode() == KeyCode.D){
				link.setX(link.getX()+10);
			}
			else if (e.getCode() == KeyCode.Q){
				link.setX(link.getX()-10);
			}
			
		});
	}

}
