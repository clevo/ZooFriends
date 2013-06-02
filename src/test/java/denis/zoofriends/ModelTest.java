package denis.zoofriends;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class ModelTest {

	@Test
	public void testModel() {
		Model model = new Model();
		assertNotNull(model);
	}

	@Test
	public void testAddAndGetAnimal() {
		Model model = new Model();
		Animal a1 = new Parrot("Parrot one", "grains", 0.2, true);
		Animal a2 = new Parrot("Parrot two", "corn", 0.5, false);
    	model.addAnimal(a1); 
    	model.addAnimal(a2);
    	List<Animal> animals = model.getAnimals();
    	// test nomal scenario
    	assertEquals(2, animals.size());
    	assertTrue(animals.contains(a1));
    	assertTrue(animals.contains(a2));
	}

	@Test
	public void testAddAnimalSameForManyTimes() {
		Model model = new Model();
		Animal a1 = new Parrot("Parrot one", "grains", 0.2, true);
		Animal a2 = new Parrot("Parrot two", "corn", 0.5, false);
    	model.addAnimal(a1);
    	model.addAnimal(a1);
    	model.addAnimal(a1);
    	model.addAnimal(a2);
    	model.addAnimal(a2);
    	model.addAnimal(a2);
    	List<Animal> animals = model.getAnimals();
    	// test
    	assertEquals(2, animals.size());
    	assertTrue(animals.contains(a1));
    	assertTrue(animals.contains(a2));
	}


	@Test
	public void testAddFriendSimple() {
		Model model = new Model();
		Animal a1 = new Parrot("Parrot one", "grains", 0.2, true);
		Animal a2 = new Parrot("Parrot two", "corn", 0.5, false);
		model.addAnimal(a1);
		model.addAnimal(a2);
		//run
		model.addFriend(a1, a2);
		//verify
		assertEquals(1, a1.getFriends().size());
		assertEquals(1, a2.getFriends().size());
		assertEquals(a1, a2.getFriends().get(0));
		assertEquals(a2, a1.getFriends().get(0));
		assertEquals(a1, model.getAnimalsAndFriendsItGain().get(a2).get(0));
		assertEquals(a2, model.getAnimalsAndFriendsItGain().get(a1).get(0));
	}
	
	@Test
	public void testAddFriendForTheSecondTime() {
		Model model = new Model();
		Animal a1 = new Parrot("Parrot one", "grains", 0.2, true);
		Animal a2 = new Parrot("Parrot two", "corn", 0.5, false);
		model.addAnimal(a1);
		model.addAnimal(a2);
		//run
		model.addFriend(a1, a2);
		model.addFriend(a1, a2);
		model.addFriend(a2, a1);
		model.addFriend(a2, a1);
		//verify
		assertEquals(1, a1.getFriends().size());
		assertEquals(1, a2.getFriends().size());
		assertEquals(a1, a2.getFriends().get(0));
		assertEquals(a2, a1.getFriends().get(0));
		assertEquals(a1, model.getAnimalsAndFriendsItGain().get(a2).get(0));
		assertEquals(a2, model.getAnimalsAndFriendsItGain().get(a1).get(0));
	}

	@Test
	public void testAddFriendShipToItself() {
		Model model = new Model();
		Animal a1 = new Parrot("Parrot one", "grains", 0.2, true);
		model.addAnimal(a1);
		
		//run
		model.addFriend(a1, a1);
		//verify
		assertEquals(0, a1.getFriends().size());
		assertTrue(!(model.getAnimalsAndFriendsItGain().containsKey(a1)));
		
	}
	
	@Test
	public void testAddFriendFromOutSide() {
		Model model = new Model();
		Animal a1 = new Parrot("Parrot one", "grains", 0.2, true);
		Animal a2 = new Parrot("Parrot two", "corn", 0.5, false);
		model.addAnimal(a1);
		//run
		model.addFriend(a1, a2);
		//verify
		assertEquals(0, a1.getFriends().size());
		assertEquals(0, a2.getFriends().size());
		assertTrue(!(model.getAnimalsAndFriendsItGain().containsKey(a1)));
		assertTrue(!(model.getAnimalsAndFriendsItGain().containsKey(a2)));
	}
	
	@Test
	public void testAddFriendFromOutSide2() {
		Model model = new Model();
		Animal a1 = new Parrot("Parrot one", "grains", 0.2, true);
		Animal a2 = new Parrot("Parrot two", "corn", 0.5, false);
		//run
		model.addFriend(a1, a2);
		//verify
		assertEquals(0, a1.getFriends().size());
		assertEquals(0, a2.getFriends().size());
		assertEquals(0, model.getAnimalsAndFriendsItGain().size());
	}
	
	@Test
	public void testRemoveFriend() {
		Model model = new Model();
		Animal a1 = new Parrot("Parrot one", "grains", 0.2, true);
		Animal a2 = new Parrot("Parrot two", "corn", 0.5, false);
		Animal a3 = new Dog("Dog 1", "meat", "sleaping dog");
		model.addAnimal(a1);
		model.addAnimal(a2);
		model.addAnimal(a3);
		model.addFriend(a1, a2);
		model.addFriend(a3, a2);
		model.addFriend(a1, a3);
		assertEquals(2, a1.getFriends().size());
		assertEquals(2, a2.getFriends().size());
		assertEquals(2, a3.getFriends().size());
		assertEquals(3, model.getAnimalsAndFriendsItGain().size());
		assertEquals(0, model.getAnimalsAndFriendsItLost().size());
		//run
		model.removeFriend(a1, a2);
		//verify
		assertEquals(1, a1.getFriends().size());
		assertEquals(a3, a1.getFriends().get(0));
		assertEquals(1, a2.getFriends().size());
		assertEquals(a3, a2.getFriends().get(0));
		assertEquals(2, a3.getFriends().size());
		assertEquals(2, model.getAnimalsAndFriendsItLost().size());
		assertEquals(a1, model.getAnimalsAndFriendsItLost().get(a2).get(0));
		assertEquals(a2, model.getAnimalsAndFriendsItLost().get(a1).get(0));
		//run
		model.removeFriend(a1, a3);
		//verify
		assertEquals(0, a1.getFriends().size());
		assertEquals(1, a2.getFriends().size());
		assertEquals(a3, a2.getFriends().get(0));
		assertEquals(1, a3.getFriends().size());
		assertEquals(a2, a3.getFriends().get(0));
		assertEquals(3, model.getAnimalsAndFriendsItLost().size());
		assertEquals(2, model.getAnimalsAndFriendsItLost().get(a1).size());
		assertTrue(model.getAnimalsAndFriendsItLost().get(a1).contains(a2));
		assertTrue(model.getAnimalsAndFriendsItLost().get(a1).contains(a3));
		assertEquals(1, model.getAnimalsAndFriendsItLost().get(a2).size());
		assertTrue(model.getAnimalsAndFriendsItLost().get(a2).contains(a1));
		assertEquals(1, model.getAnimalsAndFriendsItLost().get(a3).size());
		assertTrue(model.getAnimalsAndFriendsItLost().get(a3).contains(a1));
		//run remove the same friend for the second time
		model.removeFriend(a1, a3);
		//verify
		assertEquals(0, a1.getFriends().size());
		assertEquals(1, a2.getFriends().size());
		assertEquals(a3, a2.getFriends().get(0));
		assertEquals(1, a3.getFriends().size());
		assertEquals(a2, a3.getFriends().get(0));
		assertEquals(3, model.getAnimalsAndFriendsItLost().size());
		assertEquals(2, model.getAnimalsAndFriendsItLost().get(a1).size());
		assertTrue(model.getAnimalsAndFriendsItLost().get(a1).contains(a2));
		assertTrue(model.getAnimalsAndFriendsItLost().get(a1).contains(a3));
		assertEquals(1, model.getAnimalsAndFriendsItLost().get(a2).size());
		assertTrue(model.getAnimalsAndFriendsItLost().get(a2).contains(a1));
		assertEquals(1, model.getAnimalsAndFriendsItLost().get(a3).size());
		assertTrue(model.getAnimalsAndFriendsItLost().get(a3).contains(a1));
	}


	@Test
	public void testGetDayNumberMoveToNextDay() {
		Model model = new Model();
		//default first day is 0
		assertEquals(0, model.getDayNumber());
		//run for the second time 
		assertEquals(0, model.getDayNumber());
		assertEquals(0, model.getDayNumber());
		//move to the next day
		model.moveToNextDay();
		//verify
		assertEquals(1, model.getDayNumber());
		//move to the next day
		model.moveToNextDay();
		model.moveToNextDay();
		model.moveToNextDay();
		//verify
		assertEquals(4, model.getDayNumber());
	}

	

}
