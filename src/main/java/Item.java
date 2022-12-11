import Exceptions.InvalidHexCodeException;
import Exceptions.InvalidNumberExceptionNegative;
import Exceptions.InvalidNumberExceptionNegativeOrZero;
import Exceptions.NoInputException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO: må ha sjekk på at itemnumber ikke eksisterer
/**
 * Entity class Item which initializes an item based on price,
 * brand name, weight, length, colour,
 * how many of the item which is in the
 * warehouse and the category which the item is in.
 */
public class Item {
  private final String itemNumber;
  private final String description;
  private int price;
  private final String brandName;
  private final double weight;
  private final double length;
  private final double height;
  private final String colour;
  private int numbersInWarehouse;
  private final Category category;

  /**
   * Explanation of all the characteristics of an item.
   * @param itemNumberInput itemNumberInput ,unique code which identifies each item,
   *    *                   consists of letters and numbers.
   * @param descriptionInput a short text(140 chars) that describes the item
   * @param priceInput an integer which represents the price of the item
   * @param brandNameInput a string that contains the brand of the item
   * @param weightInput a decimal number that describes the weight of the item in kilo grams
   * @param lengthInput a decimal number that describes the length of the item in cm
   * @param heightInput a decimal number that describes the height of the item in cm
   * @param colourInput a string which should represent a hex code colour value
   * @param numbersInWarehouseInput an Integer that tells how many of that item that is in stock
   *    *                     in the warehouse
   * @param categoryInput an enum from the Category class, from the class you can choose from four
   *    *                    different categories: floorlaminates, windows, doors and lumber
   * @throws NoInputException throws an exception if the input from user is blank
   * @throws InvalidHexCodeException throws an exception if the colour input from user does
   *                        match a valid hex code
   * @throws InvalidNumberExceptionNegativeOrZero throws an exception if the user input number is negative or zero
   * @throws InvalidNumberExceptionNegative throws an exception if the user input is negative
   */

  public Item(String itemNumberInput, String descriptionInput, int priceInput,
              String brandNameInput, double weightInput, double lengthInput,
              double heightInput, String colourInput, int numbersInWarehouseInput,
              Category categoryInput) throws NoInputException, InvalidHexCodeException, InvalidNumberExceptionNegativeOrZero, InvalidNumberExceptionNegative {

    isValidStringInput(itemNumberInput);
    isValidStringInput(descriptionInput);
    isValidStringInput(brandNameInput);
    isValidHexCode(colourInput);
    isValidNumberInputNotNegativeOrZero((double) priceInput);
    isValidNumberInputNotNegative(numbersInWarehouseInput);
    isValidNumberInputNotNegativeOrZero(weightInput);
    isValidNumberInputNotNegativeOrZero(lengthInput);
    isValidNumberInputNotNegativeOrZero(heightInput);

    this.itemNumber = itemNumberInput;
    this.description = descriptionInput;
    this.price = priceInput;
    this.brandName = brandNameInput;
    this.weight = weightInput;
    this.length = lengthInput;
    this.height = heightInput;
    this.colour = colourInput;
    this.numbersInWarehouse = numbersInWarehouseInput;
    this.category = categoryInput;
  }


  public String getItemNumber() {
    return itemNumber;
  }

  public String getDescription() {
    return description;
  }


  public int getPrice() {
    return price;
  }


  public String getBrandName() {
    return brandName;
  }


  public double getWeight() {
    return weight;
  }


  public double getLength() {
    return length;
  }


  public double getHeight() {
    return height;
  }


  public String getColour() {
    return colour;
  }


  public int getNumbersInWarehouse() {
    return numbersInWarehouse;
  }


  public Category getCategory() {
    return category;
  }

  /**
     *Method that gets and returns the item number.
  */
  public void setPrice(final int priceInput) throws InvalidNumberExceptionNegativeOrZero {
    isValidNumberInputNotNegativeOrZero((double) priceInput);
    this.price = priceInput;
  }

  /**
     *Method that gets and returns the item number.
     *
     * @param numbersInWarehouseInput  item
  */
  public void setNumbersInWarehouse(final int numbersInWarehouseInput) throws InvalidNumberExceptionNegativeOrZero {
    if (numbersInWarehouseInput <= 0) {
      throw new InvalidNumberExceptionNegativeOrZero("Change in warehouse inventory cannot be zero or negative");
    }
    this.numbersInWarehouse = numbersInWarehouseInput;
  }

  static void isValidStringInput(String str) throws NoInputException {
    if (str.equals(" ")) {
      throw new NoInputException("\n The input cannot be empty");
    }
  }

  static void isValidNumberInputNotNegativeOrZero(Double number)
          throws InvalidNumberExceptionNegativeOrZero {
    if (number <= 0) {
      throw new InvalidNumberExceptionNegativeOrZero("\n Number input can not be negative or zero");
    }
  }

  static void isValidNumberInputNotNegative(int number)
          throws InvalidNumberExceptionNegative {
    if (number < 0) {
      throw new InvalidNumberExceptionNegative("\n Number input can not be negative");
    }
  }

  /**
     *Method that checks if the given hex code is a valid hex code.
     *
   *
     * TODO: write more about functionality of code
  */
/*
  static void isValidHexCode(String string) throws InvalidHexCodeException {
    String str = string.toLowerCase();

    if (str.charAt(0) != '#') {
      throw new InvalidHexCodeException(" \n hex code must start with #");
    }
    if (!(str.length() == 7)) {
      throw new InvalidHexCodeException(" \n hex code must consist of a hashtag and 6 chars");
    }

    for (int i = 1; i < str.length(); i++) {
      if (!(str.charAt(i) >= 0 && str.charAt(i) <= 9) || ((str.charAt(i) >= 'a' && str.charAt(i) <= 'f'))) {
        throw new InvalidHexCodeException("\n invalid hex code chars");
      }
    }
  }

 */

  static void isValidHexCode(String colourCode) throws InvalidHexCodeException{

  String HEX_WEBCOLOR_PATTERN
            = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$";

    Pattern pattern = Pattern.compile(HEX_WEBCOLOR_PATTERN);

      Matcher matcher = pattern.matcher(colourCode);
      if (!matcher.matches()) {
      throw new InvalidHexCodeException("not a valid hex code");
    }
  }


  @Override
  public String toString() {
    return "Item: "
                + "itemNumber :'" + itemNumber + '\''
                + ", description: " + description + '\''
                + ", price: " + price
                + ", brand: '" + brandName + '\''
                + ", weight: " + weight
                + ", height: " + height
                + ", colour: '" + colour + '\''
                + ", numbersInWarehouse: " + numbersInWarehouse
                + ", category:" + category + "\n";
  }


}
