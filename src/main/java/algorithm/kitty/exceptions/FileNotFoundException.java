package algorithm.kitty.exceptions;

/**
 * Exception thrown when a file is not found.
 */
public class FileNotFoundException extends RuntimeException {

  public FileNotFoundException() {
    super("File not found.");
  }
}
