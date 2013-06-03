package denis.zoofriends;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Test;

import denis.zoofriends.Animal;


public class AnimalTest {
	
	//to test abstract class we need to inherit from it
	class AnimalNonAbstract extends Animal {
		public AnimalNonAbstract(String name, String food) {
			super(name, food);
		}
		public String getAnimalType() {
			return "abstract Animal";
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSetNameWhiteBox() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		// MyClass is tested
		Animal tester = new AnimalNonAbstract("tester", "food");
		
		//use reflection to access fields of the class
		Class<? extends Animal> cls = tester.getClass();
		cls = (Class<Animal>)cls.getSuperclass();
		Field field = cls.getDeclaredField("name");
		field.setAccessible(true);
		field.set(tester, "no name");
		
		//call
		tester.setName("My new Name");
		
		// Check 
		assertEquals("Check is name ok", "My new Name", field.get(tester).toString());
	}

	@Test
	public void testSetNameGetNameBlackBox() {
		// MyClass is tested
		Animal tester = new AnimalNonAbstract("tester", "food");
		
		//set 
		tester.setName("this is dog1");
		
		//get
		String name = tester.getName();
		
		// Check 
		assertEquals("Check is name ok", "this is dog1", name);
		
		//set 
		tester.setName("Cat in black10");

		//get
		name = tester.getName();

		// Check 
		assertEquals("Check is name ok", "Cat in black10", name);
	}
	
	@Test
	public void testGetNameWhiteBox() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		// MyClass is tested
		Animal tester = new AnimalNonAbstract("tester", "food");
		
		//use reflection to access fields of the class
		Class<? extends Animal> cls = tester.getClass();
		cls = (Class<Animal>)cls.getSuperclass();
		Field field = cls.getDeclaredField("name");
		field.setAccessible(true);
		field.set(tester, "My lovely dog");
		
		//call
		String name =tester.getName();
		
		// Check 
		assertEquals("Check is name ok", "My lovely dog", name);
	}

	@Test
	public void testSetFavoriteFoodGetFoodBlackBox() {
		Animal tester = new AnimalNonAbstract("tester", "food");
		//set 
		tester.setFavoriteFood("Grains");
		//get
		String food = tester.getFavoriteFood();
		// Check 
		assertEquals("Check is food is ok", "Grains", food);

		//set 
		tester.setFavoriteFood("Meatballs");
		//get
		food = tester.getFavoriteFood();
		// Check 
		assertEquals("Check is food is ok", "Meatballs", food);
	}


	@Test
	public void testGetAnimalCharacteristics() {
		Animal a1 = new AnimalNonAbstract("Ny Animal","small grains");
		//run
		List<String> chars = a1.getAnimalCharacteristics();
		//check
		assertEquals(1, chars.size());
		assertTrue(chars.contains("Favorite food: small grains"));
	}

	@Test
	public void testGetFriends() {
		Animal a1 = new AnimalNonAbstract("a1","f1");
		Animal a2 = new AnimalNonAbstract("a2","f2");
		Animal a3 = new AnimalNonAbstract("a3","f3");
		List<Animal> friends;
		
		// get friends from empty object
		friends = a1.getFriends();
		assertNotNull(friends);
		assertEquals(0, friends.size());
		
		// add 2 friends
		a1.addFriend(a2);
		a1.addFriend(a3);
		friends = a1.getFriends();
		assertNotNull(friends);
		assertEquals(2, friends.size());
		assertTrue(friends.contains(a2));
		assertTrue(friends.contains(a3));
	}

	@Test
	public void testAddFriendSimple() {
		Animal a1 = new AnimalNonAbstract("a1","f1");
		Animal a2 = new AnimalNonAbstract("a2","f2");
		
		// add first friend
		boolean res = a1.addFriend(a2);
		// test
		List<Animal> friendsA1 = a1.getFriends();
		List<Animal> friendsA2 = a2.getFriends();
		
		assertEquals(1, friendsA1.size());
		assertEquals(1, friendsA2.size());
		assertTrue(res);
		assertTrue(friendsA1.contains(a2));
		assertTrue(friendsA2.contains(a1));
	}
	
