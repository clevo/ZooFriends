package denis.zoofriends;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class DogTest {



	@Test
	public void testDogConstructorSimple() {
		Dog b1 = new Dog("Big Dog", "meat balls", "sleeping dog");
		assertNotNull(b1);
		assertEquals("Big Dog", b1.getName());
		assertEquals("meat balls", b1.getFavoriteFood());
		assertEquals("sleeping dog", b1.getDogType());	
		assertEquals("Dog", b1.getAnimalType());
	}

	@Test
	public void testGetAnimalCharacteristics() {
		Dog b1 = new Dog("Dog1", "parrots", "hunting dog");
		//run
		List<String> chars = b1.getAnimalCharacteristics();
		assertEquals(2, chars.size());
		assertTrue(chars.contains("Dog type: hunting dog"));
		assertTrue(chars.contains("Favorite food: parrots"));
	}






	@Test
	public void testGetAnimalType() {
		Dog b1 = new Dog("Dog1", "parrots", "hunting dog");
		//verify
		assertEquals("Dog", b1.getAnimalType());
	}


	@Test
	public void testGetDogType() {
		Dog b1 = new Dog("Dog2", "dog food", "running dog");
		//verify
		assertEquals("running dog", b1.getDogType());
	}

}
