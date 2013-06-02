package denis.zoofriends;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import denis.zoofriends.Parrot;

public class ParrotTest {

	@Test
	public void testBirdConstructorSimple() {
		Parrot c = new Parrot("Parrot one", "grains", 0.5, true);
		assertNotNull(c);
		assertEquals("Parrot one", c.getName());
		assertEquals("grains", c.getFavoriteFood());
		assertEquals(0.5, c.getWingSpan(), 0);
		assertTrue(c.getIsSpeaking());
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testBirdConstructorNegativeValue() {
		Parrot c = new Parrot("bextra", "grain", -0.5, true);
		assertTrue(c == null);
	}

	@Test
	public void testGetAnimalCharacteristicsSpeak() {
		Parrot c = new Parrot("Parrot small", "grains", 55, true);
		//run
		List<String> chars = c.getAnimalCharacteristics();
		assertEquals(3, chars.size());
		assertTrue(chars.contains("Wingspan: 55.0"));
		assertTrue(chars.contains("Favorite food: grains"));
		assertTrue(chars.contains("Can speak"));
	}
	
	@Test
	public void testGetAnimalCharacteristicsNotSpeaker() {
		Parrot c = new Parrot("Parrot small", "small grains", 5.5, false);
		//run
		List<String> chars = c.getAnimalCharacteristics();
		assertEquals(3, chars.size());
		assertTrue(chars.contains("Wingspan: 5.5"));
		assertTrue(chars.contains("Favorite food: small grains"));
		assertTrue(chars.contains("Cannot speak"));
	}
	
	@Test
	public void testGetIsSpeaking() {
		Parrot c1 = new Parrot("c1", "grains", 55, true);
		Parrot c2 = new Parrot("c2", "grains", 55, false);
		//test
		assertTrue(c1.getIsSpeaking());
		assertTrue(!c2.getIsSpeaking());
	}
	
	@Test
	public void testGetType() {
		Parrot c1 = new Parrot("c1", "grains", 55, true);
		//test
		assertEquals("Parrot",  c1.getAnimalType());
	}

}
