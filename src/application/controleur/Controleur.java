package application.controleur;

import java.awt.Button;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;


import application.Main;
import application.modele.Deplacables;
import application.modele.Environnement;
import application.modele.Fleche;
import application.modele.Goblins;
import application.modele.Gvolants;
import application.modele.Heart;
import application.modele.Link;
import application.modele.Objets;
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

/*LISEZ MOI !
 * Ne pas oublier d'importer la librairie Json (json-simple-1.1.1.jar dans le projet)
 * 
 */

public class Controleur implements Initializable {

    @FXML
    private javafx.scene.layout.Pane pane;//ROOR
	@FXML
	private BorderPane borderP;
	@FXML
    private TilePane TileMap;//MAP
	private Environnement world;
	
	private Link link;
	Rectangle linkVue;
	
	/*IMG*/
	private static final String linkURL = "file:img/1.png";
	private static final String goblinTerreURL = "file:img/gumgum.gif";
	private static final String goblinVolantURL = "file:img/ChasupaVolant.gif";	

	private static final String imgFlecheURL = "file:img/imgFleche.png";	
	private static final String imgCaisse = "file:img/caisses.png";
	private static final String Heart = "file:img/heart.gif";

	/*GAMELOOP PART*/
	private Timeline gameLoop;
	private int temps;
	
	/*BFS*/
	private BFS myFirstBfs;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		
		
		/*CREATION LINK PART*/
		createLink();
		
		/*SET THE WORD PART*/
		world = new Environnement(link);
		fillInMap("File:img/zeldaTileset.png");
		link.setWorld(world);
		
		/*CREATION OBJETS*/
		createObjet(2,2);
		
		/*CREATION GOBLIN PART*/
		myFirstBfs = new BFS(world,link);
		createGoblinView(5,myFirstBfs);
		
		/*GAMELOOP & MOUVEHANDLE*/
		GameLoop();
		gameLoop.play();
		moveHandle();
		
		/*SET LISTENER PART*/
		initDesListener();
		
