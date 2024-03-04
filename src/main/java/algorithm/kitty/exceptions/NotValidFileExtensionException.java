package algorithm.kitty.exceptions;

/**
 * Exception thrown when a file doesn't have a txt extension.
 */
public class NotValidFileExtensionException extends RuntimeException {

  public NotValidFileExtensionException() {
    super("Illegal file extension. The file extension must be .txt.");
  }
}
