package application.controleur;

import java.awt.Button;
import java.net.URL;
import java.util.ResourceBundle;

//import com.sun.javafx.geom.Rectangle;
import com.sun.scenario.effect.impl.state.LinearConvolveKernel;

import application.Main;
import application.modele.Environnement;
import application.modele.Link;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.paint.ImagePattern;

public class Controleur implements Initializable {
	
	
	private Environnement world;
	
	
    @FXML
    private javafx.scene.layout.Pane Pane;//root
	@FXML
	private BorderPane BorderP;
	@FXML
    private TilePane TileMap;//map w/ tuilles

	//handlerScene
	private static final String linkURL = "file:img/1.png";
	private Link link;
	private Rectangle linkVue;
	
	//GAMELOOP PART
	private Timeline gameLoop;
	private int temps;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		/*SET THE WORD PART*/
		world = new Environnement(640,640,5,5);
		

		
		/*CREA LINK PART*/
		Image imgLink = new Image(linkURL);
		createLink(imgLink);
		GameLoop();
		gameLoop.play();	
		//fillInMap("file:///home/shaina/Documents/DutINFO/Amnesiacor/img/carre-rouge.png");	
		
		/*gameL*/
	}
	
	public void GameLoop(){
		
			gameLoop = new Timeline();
			temps = 0;
			gameLoop.setCycleCount(Timeline.INDEFINITE);
		
			KeyFrame kf = new KeyFrame(
				// on définit le FPS (nbre de frame par seconde)
				Duration.seconds(.030), 
				// on définit ce qui se passe à chaque frame 
				// c'est un eventHandler d'ou le lambda
				(ev ->{		
					if(temps==5000){
					System.out.println("fini");
					gameLoop.stop();
					}
					else if (temps%5==0){
						System.out.println("un tour");
						update();
						emptyTheMap();
						fillInMap("file:///home/shaina/Documents/DutINFO/Amnesiacor/img/carre-vert-fonce.png");
					}
					else {
						emptyTheMap();
						fillInMap("file:///home/shaina/Documents/DutINFO/Amnesiacor/img/carre-rouge.png");
						
						
					}
					temps++;
					})
				);
		gameLoop.getKeyFrames().add(kf);
		/*int FPS = 1;
		float FPS_ref = (1000/FPS);//when you ref
		long currentTime = System.currentTimeMillis();
		System.out.println(currentTime);
		float DeltaTime = 0;
		long StartTime = System.currentTimeMillis();
		while(true){
			DeltaTime += (System.currentTimeMillis()-StartTime);
			StartTime = System.currentTimeMillis();
			if(DeltaTime < FPS_ref) {
				System.out.println("UN TOUR");
				DeltaTime -= FPS_ref;
			}
			else {
				System.out.println("stop");
			}
			
		}
		*/
	}
	public void update(){
		/*POSITION PART*/
		moveHandle();
		linkVue.translateXProperty().bind(link.getxProporty());
		linkVue.translateYProperty().bind(link.getyProporty());
		
	}
	
	public void createLink(Image imageLink) {
		link = new Link(32, 32, "A");//crea link modele
		linkVue = new Rectangle(32, 42); //créa link vue
		linkVue.setFill(new ImagePattern(imageLink, 0, 0, 1, 1, true));
		linkVue.setId(link.getId());
		Pane.getChildren().add(linkVue);//add du link dans la map
		
	}
	
	public void fillInMap(String imgEmp) {//remplacer par fct nico
		Image img = new Image(imgEmp);
		for (int i = 0; i < 400; i++) {
			ImageView imgv = new ImageView(img);
			imgv.setFitWidth(32);
			imgv.setFitHeight(32);
	        TileMap.getChildren().add(imgv);
	    }
		TileMap.setPrefColumns(20);
		TileMap.setPrefRows(20);
	}
	public void emptyTheMap() {
		for (int i = 0; i < 400; i++) {
	        TileMap.getChildren().clear();
	    }
	}
	
	//Methode avec BorderPane
	public void moveHandle() {
		String direction;
		/*KEY PRESS PART*/
		BorderP.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.Z) {
				link.move("Top");
			}
			else if (e.getCode() == KeyCode.S){
				link.move("Down");
			}
			else if (e.getCode() == KeyCode.D){
				link.move("Right");
			}
			else if (e.getCode() == KeyCode.Q){
				link.move("Left");
			}
		});
	}
	

}