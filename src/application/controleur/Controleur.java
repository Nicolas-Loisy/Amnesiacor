package application.controleur;
//PAS REFACTORIS�
import java.awt.Button;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import application.Main;
import application.modele.Deplacables;
import application.modele.Environnement;
import application.modele.Goblins;
import application.modele.Gvolants;
import application.modele.Link;
import application.tools.BFS;
import application.tools.JsonReader;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Rectangle2D;
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


public class Controleur implements Initializable {
	
	
	
    @FXML
    private javafx.scene.layout.Pane pane;//root
	@FXML
	private BorderPane borderP;
	@FXML
    private TilePane TileMap;//map w/ tuilles

	private Environnement world;
	
	/*handlerScene*/
	private Link link;
	Rectangle linkVue;
	
	/*IMG*/
	private static final String linkURL = "file:img/1.png";

	private static final String imgCaisse = "file:img/caisses.png";
	private static final String goblinVolantURL = "file:img/ChasupaVolant.gif";	
	//GAMELOOP PART

	private static final String goblinTerreURL = "file:img/gumgum.gif";
	private static final String Heart = "file:img/Heart.gif";

	/*GAMELOOP PART*/

	private Timeline gameLoop;
	private int temps;
	
	/*BFS*/
	private BFS myFirstBfs;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		/*SET THE WORD PART*/
		world = new Environnement();
		fillInMap("File:img/zeldaTileset.png");
	
		/*CREA LINK PART*/
		createLink();
		
		//CREA CAISSE
		createDeco();
		
		/*CREA GOBLIN PART*/
		/*myFirstBfs = new BFS(world,link);
		createGoblinView(6,myFirstBfs);*/
		
				
		/*GAMELOOP & MouveHandle*/
		GameLoop();
		gameLoop.play();
		moveHandle();
		
		
	}
	public void GameLoop(){
			gameLoop = new Timeline();
			temps = 0;
			gameLoop.setCycleCount(Timeline.INDEFINITE);
		
			KeyFrame kf = new KeyFrame(

				// on d�finit le FPS (nbre de frame par seconde)

				Duration.seconds(.017), 
				// on d�finit ce qui se passe � chaque frame 
				// c'est un eventHandler d'ou le lambda
					(ev ->{		
						if(temps==100000){//TW: REMPLACER PAR UN SI KEYCODE == ESCAPE OR FUTUR MENUS QUIT
							System.out.println("fini");
							gameLoop.stop();
						}
						else if (temps%45==0){
							update();
						}
						temps++;
					})
				);
		gameLoop.getKeyFrames().add(kf);
	}
	
	public void update(){	
		ListChangeListener<Goblins> listeGoblins = (c ->{
			while (c.next()){
				if (c.wasRemoved()){
					for (Goblins gob : c.getRemoved()){
						pane.getChildren().remove(pane.lookup("#"+gob.getId()));	
					}
				}
			}
		});
		
		/*RAMASSAGE DES MORTS*/
		world.pickUpTheDead();
		world.getListeGoblins().addListener(listeGoblins);
		
		/*POSITION GOBLIN PART*/
		for (Goblins g : world.getListeGoblins()) {
			g.chooseAway();
			pane.lookup("#"+g.getId()).translateXProperty().bind(g.getxProporty());
			pane.lookup("#"+g.getId()).translateYProperty().bind(g.getyProporty());
		}
	}
	
	public void createLink() {
		Image imgLink = new Image(linkURL);//Image(linkURL)
		link = new Link(0, 16, "A", world);//crea link modele
		linkVue = new Rectangle(32, 42); //cr�a link vue
		linkVue.setFill(new ImagePattern(imgLink, 0, 0, 1, 1, true));
		linkVue.setId(link.getId());
		linkVue.translateXProperty().bind(link.getxProporty());
		linkVue.translateYProperty().bind(link.getyProporty());
		pane.getChildren().add(linkVue);//add du link dans la map
	}
	
	/*faire methode random type de goblins*/
	public void createGoblinView(int NumberOfGoblins,BFS bfs){
		Image imgGobTer = new Image(goblinTerreURL);//new Image(goblinTerreURL)
		Image imgGobVol = new Image(goblinVolantURL);

		for (int i = 0; i < NumberOfGoblins; i++) {
			if (pileOUface()) {
				Goblins gob = new Goblins(world, myFirstBfs);
				Rectangle GoblinVue = new Rectangle(32,42);
				GoblinVue.setFill(new ImagePattern(imgGobTer, 0, 0, 1, 1, true));
				GoblinVue.setId(gob.getId());
				world.addGoblins(gob);
				pane.getChildren().add(GoblinVue);
			}
			else{
				Gvolants gob = new Gvolants(96, 176, world, bfs);
				Rectangle GoblinVue = new Rectangle(32,42);
				GoblinVue.setFill(new ImagePattern(imgGobVol, 0, 0, 1, 1, true));
				GoblinVue.setId(gob.getId());
				world.addGoblins(gob);
				pane.getChildren().add(GoblinVue);
				
			}
		}
	}
	
	public boolean pileOUface(){
		Random random = new Random();
		int pf = random.nextInt(2);
		
		if (pf == 0)
			return true;
		else 
			return false;

	}

	public void createDeco() {
		Image imageCaisse = new Image(imgCaisse);
		Deplacables caisse = new Deplacables(100,100);
		Rectangle caisseVue = new Rectangle(32,32);
		caisseVue.setFill(new ImagePattern(imageCaisse, 0, 0, 1, 1, true));
		caisseVue.setId(caisse.getId());
		world.addDecorations(caisse);
		caisseVue.translateXProperty().bind(caisse.getPropertyX());
		caisseVue.translateYProperty().bind(caisse.getPropertyY());
		pane.getChildren().add(caisseVue);

	}

	public void emptyTheMap() {
		for (int i = 0; i < 400; i++) {
	        TileMap.getChildren().clear();
	    }
	}
	
	public void fillInMap(String imgEmp) {
		int [][] tab = null;
		double tile;
		double l,h;//colonnes lignes
		try {
			tab = world.getLand().clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Image img = new Image(imgEmp);
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				tile = tab[i][j];

				l = (tile-1)%5;
				h = (int) Math.ceil(tile/5)-1; //i
				
				ImageView imgv = new ImageView(img);
				Rectangle2D viewportRect = new Rectangle2D(l*32, h*32, 32, 32);
		        imgv.setViewport(viewportRect);
				imgv.setFitWidth(32);
				imgv.setFitHeight(32);
		        TileMap.getChildren().add(imgv);
			}			
	    }	
	}
		
	//Methode avec BorderPane
	public void moveHandle() {
		/*KEY PRESS PART*/
		PressKeyHandle c = new PressKeyHandle(link, world);
		borderP.addEventHandler(KeyEvent.KEY_PRESSED, c);
		
		/*REFRESH POSI PART*/
		linkVue.translateXProperty().bind(link.getxProporty());
		linkVue.translateYProperty().bind(link.getyProporty());
		link.setPersoTab();
	}
	


}