package project1;
public class LinkedBag<T> implements BagInterface<T> {

	private Node firstNode;
	private int numberOfEntries;

	public LinkedBag() {
		firstNode = null;
		numberOfEntries = 0;
	}

	@Override
	public int getCurrentSize() {
		return numberOfEntries;
	}

	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	@Override
	public boolean add(T newEntry) {
		Node newNode = new Node(newEntry);
		newNode.setNextNode(firstNode);
		firstNode = newNode;
		numberOfEntries++;

		return true;
	}

	@Override
	public T remove() {
		T result = null;
		if (firstNode != null) {
			result = firstNode.getData();
			firstNode = firstNode.getNextNode();
			numberOfEntries--;
		}

		return result;
	}

	private Node getReferenceTo(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		}
		return currentNode;
	}

	@Override
	public boolean remove(T anEntry) {
		boolean result = false;
		Node nodeN = getReferenceTo(anEntry);
		if (nodeN != null) {

			nodeN.setData(firstNode.getData());

			firstNode = firstNode.getNextNode();
			numberOfEntries--;
			result = true;
		}
		return result;
	}

	@Override
	public void clear() {
		while (!isEmpty())
			remove();
	}

	@Override
	public int getFrequencyOf(T anEntry) {
		int frequency = 0;
		int loopCounter = 0;
		Node currentNode = firstNode;

		while ((loopCounter < numberOfEntries) && (currentNode != null)) {
			if (anEntry.equals(currentNode.getData())) {
				frequency++;
			}

			loopCounter++;
			currentNode = currentNode.getNextNode();
		}

		return frequency;
	}

	@Override
	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;

		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		}
		return found;
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];

		int index = 0;
		Node currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.getData();
			index++;
			currentNode = currentNode.getNextNode();
		}
		return result;
	}

	
	private class Node {
		private T data;
		private Node next;

		public Node(T dataPortion) {
			this(dataPortion, null);
		}

		public Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}

		public T getData() {
			return data;
		}

		public Node getNextNode() {
			return next;
		}

		public void setData(T newData) {
			data = newData;
		}

		public void setNextNode(Node nextNode) {
			next = nextNode;
		}
	}
	public BagInterface<T> union(BagInterface<T> inputBag) {
		T[] thisBagArray = this.toArray(); //Storing our bag inside the array/
		T[] inputBagArray = inputBag.toArray(); //Storing the input bag in an array.
		BagInterface<T> outputBag = new LinkedBag<>(); // creating and output linkedbag.
		int totalLength = (this.getCurrentSize() + inputBag.getCurrentSize()); //getting the total length.
		for(int index = 0; index < this.getCurrentSize(); index++ ) { 	//adding everything from current bag to the output bag.
			outputBag.add(thisBagArray[index]);
		}
		for(int index = this.getCurrentSize(); index < totalLength; index++ ){	//adding everything from the inputbag next to the current bag inside output bag.
			outputBag.add(inputBagArray[index - this.getCurrentSize()]);
		}
		return outputBag;	//returning the output bag.
		}

	public BagInterface<T> intersection(BagInterface<T> inputBag) {
		T[] inputBagArray = inputBag.toArray();			//coppies the content inside inputBagArray
		BagInterface<T> outputBag = new LinkedBag<>();	//creates and output bag.
		for(int index = 0; index < inputBagArray.length; index++) {
			if(this.contains(inputBagArray[index]) && !outputBag.contains(inputBagArray[index])){ //finds the intersection and prevents duplicating.
				int timesToInput = inputBag.getFrequencyOf(inputBagArray[index]);
				int timesToThis = this.getFrequencyOf(inputBagArray[index]);
				if(timesToInput > timesToThis) {
					timesToInput = timesToThis;	//finds frequency of intersection.
				}
				for(int j = 0; j < timesToInput; j++) {
					outputBag.add(inputBagArray[index]); 		//adds intersection to output bag.
				}
				
;			}
		}
		return outputBag; 	//returns the output bag.
	}

	
	@Override
    public BagInterface<T> difference(BagInterface<T> inputBag) {
    
        T[] thisArrayBag = this.toArray();
        T[] inputArrayBag = inputBag.toArray();

        BagInterface<T> outputBag = new LinkedBag<>(); 
        boolean flag= true;
        for (int i = 0; i < thisArrayBag.length;  i++) {
            //flag is true just to set as a check while going through the loop
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
