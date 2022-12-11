import exceptions.InvalidHexCodeException;
import exceptions.InvalidNumberExceptionNegative;
import exceptions.InvalidNumberExceptionNegativeOrZero;
import exceptions.NoInputException;

import java.util.ArrayList;
import java.util.Scanner;

public class GUI {
    private ItemRegister itemRegister;

    private final int ADD_NEW_ITEM = 1;
    private final int LIST_ITEM_TYPES_WAREHOUSE = 2;
    private final int FIND_ITEM_BASED_ON_DESCRIPTION = 3;
    private final int FIND_ITEM_BASED_ON_ITEM_NUMBER = 4;
    private final int INCREASE_ITEM_OF_TYPE_IN_WAREHOUSE = 5;
    private final int DECREASE_ITEM_OF_TYPE_IN_WAREHOUSE = 6;
    private final int REMOVE_ITEM_TYPE_FROM_INVENTORY = 7;
    private final int DISCOUNT_ON_ITEM = 8;
    private final int NEW_PRICE_ITEM = 9;
    private final int EXIT = 10;

    private int showMenu() {
        int menuChoice = 0;
        System.out.println("\n***** Item Register Menu *****\n");
        System.out.println("1. Add a new item type to the register");
        System.out.println("2. List all item types");
        System.out.println("3. Search for an item based on description");
        System.out.println("4. Search for an item based on item number");
        System.out.println("5. Increase the numbers of a specific item");
        System.out.println("6. Decrease the numbers of a specific item");
        System.out.println("7. Remove an item type completely");
        System.out.println("8. Create a discount on an item type");
        System.out.println("9. Set a new price for an item type");
        System.out.println("10. Exit the Item Register Menu");
        System.out.println("\nPlease select from the menu.\n");

        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            menuChoice = sc.nextInt();
        } else {
            System.out.println("You must enter a number, not text");
        }
        return menuChoice;
    }

    /**
     * Starts the application. This is the main loop of the application,
     * presenting the menu, retrieving the selected menu choice from the user,
     * and executing the selected functionality.
     */
    public void start() {
        boolean finished = false;
        // The while-loop will run as long as the user has not selected
        // to quit the application
        while (!finished) {
            int menuChoice = this.showMenu();
            switch (menuChoice) {
                case ADD_NEW_ITEM:
                    System.out.println("new item");
                    break;
                case LIST_ITEM_TYPES_WAREHOUSE:
                    System.out.println("There are " + itemRegister.numberOfDifferentItemsInWarehouse()
                            + " numbers of distinct item types");
                    System.out.println(itemRegister.toString());
                    break;
                case FIND_ITEM_BASED_ON_DESCRIPTION:
                    System.out.println("search description");
                    break;
                case FIND_ITEM_BASED_ON_ITEM_NUMBER:
                    break;
                case INCREASE_ITEM_OF_TYPE_IN_WAREHOUSE:
                    break;
                case DECREASE_ITEM_OF_TYPE_IN_WAREHOUSE:
                    break;
                case REMOVE_ITEM_TYPE_FROM_INVENTORY:
                    break;
                case DISCOUNT_ON_ITEM:
                    break;
                case NEW_PRICE_ITEM:
                    break;
                case EXIT:
                    System.out.println("You are now closing the item register app!\n");
                    finished = true;
                    break;
                default:
                    System.out.println("Unrecognized menu selected..");
                    break;
            }
        }

            }
    public static void main (String[]args){
        GUI gui = new GUI();
        gui.preMadeItems();
        gui.start();
    }

    public void preMadeItems() {
        ArrayList<Item> itemsInItemRegister = new ArrayList<>();

        try {
            Item blackDoor = new Item("AK10032", "Black Door", 4500, "Bauhaus", 7,
                    0.8, 210, "#000000", 377, Category.DOORS);
            Item greyDoor = new Item("SJ14239", "Grey Door", 2999, "IKEA", 5.2,
                    0.77, 2.05, "#808080", 801, Category.DOORS);
            Item whiteGreyFloorlaminate = new Item("FO95722", "White-Grey floor laminate", 699, "Monter", 0.7,
                    1, 0.05, "#D6D5CB", 482, Category.FLOORLAMINATES);

            itemsInItemRegister.add(blackDoor);
            itemsInItemRegister.add(greyDoor);
            itemsInItemRegister.add(whiteGreyFloorlaminate);

            itemRegister = new ItemRegister(itemsInItemRegister);

        } catch (NoInputException | InvalidNumberExceptionNegative | InvalidNumberExceptionNegativeOrZero | InvalidHexCodeException e) {
            e.printStackTrace();
        }

    }
}














