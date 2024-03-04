package algorithm.kitty.plagiarism.paragraph;

import algorithm.kitty.plagiarism.classes.Count;
import algorithm.kitty.utilities.Constants;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
  public static List<Map<String, Count>> getParagraph(String text) {
    List<Map<String, Count>> paragraph = new ArrayList<>();
    String[] sentences = text.split(Constants.DOT_SPLIT);
    for (String sentence : sentences) {
      Map<String, Count> mapWords = new LinkedHashMap<>();
      String[] words = sentence.trim().split(Constants.SPACE_SPLIT);
      for (String word : words) {
        if (mapWords.containsKey(word)) {
          mapWords.get(word).incrementCount();
          continue;
        }
        mapWords.put(word,
                     new Count());
      }
      paragraph.add(mapWords);
    }
    return paragraph;
  }
}