	@Test
	public void testAddFriendItSelf() {
		Animal a1 = new AnimalNonAbstract("a1","f1");
		
		// add first friend
		boolean res = a1.addFriend(a1);
		// test
		List<Animal> friends = a1.getFriends();
		assertEquals(false, res);
		assertEquals(0, friends.size());
	}
	
	@Test
	public void testAddFriendSecondTime() {
		Animal a1 = new AnimalNonAbstract("a1","f1");
		Animal a2 = new AnimalNonAbstract("a2","f2");
		
		// add first friend
		a1.addFriend(a2);
		//add it for second time
		boolean res = a1.addFriend(a2);
		// test
		List<Animal> friends = a1.getFriends();
		assertEquals(false, res);
		assertEquals(1, friends.size());
		assertTrue(friends.contains(a2));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddNullFriend() {
		Animal a1 = new AnimalNonAbstract("a1","f1");
		
		// add first friend
		 a1.addFriend(null);
		fail("function should leave, but not");
	}

	@Test
	public void testRemoveFriend() {
		Animal a1 = new AnimalNonAbstract("a1","f1");
		Animal a2 = new AnimalNonAbstract("a2","f2");
		Animal a3 = new AnimalNonAbstract("a3","f3");
		
		//friend
		boolean res;
		a1.addFriend(a2);
		a1.addFriend(a3);
		// check
		List<Animal> friendsA1 = a1.getFriends();
		List<Animal> friendsA2 = a2.getFriends();
		List<Animal> friendsA3 = a3.getFriends();
		assertEquals(2, friendsA1.size());
		assertEquals(1, friendsA2.size());
		assertEquals(1, friendsA3.size());
		assertTrue(friendsA1.contains(a2));
		assertTrue(friendsA1.contains(a3));
		assertTrue(friendsA2.contains(a1));
		assertTrue(friendsA3.contains(a1));
		
		//remove friend
		res = a1.removeFriend(a2);
		
		// check
		friendsA1 = a1.getFriends();
		friendsA2 = a2.getFriends();
		friendsA3 = a3.getFriends();
		assertEquals(1, friendsA1.size());
		assertEquals(0, friendsA2.size());
		assertEquals(1, friendsA3.size());
		assertTrue(res);
		assertTrue(friendsA1.contains(a3));
		assertTrue(!friendsA1.contains(a2));
		assertTrue(friendsA3.contains(a1));
	}

	@Test
	public void testRemoveNonFriend() {
		Animal a1 = new AnimalNonAbstract("a1","f1");
		Animal a2 = new AnimalNonAbstract("a2","f2");
		Animal a3 = new AnimalNonAbstract("a3","f3");
		
		//friend
		boolean res;
		a1.addFriend(a2);
		// check
		List<Animal> friendsA1 = a1.getFriends();
		List<Animal> friendsA2 = a2.getFriends();
		assertEquals(1, friendsA1.size());
		assertEquals(1, friendsA2.size());
		assertTrue(friendsA1.contains(a2));
		assertTrue(friendsA2.contains(a1));		
		//remove non friend
		res = a1.removeFriend(a3);
		// check
		friendsA1 = a1.getFriends();
		friendsA2 = a2.getFriends();
		assertEquals(1, friendsA1.size());
		assertEquals(1, friendsA2.size());
		assertTrue(!res);
		assertTrue(friendsA1.contains(a2));
		assertTrue(friendsA2.contains(a1));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRemoveNullFriend() {
		Animal a1 = new AnimalNonAbstract("a1","f1");	
		// add first friend
		 a1.removeFriend(null);
		fail("function should leave, but not");
	}

}
