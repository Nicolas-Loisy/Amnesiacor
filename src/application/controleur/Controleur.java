package application.controleur;

import java.awt.Button;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.geom.Rectangle;
import com.sun.scenario.effect.impl.state.LinearConvolveKernel;

import application.Main;
import application.modele.Environnement;
import application.modele.Link;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Controleur implements Initializable {
	
	
	private Environnement word;
	private Link link;
	private Circle linkVue;
	
	@FXML
	private BorderPane BorderP;
	@FXML
    private TilePane TileMap;//center du Borderpane

	//handlerScene

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		word = new Environnement(10,10);
		
		
		
		insertImg("file:///home/shaina/Documents/DutINFO/Amnesiacor/carre-vert-fonce.png");
		
		/*CREA LINK PART*/
		link = new Link(1, 1, "A");//crea link modele
		linkVue = new Circle(7); //créa link vue
		
		
		linkVue.setId(link.getId());
		linkVue.setFill(Color.BLACK);
		
		linkVue.setTranslateX(400);
		linkVue.setTranslateY(0);
		System.out.println("("+linkVue.getTranslateX()+";"+linkVue.getTranslateY()+") vs ("+link.getX()+";"+link.getY()+")");
		
		/*linkVue.translateXProperty().bind(link.getxProporty());
		linkVue.translateYProperty().bind(link.getyProporty());*/
		
		TileMap.getChildren().add(linkVue);//add du link dans la map
		
		
		/*KEY PRESS PART*/
		moveHandle();
		
		
		
		
		
	}
	
	public void insertImg(String imgEmp) {
		Image img = new Image(imgEmp);
		for (int i = 0; i < 64; i++) {
			ImageView imgv = new ImageView(img);
			imgv.setFitWidth(49);
			imgv.setFitHeight(49);
	        TileMap.getChildren().add(imgv);
	    }
		//TileMap.setPrefColumns(10);

		//TileMap.setOrientation(Orientation.HORIZONTAL);
	}
	
	//Methode avec BorderPane
	public void moveHandle() {
		/*KEY PRESS PART*/
		BorderP.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.Z) {
					link.setY(link.getY()-50);
					System.out.println("("+linkVue.getTranslateX()+";"+linkVue.getTranslateY()+") vs ("+link.getX()+";"+link.getY()+")");
			}
			else if (e.getCode() == KeyCode.S){
				link.setY(link.getY()+50);
				System.out.println("("+linkVue.getTranslateX()+";"+linkVue.getTranslateY()+") vs ("+link.getX()+";"+link.getY()+")");
			}
			else if (e.getCode() == KeyCode.D){
				link.setX(link.getX()+50);
				System.out.println("("+linkVue.getTranslateX()+";"+linkVue.getTranslateY()+") vs ("+link.getX()+";"+link.getY()+")");
			}
			else if (e.getCode() == KeyCode.Q){
				link.setX(link.getX()-50);
				System.out.println("("+linkVue.getTranslateX()+";"+linkVue.getTranslateY()+") vs ("+link.getX()+";"+link.getY()+")");
			}
		});
	}
	
	//Methode sans BorderPane 
	/*@FXML
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
	}*/

}
