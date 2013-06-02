package denis.zoofriends;


import java.util.List;
import java.util.Map;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class View extends JFrame {

    /**
     * Generated serial version ID, required by JFrame
     */
    private static final long serialVersionUID = -2822972706673165532L;
    
    /* button on the screen*/
    private JButton timerOnOffButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton forceNextDayButton;
    /**
     * text area, which represent view to the zoo.
     * each time new day starts, model will printout new state of the zoo
     * to this text area 
     */
    private JTextArea outputScreen;
    /*model of our zoo*/
    private Model model;

    public View(Model model) {
        this.model = model;
        initUI();
        redraw();
    }

    public final void initUI() {

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        panel.setLayout(null);

        outputScreen = new JTextArea(20, 20);
        outputScreen.setEditable(false);
        outputScreen.setBounds(10, 60, 620, 680);

        saveButton = new JButton("Save Zoo state");
        saveButton.setBounds(10, 10, 140, 40);
        saveButton.setToolTipText("Save current state of zoo to the file");

        loadButton = new JButton("Restore Zoo state");
        loadButton.setBounds(160, 10, 140, 40);
        
        timerOnOffButton = new JButton("Start/Stop timer");
        timerOnOffButton.setBounds(310, 10, 140, 40);
        timerOnOffButton.setToolTipText("Automatical day change by the timer");

        forceNextDayButton = new JButton("Force next day");
        forceNextDayButton.setBounds(460, 10, 140, 40);

        panel.add(outputScreen);
        panel.add(saveButton);
        panel.add(loadButton);
        panel.add(timerOnOffButton);
        panel.add(forceNextDayButton);
        add(panel);

        setTitle("Zoo Friends");
        setSize(640, 740);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JButton getSaveButton() {
        return saveButton;
    }
    
    public JButton getTimerButton() {
        return timerOnOffButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }
    
    public JButton getForceNextDayButton(){
        return forceNextDayButton;
    }
    /**
     * Redraw model representation on the screen. 
     * In current implementation, there are no actual drawing, all information extracted 
     * from Model is formated and written in text format to the text area.
     */
    public final void redraw() {
        // first write everything to the the string
        // second update text area with this string.
        List<Animal> animals = model.getAnimals();
        Map<Animal,List<Animal>> aniamalsNewfriends = model.getAnimalsAndFriendsItGain();
        Map<Animal,List<Animal>> animalLostfriends = model.getAnimalsAndFriendsItLost();
        String text = "Today is " + model.getDayNumber() + " day in our Zoo\n";
        text += "And in this perfect day you can watch wild animals in our zoo\n";
        text += "We have big range of different animals, they are very friendly ";
        text += "and like to make new friends every day\n";
        
        text += "Animals:\n";
        for(Animal animal : animals) {
            text += animal.getAnimalType() + " with name " + animal.getName() + "\n";
            // print information about animal
            List<String> chars = animal.getAnimalCharacteristics();
            for(String animalChar : chars) {
                text +=  "  " + animalChar + ";\n";
            }
            //print information about friends
            List<Animal> friends = animal.getFriends();
            if (friends.size() > 0) {

                text += " Friends: ";

                for (Animal friend : friends) {
                    text += friend.getName() + ", ";
                }
                text += "\n";
            }
            //print information about new friends
            if (aniamalsNewfriends.containsKey(animal)) {
                List<Animal> newfriends = aniamalsNewfriends.get(animal);
                text +=" Today " + animal.getName() + " become a friend with: ";
                for (Animal friend : newfriends) {
                    text += friend.getName() + ", ";
                }
                text += "\n";
            }
            //print information about lost friends
            if (animalLostfriends.containsKey(animal)) {
                List<Animal> lostfriends = animalLostfriends.get(animal);
                text +=" and broke up with old friends: ";
                for (Animal friend : lostfriends) {
                    text += friend.getName() + ", ";
                }
                text += "\n";
            }
            text += "\n";
        }
        
        
        

        outputScreen.setText(text);
    }

}
