package project1;

import java.util.Arrays;

public class LinkedBagTest {
    public static void main(String[]args){
        //creates two empty LinkedBags
        BagInterface<String> bag1 = new LinkedBag<>();
        BagInterface<String> bag2 = new LinkedBag<>();
        bag1.add("a");
        bag1.add("b");
        bag1.add("c");
        bag2.add("b");
        bag2.add("b");
        bag2.add("d");
        bag2.add("e");
        System.out.println();
        System.out.println("Bag 1: " + Arrays.toString(bag1.toArray()));

        System.out.println("Bag 2: " + Arrays.toString(bag2.toArray()));

        System.err.println("Union");
        //union
        System.out.println("Union: " + Arrays.toString(bag1.union(bag2).toArray()));
        System.err.println("Intersection");
        //intersection
        System.out.println("Interestion: " + Arrays.toString(bag1.intersection(bag2).toArray()));
        System.err.println("Difference");
        //difference
        System.out.println("Difference: " + Arrays.toString(bag1.difference(bag2).toArray()));
        

    }
}