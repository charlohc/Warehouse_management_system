import exceptions.InvalidHexCodeException;
import exceptions.InvalidNumberExceptionNegative;
import exceptions.InvalidNumberExceptionNegativeOrZero;
import exceptions.NoInputException;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ItemRegisterTest {
    Item itemDoor, itemWindowMedium, itemWindowBig, itemFloorLaminateNotInRegister;
    ItemRegister itemRegister;
    ArrayList<Item> items = new ArrayList<>();

    @BeforeEach
    void setUp() throws InvalidNumberExceptionNegativeOrZero, NoInputException, InvalidHexCodeException, InvalidNumberExceptionNegative {

        itemDoor = new Item("AQ378912", "White Door with one small window", 2050,
                "Swedoor", 5, 0.4, 2.05, "#FFFFFF", 14, Category.DOORS);

        itemWindowBig = new Item("ZU34436", "Black window", 6000, "Skanva", 12, 1,
                1, "#000000",5,Category.WINDOWS);
        itemWindowMedium = new Item("ZZ214356", "Black window", 4299, "Skanva", 8, 0.6,
                0.6, "#000000",12,Category.WINDOWS);

        itemFloorLaminateNotInRegister = new Item("IL2239", "Brown floorLaminate", 1499, "Montér",
                0.5, 1, 0.05, "#7D6C5B", 188, Category.FLOORLAMINATES);


        items.add(itemDoor);
        items.add(itemWindowMedium);
        items.add(itemWindowBig);

        itemRegister = new ItemRegister(items);
    }

    @Nested
    @DisplayName("tests the metods of the itemRegister class with valid input")
    class validInputMethods {

        @Test
        void numbersOfDifferentItemsInWarehouse() {
            Assertions.assertEquals(3, itemRegister.numberOfDifferentItemsInWarehouse());
        }

        @Test
        void totalNumberOfItemsInWarehouse() {
            int totalNumbersOfItemsEachType = itemDoor.getNumbersInWarehouse() + itemWindowMedium.getNumbersInWarehouse()
                    + itemWindowBig.getNumbersInWarehouse();

            Assertions.assertEquals(totalNumbersOfItemsEachType, itemRegister.totalNumberOfItemsInWarehouse());
        }

        //søker etter varnummer eller beskrivelse, hvis søker etter varenummer da får man bare opp hvis riktig
        //søker på beskrivelse, kan få opp flere
        @Test
        void findItemBasedOnNumber() {
            Assertions.assertEquals(itemWindowBig, itemRegister.findItemBasedOnItemNumber("ZU34436"));
        }

        @Test
        void findItemBasedOnDescription() {
            Assertions.assertEquals(2, itemRegister.findItemBasedOnDescription("Black window").size());
        }

        @Test
        void newItemSuccess() throws InvalidNumberExceptionNegativeOrZero, NoInputException, InvalidNumberExceptionNegative, InvalidHexCodeException {
            int numbersInWareHouse = itemRegister.numberOfDifferentItemsInWarehouse();
            Item itemLumber = new Item("QW21345", "Brown lumber", 2999, "Timber CO",
                    12, 3, 0.3, "#371D10", 400, Category.LUMBER);

            itemRegister.newItem(itemLumber);

            assertEquals(itemRegister.numberOfDifferentItemsInWarehouse(), (numbersInWareHouse + 1));
        }

        @Test
        void increaseInItemInventory() {
            int inventorySize = itemWindowBig.getNumbersInWarehouse();
            itemRegister.increaseNumbersOfItemsOfTypeInWarehouse(itemWindowBig, 10);

            Assertions.assertEquals(itemWindowBig.getNumbersInWarehouse(), (inventorySize + 10));
        }

        @Test
        void decreaseInItemInventory() {
            int numbersOfItemsWindowBigInWareHouse = itemWindowBig.getNumbersInWarehouse();
            itemRegister.decreaseNumbersOfItemsOfTypeInWarehouse(itemWindowBig, 3);
            Assertions.assertEquals((numbersOfItemsWindowBigInWareHouse - 3), itemWindowBig.getNumbersInWarehouse());
        }


        @Test
        void removeItemFromInventory() {
            int numbersOfItemsInRegister = itemRegister.numberOfDifferentItemsInWarehouse();
            itemRegister.removeItemFromInventory(itemDoor);
             Assertions.assertEquals((numbersOfItemsInRegister - 1), itemRegister.numberOfDifferentItemsInWarehouse());
        }

        @Test
        void discount() {
            Assertions.assertEquals((itemWindowBig.getPrice()/4), itemRegister.discount(itemWindowBig, 25));
        }

        @Test
        void newPrice() {
            itemRegister.newPrice(itemWindowBig, 5000);
            Assertions.assertEquals(5000, itemWindowBig.getPrice());
        }
    }

    @Nested
    @DisplayName("tests the methods of the itemRegister class with invalid input, should throw exception, return false or return null")
    class invalidInputMethods{

    @Test
    void findItemBasedOnNumberNoMatch() {
            assertNull(itemRegister.findItemBasedOnItemNumber("123321"));
        }
    }

    @Test
    void findItemBasedOnDescriptionNoMatch() {
        Assertions.assertEquals(0, itemRegister.findItemBasedOnDescription("Yellow floorlaminate").size());
    }

    @Test
    @DisplayName("Attempt at adding item with exsisting itemnumber to the register twice, gives an exception")
    void newItemFailure() throws InvalidNumberExceptionNegativeOrZero, NoInputException, InvalidNumberExceptionNegative, InvalidHexCodeException {

        Item itemLumber = new Item("AQ378912", "Brown lumber", 2999, "Timber CO",
                12, 3, 0.3, "#371D10", 400, Category.LUMBER);

        Assertions.assertFalse(itemRegister.newItem(itemLumber));
    }

    @Test
    @DisplayName("Attempt at setting a negative increase to the inventory in warehouse, should return negative and not allow increase")
    void negativeIncreaseInItemInventory() {
        int inventorySize = itemWindowBig.getNumbersInWarehouse();
        itemRegister.increaseNumbersOfItemsOfTypeInWarehouse(itemWindowBig, -1);
        Assertions.assertEquals(inventorySize, itemWindowBig.getNumbersInWarehouse());

    }

    @Test
    @DisplayName("Attempt at setting a negative decrease to the inventory in warehouse, should return negative and not allow increase")
    void negativeDecreaseInInventory() {
        Assertions.assertFalse(itemRegister.decreaseNumbersOfItemsOfTypeInWarehouse(itemWindowBig, -1));
    }

    @Test
    @DisplayName("Attempt at removing an item that does not exist")
    void removeNonExistingItem(){
       Assertions.assertFalse(itemRegister.removeItemFromInventory(itemFloorLaminateNotInRegister));
    }
    
    @Test
    void discountNegativePercent() {
        Assertions.assertEquals(-1,itemRegister.discount(itemWindowBig, -10));
    }
    @Test
    void newPriceNegative() {
        int oldPrice = itemWindowBig.getPrice();
        itemRegister.newPrice(itemWindowBig, -100);
        Assertions.assertEquals(oldPrice, itemWindowBig.getPrice());
    }

}