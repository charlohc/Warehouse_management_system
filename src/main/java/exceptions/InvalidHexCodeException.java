package exceptions;

/** Custom exception class InvalidHexCodeException.
 *  Gets called on if a colour code input does not fulfill the requirements for
 *  a valid hex code.
 */
public class InvalidHexCodeException extends Exception {
  public InvalidHexCodeException(String message) {
    super(message);
  }
}
