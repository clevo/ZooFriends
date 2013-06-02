package denis.zoofriends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repent model of our zoo. Contains information about animals, 
 * it's friends, last friendships created and last broke-up made. 
 */
public class Model {
	
	/* day number of the zoo after start or opening */
	private int dayNumber;
	private List<Animal> animals;
	
	/**
	 * Keeps pair of animal and it's friend 
	 */
	private Map<Animal, List<Animal>> animalsAndFriendsItGain;
	/**
	 * Keeps pair of animal and it's friend 
	 */
	private Map<Animal, List<Animal>> animalsAndFriendsItLost;
	
	/**
	 * Defauld constructor
	 */
	public Model() {
		dayNumber = 0;
		animals = new ArrayList<Animal>();
		animalsAndFriendsItGain = new HashMap<Animal, List<Animal>>();
		animalsAndFriendsItLost = new HashMap<Animal, List<Animal>>();
	}
	
	/**
	 * Add new animal to the model 
	 * NOTE: the same animal will not be added for the second time.
	 * @param animal the animal to add.
	 */
	public void addAnimal(Animal animal) {
		if (animals.contains(animal)) {
			return;
		}
		animals.add(animal);
	}
	
	public List<Animal> getAnimals() {
		return animals;
	}
	
	/**
	 * Create friendship between animals. Add information about last friends to 
	 * the list of last friends log. 
	 * NOTE: if animals already are friends, new friendship will not be established
	 * NOTE: even one of the animals is not from the zoo, friendship will not be established
	 * @param animalOne the first animal to be friend
	 * @param animalSecond the second animal to be friend.
	 */
	public void addFriend(Animal animalOne, Animal animalSecond) {
		// check if the animals from our zoo
		if (!animals.contains(animalOne) || !animals.contains(animalSecond)) {
			return;
		}
		// try to add friend
		boolean isAdded = animalOne.addFriend(animalSecond);
		// if added updated last friend ship information.
		if (isAdded) {
			// get list of last friend added from animal one
			List<Animal> friendsOne = animalsAndFriendsItGain.get(animalOne);
			//if we do not know nothing about his friends, let's create to him empty list
			if (friendsOne == null) {
				friendsOne = new ArrayList<Animal>();
				animalsAndFriendsItGain.put(animalOne, friendsOne);
			}
			//and to the list new friend animal second.
			friendsOne.add(animalSecond);
			//do the same for the second animal
			List<Animal> friendsSecond = animalsAndFriendsItGain.get(animalSecond);
			if (friendsSecond == null) {
				friendsSecond = new ArrayList<Animal>();
				animalsAndFriendsItGain.put(animalSecond, friendsSecond);
			}
			friendsSecond.add(animalOne);
		}
	}
	
	/**
	 * Broke friendship between animals. Add information about last friends to 
	 * the list of last friends log. 
	 * NOTE: if animals is not a friend nothing will be performed
	 * NOTE: even one of the animals is not from the zoo, friendship will not be broken
	 * @param animalOne the first animal to broke friendship
	 * @param animalSecond the second animal to broke friendship.
	 */
	public void removeFriend(Animal animalOne, Animal animalSecond){
		if (!animals.contains(animalOne) || !animals.contains(animalSecond)) {
			return;
		}
		// try to remove friend, if they are not friend, we will get false here
		boolean isRemoved = animalOne.removeFriend(animalSecond);
		// if remove updated last friendship information.
		if (isRemoved) {
			// get list of last friend added from animal one
			List<Animal> friendsOne = animalsAndFriendsItLost.get(animalOne);
			//if we do not know nothing about his friends, let's create to him empty list
			if (friendsOne == null) {
				friendsOne = new ArrayList<Animal>();
				animalsAndFriendsItLost.put(animalOne, friendsOne);
			}
			//and to the list old friend animal second.
			friendsOne.add(animalSecond);
			//do the same for the second animal
			List<Animal> friendsSecond = animalsAndFriendsItLost.get(animalSecond);
			if (friendsSecond == null) {
				friendsSecond = new ArrayList<Animal>();
				animalsAndFriendsItLost.put(animalSecond, friendsSecond);
			}
			friendsSecond.add(animalOne);
		}
	}
	

	/**
	 * Provided list of the friendships broken during the day
	 * @return the list of the friendships broken during the day
	 */
	public Map<Animal,List<Animal>> getAnimalsAndFriendsItLost() {
		return animalsAndFriendsItLost;
	}
	
	/**
	 * Provided list of the friendships established during the day
	 * @return the list of the friendships established during the day
	 */
	public Map<Animal,List<Animal>> getAnimalsAndFriendsItGain() {
		return animalsAndFriendsItGain;
	}
	
	/**
	 * Clear last day friendship information 
	 */
	private void clearFriendsLostAndFoundInfo() {
		animalsAndFriendsItGain.clear();
		animalsAndFriendsItLost.clear();
	}
	
	/**
	 * Get day number after zoo opening 
	 * @return the current day number
	 */
	public int getDayNumber() {
		return dayNumber;		
	}
	
	/**
	 * Move to the next day
	 */
	public void moveToNextDay() {
		dayNumber++;
		//it is a new day clear the information from 
		//previous day
		clearFriendsLostAndFoundInfo();
	}
	
}
