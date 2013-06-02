package denis.zoofriends;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import denis.zoofriends.Bird;


public class BirdTest {
	
	private class AbstractBird extends Bird{
		
		public AbstractBird(String name, String favoriteFood, double wingSpan) throws IllegalArgumentException {
		super(name, favoriteFood, wingSpan);
		}

		public String getAnimalType() {
			return "AbstractBird";
		}
	}
	

	@Test
	public void testBirdConstructorSimple() {
		AbstractBird b1 = new AbstractBird("b1", "grains", 0.5);
		assertNotNull(b1);
		assertEquals("b1", b1.getName());
		assertEquals("grains", b1.getFavoriteFood());
		assertEquals(0.5, b1.getWingSpan(), 0);		
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testBirdConstructorNegativeValue() {
		AbstractBird b1 = new AbstractBird("bextra", "grain", -0.5);
		assertTrue(b1 == null);
	}

	@Test
	public void testGetAnimalCharacteristics() {
		AbstractBird b1 = new AbstractBird("New Pelican", "grains", 55);
		//run
		List<String> chars = b1.getAnimalCharacteristics();
		assertEquals(2, chars.size());
		assertTrue(chars.contains("Wingspan: 55.0"));
		assertTrue(chars.contains("Favorite food: grains"));
	}

	@Test
	public void testSetGetWingSpan() {
		AbstractBird b1 = new AbstractBird("New Pelican", "grains", 55);
		//run
		double wingspan;
		wingspan = b1.getWingSpan();
		//test 
		assertEquals(wingspan, 55, 0);
		
		//set and run
		b1.setWingSpan(0.12);
		wingspan = b1.getWingSpan();
		//test
		assertEquals(0.12, wingspan, 0);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetWingSpanNegative() {
		AbstractBird b1 = new AbstractBird("New Pelican", "grains", 55);
		
		//run
		b1.setWingSpan( -12.2);
		assertTrue(b1 == null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetWingSpanZero() {
		AbstractBird b1 = new AbstractBird("New Pelican", "grains", 55);
		
		//run
		b1.setWingSpan( 0);
		assertTrue(b1 == null);
	}

	

}
