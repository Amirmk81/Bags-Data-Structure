package project1;

import java.util.Arrays;

public class ResizableArrayBagTest {
    public static void main(String[]args){
        //creating two test bags and adding elements.
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        BagInterface<String> bag2 = new ResizableArrayBag<>();
        bag1.add("a");
        bag1.add("b");
        bag1.add("c");
        bag2.add("b");
        bag2.add("b");
        bag2.add("d");
        bag2.add("e");
        System.out.println();
        //printing what's inside the bag and using the three methods for testing.
        System.out.println("Bag 1: " + Arrays.toString(bag1.toArray()));

        System.out.println("Bag 2: " + Arrays.toString(bag2.toArray()));

        System.err.println("union");
        //union
        System.out.println("Union: " + Arrays.toString(bag1.union(bag2).toArray()));
        System.err.println("intersection");
        //intersection
        System.out.println("Interestion: " + Arrays.toString(bag1.intersection(bag2).toArray()));
        System.err.println("difference");
        //difference
        System.out.println("Difference: " + Arrays.toString(bag1.difference(bag2).toArray()));
        

    }
}