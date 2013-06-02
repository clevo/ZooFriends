package denis.zoofriends;

import java.util.List;

public class Dog extends Animal  {

    /* type of the animal of this class */
    private static final String ANIMAL_TYPE = "Dog";
    
    private String dogType;

    /**
     * Default constructor for the dog
     * @param name the name of the dog
     * @param favoriteFood the favorite food of the dog 
     * @param dogType type of the dog
     */
    public Dog(String name, String favoriteFood, String dogType) {
        super(name, favoriteFood);
        this.dogType = dogType;
    }

    /**
     * Provide type of the animal, dog in our case. 
     * @return the type of the animal, dog in our case.
     */
    @Override
    public String getAnimalType() {
        return ANIMAL_TYPE;
    }
    /**
     * Get dog type
     * @return the dog type
     */
    public String getDogType() {
        return dogType;
    }
    
    /**
     * Function generate description of the dog and it's abilities in string format.
     * Each characteristic of the dog is written in separate line and added to 
     * sting list of dog characteristic.
     *
     * @return the list of the strings with characteristic type and it value, each in 
     * separate line.   
     */
    @Override
    public List<String> getAnimalCharacteristics() {
        List<String> characteristics = super.getAnimalCharacteristics();    
        characteristics.add("Dog type: " + dogType);
        return characteristics;
    }
}
