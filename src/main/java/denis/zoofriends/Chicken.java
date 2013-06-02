package denis.zoofriends;

import java.util.List;

public class Chicken extends Bird {
    
    /* type of the animal of this class */
    private static final String ANIMAL_TYPE = "Chicken";
    
    private boolean isBroiler;

    /**
     * Default constructor for the chicken
     * @param name the name of the chicken
     * @param favoriteFood the favorite food of the chicken 
     * @param wingSpan the wing span of the chicken
     * @param isBroiler the type of the chicken, broiler or not
     * @throws IllegalArgumentException in case wing span zero or negative
     */
    public Chicken(String name, String favoriteFood, double wingSpan, boolean isBroiler)
            throws IllegalArgumentException {
        super(name, favoriteFood, wingSpan);
        this.isBroiler = isBroiler;
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
     * Get chicken type boiler or not 
     * @return is chicken boiler or not
     */
    public boolean getIsBoiler() {
        return isBroiler;
    }
    
    /**
     * Function generate description of the chicken and it's abilities in string format.
     * Each characteristic of the chicken is written in separate line and added to 
     * sting list of bird characteristic.
     *
     * @return the list of the strings with characteristic type and it value, each in 
     * separate line.   
     */
    @Override
    public List<String> getAnimalCharacteristics() {
        List<String> characteristics = super.getAnimalCharacteristics();
        if (isBroiler) {
            characteristics.add("Is boiler");   
        } else {
            characteristics.add("Normal chicken");
        }
        return characteristics;
    }

}
