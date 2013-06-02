package denis.zoofriends;

import javax.swing.SwingUtilities;

/**
 * Class which starts our application. 
 */
public final class App {

	/**
	 * Constructor of app is hidden as it is not suppose to be created.
	 */
	private App() {}	
	/**
	 * Main method
	 * @param args the arguments 
	 */
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Model model = new Model();
            	model.addAnimal(new Dog("Dog Funny", "foxes", "hunting dog"));
            	model.addAnimal(new Parrot("Parrot one", "grains", 0.2, true));
            	model.addAnimal(new Parrot("Parrot two", "corn", 0.5, false));
            	model.addAnimal(new Dog("Dog ONE", "meat", "running dog"));
            	model.addAnimal(new Dog("Dog Sleepy", "meat balls", "sleeping dog"));
            	model.addAnimal(new Chicken("Chiken small", "grass", 0.1, false));
            	model.addAnimal(new Chicken("Chiken M&D", "grains+", 1.4, true));
                View view = new View(model);
                view.setVisible(true);
                Controller controller = new Controller(model,view);
                controller.contol();
            }
        });

	}

}


