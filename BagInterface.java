package project1;

/**
 * An interface that describes the operations of a bag of objects.
 * @author Amir Kouhpainejad
 * @author Omar Morales
 * @author Eric Castadenas
 *
 */

public interface BagInterface<T> {
    /**
     * Gets the current number of entries in the bag.
     * @return The integer number of entries in bag.
     */
    public int getCurrentSize();

    /**
     * Sees whether this bag is empty.
     * @return True if bag is empty and false if not
     */
    public boolean isEmpty();

    /**
     * Adds a new entry to bag.
     * @param newEntry: the object to be added as a new entry.
     * @return True if addition is successful and false if not.
     */
    public boolean add(T newEntry);

    /**
     * Removes one unspecified entry from this bag, if possible.
     * @return True if addition is successful, or false if not.
     */
    public T remove();

    /**
     * Removes one Specified entry from the bag.
     * @param anEntry: The object to be removed from the bag
     * @return true if remove is successful and false if not.
     */
    public boolean remove(T anEntry);

    /**
     * Removes all the objects in the bag.
     */
    public void clear();

    /**
     * get the freqguence of a specified object
     * @param anEntry: object in the bag.
     * @return the frequency of the object
     */
    public int getFrequencyOf(T anEntry);

    /**
     * checks to see if a bag contains an object.
     * @param anEntry: type of the object
     * @return true if the bag contains the object and false if not.
     */
    public boolean contains(T anEntry);

    /**
     * copies the content of bag inside to an array
     * @return the result array
     */
    public T[] toArray();
    


    public BagInterface<T> union(BagInterface<T> inputBag);
    public BagInterface<T> intersection(BagInterface<T> inputBag);
    public BagInterface<T> difference(BagInterface<T> inputBag);


    

    
}
