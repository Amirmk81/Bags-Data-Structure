package project1;

import java.util.Arrays;

public class ResizableArrayBag<T> implements BagInterface<T>  {
    private T[] bag;
    private static final int DEFAULT_CAPACITY = 25;
    private int numberOfEntries;
    private boolean integrityOk = false;
    private static int MAX_CAPACITY = 10000;


    //default constructor
    public ResizableArrayBag() {
        this(DEFAULT_CAPACITY);
    }

    //creates and empty nag when we have a certain initial capacity.
    public ResizableArrayBag(int capacity){
        
        
        numberOfEntries = 0;
        @SuppressWarnings("unchecked")
        T[] tempBag = (T[]) new Object[capacity];
        bag = tempBag;
        integrityOk = true;
    }
    public ResizableArrayBag(T[] contents){
        checkCapacity(contents.length);
        bag = Arrays.copyOf(contents, contents.length);
        integrityOk = true;
        
    }

    public boolean add(T newEntry){

        checkintegrity();
        if(isArrayFull()){
            doubleCapacity();
        } 
        bag[numberOfEntries] = newEntry;
        numberOfEntries++;
        return true;
    }
    

    private boolean isArrayFull(){
        return numberOfEntries > bag.length;
    }

    public T[] toArray(){
        checkintegrity();
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        for(int index = 0; index < numberOfEntries;index++){
            result[index] = bag[index];
        }
        return result;

    }
    public boolean isEmpty(){
        return numberOfEntries == 0;
    }
    
    public int getCurrentSize(){
        return numberOfEntries;

    }
    public T remove(){
        checkintegrity();
        T result = removeEntry(numberOfEntries -1);
        return result;

    }
    private T removeEntry(int givenIndex) {
        T result = null;
        if(!isEmpty() && (givenIndex >= 0)){
            result = bag[givenIndex];
            int lastIndex = numberOfEntries - 1;
            bag[lastIndex] = null;
            numberOfEntries --;
        }
        return result;
    }

    public boolean remove(T anEntry){
        checkintegrity();
		int index = getIndexOf(anEntry);
		T result = removeEntry(index);
		return anEntry.equals(result);
    }

    private int getIndexOf(T anEntry) {
        int which = -1;
        boolean found = false;
        int index = 0;
        while(!found && (index < numberOfEntries))
        {
            if(anEntry.equals(bag[index])){
                found = true;
                which = index;
            }
            index++;
        }
        return which;
    }

    public void clear(){
        while(!isEmpty()){
            remove();
        }

    }
    public int getFrequencyOf(T anEntry){
        checkintegrity();
        int count = 0;
        for(int index=0; index < numberOfEntries; index++ ){
            if(anEntry.equals(bag[index])){
                count++;
            }

        }
        return count;
    }
    public boolean contains(T anEntry){
        checkintegrity();
        return getIndexOf(anEntry) >= 0;

    }

    private void checkCapacity(int capacity) {
        if(capacity > MAX_CAPACITY){
            throw new IllegalStateException(
                "Attempt to create a bage whose" + "capacity exceeds "
                + "allowed maximum of " + MAX_CAPACITY + "."
            );
        }
    }
    private void doubleCapacity() {

      int newLength = 2 * bag.length;
      checkCapacity(newLength);
      bag = Arrays.copyOf(bag, newLength);
    }
    
    

    private void checkintegrity() {
		if (!integrityOk)
			throw new SecurityException("ArrayBag object is corrupt.");
	}

    //Union Method 
    public BagInterface<T> union(BagInterface<T> inputBag) {
        T[] thisBagArray = this.toArray(); //coppies content of bag into the array.
        T[] inputBagArray = inputBag.toArray(); //coppies content of the input bag into the array.
        int totalLength = (this.getCurrentSize() + inputBag.getCurrentSize()); //Total lenth of both arrays combined.
        //@SuppressWarnings("unchecked")
        T[] outputArray = (T[]) new Object[totalLength]; //Creating a new Array with the size of total length
        for(int index = 0; index < this.getCurrentSize();index++) {  //coppies everything from thisBagArray to output array
            outputArray[index] = thisBagArray[index];
        }
        for(int index = this.getCurrentSize(); index < totalLength; index++) {
            outputArray[index] = inputBagArray[index - this.getCurrentSize()];    //coppies everything from the input bag next to the this array bag contents inside the output array.
        }
        
        BagInterface<T> outputBag = new ResizableArrayBag<>(outputArray); //creates a new array bag with outputArray.
        for(int i = 0; i < totalLength;i++){
            outputBag.add(outputArray[i]);
        }
        return outputBag; //returning the created output bag*/


    }

    //Intersection method
    public BagInterface<T> intersection(BagInterface<T> inputBag) {
        T[] inputBagArray = inputBag.toArray();
        BagInterface<T> outputBag = new ResizableArrayBag<>(); //creates new resizablearraybag named outputBag
        for(int index = 0; index < inputBagArray.length ; index++) {
            //we only need one loop because for intersection we only need to check if items in one bag exists in the other one as well.
            if((this.contains(inputBagArray[index]) && !outputBag.contains(inputBagArray[index]))) {
                int frequencyinInput = inputBag.getFrequencyOf(inputBagArray[index]); //finds the frequency of element in input bag.
                int frequencyinThis = this.getFrequencyOf(inputBagArray[index]);    //finds the frequence of element in this bag.
                if(frequencyinInput > frequencyinThis) {       //intersection would be the lower frequency for an index.
                    frequencyinInput = frequencyinThis;
                }
                for(int j =0; j < frequencyinInput;j++){
                    outputBag.add(inputBagArray[index]); // coppies inputBagArray[i] into the outputBag as amount of times needed.
                }
            }

        }
        return outputBag; // returns output bag.
    }
    
    @Override
    public BagInterface<T> difference(BagInterface<T> inputBag) {
    
        T[] thisArrayBag = this.toArray();
        T[] inputArrayBag = inputBag.toArray();

        BagInterface<T> outputBag = new ResizableArrayBag<>(); 
        boolean flag= true;
        for (int i = 0; i < thisArrayBag.length;  i++) {
            //flag is to just to set as a check while going through the loop
            flag = true;
            for (int j = 0; j < inputArrayBag.length; j++) {
                //checks to see if they are the same. Only changes if indexes are equal and will change flag and will 
                //break the loop when flagged and replaces that index on inputBag to prevent bugs will multiples of same value
                if((thisArrayBag[i] == inputArrayBag[j])){
                    flag = false;
                    inputArrayBag[j] = null;
                    break;
                }
            }   
            //if flag is still true after checking all of the input bag, then that index of the first bag will be added to output
            if((flag == true) ){
                outputBag.add(thisArrayBag[i]);
            }
        }
        return outputBag;
    }
        

    
    
    
}
