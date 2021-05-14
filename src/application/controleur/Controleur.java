package application.controleur;

import java.awt.Button;
import java.net.URL;
import java.util.ResourceBundle;

//import com.sun.javafx.geom.Rectangle;
import com.sun.scenario.effect.impl.state.LinearConvolveKernel;

import application.Main;
import application.modele.Environnement;
import application.modele.Link;
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
	
	private Timeline gameLoop;
	
	static boolean running = true;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		/*SET THE WORD PART*/
		world = new Environnement(640,640,5,5);
		

		
		/*CREA LINK PART*/
		Image imgLink = new Image(linkURL);
		createLink(imgLink);
		moveHandle();
		//GameLoop();
		fillInMap("file:///home/shaina/Documents/DutINFO/Amnesiacor/img/carre-rouge.png");	
		
		/*gameL*/
	}
	
	public void GameLoop(){
		int FPS_MAX = 50;
		int UPS_MAX = 50;
		
		float FTimeRef = 1000/FPS_MAX;//1000ms
		float UTimeRef = 1000/UPS_MAX;//1000ms
		
		float FdeltaTime = 0, UdeltaTime = 0;
		long startT = System.currentTimeMillis();
		
		
		while(running) {
			long currentT = System.currentTimeMillis();
			FdeltaTime += (currentT - startT);
			System.out.println("1:"+ FdeltaTime);
			UdeltaTime += (currentT - startT);
			System.out.println("2:"+ UdeltaTime);
			startT = currentT;
			
			if((UTimeRef <= UdeltaTime)){
				//UPDATE
				update();
				UdeltaTime -= UTimeRef;
			}
			if((FTimeRef <= FdeltaTime)){
				int changeImg = 0;
				/*if(changeImg%250==0) {
					fillInMap("file:///home/shaina/Documents/DutINFO/Amnesiacor/img/carre-vert-fonce.png");//remp les tuilles
				}
				else {
					fillInMap("file:///home/shaina/Documents/DutINFO/Amnesiacor/img/carre-rouge.png");	
				}*/
				changeImg++;
				FdeltaTime -= FTimeRef;
			}
		}
	}
	public void update(){
		/*POSITION PART*/
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