import java.util.ArrayList;


/**
 * Item Register class which holds the different items in the register.
 */
public class ItemRegister {
  private final ArrayList<Item> items;

  public ItemRegister() {
    this.items = new ArrayList<Item>();
  }

  public ItemRegister(ArrayList<Item> items) {
    this.items = items;
  }

  /**
   *Method that returns the amount of different types of items in the register.
   *
   * @return an int which represents the number of item types
   */
  public int numberOfDifferentItemsInWarehouse() {
    return items.size();
  }

  /**
   *Method that returns the total amount of items stored in the register.
   *
   * @return an int which represents the amount of items stored
   */
  public int totalNumberOfItemsInWarehouse() {
    int totalNumberOfEachItemType = 0;

    for (Item itemType : items) {
      totalNumberOfEachItemType += itemType.getNumbersInWarehouse();
    }
    return totalNumberOfEachItemType;
  }

  /**
   *Method that attempts to find a list of item that matches the description from user.
   *
   * @param itemDescription string which unique represents item
   * @return a list with all the matching items
   */
  public ArrayList<Item> findItemBasedOnDescription(String itemDescription) {
    ArrayList<Item> itemMatchesDescription = new ArrayList<>();

    for (Item item : items) {
      if (item.getDescription().equalsIgnoreCase(itemDescription)) {
        itemMatchesDescription.add(item);
      }
    }
    return itemMatchesDescription;
  }

  /**
   *Method that attempts to find an item that matches the item number from user.
   *
   * @param itemNumber string which represents unique item
   * @return item or null, depending on if the method finds a match
   */
  public Item findItemBasedOnItemNumber(String itemNumber) {
    for (Item item : items) {
      if (item.getItemNumber().equalsIgnoreCase(itemNumber)) {
        return item;
      }
    }
    return null;
  }

  /**
   *Method that adds a new item to the register if the item is not already in the register.
   *
   * @param newItem an item
   * @return boolean true or false depending on if the item gets added to the register or not
   */
  public Boolean newItem(Item newItem) {
    for (Item itemsInList : items) {
      if (itemsInList.getItemNumber().equals(newItem.getItemNumber())) {
        return false;
      }
    }
    items.add(newItem);
    return true;
  }

  /**
   *Method that increases the amount of items of a type in the storage.
   *
   * @param itemNumber the item number of the item type that is getting increased
   * @param numbersOfItemsIncrease int representing numbers of items to increase
   * @return boolean true or false depending on if the increase is successful or not
   */
  public Boolean increaseNumbersOfItemsOfTypeInWarehouse(String itemNumber,
                                                         int numbersOfItemsIncrease) {
    Item item = this.findItemBasedOnItemNumber(itemNumber);
    if (numbersOfItemsIncrease < 0) {
      return false;
    } else {
      item.setNumbersInWarehouse(item.getNumbersInWarehouse() + numbersOfItemsIncrease);
      return true;
    }
  }

  /**
     *Method that decreases the amount of items of a type in the storage.
     *
     * @param itemNumber the item number of the item type that is getting decreased
     * @param numbersOfItemsDecrease int representing numbers of items to decrease
     * @return boolean true or false depending on if the decrease is successful or not
  */
  public Boolean decreaseNumbersOfItemsOfTypeInWarehouse(String itemNumber,
                                                         int numbersOfItemsDecrease) {
    Item item = this.findItemBasedOnItemNumber(itemNumber);
    if (numbersOfItemsDecrease < 0 || (item.getNumbersInWarehouse() - numbersOfItemsDecrease) < 0) {
      return false;
    }
    item.setNumbersInWarehouse((item.getNumbersInWarehouse() - numbersOfItemsDecrease));
    return true;
  }

  /**
   *Method that removes an item type from the register.
   *
   * @param itemNumberItemToBeRemoved the item number of the item type that is getting removed
   * @return boolean true or false depending on if the removal is successful or not
   */
  public boolean removeItemFromInventory(String itemNumberItemToBeRemoved) {
    Item itemToBeRemoved = this.findItemBasedOnItemNumber(itemNumberItemToBeRemoved);
    if (items.contains(itemToBeRemoved)) {
      items.remove(itemToBeRemoved);
      return true;
    }
    return false;
  }

  /**
     *Method that calculates a given discount percentage on a given item.
     *
     * @param itemNumberDiscount the item number of the item type that is getting discount
     * @param discountPercentage integer that represents discount
     * @return integer discount or -1 depending on if the discount calculation was successful
  */
  public int discount(String itemNumberDiscount, float discountPercentage) {
    Item item = this.findItemBasedOnItemNumber(itemNumberDiscount);
    if (discountPercentage < 0 || item == null) {
      return -1;
    }
    float discountInKr = ((float) item.getPrice() / 100) * discountPercentage;
    return (int) discountInKr;
  }

  /**
     *Method that lets the user set the price of an item.
     *
     * @param itemNumberNewPrice the item number of the item that is getting new price
     * @param newPrice integer new price
     * @return the new price or -1 depending on if the new price setting was successful
  */
  public int newPrice(String itemNumberNewPrice, int newPrice)  {
    Item item = this.findItemBasedOnItemNumber(itemNumberNewPrice);
    if (newPrice <= 0 || item == null) {
      return -1;
    }
    item.setPrice(newPrice);
    return item.getPrice();
  }

  /**
   *Method that creates a string with all the items, without the brackets at the end.
   *
   * @return string list with items
   */
  public String itemsWithoutBracketsFromList() {
    StringBuilder sb = new StringBuilder();
    for (Item item : items) {
      sb.append(item).append(" ");
    }
    return sb.toString();
  }

  /**
     *Method that returns a string list of all the items.
     *
     * @return string list with items
  */
  @Override
  public String toString() {
    return "The items in the item-register are the following. "
            + "\n " + itemsWithoutBracketsFromList();
  }
}
