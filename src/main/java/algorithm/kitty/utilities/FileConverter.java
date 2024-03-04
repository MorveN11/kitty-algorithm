package algorithm.kitty.utilities;

import algorithm.kitty.exceptions.FileNotFoundException;
import algorithm.kitty.exceptions.NotValidFileExtensionException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * This class is responsible for converting a file to a string.
 */
public class FileConverter {

  /**
   * This method converts a file to a string.
   *
   * @param filePath the file path
   * @return the file as a string
   */
  public static String fileToString(String filePath) {
    if (!isTxtFile(filePath)) {
      throw new NotValidFileExtensionException();
    }
    StringBuilder text = new StringBuilder();
    try {
      Path path = Paths.get(filePath);
      List<String> lines = Files.readAllLines(path);
      for (String line : lines) {
        text.append(line).append(Constants.SPACE_SYMBOL);
      }
    } catch (IOException e) {
      throw new FileNotFoundException();
    }
    return removeLastPeriod(text.toString().trim());
  }

  private static String removeLastPeriod(String txt) {
    if (txt.endsWith(Constants.PERIOD_SYMBOL)) {
      return txt.substring(0,
                           txt.length() - 1);
    }
    return txt;
  }

  private static boolean isTxtFile(String filePath) {
    return filePath.endsWith(Constants.TXT_EXTENSION);
  }
}
