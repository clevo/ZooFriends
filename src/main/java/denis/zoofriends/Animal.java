package denis.zoofriends;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.List;


/**
 * Class represents basic information about an Animal. 
 * Animal is able to add new friend {@code addFriend} and broke-up with 
 * friend {@code removeFriend}. 
 */
public abstract class Animal {
    
    /* name of the animal */
    private String name;
    /* favorite food */
    private String favoriteFood;
    /* list of the friends of the animal */
    private List<Animal> friends;
    
    /**
     * Default constructor of the Animal
     * @param name the name of the Animal
     * @param favoriteFood the favorite food of the Animal
     */
    public Animal(String name, String favoriteFood) {
        setName(name);
        setFavoriteFood(favoriteFood);
        friends = new ArrayList<Animal>(); 
    }
    
    /**
     * Provide type of the animal like "Dog" or "Chicken"
     * @return the animal type
     */
    public abstract String getAnimalType();
    
    /**
     * Default setter for the name of the animal
     * @param name the name of the animal
     */
    public final void setName(String name) {
        this.name = name;
    }
    
    /**
     * Default getter for the name of the animal
     * @return the name of the animal
     */
    public final String getName() {
        return name;
    }
    
    /**
     * Default setter for the favorite food of the animal
     * @param favoriteFood the favorite food of the animal
     */
    public final void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }
    
    /**
     * Default getter of the favorite food of the animal
     * @return the favorite food of the animal
     */
    public final String getFavoriteFood() {
        return favoriteFood;
    }
    
    /**
     * Function generate description of the animal and it's ability in string format.
     * Each characteristic of the animal is written in separate line and added to 
     * sting list of animal characteristic.
     *
     * @return the list of the strings with characteristic type and it value, each in 
     * separate line.   
     */
    public List<String> getAnimalCharacteristics() {
        // create list
        List<String> characteristics = new ArrayList<String>(); 
        // add to the list existing information
        characteristics.add("Favorite food: " + favoriteFood);
        return characteristics;     
    }
    
    /**
     * 
     * @return the list which contained all the friend of current animal 
     */
    public final List<Animal> getFriends() {
        // create shadows copy of friend list
        List<Animal> fs = new ArrayList<Animal>(this.friends.size());
        fs.addAll(this.friends);
        return fs;
    }
    
    /**
     * Add new friend to this animal. New friend will added to the 
     * friend list of the animal, and animal will ask it self for friendship 
     * confirmation from new friend. New friend is not able to regret 
     * request. 
     * Note: newFriend will be notified about friendship by this method, so 
     * there are no sense to call {@link addFriend} for the {@code newFriend}.
     * @param newFriend the new friend to be added to current animal
     * @throws IllegalArgumentException if input param is null
     * @return true after new friend added; false if friend already in the friend list or 
     *     if we try to add itself as a friend.
     */
    public final boolean addFriend(Animal newFriend) throws IllegalArgumentException {
        checkArgument(newFriend != null, "newFriend is not suppose to be null");
        if (newFriend.equals(this)) {
            return false;
        }
        //check if friend is already added
        if (friends.contains(newFriend)) {
            //even we find that we are already in friend relation with newFriend
            //we should notify newFriend about our friendship to be sure both 
            //of them are know about friendship
            newFriend.confirmFriendship(this);
            return false;
        }
        friends.add(newFriend);
        newFriend.confirmFriendship(this);
        return true;
    }
    
    /**
     * Internally activated call to confirm friendship from other side, will be called 
     * on reaction on the method {@link addFriend}.
     * Will and to the internal friend list, newFriend.
     * Note: {@code newFriend} has this object already in internal friend list.
     * @param newFriend the new friend to add.
     */
    private void confirmFriendship(Animal newFriend) {
        if (!friends.contains(newFriend)) {
            // add new friend only if it is not jet.
            friends.add(newFriend);
        }
    }
    
    /**
     * Broke-up with {@code oldFriend}. Remove it from relations and forcly notify 
     * old friend to cancel friendship as well from his side. 
     * Old friend is not able to regret request. 
     * Note: {@code oldFriend} will be notified about broke-up by this method, so 
     * there are no sense to call {@link removeFriend} for the {@code oldFriend}.
     * @param newFriend the new friend to be added to current animal 
     * @throws IllegalArgumentException if input param is null
     * @return true after new friend added; false if friend already in the friend list
     */
    public final boolean removeFriend(Animal oldFriend) throws IllegalArgumentException {
        checkArgument(oldFriend != null, "oldFriend is not suppose to be null");
        //check if friend is already added
        if (!friends.contains(oldFriend)) {
            //even we find that we are not a friend to old friend
            //we should notify oldFriend about broke-up.
            oldFriend.confimFriendshipRemoval(this);
            return false;
        }
        friends.remove(oldFriend);
        oldFriend.confimFriendshipRemoval(this);
        return true;
    }
    /**
     * Internally activated call to confirm friendship canceling from other side, will 
     * be called on reaction on the method {@link removeFriend}.
     * Will remove from the internal friend list oldFriend.
     * Note: {@code removeFriend} removed this object already from internal friend list.
     * @param oldFriend the old friend to remove
     */
    public final void confimFriendshipRemoval(Animal oldFriend) {
        if (friends.contains(oldFriend)) {
            friends.remove(oldFriend);
        }
    }

    
}