		/*PRESENTATION*/
		Intro();
	
	}
	
	public void Intro() {
		System.out.println("------------------------------------ Bienvenue sur le fabuleux mini-jeux AMNESIACOR !----------------------------------------");
		System.out.println("Ce monde est inspir?? d'une histoire fausse."+
		"\n"+
		"Notre h??ros vient tout juste de se r??veiller d'un sommeil de plusieurs d??cennies."+
		"\n"+
		"En sortant du temple des sables qui lui servit de refuge lors de son long sommeil, rien, plus aucun souvenir !"
		+"\n"+
		"Sa m??moire... compl??tement effac??e."+
		"\n"+
		"Partez ?? l'aventure pour chercher des r??ponses et vous battre pour r??tablir le calme sur ce monde obscurci par les t??n??bres !");
		System.out.println("\n"+"Les touches:"+"\n"+"Touches de d??placement : Z/Q/S/D"+
		"\n"+ "Touche Inventaire : 1/2"+
		"\n"+ "Touche Attaque : ESPACE"+
		"\n"+"Touche Prendre : E"+
		"\n"+"Touche L??cher : R");
		System.out.println("\n"+"---INFO GAME---"+"\n"+
		"\n"+"RESOLUTION: 1920x1080"+
		"\n"+"DIM TABLEAU : "+world.GetWidthTabTiles()+"|"+world.GetHeightTabTiles()+
		"\n"+"NB GOBLINS: "+world.getListeGoblins().size());
		System.out.println("\n"+"by SHASHOU & NASHT & VIRNES"+"\n");
	}
	
	public void GameLoop(){
			gameLoop = new Timeline();
			temps = 0;
			gameLoop.setCycleCount(Timeline.INDEFINITE);
			
			KeyFrame kf = new KeyFrame(
				//FPS = 58
				Duration.seconds(.017),
					(ev ->{		
						if (temps%45==0){
							update();
						}
						temps++;
					})
				);
		gameLoop.getKeyFrames().add(kf);
	}
	
	
	public void update(){	
		/*RAMASSAGE DES MORTS*/
		world.pickUpTheDead();
		
		/*POSITION GOBLIN PART*/
		for (Goblins g : world.getListeGoblins()) {
			g.move();
		}
		
		/*GESTION FLECHES*/
		world.deplaceEtSuprFleches(world);
	}
	
	public void createLink() {
		Image imgLink = new Image(linkURL);
		link = new Link(1728, 880, "A", null);//MODELE
		linkVue = new Rectangle(32, 42); //VUE
		linkVue.setFill(new ImagePattern(imgLink, 0, 0, 1, 1, true));
		linkVue.setId(link.getId());
		linkVue.translateXProperty().bind(link.getxProporty());
		linkVue.translateYProperty().bind(link.getyProporty());
		pane.getChildren().add(linkVue);//add du link dans la map
		link.persoTabListener(); //POSI X/Y CASE EN FCT POSI X/Y PIXEL

	}
	
	public void createGoblinView(int NumberOfGoblins,BFS bfs){
		Image imgGobTer = new Image(goblinTerreURL);
		Image imgGobVol = new Image(goblinVolantURL);
		for (int i = 0; i < NumberOfGoblins; i++) {
			if (pileOUface()) {
				Goblins gob = new Goblins(world, myFirstBfs,link,10);
				Rectangle GoblinVue = new Rectangle(32,42);
				GoblinVue.setFill(new ImagePattern(imgGobTer, 0, 0, 1, 1, true));
				GoblinVue.setId(gob.getId());
				world.addGoblins(gob);
				pane.getChildren().add(GoblinVue);
				pane.lookup("#"+gob.getId()).translateXProperty().bind(gob.getxProporty());
				pane.lookup("#"+gob.getId()).translateYProperty().bind(gob.getyProporty());
				gob.persoTabListener();
			}
			else{
				Gvolants gob = new Gvolants(world,myFirstBfs,link,10);
				Rectangle GoblinVue = new Rectangle(32,42);
				GoblinVue.setFill(new ImagePattern(imgGobVol, 0, 0, 1, 1, true));
				GoblinVue.setId(gob.getId());
				world.addGoblins(gob);
				pane.getChildren().add(GoblinVue);
				pane.lookup("#"+gob.getId()).translateXProperty().bind(gob.getxProporty());
				pane.lookup("#"+gob.getId()).translateYProperty().bind(gob.getyProporty());
				gob.persoTabListener();
			}
		}
	}
	
	
	public void createFlechesView(Fleche fleche) {//CREATION FLECHES 
		Image imgFleche = new Image(imgFlecheURL);
			Rectangle FlecheVue = new Rectangle(6,32);
			
			if(fleche.getDirection() == "Right") {
				FlecheVue.setRotate(90);
			}else if(fleche.getDirection() == "Down") {
				FlecheVue.setRotate(180);
			}else if(fleche.getDirection() == "Left") {
				FlecheVue.setRotate(270);
			}			
				FlecheVue.setFill(new ImagePattern(imgFleche, 0, 0, 1, 1, true));
				FlecheVue.setId(fleche.getId());
				pane.getChildren().add(FlecheVue);	
							
				pane.lookup("#"+fleche.getId()).translateXProperty().bind(fleche.getxProporty());
				pane.lookup("#"+fleche.getId()).translateYProperty().bind(fleche.getyProporty());					
	}
	
	public void initDesListener() {
		/*GOBLINS*/
		ListChangeListener<Goblins> listeGoblins = (c ->{
			while (c.next()){
				if (c.wasRemoved()){
					for (Goblins gob : c.getRemoved()){
						pane.getChildren().remove(pane.lookup("#"+gob.getId()));	
					}
				}
			}
		});
		world.getListeGoblins().addListener(listeGoblins);
	
			/*FLECHES*/
			ListChangeListener<Fleche> listeFleche = (c ->{
				while (c.next()){
					if (c.wasRemoved()){
						for (Fleche fleche : c.getRemoved()){
							pane.getChildren().remove(pane.lookup("#"+fleche.getId()));	
						}
					}
					if (c.wasAdded()){
						for (Fleche fleche : c.getAddedSubList()){
							createFlechesView(fleche);	
						}
					}
				}
			});
			world.getListeFleches().addListener(listeFleche);
			
			/*COEURS*/
			ListChangeListener<Objets> objCheck = c ->{
				while(c.next()) {
					if (c.wasRemoved()){
						for (Objets obj: c.getRemoved()) {
							pane.getChildren().remove(pane.lookup("#"+obj.getId()));
						}	
					}	
				}
			};
			world.getListeObject().addListener(objCheck);
		
			/*LinkHEALTH*/
			link.getPvProperty().addListener((obs,old,nouv)->{
				if((int)nouv == 0) {
					gameOver();
					gameLoop.stop();
				}
			});
	}	

	public boolean pileOUface(){
		Random random = new Random();
		int pf = random.nextInt(2);
		
		if (pf == 0)
			return true;
		else 
			return false;
	}

	
	public void createObjet(int nbrHeart, int nbrObjetsDeplacable){
		Image heartIMG = new Image(Heart);
		Image imageCaisse = new Image(imgCaisse);
		
		/*COEURS*/
		for (int i = 0; i < nbrHeart; i++){
			Heart hrt = new Heart(world);
			Rectangle hrtVue = new Rectangle(32,32);
			hrtVue.setFill(new ImagePattern(heartIMG, 0, 0, 1, 1, true));
			hrtVue.setId(hrt.getId());
			world.addObjets(hrt);
			pane.getChildren().add(hrtVue);
			hrtVue.translateXProperty().bind(hrt.getXobjProperty());
			hrtVue.translateYProperty().bind(hrt.getYobjProperty());
		}
		/*DEPLACABLE*/
		for (int i = 0; i < nbrObjetsDeplacable; i++){
			Deplacables obj = new Deplacables(world);
			Rectangle objVue = new Rectangle(32,32);
			objVue.setFill(new ImagePattern(imageCaisse, 0, 0, 1, 1, true));
			objVue.setId(obj.getId());
			world.addObjets(obj);
			objVue.translateXProperty().bind(obj.getXobjProperty());
			objVue.translateYProperty().bind(obj.getYobjProperty());
			pane.getChildren().add(objVue);
		}
		
	}
	
	public boolean gameOver(){
		System.out.println("GAME OVER");
		pane.getChildren().remove(pane.lookup("#"+link.getId()));
		return link.stillAlive();
	}

	public void emptyTheMap() { //NOT USE (UTILE POUR EVOL)
		for (int i = 0; i < world.getHeightTabPix()*world.getWidthTabPix(); i++) {
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
			System.out.println("ERREUR");
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
		
	public void moveHandle() {
		/*KEY PRESS PART*/
		PressKeyHandle c = new PressKeyHandle(link, world,linkVue);
		borderP.addEventHandler(KeyEvent.KEY_PRESSED, c);
	}
	


}