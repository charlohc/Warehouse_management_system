import Exceptions.InvalidHexCodeException;
import Exceptions.InvalidNumberExceptionNegative;
import Exceptions.InvalidNumberExceptionNegativeOrZero;
import Exceptions.NoInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ItemRegisterTest {
    Item itemDoorFullFunctioning, itemWindowMedium, itemWindowBig, itemFloorLaminate;
    ItemRegister itemRegister;
    ArrayList<Item> items = new ArrayList<>();

    @BeforeEach
    void setUp() throws InvalidNumberExceptionNegativeOrZero, NoInputException, InvalidHexCodeException, InvalidNumberExceptionNegative {

        itemDoorFullFunctioning = new Item("AQ378912", "White Door with one small window", 2050,
                "Swedoor", 5, 0.4, 2.05, "#FFFFFF", 14, Category.DOORS);

        itemWindowBig = new Item("ZU34436", "Black window", 6000, "Skanva", 12, 1,
                1, "#000000",5,Category.WINDOWS);
        itemWindowMedium = new Item("ZZ214356", "Black window", 4299, "Skanva", 8, 0.6,
                0.6, "#000000",12,Category.WINDOWS);

        itemFloorLaminate = new Item("IL2239", "Brown floorLaminate", 1499, "Montér",
                0.5, 1, 0.05, "#7D6C5B", 188, Category.FLOORLAMINATES);


        items.add(itemDoorFullFunctioning);
        items.add(itemWindowMedium);
        items.add(itemWindowBig);
        items.add(itemFloorLaminate);

        itemRegister = new ItemRegister(items);
    }

    @Test
    void numbersOfItemsInWarehouse() {
        Assertions.assertEquals(4, itemRegister.numbersOfItemsInWarehouse());
    }

    //søker etter varnummer eller beskrivelse, hvis søker etter varenummer da får man bare opp hvis riktig
    //søker på beskrivelse, kan få opp flere
    @Test
    void findItemBasedOnNumber() {
        Assertions.assertEquals(itemWindowBig, itemRegister.findItemBasedOnItemNumber("ZU34436"));
    }

    @Test
    void findItemBasedOnNumberNoMatch() {
        assertNull(itemRegister.findItemBasedOnItemNumber("123321"));
    }

    @Test
    void findItemBasedOnDescription() {
        Assertions.assertEquals(2, itemRegister.findItemBasedOnDescription("Black window").size());
    }

    @Test
    void findItemBasedOnDescriptionNoMatch() {
        Assertions.assertEquals(0, itemRegister.findItemBasedOnDescription("Yellow floorlaminate").size());
    }


    @Test
    void newItemSuccess() throws InvalidNumberExceptionNegativeOrZero, NoInputException, InvalidNumberExceptionNegative, InvalidHexCodeException {
        int numbersInWareHouse = itemRegister.numbersOfItemsInWarehouse();
        Item itemLumber = new Item("QW21345", "Brown lumber", 2999, "Timber CO",
                12, 3, 0.3, "#371D10", 400, Category.LUMBER);

        itemRegister.newItem(itemLumber);

        assertEquals(itemRegister.numbersOfItemsInWarehouse(), (numbersInWareHouse + 1));
    }

    @Test
    @DisplayName("Attempt at adding item with exsisting itemnumber to the register twice, gives an exception")

    void newItemFailure() throws InvalidNumberExceptionNegativeOrZero, NoInputException, InvalidNumberExceptionNegative, InvalidHexCodeException {

        Item itemLumber = new Item("AQ378912", "Brown lumber", 2999, "Timber CO",
                12, 3, 0.3, "#371D10", 400, Category.LUMBER);

        Assertions.assertFalse(itemRegister.newItem(itemLumber));
    }

    @Test
    void increaseInItemInventory() throws InvalidNumberExceptionNegativeOrZero {
        int inventorySize = itemWindowBig.getNumbersInWarehouse();
        itemRegister.increaseInItemInventory(itemWindowBig, 10);

        Assertions.assertEquals(itemWindowBig.getNumbersInWarehouse(), (inventorySize + 10));
    }

    @Test
    void increaseInItemInventoryFail() throws InvalidNumberExceptionNegativeOrZero {
        int inventorySize = itemWindowBig.getNumbersInWarehouse();

        itemRegister.increaseInItemInventory(itemWindowBig, -1);

        Assertions.assertEquals(inventorySize,itemWindowBig.getNumbersInWarehouse());

    }

    @Test
    void decreaseInItemInventory() {
    }

    @Test
    void removeItemFromInventory() {
    }

    @Test
    void discount() {
    }

    @Test
    void newPrice() {
    }
}