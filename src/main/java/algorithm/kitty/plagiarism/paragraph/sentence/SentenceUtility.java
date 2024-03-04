package algorithm.kitty.plagiarism.paragraph.sentence;

import algorithm.kitty.plagiarism.classes.Count;
import java.util.Map;

/**
 * Utility class to handle the sentences.
 */
public class SentenceUtility {

  public static void resetSentence(Map<String, Count> sentence) {
    sentence.forEach((word, count) -> count.resetCount());
  }

  /**
   * Get the size of the sentence.
   *
   * @param sentence the sentence.
   * @return the size of the sentence.
   */
  public static int getSentenceSize(Map<String, Count> sentence) {
    int size = 0;
    for (Map.Entry<String, Count> entry : sentence.entrySet()) {
      size += entry.getValue().getCount();
    }
    return size;
  }
}
