package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<PFArray> arrays = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            mainMenu();
            int option = readIntFromUser("Choose an option: ");

            switch (option) {
                case 1:
                    createNewArray();
                    break;
                case 2:
                    yourArrays(arrays);
                    break;
                case 3:
                    //findArrayWithMaxSum();
                    break;
                case 4:
                    //findArrayWithMinSum();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting program. Bye!");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static void mainMenu() {
        System.out.println(Menu.mainMenuToString());
    }

    private static void createNewArray() {

        while (true) {
            System.out.println(Menu.creationMenuToString());
            int option = readIntFromUser("Choose an option: ");
            PFArray newArray;
            switch (option) {
                case 1:
                    while (true) {
                        System.out.println("Enter numbers separated by commas (f.e. \"1,2,3\"): ");
                        String input = scanner.nextLine();
                        newArray = new PFArray();
                        newArray.createArrayManually(input);
                        if (newArray.getArray().length > 0) {
                            System.out.println("Array created manually.");
                            arrays.add(newArray);
                            return;
                        } else {
                            System.out.println("Try it again;)!");
                        }
                    }
                case 2:
                    newArray = new PFArray();
                    newArray.createRandomArray();
                    System.out.println("Random array created.");
                    arrays.add(newArray);
                    return;

                default:
                    System.out.println("Invalid option, please enter 1 or 2.");
                    break;
            }
        }
    }

    private static void yourArrays(List<PFArray> arrays) {

        while (true) {
            System.out.println("Choose the array to work with:");
            for (int i = 0; i < arrays.size(); i++) {
                System.out.println((i + 1) + ". Array " + Arrays.toString(arrays.get(i).getArray()));
            }
            int arrayOption = readIntFromUser("Enter the option" + "(1-" + arrays.size() + "): ");
            if (arrayOption >= 1 && arrayOption <= arrays.size()) {
                PFArray selectedArray = arrays.get(arrayOption - 1);
                selectedArrayMenu(selectedArray);
                System.out.println("You've decided to work with: " + arrayOption);
                break;
            } else {
                System.out.println("Invalid array index.");
            }
        }
    }

    private static void selectedArrayMenu(PFArray array) {
        boolean workingWithArray = true;
        while (workingWithArray) {
            System.out.println(Menu.selectedArrayMenuToString());
            int option = readIntFromUser("Choose an option: ");

            switch (option) {
                case 1:
                    array.printArray();
                    break;
                case 2:
                    int number = readIntFromUser("Enter number to add: ");
                    array.addNumber(number);
                    break;
                case 3:
                    System.out.println(array.findMaxNumber());
                    break;
                case 4:
                    System.out.println(array.findMinNumber());
                    break;
                case 5:
                    int lengthOfArray = array.getArray().length;
                    if (lengthOfArray > 0) {
                        while (true) {
                            int numberToRemove = readIntFromUser("Enter a number to remove: ");
                            int count = array.findOccurencyOfNumber(array.getArray(), numberToRemove);
                            boolean onlyFirst = false;
                            if (count > 1) {
                                onlyFirst = readYesNoFromUser("Remove only the first occurrence? (yes/no): ");
                            }
                            array.removeNumber(numberToRemove, count, onlyFirst);

                            int newLengthOFArray = array.getArray().length;
                            if (lengthOfArray > newLengthOFArray) {
                                System.out.println("Number removed;).");
                                break;
                            } else {
                                System.out.println("Try again;).");
                            }
                        }
                    }
                         else{
                            System.out.println("The Array is empty, there is nothing to remove;).");
                            break;
                        }
                        case 6:
                          //  array.removeAllNumbers();
                          //  System.out.println("Array cleared.");
                            break;
                        case 7:
                          //  array.regenerateNumbers();
                          //  System.out.println("Numbers in array regenerated.");
                            break;
                        case 8:
                            workingWithArray = false;

                        default:
                            System.out.println("Invalid option, please try again.");
                            break;
                    }
            }
        }

        private static int readIntFromUser (String prompt){
            while (true) {
                System.out.print(prompt);
                String input = scanner.nextLine();
                try {
                    return Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, please enter a number.");
                }
            }
        }

        public static boolean readYesNoFromUser (String prompt){
            while (true) {
                System.out.print(prompt);
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("yes")) {
                    return true;
                } else if (input.equals("no")) {
                    return false;
                } else {
                    System.out.println("Invalid input, please enter 'yes' or 'no'.");
                }
            }
        }

    }

