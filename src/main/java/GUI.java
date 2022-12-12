import exceptions.InvalidHexCodeException;
import exceptions.InvalidNumberExceptionNegative;
import exceptions.InvalidNumberExceptionNegativeOrZero;
import exceptions.NoInputException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


//TODO: hvis tid, legge inn mer rimelig rekkefølge på attributtene,lage definert item number
public class GUI {
    private ItemRegister itemRegister;
    private Item item;

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
        System.out.println( "1. Add a new item type to the register");
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
         if(sc.toString().equals("x")) {
            menuChoice = Integer.parseInt(sc.next());
        } else if (sc.hasNextInt()) {
            menuChoice = sc.nextInt();
        } else {
            System.out.println("You must enter a number or x for exiting, no other input is accepted!");
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
        boolean launch = true;
        item = new Item();
        Scanner stringScanner = new Scanner(System.in);
        Scanner numberScanner = new Scanner(System.in);

        // The while-loop will run as long as the user has not selected
        // to quit the application
        while (!finished) {
            if(!launch) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            launch = false;

            int menuChoice = this.showMenu();

            switch (menuChoice) {
                case ADD_NEW_ITEM:
                    boolean inputNameBlank = true;
                    boolean descriptionBlank = true;
                    boolean priceBlankOrNegative = true;
                    boolean brandNameBlank = true;
                    boolean weightBlankOrNegative = true;
                    boolean lengthBlankOrNegative = true;
                    boolean heightBlankOrNegative = true;
                    boolean colourIncorrect = true;
                    boolean numbersInWarehouseBlankOrNegative = true;
                    boolean incorrectCategoryInput = true;

                    System.out.println("To add a new item to the register, we first need som data about the item." + "\n");

                    String itemNumber = null;
                    while(inputNameBlank) {
                        System.out.println("Item number: ");
                        System.out.println("(String of numbers and letters)");
                        itemNumber = stringScanner.nextLine();
                        if(itemNumber.trim().length() == 0 || !itemNumber.matches("[a-zA-Z0-9 ]*")) {
                            System.out.println("Item number cannot be blank! Try again... \n");
                        } else {
                            inputNameBlank = false;
                        }
                    }

                    String description = null;
                    while (descriptionBlank) {
                        System.out.println("Short description (max. 35 signs): ");
                        System.out.println("Format: Colour name(not hex code) + category, Example: White window");
                        description = stringScanner.nextLine();
                        if(description.trim().equals(" ")) {
                            System.out.println("Description cannot be blank or contain special characters! Try again... \n");
                        } else {
                            descriptionBlank = false;
                        }
                    }

                    int price = 0;
                    while (priceBlankOrNegative) {
                        System.out.println("Price in kr: ");
                        System.out.println("(Must be an integer)");
                        price = numberScanner.nextInt();
                        if(price <= 0 || item.lengthOfNumberInputInteger(price) == 0) {
                            System.out.println("Description cannot be blank! Try again... \n");
                        } else {
                            priceBlankOrNegative = false;
                        }
                    }

                    String brandName = null;
                    while (brandNameBlank) {
                        System.out.println("Brand name: ");
                        brandName = stringScanner.nextLine();
                        if(brandName.trim().equals(" ")) {
                            System.out.println("Brand name cannot be blank! Try again... \n");
                        } else {
                            brandNameBlank = false;
                        }
                    }

                    double weight = 0;
                    while (weightBlankOrNegative) {
                        System.out.println("Weight in kg: ");
                        System.out.println("(decimal number must be made with ',')");
                        weight = numberScanner.nextDouble();
                        if(weight <= 0 || item.lengthOfNumberInputDouble(weight) == 0) {
                            System.out.println("Weight cannot be blank or set to zero or a negative value! Try again... \n");
                        } else {
                            weightBlankOrNegative = false;
                        }
                    }

                    double length = 0;
                    while (lengthBlankOrNegative) {
                        System.out.println("Length in m: ");
                        System.out.println("(decimal number must be made with ',')");
                        length = numberScanner.nextDouble();
                        if(length <= 0 || item.lengthOfNumberInputDouble(length) == 0) {
                            System.out.println("Length cannot be blank or set to zero or a negative value! Try again... \n");
                        } else {
                            lengthBlankOrNegative = false;
                        }
                    }

                    double height = 0;
                    while (heightBlankOrNegative) {
                        System.out.println("Height in m: ");
                        System.out.println("(decimal number must be made with ',')");
                        height = numberScanner.nextDouble();
                        if(height <= 0 || item.lengthOfNumberInputDouble(height) == 0) {
                            System.out.println("Height cannot be blank or set to zero or a negative value! Try again... \n");
                        } else {
                            heightBlankOrNegative = false;
                        }
                    }

                    String colour = null;
                    while (colourIncorrect) {
                        System.out.println("Colour: ");
                        System.out.println("1: Black, 2: White, 3: Grey, 4: Brown, 5: Red, 6: Blue");
                        int colourInt = stringScanner.nextInt();

                        if (colourInt == 1) {
                            colour = String.valueOf(Colour.BLACK);
                            colourIncorrect = false;

                        } else if (colourInt == 2) {
                            colour = String.valueOf(Colour.WHITE);
                            colourIncorrect = false;

                        } else if (colourInt == 3) {
                            colour = String.valueOf(Colour.GREY);
                            colourIncorrect = false;

                        } else if (colourInt == 4) {
                            colour = String.valueOf(Colour.BROWN);
                            colourIncorrect = false;

                        } else if (colourInt == 5) {
                            colour = String.valueOf(Colour.RED);
                            colourIncorrect = false;

                        } else if (colourInt == 6) {
                            colour = String.valueOf(Colour.BLUE);
                            colourIncorrect = false;

                        } else {
                            System.out.println("Must type inn number from 1 - 6 ! Try again... \n");
                        }
                    }

                    int numbersInWarehouse = 0;
                    while (numbersInWarehouseBlankOrNegative) {
                        System.out.println("Numbers in warehouse: ");
                        System.out.println("(Must be an integer)");
                        numbersInWarehouse = numberScanner.nextInt();
                        if(numbersInWarehouse <= 0 || item.lengthOfNumberInputInteger(numbersInWarehouse) == 0) {
                            System.out.println("The numbers of items in warehouse cannot be blank or set as negative or zero! Try again... \n");
                        } else {
                            numbersInWarehouseBlankOrNegative = false;
                        }
                    }

                    Category categoryInput = null;
                    while (incorrectCategoryInput) {
                        System.out.println("Category: ");
                        System.out.println("1: Floorlaminates, 2: Windows, 3: Doors, 4: Lumber");
                        int categoryNumber = stringScanner.nextInt();

                        if (categoryNumber == 1) {
                            categoryInput = Category.FLOORLAMINATES;
                            incorrectCategoryInput = false;

                        } else if (categoryNumber == 2) {
                            categoryInput = Category.WINDOWS;
                            incorrectCategoryInput = false;

                        } else if (categoryNumber == 3) {
                            categoryInput = Category.DOORS;
                            incorrectCategoryInput = false;

                        } else if (categoryNumber == 4) {
                            categoryInput = Category.LUMBER;
                            incorrectCategoryInput = false;

                        } else {
                            System.out.println("Must type inn number from 1 - 4 ! Try again... \n");
                        }
                    }

                    Item newItem = null;
                    try {
                        newItem = new Item(itemNumber, description, price, brandName, weight, length,
                                height, colour, numbersInWarehouse, categoryInput);

                        if(itemRegister.newItem(newItem)) {
                            System.out.println("Item added to register successfully!");
                        } else {
                            System.out.println("Could not add item to register, since it is already registered!");
                        }

                    } catch (NoInputException | InvalidNumberExceptionNegative | InvalidNumberExceptionNegativeOrZero | InvalidHexCodeException e) {
                        e.printStackTrace();
                    }



                    break;
                case LIST_ITEM_TYPES_WAREHOUSE:
                    System.out.println("There are " + itemRegister.numberOfDifferentItemsInWarehouse()
                            + " numbers of distinct item types.");
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
                    System.out.println("Unrecognized menu selected");
                    System.out.println("Returning to main menu...");
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














