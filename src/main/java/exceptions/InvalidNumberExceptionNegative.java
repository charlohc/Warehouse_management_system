package exceptions;

/** Custom exception class InvalidNumberExceptionNegative.
 *  Gets called on if a number input is negative.
 */
public class InvalidNumberExceptionNegative extends Exception {
  public InvalidNumberExceptionNegative(String message) {
    super(message);
  }
}
