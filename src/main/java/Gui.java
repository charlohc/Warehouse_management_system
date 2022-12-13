import exceptions.InvalidNumberExceptionNegative;
import exceptions.InvalidNumberExceptionNegativeOrZero;
import exceptions.NoInputException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Graphic User Interface class.
 * Contains a menu which lets the user use the different methods'
 * int the ItemRegister class.
 */
public class Gui {
  private ItemRegister itemRegister;
  Item item = new Item();
  private static final String ANSI_RED = "\u001B[31m";
  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_GREEN = "\u001B[32m";

  Scanner stringScanner = new Scanner(System.in);
  Scanner numberScanner = new Scanner(System.in);


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
    System.out.println("\nPlease select from the menu.");

    Scanner sc = new Scanner(System.in);
    if (sc.hasNextInt()) {
      menuChoice = sc.nextInt();
    } else {
      System.out.println("You must enter a number no other input is accepted!");
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

    while (!finished) {
      if (!launch) {
        try {
          TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      launch = false;

      int menuChoice = this.showMenu();

      switch (menuChoice) {
        case 1 -> this.addNewItem();
        case 2 -> this.listItemTypesWarehouse();
        case 3 -> this.findItemBasedOnDescription();
        case 4 -> this.findItemBasedOnItemNumber();
        case 5 -> this.increaseItemOfTypeInWarehouse();
        case 6 -> this.decreaseItemOfTypeInWarehouse();
        case 7 -> this.removeItemTypeFromInventory();
        case 8 -> this.discountItem();
        case 9 -> this.newPriceItem();
        case 10 -> {
          System.out.println("You are now closing the item register app!\n");
          finished = true;
        }
        default -> {
          System.out.println("Unrecognized menu selected");
          System.out.println("Returning to main menu...");
        }
      }
    }
  }

  /**
     * main method goes to method that adds premade items to the register.
     * calls on the start() function to start the menu.
  */
  public static void main(String[]args) {
    Gui gui = new Gui();
    gui.preMadeItems();
    gui.start();
  }

  /**
   * Methode that lets the user give an item a new price.
   */
  private void newPriceItem() {
    System.out.println("Here you can change the price of an item type.");
    System.out.println("You will need to type in the item number of the item,\n"
                + "to identify which item you want to change the price");
    System.out.println("-".repeat(60));
    System.out.println("You can now type in the item number: ");
    String itemNumberChangePrice = stringScanner.nextLine();
    System.out.println("Type in the new price (only digits):");

    int newPrice = numberScanner.nextInt();
    if (itemRegister.newPrice(itemNumberChangePrice.trim(), newPrice) == -1) {
      System.out.println(ANSI_RED + "The price change was unsuccessful! \n"
                    + "Either there is no item with the item number: " + itemNumberChangePrice
                    + ", or the new price was not valid" + ANSI_RESET);
    } else {
      System.out.println(ANSI_GREEN + "The prices was successfully changed! " + ANSI_RESET);
    }
  }

  /**
   * Methode that lets the user calculate the discount of an item.
   */
  private void discountItem() {
    System.out.println("Here you can calculate the discount value of an item,\n"
                + "and find out how much less a customer will pay for a specific"
            + " discount percentage.");
    System.out.println("You will need to type in the item number of the item,\n"
                + "to identify which item you want to calculate the discount.");
    System.out.println("-".repeat(60));
    System.out.println("You can now type in the item number: ");
    String itemNumberDiscount = stringScanner.nextLine();
    System.out.println("Type in the discount number (only digits):");
    String discountInPercentage = stringScanner.nextLine();

    if (itemRegister.discount(itemNumberDiscount.trim(),
          Integer.parseInt(discountInPercentage)) == -1) {
      System.out.println(ANSI_RED + "The discount calculation was unsuccessful! \n"
                    + "Either there is no item with the item number: " + itemNumberDiscount
                    + ", or the discount value was not valid." + ANSI_RESET);
    } else {
      Item itemCalculatedDiscount = itemRegister
              .findItemBasedOnItemNumber(itemNumberDiscount.trim());
      int discountInKr = itemRegister.discount(itemNumberDiscount.trim(),
              Integer.parseInt(discountInPercentage));

      System.out.println(ANSI_GREEN + "The discount calculation was successful! " + ANSI_RESET);
      System.out.println("".repeat(60));
      System.out.println("With the discount of " + discountInPercentage + "% the customer will pay "
                    + discountInKr + " kr less");
      System.out.println("The price before discount: " + itemCalculatedDiscount.getPrice() + " kr");
      System.out.println("The price after discount: "
              + (itemCalculatedDiscount.getPrice() - discountInKr) + " kr");
    }
  }

  /**
   * Methode that lets the user remove an item from the inventory.
   */
  private void removeItemTypeFromInventory() {
    System.out.println("Here you can remove an item type "
                + "and all of the corresponding items from the warehouse.");
    System.out.println("You will need to type in the item number of the item,\n"
                + "to identify which item you want to remove");
    System.out.println("-".repeat(60));
    System.out.println("You can now type in the item number: ");
    String itemNumberRemoveItem = stringScanner.nextLine();

    if (itemRegister.removeItemFromInventory(itemNumberRemoveItem.trim())) {
      System.out.println(ANSI_GREEN + "The removal was successful! " + ANSI_RESET);
    } else {
      System.out.println(ANSI_RED + "The removal was unsuccessful! \n"
                    + "There is no item with the item number: "
              + itemNumberRemoveItem + ANSI_RESET);
    }
  }

  /**
   * Methode that lets the user decrease the numbers of items in the warehouse.
  */
  private void decreaseItemOfTypeInWarehouse() {
    System.out.println("Here you can decrease the numbers of items"
            + " of an item type in the warehouse.");
    System.out.println("You will need to type in the item number of the item, "
                + "to identify which item you want to increase.");
    System.out.println("-".repeat(60));
    System.out.println("You can now type in the item number: ");
    String itemNumberDecreaseWarehouse = stringScanner.nextLine();
    System.out.println("Type in how many items you want it to decrease with:");
    String decreasement = stringScanner.nextLine();

    if (itemRegister.decreaseNumbersOfItemsOfTypeInWarehouse(itemNumberDecreaseWarehouse.trim(),
              Integer.parseInt(decreasement))) {
      System.out.println(ANSI_GREEN + "The decreasement was successful! " + ANSI_RESET);
    } else {
      System.out.println(ANSI_RED + "The decreasement was unsuccessful! \n"
                    + "Either there is no item with the item number: " + itemNumberDecreaseWarehouse
                    + ",or the decreasement value was not valid." + ANSI_RESET);
    }
  }

  /**
   * Methode that lets the user increase the numbers of items in the warehouse.
  */
  private void increaseItemOfTypeInWarehouse() {
    System.out.println("Here you can increase the numbers of items"
            + " of an item type in the warehouse.");
    System.out.println("You will need to type in the item number of the item,"
                + " to identify which item you want to increase.");
    System.out.println("-".repeat(60));
    System.out.println("You can now type in the item number: ");
    String itemNumberIncreaseWarehouse = stringScanner.nextLine();
    System.out.println("Type in how many items you want to increase with:");
    String increasement = stringScanner.nextLine();

    if (itemRegister.increaseNumbersOfItemsOfTypeInWarehouse(itemNumberIncreaseWarehouse.trim(),
              Integer.parseInt(increasement))) {
      System.out.println(ANSI_GREEN + "The increasement was successful! " + ANSI_RESET);
    } else {
      System.out.println(ANSI_RED + "The increasement was unsuccessful! \n"
                    + "Either there is no item with the item number: " + itemNumberIncreaseWarehouse
                    + ",or the increasement value was not valid." + ANSI_RESET);
    }
  }

  /**
   * Methode that lets the user search for an item based on the item number.
  */
  private void findItemBasedOnItemNumber() {
    boolean searchingItemNumber = true;
    while (searchingItemNumber) {
      System.out.println("Here you can search for an item by the item number.");
      System.out.println("-".repeat(60));
      System.out.println("You can now type in the item number: ");

      String itemNumberSearchInput = stringScanner.nextLine();

      if (itemRegister.findItemBasedOnItemNumber(itemNumberSearchInput.trim()) == null) {
        System.out.println(ANSI_RED + "Could not find any item matching your item number.."
                        + ANSI_RESET);
        System.out.println("-".repeat(60));
        System.out.println("Do you want another search?"
                        + "\ntype 1 to return to main menu, press another key to keep searching");

        String searchAgainChoice = numberScanner.nextLine();
        if (searchAgainChoice.equals("1")) {
          searchingItemNumber = false;
        }

      } else {
        System.out.println(ANSI_GREEN + "Found an item matching the item number!" + ANSI_RESET);
        System.out.println(itemRegister
                .findItemBasedOnItemNumber(itemNumberSearchInput.trim()).toString());
        searchingItemNumber = false;
      }
    }
  }

  /**
   * Methode that lets the user search for an item based on description.
   */
  private void findItemBasedOnDescription() {
    boolean searchingDescription = true;
    while (searchingDescription) {
      System.out.println("Here you can search for an item by description.");
      System.out.println("-".repeat(60));
      System.out.println("you can now type in the description: ");

      String descriptionSearchInput = stringScanner.nextLine();

      if (itemRegister.findItemBasedOnDescription(descriptionSearchInput).size() == 0) {
        System.out.println(ANSI_RED + "Could not find any item matching your description.."
                    + ANSI_RESET);
        System.out.println("-".repeat(60));
        System.out.println("Do you want another search?"
                        + "\ntype 1 to return to main menu, press another key to keep searching");

        String searchAgainChoice = numberScanner.nextLine();
        if (searchAgainChoice.equals("1")) {
          searchingDescription = false;
        }


      } else {
        System.out.println(ANSI_GREEN + "These are the item(s) matching your description!"
                  + ANSI_RESET);
        System.out.println(itemRegister
                .findItemBasedOnDescription(descriptionSearchInput).toString());
        searchingDescription = false;
      }
    }
  }

  /**
   * Methode that lists all the different item types in the warehouse.
   */
  private void listItemTypesWarehouse() {
    if (itemRegister.numberOfDifferentItemsInWarehouse() == 0) {
      System.out.println("No items are registered in the warehouse");
    } else {
      System.out.println("There are " + itemRegister.numberOfDifferentItemsInWarehouse()
                    + " numbers of distinct item types.");
      System.out.println("there are in total " + itemRegister.totalNumberOfItemsInWarehouse()
                    + " items stored in the warehouse");
      System.out.println("-".repeat(60));
      System.out.println(itemRegister.toString());
    }
  }

  /**
   * Methode that lets the user add a new item to the register.
   */
  private void addNewItem() {
    boolean inputNameBlank = true;
    System.out.println("To add a new item to the register,"
            + " we first need som data about the item." + "\n");

    String itemNumberInput = null;
    while (inputNameBlank) {
      System.out.println("Item number: ");
      System.out.println("(String of numbers and letters)");
      itemNumberInput = stringScanner.nextLine();
      if (itemNumberInput.trim().length() == 0 || !itemNumberInput.matches("[a-zA-Z0-9 ]*")) {
        System.out.println(ANSI_RED + "Item number cannot be blank or contain special characters!"
                        + " Try again... \n" + ANSI_RESET);
      } else {
        inputNameBlank = false;
      }
    }
    boolean incorrectCategoryInput = true;
    Category categoryInput = null;
    while (incorrectCategoryInput) {
      System.out.println("Category: ");
      System.out.println("1: Floorlaminates, 2: Windows, 3: Doors, 4: Lumber");
      String categoryNumber = stringScanner.nextLine();

      if (categoryNumber.equals(String.valueOf(1))) {
        categoryInput = Category.FLOORLAMINATES;
        incorrectCategoryInput = false;

      } else if (categoryNumber.equals(String.valueOf(2))) {
        categoryInput = Category.WINDOWS;
        incorrectCategoryInput = false;

      } else if (categoryNumber.equals(String.valueOf(3))) {
        categoryInput = Category.DOORS;
        incorrectCategoryInput = false;

      } else if (categoryNumber.equals(String.valueOf(4))) {
        categoryInput = Category.LUMBER;
        incorrectCategoryInput = false;

      } else {
        System.out.println(ANSI_RED + "Must type inn number from 1 - 4 !"
                + " Try again... \n" + ANSI_RESET);
      }
    }

    boolean colourIncorrect = true;
    Colour colourInput = null;
    while (colourIncorrect) {
      System.out.println("Colour: ");
      System.out.println("1: Black, 2: White, 3: Grey, 4: Brown, 5: Red, 6: Blue");
      String colourChoiceIntString = stringScanner.nextLine();

      if (colourChoiceIntString.equals(String.valueOf(1))) {
        colourInput = Colour.BLACK;
        colourIncorrect = false;

      } else if (colourChoiceIntString.equals(String.valueOf(2))) {
        colourInput = Colour.WHITE;
        colourIncorrect = false;

      } else if (colourChoiceIntString.equals(String.valueOf(3))) {
        colourInput = Colour.GREY;
        colourIncorrect = false;

      } else if (colourChoiceIntString.equals(String.valueOf(4))) {
        colourInput = Colour.BROWN;
        colourIncorrect = false;

      } else if (colourChoiceIntString.equals(String.valueOf(5))) {
        colourInput = Colour.RED;
        colourIncorrect = false;

      } else if (colourChoiceIntString.equals(String.valueOf(6))) {
        colourInput = Colour.BLUE;
        colourIncorrect = false;

      } else {
        System.out.println(ANSI_RED + "Must type inn number from 1 - 6 !"
                + " Try again... \n" + ANSI_RESET);
      }
    }

    boolean descriptionBlank = true;
    String descriptionInput = null;
    while (descriptionBlank) {
      System.out.println("Short description (max. 35 signs): ");
      System.out.println("Format: Colour + category, Example: White window");
      descriptionInput = stringScanner.nextLine();
      if (descriptionInput.trim().length() == 0
                    || !descriptionInput.matches(".*[a-zA-Z]+.*")) {
        System.out.println(ANSI_RED + "Description cannot be blank or not contain any letters!"
              + " Try again... \n" + ANSI_RESET);
      } else {
        descriptionBlank = false;
      }
    }

    boolean priceBlankOrNegative = true;
    String priceInput = null;
    while (priceBlankOrNegative) {
      System.out.println("Price in kr: ");
      System.out.println("(Must be an integer)");
      priceInput = numberScanner.nextLine();
      if (!priceInput.matches("[0-9]+")
              || item.lengthOfNumberInputInteger(Integer.parseInt(priceInput)) == 0
              || priceInput.trim().equals("0")) {
        System.out.println(ANSI_RED
                        + "Price cannot be blank or contain special characters or be set to zero! "
                        + "Try again... \n" + ANSI_RESET);
      } else {
        priceBlankOrNegative = false;
      }
    }

    boolean brandNameBlank = true;
    String brandNameInput = null;
    while (brandNameBlank) {
      System.out.println("Brand name: ");
      brandNameInput = stringScanner.nextLine();
      if (brandNameInput.trim().length() == 0 || !brandNameInput.matches(".*[a-zA-Z]+.*")) {
        System.out.println(ANSI_RED + "Brand name can not be blank or not contain any letters!"
                        + " Try again... \n" + ANSI_RESET);
      } else {
        brandNameBlank = false;
      }
    }
    boolean weightBlankOrNegative = true;
    String weightInput = null;
    while (weightBlankOrNegative) {
      System.out.println("Weight in kg: ");
      System.out.println("(Decimal number must be made with '.')");
      weightInput = numberScanner.nextLine();
      if (!(weightInput.matches("[0-9]+") || weightInput.contains("."))
                    || item.lengthOfNumberInputDouble(Double.valueOf(weightInput)) == 0
                    || weightInput.trim().equals("0")) {
        System.out.println(ANSI_RED
                    + "Weight cannot be blank or set to zero or a negative value,"
                        + " or contain special characters! "
                        + "Try again... \n" + ANSI_RESET);
      } else {
        weightBlankOrNegative = false;
      }
    }
    boolean lengthBlankOrNegative = true;
    String lengthInput = null;
    while (lengthBlankOrNegative) {
      System.out.println("Length in m: ");
      System.out.println("(Decimal number must be made with '.')");
      lengthInput = numberScanner.nextLine();
      if (!(lengthInput.matches("[0-9]+")
                    || lengthInput.contains("."))
                    || item.lengthOfNumberInputDouble(Double.valueOf(lengthInput)) == 0
                    || lengthInput.trim().equals("0")) {
        System.out.println(ANSI_RED
                        + "Length cannot be blank or set to zero or a negative value,"
                        + " or contain special characters!"
                        + " Try again... \n" + ANSI_RESET);
      } else {
        lengthBlankOrNegative = false;
      }
    }
    boolean heightBlankOrNegative = true;
    String heightInput = null;
    while (heightBlankOrNegative) {
      System.out.println("Height in m: ");
      System.out.println("(Decimal number must be made with '.')");
      heightInput = numberScanner.nextLine();
      if (!(heightInput.matches("[0-9]+")
                    || heightInput.contains("."))
                    || item.lengthOfNumberInputDouble(Double.valueOf(heightInput)) == 0
                    || heightInput.trim().equals("0")) {
        System.out.println(ANSI_RED
              + "Height cannot be blank or set to zero or a negative value,"
              + " or contain special characters!"
                        + " Try again... \n" + ANSI_RESET);
      } else {
        heightBlankOrNegative = false;
      }
    }

    boolean numbersInWarehouseBlankOrNegative = true;
    String numbersInWarehouseInput = null;
    while (numbersInWarehouseBlankOrNegative) {
      System.out.println("Numbers in warehouse: ");
      System.out.println("(Must be an integer)");
      numbersInWarehouseInput = numberScanner.nextLine();
      if (!numbersInWarehouseInput.matches("[0-9]+")
                    || item
              .lengthOfNumberInputInteger(Integer.valueOf(numbersInWarehouseInput)) == 0
                    || numbersInWarehouseInput.trim().equals("0")) {
        System.out.println(ANSI_RED
              + "The numbers of items in warehouse cannot be blank or set as negative or zero,"
              + " or contain special characters!"
                        + " Try again... \n" + ANSI_RESET);
      } else {
        numbersInWarehouseBlankOrNegative = false;
      }
    }


    Item newItem;
    try {
      newItem = new Item(itemNumberInput.toUpperCase(Locale.ROOT).trim(),
              categoryInput, colourInput,
              descriptionInput, Integer.parseInt(priceInput),
              brandNameInput, Double.parseDouble(weightInput),
              Double.parseDouble(lengthInput),
              Double.parseDouble(heightInput), Integer.parseInt(numbersInWarehouseInput));

      if (itemRegister.newItem(newItem)) {
        System.out.println(ANSI_GREEN
                        + "Item added to register successfully!" + ANSI_RESET);
      } else {
        System.out.println(ANSI_RED + "Could not add item to register,"
                        + " since the item number is already registered!" + ANSI_RESET);
      }

    } catch (NoInputException | InvalidNumberExceptionNegative
            | InvalidNumberExceptionNegativeOrZero e) {
      e.printStackTrace();
    }
  }

  /**
     * Method that adds existing items to the register.
   */
  public void preMadeItems() {
    ArrayList<Item> itemsInItemRegister = new ArrayList<>();

    try {
      Item blackDoor = new Item("AK10032", Category.DOORS,
               Colour.BLACK, "Black Door", 4500, "Bauhaus", 7,
                     0.8,  210,  377);
      Item greyDoor = new Item("SJ14239", Category.DOORS, Colour.BLACK,
              "Black Door", 2999, "IKEA", 5.2,
                    0.77, 2.05,  801);
      Item whiteGreyFloorlaminate = new Item("FO95722", Category.FLOORLAMINATES,
              Colour.WHITE, "White-Grey floor laminate", 699,
                    "Monter", 0.7, 1, 0.05, 482);

      itemsInItemRegister.add(blackDoor);
      itemsInItemRegister.add(greyDoor);
      itemsInItemRegister.add(whiteGreyFloorlaminate);

      itemRegister = new ItemRegister(itemsInItemRegister);

    } catch (NoInputException | InvalidNumberExceptionNegative
      | InvalidNumberExceptionNegativeOrZero e) {
      e.printStackTrace();
    }

  }

}














