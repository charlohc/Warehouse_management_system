import Exceptions.InvalidHexCodeException;
import Exceptions.InvalidNumberExceptionNegative;
import Exceptions.InvalidNumberExceptionNegativeOrZero;
import Exceptions.NoInputException;
import java.util.ArrayList;


//TODO: legg inn lambda

/**
 * Register class which holds all the items and several important methods such as.
 */
public class ItemRegister {
  private ArrayList<Item> items;

  public ItemRegister(ArrayList<Item> items) {
    this.items = items;
  }

  /**
     *Method that gets and returns the item number.
     *
     * @return item
   */
  public int numbersOfItemsInWarehouse() {
    int totNumberOfItemsWarehouse = 0;

    for (Item item : items) {
      totNumberOfItemsWarehouse ++;
    }
    return totNumberOfItemsWarehouse;
  }

  //TODO: when checking need to make sure of upper and lowercase,
  // checks for both but does it work when one is blank?


  /**
     *Method that gets and returns the item number.
     *
     * @return item
  */

  //TODO: lag teksten til en array hvor splitter på mellom rom, og hvis den inneholder 2/3 av søke ordene gi som forslag
  //send da en array med tekst forslag, negativt kan få ganske høy tidskompleksitet hvis veldig mage varer i vare huset
  //TODO: burde ha et tall, hvis 1 søker ved hjelp av beskrivelse hvis 2 med nummer - burde bare ha to separate metoder?
  public ArrayList<Item> findItemBasedOnDescription(String itemDescription) {
    ArrayList<Item> itemMatchesDescription = new ArrayList<>();

    for(Item item : items) {
      if(item.getDescription().equalsIgnoreCase(itemDescription)) {
        itemMatchesDescription.add(item);
      }
    }
    return itemMatchesDescription;
  }

  public Item findItemBasedOnItemNumber(String itemNumber){
    for (Item item : items) {
      if (item.getItemNumber().equalsIgnoreCase(itemNumber)) {
        return item;
      }
    }
    return null;
  }


  /**
     *Method that gets and returns the item number.
     *
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
  */
  public Boolean increaseInItemInventory(Item item, int numbersOfItemsIncrease) throws InvalidNumberExceptionNegativeOrZero {
    if (numbersOfItemsIncrease <= 0){
      return false;
    }else {
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
  public Boolean decreaseInItemInventory(Item item, int numbersOfItemsDecrease) throws InvalidNumberExceptionNegativeOrZero {
    int newItemInventory = item.getNumbersInWarehouse() - numbersOfItemsDecrease;
    if (newItemInventory < 0) {
      return false;
    }
    item.setNumbersInWarehouse(newItemInventory);
    return true;
  }


  /**
     *Method that gets and returns the item number.
     *
  */
  //Todo: need o check if it is in the inventory? tries to delete
  public void removeItemFromInventory(Item item) {
    items.remove(item);
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
    return item.getPrice() - (item.getPrice() * discountPercentage);
  }


  /**
     *Method that gets and returns the item number.
     *
     * @param item item
     * @param newPrice price
     * @return item
  */
  public int newPrice(Item item, int newPrice) throws InvalidNumberExceptionNegativeOrZero {
    item.setPrice(newPrice);
    return item.getPrice();
  }


  /**
     *Method that gets and returns the item number.
     *
     * @return item
  */
  @Override
  public String toString() {
    return "ItemRegister" + "" + "items" + items + ", numbersOfItems=" + items.size() ;
  }
}
