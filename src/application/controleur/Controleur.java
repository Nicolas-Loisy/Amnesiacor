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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.paint.ImagePattern;

public class Controleur implements Initializable {
	
	
	private Environnement world;
	private Link link;
<<<<<<< HEAD
	private Circle linkVue;
	
    @FXML
    private javafx.scene.layout.Pane Pane;
=======
	private Rectangle linkVue;
>>>>>>> 74cef26c10b4de7615966b515aa99a638eefbdc2

	
	@FXML
	private BorderPane BorderP;
	
	@FXML
    private TilePane TileMap;//center du Borderpane

	//handlerScene
	private static final String linkURL = "file:img/1.png";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
<<<<<<< HEAD
		world = new Environnement(640,640,5,5);
		
		
		TileMap.setPrefColumns(20);
		TileMap.setPrefRows(20);
		insertImg("file:///home/shaina/Documents/DutINFO/Amnesiacor/carre-vert-fonce.png");
		

=======
		
		Image imgLink = new Image(linkURL);
		
		word = new Environnement(50, 50);
		
		
		
		link = new Link(32, 32, "A");//crea link modele
		
		linkVue = new Rectangle(32, 42); //créa link vue
>>>>>>> 74cef26c10b4de7615966b515aa99a638eefbdc2
		
		/*CREA LINK PART*/
		link = new Link(16, 16, "A");//crea link modele
		linkVue = new Circle(7); //créa link vue
		
		
		linkVue.setId(link.getId());
		linkVue.setFill(new ImagePattern(imgLink, 0, 0, 1, 1, true));
		
		linkVue.setTranslateX(0);
		linkVue.setTranslateY(0);
		System.out.println("("+linkVue.getTranslateX()+";"+linkVue.getTranslateY()+") vs ("+link.getX()+";"+link.getY()+")");
		
		linkVue.translateXProperty().bind(link.getxProporty());
		linkVue.translateYProperty().bind(link.getyProporty());
		
		Pane.getChildren().add(linkVue);//add du link dans la map
		
		
		/*KEY PRESS PART*/
		moveHandle();
		
		
		
		
		
	}
	
	public void insertImg(String imgEmp) {
		Image img = new Image(imgEmp);
		for (int i = 0; i < 400; i++) {
			ImageView imgv = new ImageView(img);
			imgv.setFitWidth(32);
			imgv.setFitHeight(32);
	        TileMap.getChildren().add(imgv);
	    }
		
	}
	
	//Methode avec BorderPane
	public void moveHandle() {
		/*KEY PRESS PART*/
		BorderP.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.Z) {
					link.setY(link.getY()-32);
					System.out.println("("+linkVue.getTranslateX()+";"+linkVue.getTranslateY()+") vs ("+link.getX()+";"+link.getY()+")");
			}
			else if (e.getCode() == KeyCode.S){
				link.setY(link.getY()+32);
				System.out.println("("+linkVue.getTranslateX()+";"+linkVue.getTranslateY()+") vs ("+link.getX()+";"+link.getY()+")");
			}
			else if (e.getCode() == KeyCode.D){
				link.setX(link.getX()+32);
				System.out.println("("+linkVue.getTranslateX()+";"+linkVue.getTranslateY()+") vs ("+link.getX()+";"+link.getY()+")");
			}
			else if (e.getCode() == KeyCode.Q){
				link.setX(link.getX()-32);
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
