package denis.zoofriends;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

/**
 * Super class represent birds. 
 * Has bird specific information.
 */
public abstract class Bird extends Animal {
	
	/* wing span of the bird*/
	private double wingSpan;

	/**
	 * Default constructor for the bird
	 * @param name the name of the bird
	 * @param favoriteFood the favorite food of the bird
	 * @param wingSpan the wing span of the bird
	 * @throws IllegalArgumentException if wing span is zero on negative
	 */
	public Bird(String name, String favoriteFood, double wingSpan) throws IllegalArgumentException {
		super(name, favoriteFood);
		setWingSpan(wingSpan);
	}
	
	/**
	 * Set wing span of the bird
	 * @param wingSpan the wing span of the bird
	 * @throws IllegalArgumentException if wing span is zero on negative
	 */
	public final void setWingSpan(double wingSpan) throws IllegalArgumentException {
		checkArgument(wingSpan > 0, "wing span of the bird cannot be negative or nil");
		this.wingSpan = wingSpan;
	}
	
	/**
	 * Getter method for wing span of the bird
	 * @return the wing span of the bird
	 */
	public final double getWingSpan() {
		return wingSpan;
	}
	
	
	/**
	 * Function generate description of the bird and it's abilities in string format.
	 * Each characteristic of the bird is written in separate line and added to 
	 * sting list of bird characteristic.
	 *
	 * @return the list of the strings with characteristic type and it value, each in 
	 * separate line.   
	 */
	@Override
	public List<String> getAnimalCharacteristics() {
		// get bird characteristic as an animal
		List<String> characteristics = super.getAnimalCharacteristics(); 
		// add bird specific to the list
		characteristics.add("Wingspan: " + wingSpan);
		return characteristics;		
	}
}
