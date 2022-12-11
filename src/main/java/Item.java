import exceptions.InvalidHexCodeException;
import exceptions.InvalidNumberExceptionNegative;
import exceptions.InvalidNumberExceptionNegativeOrZero;
import exceptions.NoInputException;
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
   *
   *  @param itemNumberInput itemNumberInput ,unique code which identifies each item,
   *                   consists of letters and numbers.
   * @param descriptionInput a short text(140 chars) that describes the item
   * @param priceInput an integer which represents the price of the item
   * @param brandNameInput a string that contains the brand of the item
   * @param weightInput a decimal number that describes the weight of the item in kilo grams
   * @param lengthInput a decimal number that describes the length of the item in cm
   * @param heightInput a decimal number that describes the height of the item in cm
   * @param colourInput a string which should represent a hex code colour value
   * @param numbersInWarehouseInput an Integer that tells how many of that item that is in stock
   *                    in the warehouse
   * @param categoryInput an enum from the Category class, from the class you can choose from four
   *                    different categories: floorlaminates, windows, doors and lumber
   * @throws NoInputException throws an exception if the input from user is blank
   * @throws InvalidHexCodeException throws an exception if the colour input from user does
   *                    match a valid hex code
   * @throws InvalidNumberExceptionNegativeOrZero throws an exception if the user input number
   *                    is negative or zero
   * @throws InvalidNumberExceptionNegative throws an exception if the user input is negative
   */

  public Item(String itemNumberInput, String descriptionInput, int priceInput,
              String brandNameInput, double weightInput, double lengthInput,
              double heightInput, String colourInput, int numbersInWarehouseInput,
              Category categoryInput) throws NoInputException, InvalidHexCodeException,
          InvalidNumberExceptionNegativeOrZero, InvalidNumberExceptionNegative {

    if (!isValidInputNotBlank(itemNumberInput) || !isValidInputNotBlank(descriptionInput)
            || !isValidInputNotBlank(brandNameInput)) {
      throw new NoInputException("\n The input cannot be empty");

    } else if (!isValidNumberInputNotNegative(numbersInWarehouseInput)) {
      throw new InvalidNumberExceptionNegative("\n Number input can not be negative");

    } else if (!isValidNumberInputNotNegativeOrZero((double) priceInput)
            || !isValidNumberInputNotNegativeOrZero(weightInput)
            || !isValidNumberInputNotNegativeOrZero(lengthInput)
            || !isValidNumberInputNotNegativeOrZero(heightInput)) {
      throw new InvalidNumberExceptionNegativeOrZero("\n Number input can not be negative or zero");

    } else if (!isValidHexCode(colourInput)) {
      throw new InvalidHexCodeException("not a valid hex code");

    } else {
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
  public Boolean setPrice(final int priceInput) {
    if (priceInput > 0) {
      this.price = priceInput;
      return true;
    }
    return false;
  }

  /**
     *Method that gets and returns the item number.
     *
     * @param numbersInWarehouseInput  item
  */
  public Boolean setNumbersInWarehouse(final int numbersInWarehouseInput) {
    if (numbersInWarehouseInput > 0) {
      this.numbersInWarehouse = numbersInWarehouseInput;
      return true;
    }
    return false;
  }

  /**
   * Method that checks if a sting input is blank or not.
   * @param str string input from the user, to create object item
   * @return boolean true or false, depending on if the input is blank or not
   */
  static Boolean isValidInputNotBlank(String str) {
    return !str.equals(" ");
  }

  /**
   * Method that checks is a double number is bigger than zero.
   * @param number double number input from user, to create object item
   * @return boolean true or false, depending on if the number is bigger than zero
   */
  static Boolean isValidNumberInputNotNegativeOrZero(Double number) {
    return number > 0;
  }

  /**
   * Method that checks if an integer is bigger or equal to zero, or negative.
   * @param number integer number input from user, to create object item
   * @return boolean true or false, depending on if the number is negative or not
   */
  static Boolean isValidNumberInputNotNegative(int number) {
    return number >= 0;
  }

  /**
     *Method that checks if the given hex code is a valid hex code.
     * TODO: write more about functionality of code
  */

  static Boolean isValidHexCode(String colourCode) {
    String hexColourPattern = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$";

    Pattern pattern = Pattern.compile(hexColourPattern);

    Matcher matcher = pattern.matcher(colourCode);
    return matcher.matches();
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
