package algorithm.kitty.plagiarism.paragraph;

import algorithm.kitty.utilities.Constants;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class to convert a paragraph into a list of maps of words and their counts.
 */
public class ParagraphConverter {

  /**
   * Get the paragraph from the text.
   *
   * @param text the text.
   * @return the paragraph.
   */
  public static List<List<String>> getParagraph(String text) {
    List<List<String>> paragraph = new ArrayList<>();
    String[] sentences = text.split(Constants.DOT_SPLIT);
    for (String sentence : sentences) {
      List<String> words = new LinkedList<>(List.of(sentence.trim().split(Constants.SPACE_SPLIT)));
      paragraph.add(words);
    }
    return paragraph;
  }
}
