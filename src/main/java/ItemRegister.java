import java.util.ArrayList;


//TODO: legg inn lambda, legg inn slik at konsekvent true eller false return først

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
     * TODO: write comment that says allow increase and decrease with 0 incase of user
     *
  */
  public Boolean increaseNumbersOfItemsOfTypeInWarehouse(Item item, int numbersOfItemsIncrease) {
    if (numbersOfItemsIncrease < 0) {
      return false;
    } else {
      item.setNumbersInWarehouse(item.getNumbersInWarehouse() + numbersOfItemsIncrease);
      return true;
    }
  }

  /**
     *Method that gets and returns the item number.
     *
     * @param item item.
     * @param numbersOfItemsDecrease item.
     * @return item
  */
  //TODO: make sure that inventory is not negative
  public Boolean decreaseNumbersOfItemsOfTypeInWarehouse(Item item, int numbersOfItemsDecrease) {
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
  public boolean removeItemFromInventory(Item itemToBeRemoved) {
    if (items.contains(itemToBeRemoved)) {
      items.remove(itemToBeRemoved);
      return true;
    }
    return false;
  }

  /**
     *Method that gets and returns the item number.
     *
     * @param item item
     * @param discountPercentage param
     * @return item
  */
  //TODO: explain why divided discount, price and description into three methods instead of one
  public int discount(Item item, int discountPercentage) {
    if (discountPercentage >= 0) {
      return (item.getPrice() / 100) * discountPercentage;
    }

    return -1;
  }

  /**
     *Method that gets and returns the item number.
     *
     * @param item item
     * @param newPrice price
     * @return item
  */
  public int newPrice(Item item, int newPrice)  {
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
     *TODO: må gjøre til kul boks
     * @return item
  */
  @Override
  public String toString() {
    return "The items in the item-register are the following. " + "\n "+ itemsWithoutBracketsFromList();
  }
}
