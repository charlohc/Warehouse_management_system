import java.util.ArrayList;


/**
 * Register class which holds all the items and several important methods such as.
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
     *Method that gets and returns the item number.
     *
     * @return item
   */
  public int numberOfDifferentItemsInWarehouse() {
    return items.size();
  }

  /**
   *Method that gets and returns the item number.
   *
   * @return item
   */
  public int totalNumberOfItemsInWarehouse() {
    int totalNumberOfEachItemType = 0;

    for (Item itemType : items) {
      totalNumberOfEachItemType += itemType.getNumbersInWarehouse();
    }
    return totalNumberOfEachItemType;
  }

  /**
     *Method that gets and returns the item number.
     *
     * @return item
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
   *Method that gets and returns the item number.
   *
   * @return item
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
     *Method that gets and returns the item number.
     * kunne også brukt metoden ovenfor
     * @return item
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
     *Method that gets and returns the item number.
     *
     *
  */
  public Boolean increaseNumbersOfItemsOfTypeInWarehouse(String itemNumber, int numbersOfItemsIncrease) {
    Item item = this.findItemBasedOnItemNumber(itemNumber);
    if (numbersOfItemsIncrease < 0 ) {
      return false;
    } else {
      item.setNumbersInWarehouse(item.getNumbersInWarehouse() + numbersOfItemsIncrease);
      return true;
    }
  }

  /**
     *Method that gets and returns the item number.
     *
     * @param itemNumber item.
     * @param numbersOfItemsDecrease item.
     * @return item
  */
  //TODO: make sure that inventory is not negative
  public Boolean decreaseNumbersOfItemsOfTypeInWarehouse(String itemNumber, int numbersOfItemsDecrease) {
    Item item = this.findItemBasedOnItemNumber(itemNumber);
    if (numbersOfItemsDecrease < 0 || (item.getNumbersInWarehouse() - numbersOfItemsDecrease) < 0) {
      return false;
    }
    item.setNumbersInWarehouse((item.getNumbersInWarehouse() - numbersOfItemsDecrease));
    return true;
  }


  /**
     *Method that gets and returns the item number.
     * Removes the item type and every instance of it in the warehouse
     *
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
     *Method that gets and returns the item number.
     *
     * @param itemNumberDiscount item
     * @param discountPercentage param
     * @return item
  */
  public int discount(String itemNumberDiscount, float discountPercentage) {
    Item item = this.findItemBasedOnItemNumber(itemNumberDiscount);
    if (discountPercentage < 0 || item == null) {
      return -1;
    }
    float discountInKr = ((float)item.getPrice() / 100) * discountPercentage;
    return (int) discountInKr;
  }

  /**
     *Method that gets and returns the item number.
     *
     * @param itemNumberNewPrice item
     * @param newPrice price
     * @return item
  */
  public int newPrice(String itemNumberNewPrice, int newPrice)  {
    Item item = this.findItemBasedOnItemNumber(itemNumberNewPrice);
    if (newPrice <= 0 || item == null) {
      return -1;
    }
    item.setPrice(newPrice);
    return item.getPrice();
  }

  public String itemsWithoutBracketsFromList() {
    StringBuilder sb = new StringBuilder();
    for (Item item : items) {
      sb.append(item).append(" ");
    }
    return sb.toString();
  }

  /**
     *Method that gets and returns the item number.
     *
     * @return item
  */
  @Override
  public String toString() {
    return "The items in the item-register are the following. " + "\n "+ itemsWithoutBracketsFromList();
  }
}
