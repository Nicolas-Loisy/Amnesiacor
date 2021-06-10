import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.modele.Environnement;

public class testEnvironnement {
	private Environnement env;
	


	@Test
	void inMapTest() {
		this.env= new Environnement();
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
		this.env= new Environnement();
		assertFalse(this.env.marcheSurCase(-1, 1));
		assertFalse(this.env.marcheSurCase(1, -1));
		assertFalse(this.env.marcheSurCase(-1, -1));
		assertFalse(this.env.marcheSurCase(-1, 0));
		assertFalse(this.env.marcheSurCase(env.GetWidthTabTiles(), env.GetHeightTabTiles()));
		assertTrue(this.env.marcheSurCase(0, 0));
		assertFalse(this.env.marcheSurCase(1,1));
		
		
		
	}

}
