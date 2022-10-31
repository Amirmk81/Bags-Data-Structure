package project1;

import java.util.Arrays;
import java.util.Scanner;
public class BagDriver {

    public static void main(String[] args) {

        //scanner for user inputs
        Scanner scan = new Scanner (System.in);
        int temp;
        boolean flag2 = false;

        //Asks the user/ client to either use a resizable bag or a linked bag.
        //Will create a resizable bag for inputting 1 and linked bag for inputting 2
        System.out.println(" Press '1' to use a resizable bag. Press '2' to use a linked bag.");
        temp = scan.nextInt();
        boolean flag = true;
        while(flag){
            if(temp == 1){
                //creates two bags and add contents 
                BagInterface<String> Rbag = new ResizableArrayBag<>();
                Rbag.add("a");
                Rbag.add("b");
                Rbag.add("c");
                //Rbag.add();

                BagInterface<String> Rbag1 = new ResizableArrayBag<>();
                Rbag1.add("b");
                Rbag1.add("b");
                Rbag1.add("d");
                Rbag1.add("e");
                //Rbag1.add();

                flag = false;
                //Flag for the while loop to run while the user is still using the interface.
                boolean flag1 = true;
                //Prints each bag to the user console. 
                System.out.println("Bag 1: " + Arrays.toString(Rbag.toArray()));
                System.out.println("Bag 2: " + Arrays.toString(Rbag1.toArray()));
                while(flag1){
                    //Possible selections for the user to choose. 
                    System.out.println("Enter '1' to find the union of the two bags.");
                    System.out.println("Enter '2' to find the intersection of the two bags.");
                    System.out.println("Enter '3' to find the difference of the two bags.");
                    System.out.println("Enter '4' to quit.");

                    Integer input = scan.nextInt();

                    switch (input){
                        // different case based on user input
                        case 1:
                            System.out.println("Union: " + Arrays.toString(Rbag1.union(Rbag).toArray()));
                            break;
                        case 2:
                            System.out.println("Intersection: " + Arrays.toString(Rbag1.intersection(Rbag).toArray()));
                            break;
                        case 3:
                            System.out.println("Difference: " + Arrays.toString(Rbag.difference(Rbag1).toArray()));
                            break;
                        //this will quit the program for the user. Flag is switched off.
                        case 4:
                            flag1 = false;
                            break;
                        //default runs if anything else is inputted
                        default:
                            System.out.println("Invalid input, try again.");
                            break;
                    }
                }
            }
            
            else if(temp == 2){
                //creates two bags and add contents 
                BagInterface<String> Lbag = new LinkedBag<>();
                Lbag.add("a");
                Lbag.add("b");
                Lbag.add("c");
                BagInterface<String> Lbag1 = new LinkedBag<>();
                Lbag1.add("b");
                Lbag1.add("b");
                Lbag1.add("d");
                Lbag1.add("e");

                flag = false;
                flag2 = true;
                boolean flag1 = true;
                //Prints each bag to the user console. 

                System.out.println("Bag 1: " + Arrays.toString(Lbag.toArray()));
                System.out.println("Bag 2: " + Arrays.toString(Lbag1.toArray()));

                //Flag for the while loop to run while the user is still using the interface.
                while(flag2){
                    //User will input 1-4 for what they want to find
                    System.out.println("Enter '1' to find the union of the two bags.");
                    System.out.println("Enter '2' to find the intersection of the two bags.");
                    System.out.println("Enter '3' to find the difference of the two bags.");
                    System.out.println("Enter '4' to quit.");

                    Integer input = scan.nextInt();

                    switch (input){
                        // different case based on user input
                        case 1:
                            System.out.println("Union: " + Arrays.toString(Lbag1.union(Lbag).toArray()));
                            break;
                        case 2:
                            System.out.println("Intersection: " + Arrays.toString(Lbag1.intersection(Lbag).toArray()));
                            break;
                        case 3:
                            System.out.println("Difference: " + Arrays.toString(Lbag.difference(Lbag1).toArray()));
                            break;

                        //this will quit the program for the user. Flag is switched off.
                        case 4:
                            flag2 = false;
                            break;

                        //default runs if anything else is inputted
                        default:
                            System.out.println("Invalid input, try again.");
                            break;
                    }
                }
                scan.close();
            }
        }
    }
}