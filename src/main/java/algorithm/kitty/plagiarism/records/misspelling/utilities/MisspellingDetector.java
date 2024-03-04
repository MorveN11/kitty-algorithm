package algorithm.kitty.plagiarism.records.misspelling.utilities;

import algorithm.kitty.utilities.Constants;

/**
 * Utility class to detect misspelling between two words.
 */
public class MisspellingDetector {

  /**
   * Check if the two words are misspelled.
   *
   * @param firstWord  the first word.
   * @param secondWord the second word.
   * @return true if the two words are misspelled, false otherwise.
   */
  public static boolean isMisspelling(String firstWord, String secondWord) {
    int firstWordLength = firstWord.length();
    int secondWordLength = secondWord.length();
    int longestCommonSubsequence = lcs(firstWord,
                                       firstWordLength,
                                       secondWord,
                                       secondWordLength);
    int maxWordLength = Math.max(firstWordLength,
                                 secondWordLength);
    float misspellingPercentage =
            (float) (longestCommonSubsequence * Constants.PERCENTAGE) / maxWordLength;
    return misspellingPercentage >= Constants.MISSPELLING_THRESHOLD;
  }

  /**
   * Get the longest common subsequence between two words. Time complexity: O(n*m).
   *
   * @param firstWord        the first word.
   * @param firstWordLength  the length of the first word.
   * @param secondWord       the second word.
   * @param secondWordLength the length of the second word.
   * @return the longest common subsequence between the two words.
   */
  private static int lcs(String firstWord, int firstWordLength, String secondWord,
                         int secondWordLength) {
    int[][] dp = new int[firstWordLength + 1][secondWordLength + 1];
    for (int i = 1; i <= firstWordLength; i++) {
      for (int j = 1; j <= secondWordLength; j++) {
        dp[i][j] = Math.max(dp[i - 1][j],
                            dp[i][j - 1]);
        if (firstWord.charAt(i - 1) == secondWord.charAt(j - 1)) {
          dp[i][j] = Math.max(dp[i][j],
                              dp[i - 1][j - 1] + 1);
        }
      }
    }
    return dp[firstWordLength][secondWordLength];
  }
}
