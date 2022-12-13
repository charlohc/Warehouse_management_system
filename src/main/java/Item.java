import exceptions.InvalidNumberExceptionNegative;
import exceptions.InvalidNumberExceptionNegativeOrZero;
import exceptions.NoInputException;

/**
 * Entity class Item which initializes an item based on price,
 * brand name, weight, length, colour,
 * how many of the item which is in the
 * warehouse and the category which the item is in.
 */
public class Item {
  private String itemNumber;
  private Category category;
  private Colour colour;
  private String description;
  private int price;
  private String brandName;
  private double weight;
  private double length;
  private double height;
  private int numbersInWarehouse;

  /**
   * Explanation of all the characteristics of an item.
   *
   *  @param itemNumberInput itemNumberInput ,unique code which identifies each item,
   *                   consists of letters and numbers.
   * @param categoryInput an enum from the Category class, from the class you can choose from four
   *                   different categories: floorlaminates, windows, doors and lumber
   * @param colourInput an enum from the Colour class, from the class you can choose six different
   *                    colours for the items, black, white, grey, brown, blue or red
   * @param descriptionInput a short text that describes the item
   * @param priceInput an integer which represents the price of the item
   * @param brandNameInput a string that contains the brand of the item
   * @param weightInput a decimal number that describes the weight of the item in kilo grams
   * @param lengthInput a decimal number that describes the length of the item in cm
   * @param heightInput a decimal number that describes the height of the item in cm
   * @param numbersInWarehouseInput an Integer that tells how many of that item that is in stock
   *                    in the warehouse
   * @throws NoInputException throws an exception if the input from user is blank
   * @throws InvalidNumberExceptionNegativeOrZero throws an exception if the user input number
   *                    is negative or zero
   * @throws InvalidNumberExceptionNegative throws an exception if the user input is negative
   */

  public Item(String itemNumberInput, Category categoryInput, Colour colourInput,
              String descriptionInput, int priceInput,
              String brandNameInput, double weightInput, double lengthInput,
              double heightInput, int numbersInWarehouseInput) throws NoInputException,
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

    } else {
      this.itemNumber = itemNumberInput;
      this.category = categoryInput;
      this.colour = colourInput;
      this.description = descriptionInput;
      this.price = priceInput;
      this.brandName = brandNameInput;
      this.weight = weightInput;
      this.length = lengthInput;
      this.height = heightInput;
      this.numbersInWarehouse = numbersInWarehouseInput;
    }
  }

  public Item() {
  }

  public String getItemNumber() {
    return itemNumber;
  }

  public Category getCategory() {
    return category;
  }

  public Colour getColour() {
    return colour;
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

  public int getNumbersInWarehouse() {
    return numbersInWarehouse;
  }


  /**
    *Method that sets the price of an item.
    *
    * @param priceInput integer which represents the new price
    * @return boolean true or false,
    * depending on if the setting of price is successful
  */
  public Boolean setPrice(int priceInput) {
    if (priceInput > 0) {
      this.price = priceInput;
      return true;
    }
    return false;
  }

  /**
    *Method that sets the amount of items of an item type in the warehouse.
    *
    * @param numbersInWarehouseInput  integer which represents the new numbers in warehouse number
    * @return boolean true or false,
    * depending on if the setting of numbers in warehouse is
    * successful
  */
  public Boolean setNumbersInWarehouse(int numbersInWarehouseInput) {
    if (numbersInWarehouseInput > 0) {
      this.numbersInWarehouse = numbersInWarehouseInput;
      return true;
    }
    return false;
  }

  /**
   * Method that checks if a sting input is blank or not.
   *
   * @param str string input from the user, to create object item
   * @return boolean true or false, depending on if the input is blank or not
   */
  static Boolean isValidInputNotBlank(String str) {
    return str.trim().length() != 0;
  }

  /**
   * Method that checks is a double number is bigger than zero.
   *
   * @param number double number input from user, to create object item
   * @return boolean true or false, depending on if the number is bigger than zero
   */
  static Boolean isValidNumberInputNotNegativeOrZero(Double number) {
    return number > 0;
  }

  /**
   * Method that checks if an integer is bigger or equal to zero, or negative.
   *
   * @param number integer number input from user, to create object item
   * @return boolean true or false, depending on if the number is negative or not
   */
  static Boolean isValidNumberInputNotNegative(int number) {
    return number >= 0;
  }

  /**
   * Methode that calculates the length of an integer.
   *
   * @param number integer which want to find out length
   * @return integer length of number
   */
  public int lengthOfNumberInputInteger(Integer number) {
    return (int) (Math.log10(number) + 1);
  }

  /**
   * Method that calculates the length of a decimal number.
   *
   * @param number double which want to find out length
   * @return integer length of number
   */
  public int lengthOfNumberInputDouble(Double number) {
    String numberToString = number.toString();
    char[] lengthOfNumberString = numberToString.toCharArray();
    return lengthOfNumberString.length;
  }

  /**
   * Methode that creates and returns a visual representation of an item.
   *
   * @return a string containing information about item
   */
  @Override
  public String toString() {
    return "\n"
            +  "+" + "-".repeat(20) + " + " + "-".repeat(35) + "+" + "\n"
                + "|" +  "ItemNumber " + " ".repeat(10) + "| " + itemNumber
            + " ".repeat(36 - itemNumber.length()) + "|" + " \n"
            +  "+" + "-".repeat(20) + " + " + "-".repeat(35) + "+" + "\n"
            + "| " +  "Category" + " ".repeat(12) + "| " + category
            + " ".repeat(36 - category.toString().length()) + "|" + " \n"
            + "| " +  "Colour" + " ".repeat(14) + "| " + colour
            + " ".repeat(36 - colour.toString().length()) + "|" + " \n"
            + "| " +  "Description" + " ".repeat(9) + "| " + description
            + " ".repeat(36 - description.length()) + "|" + " \n"
            + "| " +  "Price (kr)" + " ".repeat(10) + "| " + price
            + " ".repeat(36 - lengthOfNumberInputInteger(price)) + "|" + " \n"
            + "| " +  "Brand" + " ".repeat(15) + "| " + brandName
            + " ".repeat(36 - brandName.length()) + "|" + " \n"
            + "| " +  "Weight (kg)" + " ".repeat(9) + "| " + weight
            + " ".repeat(36 - lengthOfNumberInputDouble(weight)) + "|" + " \n"
            + "| " +  "Length (m)" + " ".repeat(10) + "| " + length
            + " ".repeat(36 - lengthOfNumberInputDouble(length)) + "|" + " \n"
            + "| " +  "Height (m)" + " ".repeat(10) + "| " + height
            + " ".repeat(36 - lengthOfNumberInputDouble(height)) + "|" + " \n"
            + "| " +  "Stock in warehouse" + " ".repeat(2) + "| " + numbersInWarehouse
            + " ".repeat(36 - lengthOfNumberInputInteger(numbersInWarehouse)) + "|" + " \n"
            + "+" + "-".repeat(20) + " + " + "-".repeat(35) + "+"  + "\n";

  }


}
