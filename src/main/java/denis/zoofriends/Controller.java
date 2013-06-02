package denis.zoofriends;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;

/**
 * ZooController class represent controller class of Zoo. 
 *
 */
public class Controller {


	/* duration of the day in the zoo in ms */
	private static final int DAY_DURATION = 6000;
	
	/* Model and View of out zoo */
	private Model model;
	private View view;
	
	/* timer create events after every 3 second to change the day */
	private Timer timer;
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;

		// create action listeners for the timer
		ActionListener timerNextDayActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				processNextDayEvent();
			}
		};
		// create timer but do not start it
		timer = new Timer(DAY_DURATION, timerNextDayActionListener);
		timer.setRepeats(true);
		// start day timer to change days automatically after 3 second to the next
				timer.start();
	}

	
	public void processNextDayEvent() {
		model.moveToNextDay();
		addRemoveFriendToEachAnimal();
		view.redraw();		
	}
	
	/**
	 * Add and remove one friend from each animal 
	 */
	private void addRemoveFriendToEachAnimal() {
		// now lets remove friendship from each animal 
		// and add friendship to each animal.
		List<Animal> animals = model.getAnimals();
		for (Animal animal : animals) {
			{ //logical intended of block where we remove one friend
				//get friends of the animal
				List<Animal> friends = animal.getFriends();
				int friendsCount = friends.size();
				if (friendsCount > 0) {
					// pick one to remove
					int randFriendNumber = (int) Math.round(Math.random()*(friendsCount-1) );
					Animal friendToBrokeUp = friends.get(randFriendNumber);
					//remove friendship
					model.removeFriend(animal, friendToBrokeUp);
				}
			}
			{//logical intended of section where be add new friend
				// make friendship with one animal 
				// 1) create list of the animals to be a friend
				// each in this list, should not be us or already a friend of us
				// simplest is to make new list and pick one from it. 
				List<Animal> possibleFriends = new LinkedList<Animal>();
				possibleFriends.addAll(animals);
				possibleFriends.remove(animal);
				List<Animal> alreadyFriends = animal.getFriends();
				for(Animal alreadyFriend : alreadyFriends) {
					possibleFriends.remove(alreadyFriend);
				}
				//now if there are more then zero select one. 
				int possibleFriendsCount = possibleFriends.size();
				if (possibleFriendsCount > 0) {
					// pick one to add
					int randToBeFriendNumber = (int) Math.round(Math.random()*(possibleFriendsCount-1));
					Animal animalToBeFriend = possibleFriends.get(randToBeFriendNumber);
					//remove friendship
					model.addFriend(animal, animalToBeFriend);
				}
			}
		}
				
	}
	
	public void saveModel() {
		List<Animal> animals = model.getAnimals();
		model.addFriend(animals.get(0), animals.get(1));
		view.redraw();
		
	}
	
	public void loadModel() {
		
	}
	
	/**
	 * Change state of the timer, if timer if ON change it to OFF
	 * and vice versa.
	 */
	private void processTimerOnOffButtonEvent() {
		if (timer == null) {
			return;
		}
		if (timer.isRunning()) {
			timer.stop();
		} else {
			timer.start();
		}
	}
	
	public void contol(){   
		// add action listener for load button and assign to call 
		// appropriative function from control
		ActionListener loadActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {                  
				loadModel();
			}
		};                
		view.getLoadButton().addActionListener(loadActionListener);
		
		// do the same for load button from the view
		ActionListener saveActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {                  
				saveModel();
			}
		};                
		view.getSaveButton().addActionListener(saveActionListener);
		
		// do the same for force next day button from the view
		ActionListener forceNextDayListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {                  
				processNextDayEvent();
			}
		};                
		view.getForceNextDayButton().addActionListener(forceNextDayListener);
		
		// do the same for force next day button from the view
		ActionListener timerOnOffButtonListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {                  
				processTimerOnOffButtonEvent();
			}
		};                
		view.getTimerButton().addActionListener(timerOnOffButtonListener);


		// start day timer to change days automatically after 3 second to the next
		timer.start();
		
		view.redraw();
	}

}
