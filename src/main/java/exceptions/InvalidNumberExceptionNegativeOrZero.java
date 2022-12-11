package exceptions;

/** Custom exception class InvalidNumberExceptionNegativeOrZero.
 *  Gets called on if a number input is negative or zero.
 */
public class InvalidNumberExceptionNegativeOrZero extends Exception {
  public InvalidNumberExceptionNegativeOrZero(String message) {
    super(message);
  }
}
