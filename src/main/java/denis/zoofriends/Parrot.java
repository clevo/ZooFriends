package denis.zoofriends;

import java.util.List;

public class Parrot extends Bird {
    /* type of the animal of this class */
    private static final String ANIMAL_TYPE = "Parrot";
    
    /* is speaking parrot or not*/
    private boolean isSpeaking;

    /**
     * Default constructor for the chicken
     * @param name the name of the chicken
     * @param favoriteFood the favorite food of the chicken 
     * @param wingSpan wing span of the chicken
     * @param isSpeaking speaking ability of the parrot
     * @throws IllegalArgumentException in case wing span zero or negative
     */
    public Parrot(String name, String favoriteFood, double wingSpan, boolean isSpeaking)
            throws IllegalArgumentException {
        super(name, favoriteFood, wingSpan);
        this.isSpeaking = isSpeaking;
    }

    /**
     * Provide type of the animal, chicken in our case. 
     * @return the type of the animal, chicken in our case.
     */
    @Override
    public String getAnimalType() {
        return ANIMAL_TYPE;
    }
    /**
     * Get is parrot able to speak or not
     * @return is speaking parrot
     */
    public boolean getIsSpeaking() {
        return isSpeaking;
    }
    
    /**
     * Function generate description of the parrot and it's abilities in string format.
     * Each characteristic of the parrot is written in separate line and added to 
     * sting list of parrot characteristic.
     *
     * @return the list of the strings with characteristic type and it value, each in 
     * separate line.   
     */
    @Override
    public List<String> getAnimalCharacteristics() {
        List<String> characteristics = super.getAnimalCharacteristics();
        if (isSpeaking) {
            characteristics.add("Can speak");   
        } else {
            characteristics.add("Cannot speak");
        }
        return characteristics;
    }

}
