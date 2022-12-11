import Exceptions.InvalidHexCodeException;
import Exceptions.InvalidNumberExceptionNegative;
import Exceptions.InvalidNumberExceptionNegativeOrZero;
import Exceptions.NoInputException;
import jdk.jfr.Name;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemTest {
    Item itemDoorFullFunctioning;

    @BeforeEach
    void setUp() throws InvalidNumberExceptionNegativeOrZero, NoInputException, InvalidHexCodeException, InvalidNumberExceptionNegative {
        itemDoorFullFunctioning = new Item("AQ378912", "White Door with one small window", 2050,
                "Swedoor", 5, 0.4, 2.05, "#FFFFFF", 14, Category.DOORS);
    }

    @Nested
    @DisplayName("tests the get methods of the Item class")
    class getMethodsItem {
        @Test
        void getItemNumber() {
            Assertions.assertEquals("AQ378912", itemDoorFullFunctioning.getItemNumber());
        }

        @Test
        void getDescription() {
            Assertions.assertEquals("White Door with one small window", itemDoorFullFunctioning.getDescription());
        }

        @Test
        void getPrice() {
            Assertions.assertEquals(2050, itemDoorFullFunctioning.getPrice());
        }

        @Test
        void getBrandName() {
            Assertions.assertEquals("Swedoor", itemDoorFullFunctioning.getBrandName());
        }

        @Test
        void getWeight() {
            Assertions.assertEquals(5, itemDoorFullFunctioning.getWeight());
        }

        @Test
        void getLength() {
            Assertions.assertEquals(0.4, itemDoorFullFunctioning.getLength());
        }

        @Test
        void getHeight() {
            Assertions.assertEquals(2.05, itemDoorFullFunctioning.getHeight());
        }

        @Test
        void getColour() {
            Assertions.assertEquals("#FFFFFF", itemDoorFullFunctioning.getColour());
        }

        @Test
        void getNumbersInWarehouse() {
            Assertions.assertEquals(14, itemDoorFullFunctioning.getNumbersInWarehouse());
        }

        @Test
        void getCategory() {
            Assertions.assertEquals(Category.DOORS, itemDoorFullFunctioning.getCategory());
        }
    }

    @Nested
    @DisplayName("Test of the set methods with valid input")
    class SetMethodsValidInput {
        @Test
        void SetPriceValid() throws InvalidNumberExceptionNegativeOrZero {
            itemDoorFullFunctioning.setPrice(100);
            Assertions.assertEquals(100, itemDoorFullFunctioning.getPrice());
        }

        @Test
        void setNumbersInWarehouseValidInput() throws InvalidNumberExceptionNegativeOrZero {
            itemDoorFullFunctioning.setNumbersInWarehouse(12);
            Assertions.assertEquals(12, itemDoorFullFunctioning.getNumbersInWarehouse());
        }
    }

    @Nested
    @DisplayName("Test of the set methods with invalid input should throw exceptions")
    class SetMethodsInvalidInputThrowsException {

        @Test
        @Name("Attempt setting the price of the item to a negative price")
        void SetPriceNegative() {

            assertThrows(InvalidNumberExceptionNegativeOrZero.class, () -> {
                itemDoorFullFunctioning.setPrice(-450);
            });
        }

        @Test
        void setNumbersInWarehouseNegative() {
            assertThrows(InvalidNumberExceptionNegative.class, () -> {
                itemDoorFullFunctioning.setNumbersInWarehouse(-2);
            });
        }

    }
        @Nested
        @DisplayName("Tests input validation methods with valid input, should not throw exception")
        class validationMethodsWithValidInput {
            @Test
            void isValidStringInputDescription() {
                Assertions.assertDoesNotThrow(() -> Item.isValidStringInput(itemDoorFullFunctioning.getDescription()));
            }

            @Test
            void isValidNumberInputPrice() {
                Assertions.assertDoesNotThrow(() -> Item.isValidNumberInputNotNegative(itemDoorFullFunctioning.getPrice()));
            }

            @Test
            void isValidNumberInputWeight() {
                Assertions.assertDoesNotThrow(() -> Item.isValidNumberInputNotNegativeOrZero(itemDoorFullFunctioning.getWeight()));
            }

            @Test
            void testIsValidHexCode() {
                Assertions.assertDoesNotThrow(() -> Item.isValidHexCode(itemDoorFullFunctioning.getColour()));
            }
        }

    @Nested
    @DisplayName("Tests input validation methods with invalid input, should throw exception")
    class ValidationMethodsWithInvalidInput {
        @Test
        void invalidStringInputDescription() {

            assertThrows(NoInputException.class, () -> {
                Item itemWithoutOutDescription = new Item("AQ378912", " ", 2050,
                        "Swedoor", 5, 0.4, 2.05, "#FFFFFF", 14, Category.DOORS);

            });

        }

        @Test
        void invalidNumberInput() {

            assertThrows(InvalidNumberExceptionNegativeOrZero.class, () -> {
                Item itemWithZeroWeight = new Item("AQ378912", "White door with small window ", 2050,
                        "Swedoor", 0, 0.4, 2.05, "#FFFFFF", 14, Category.DOORS);

            });

        }

        @Test
        void invalidHexCodeToLong() {
           assertThrows(InvalidHexCodeException.class, () -> {
                Item itemWithTooLongHexCode = new Item("AQ378912", "White door with small window ", 2050,
                        "Swedoor", 5, 0.4, 2.05, "#ABC1233", 14, Category.DOORS);

            });

        }

        @Test
        void invalidHexCodeMissingHashtag() {
            assertThrows(InvalidHexCodeException.class, () -> {
                Item itemWithHexCodeWithoutHashtag = new Item("AQ378912", "White door with small window ", 2050,
                        "Swedoor", 5, 0.4, 2.05, "123454", 14, Category.DOORS);
            });

        }

        @Test
        void invalidHexCodeChars() {
            assertThrows(InvalidHexCodeException.class, () -> {
                Item itemWithHexCodeWithoutHashtag = new Item("AQ378912", "White door with small window ", 2050,
                        "Swedoor", 5, 0.4, 2.05, "#AB!344", 14, Category.DOORS);
            });

        }
    }



}
