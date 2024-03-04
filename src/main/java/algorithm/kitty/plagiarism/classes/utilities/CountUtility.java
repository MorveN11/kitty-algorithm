package algorithm.kitty.plagiarism.classes.utilities;

import algorithm.kitty.plagiarism.classes.Count;

/**
 * Utility class to handle the count of words.
 */
public class CountUtility {

  /**
   * Get the minimum count between two counts.
   *
   * @param firstCount  the first count.
   * @param secondCount the second count.
   * @return the minimum count between the two counts.
   */
  public static int getMinCount(Count firstCount, Count secondCount) {
    if (firstCount.getCount() < secondCount.getCount()) {
      int difference = firstCount.getCount();
      firstCount.setCount(0);
      secondCount.setCount(secondCount.getCount() - difference);
      return difference;
    }
    int difference = secondCount.getCount();
    secondCount.setCount(0);
    firstCount.setCount(firstCount.getCount() - difference);
    return difference;
  }
}
