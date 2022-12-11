package exceptions;

/** Custom exception class NoInputException.
 *  Gets called on if a string input is blank.
 */
public class NoInputException extends Exception {
  public NoInputException(String message) {
    super(message);
  }
}
