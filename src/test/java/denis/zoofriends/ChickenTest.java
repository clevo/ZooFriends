package denis.zoofriends;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import denis.zoofriends.Chicken;

public class ChickenTest {

	@Test
	public void testBirdConstructorSimple() {
		Chicken c = new Chicken("chiken one", "grains", 0.5, true);
		assertNotNull(c);
		assertEquals("chiken one", c.getName());
		assertEquals("grains", c.getFavoriteFood());
		assertEquals(0.5, c.getWingSpan(), 0);
		assertTrue(c.getIsBoiler());
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testBirdConstructorNegativeValue() {
		Chicken c = new Chicken("bextra", "grain", -0.5, true);
		assertTrue(c == null);
	}

	@Test
	public void testGetAnimalCharacteristicsBoiler() {
		Chicken c = new Chicken("Chiken small", "grains", 55, true);
		//run
		List<String> chars = c.getAnimalCharacteristics();
		assertEquals(3, chars.size());
		assertTrue(chars.contains("Wingspan: 55.0"));
		assertTrue(chars.contains("Favorite food: grains"));
		assertTrue(chars.contains("Is boiler"));
	}
	
	@Test
	public void testGetAnimalCharacteristicsNotBoiler() {
		Chicken c = new Chicken("Chiken small", "small grains", 5.5, false);
		//run
		List<String> chars = c.getAnimalCharacteristics();
		assertEquals(3, chars.size());
		assertTrue(chars.contains("Wingspan: 5.5"));
		assertTrue(chars.contains("Favorite food: small grains"));
		assertTrue(chars.contains("Normal chicken"));
	}
	
	@Test
	public void testGetIsBoiler() {
		Chicken c1 = new Chicken("c1", "grains", 55, true);
		Chicken c2 = new Chicken("c2", "grains", 55, false);
		//test
		assertTrue(c1.getIsBoiler());
		assertTrue(!c2.getIsBoiler());
	}
	
	@Test
	public void testGetType() {
		Chicken c1 = new Chicken("c1", "grains", 55, true);
		//test
		assertEquals("Chicken",  c1.getAnimalType());
	}
	

	

}