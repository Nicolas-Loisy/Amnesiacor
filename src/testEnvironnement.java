import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.modele.Environnement;
import application.modele.Fleche;
import application.modele.Goblins;
import application.modele.Link;

public class testEnvironnement {
	private Environnement env;
	private Link link;
	


	@Test
	void inMapTest() {
		
		this.env= new Environnement(link);
		
		
		assertFalse(this.env.inMap(-1, 1));
		assertFalse(this.env.inMap(1, -1));
		assertFalse(this.env.inMap(-1, -1));
		assertFalse(this.env.inMap(-1, 0));
		assertFalse(this.env.inMap(env.GetWidthTabTiles(), env.GetHeightTabTiles()));
		assertTrue(this.env.inMap(0, 0));
		assertTrue(this.env.inMap(env.GetWidthTabTiles()-1, env.GetHeightTabTiles()-1));
	}
	
	//Test pour Map Minish
	@Test
	void marcheSurCaseTest() {
		this.env= new Environnement(link);
		assertFalse(this.env.marcheSurCase(-1, 1));
		assertFalse(this.env.marcheSurCase(1, -1));
		assertFalse(this.env.marcheSurCase(-1, -1));
		assertFalse(this.env.marcheSurCase(-1, 0));
		assertFalse(this.env.marcheSurCase(env.GetWidthTabTiles(), env.GetHeightTabTiles()));
		assertTrue(this.env.marcheSurCase(0, 0));
		assertFalse(this.env.marcheSurCase(1,1));		
	}
	
	
	@Test
	void ennemiCloseTest() {
		this.env= new Environnement(link);
		Goblins gob1 = new Goblins(32,32,env, null,link,0);
		this.env.addGoblins(gob1);
		
		
		//Test differentes positions ennemies et test portee
		assertFalse(this.env.ennemiClose(0, 0, 16) != null);
		assertTrue(this.env.ennemiClose(0, 0, 32) != null);
		
		assertFalse(this.env.ennemiClose(32, 64, 16) != null);
		assertTrue(this.env.ennemiClose(32, 64, 32) != null);
		
		assertFalse(this.env.ennemiClose(64, 32, 16) != null);
		assertTrue(this.env.ennemiClose(64, 32, 32) != null);
		
		assertFalse(this.env.ennemiClose(64, 64, 16) != null);
		assertTrue(this.env.ennemiClose(64, 64, 32) != null);
		
		assertFalse(this.env.ennemiClose(0, 32, 16) != null);
		assertTrue(this.env.ennemiClose(0, 32, 32) != null);
		
		assertFalse(this.env.ennemiClose(0, 64, 16) != null);
		assertTrue(this.env.ennemiClose(0, 64, 32) != null);
		
		assertFalse(this.env.ennemiClose(32, 0, 16) != null);
		assertTrue(this.env.ennemiClose(32, 0, 32) != null);
		
		assertFalse(this.env.ennemiClose(64, 0, 16) != null);
		assertTrue(this.env.ennemiClose(64, 0, 32) != null);
		
		assertTrue(this.env.ennemiClose(32, 32, 0) != null);
		
		//test deux ennemies
		Goblins gob2 = new Goblins(64,64,env, null,link,0);
		this.env.addGoblins(gob2);
		
		assertTrue(this.env.ennemiClose(64, 32, 32) != null);
		
	}
	
	
	@Test
	void availablePositionSpawnTest() {
		link = new Link(0, 16, "A", null);
		link.setWorld(env);
		
		this.env= new Environnement(link);
		// case 0:9
		Goblins gob1 = new Goblins(0,8*32+16,env, null,link,0);
		this.env.addGoblins(gob1);
		// case 0:10
		Goblins gob2 = new Goblins(0,9*32+16,env, null,link,0);
		this.env.addGoblins(gob2);
		
		//case libre 
		assertTrue(this.env.availablePositionSpawn(0, -16));
		assertTrue(this.env.availablePositionSpawn(0, 10*32+16));
		assertTrue(this.env.availablePositionSpawn(32, 8*32+16));
		assertTrue(this.env.availablePositionSpawn(3*32, 8*32+16));
		
		
		// cas depasse Map
		assertFalse(this.env.availablePositionSpawn(0, -64));
		assertFalse(this.env.availablePositionSpawn(-64, 0));
		assertFalse(this.env.availablePositionSpawn(-64, -64));
		assertFalse(this.env.availablePositionSpawn(0, 30000));
		assertFalse(this.env.availablePositionSpawn(30000, 0));
		assertFalse(this.env.availablePositionSpawn(30000, 30000));
		
		// cas case occupe par monstre ou objet ou perso
		assertFalse(this.env.availablePositionSpawn(-8, 16));
		assertFalse(this.env.availablePositionSpawn(0, 16));
		assertFalse(this.env.availablePositionSpawn(0, 8*32+16));
		assertFalse(this.env.availablePositionSpawn(0, 9*32+16));
		
				
	}
	
	@Test
	void availablePositionWalkTest() {
		link = new Link(0, 16, "A", null);
		link.setWorld(env);
		
		this.env= new Environnement(link);
		// case 0:9
		Goblins gob1 = new Goblins(0,8*32+16,env, null,link,0);
		this.env.addGoblins(gob1);
		// case 0:10
		Goblins gob2 = new Goblins(0,9*32+16,env, null,link,0);
		this.env.addGoblins(gob2);
		
		//case libre 
		assertTrue(this.env.availablePositionWalk(0, -16));
		assertTrue(this.env.availablePositionWalk(0, 10*32+16));
		assertTrue(this.env.availablePositionWalk(32, 8*32+16));
		assertTrue(this.env.availablePositionWalk(3*32, 8*32+16));
		
		
		// cas depasse Map
		assertFalse(this.env.availablePositionWalk(0, -64));
		assertFalse(this.env.availablePositionWalk(-64, 0));
		assertFalse(this.env.availablePositionWalk(-64, -64));
		assertFalse(this.env.availablePositionWalk(0, 30000));
		assertFalse(this.env.availablePositionWalk(30000, 0));
		assertFalse(this.env.availablePositionWalk(30000, 30000));
		
		// cas case occupe par monstre ou objet ou perso
		assertFalse(this.env.availablePositionWalk(-8, 16));
		
		assertFalse(this.env.availablePositionWalk(0, 8*32+16));
		assertFalse(this.env.availablePositionWalk(0, 9*32+16));
		
				
	}
}
