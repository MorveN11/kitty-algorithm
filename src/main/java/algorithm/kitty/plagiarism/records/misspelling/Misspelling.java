package algorithm.kitty.plagiarism.records.misspelling;

import algorithm.kitty.plagiarism.records.misspelling.utilities.MisspellingDetector;
import algorithm.kitty.utilities.Constants;

/**
 * Record to represent a misspelling between two words.
 *
 * @param firstWord  the first word.
 * @param secondWord the second word.
 */
public record Misspelling(String firstWord, String secondWord) {

  /**
   * Verify if the second word is a possible misspelling of the first word.
   *
   * @return true if the second word is a possible misspelling of the first word, false otherwise.
   */
  public boolean isMisspelling() {
    if (this.firstWord.length() < this.secondWord.length()) {
      return false;
    }
    return MisspellingDetector.isMisspelling(this.firstWord.toLowerCase(),
                                             this.secondWord.toLowerCase());
  }

  @Override
  public String toString() {
    return "%s%s%s".formatted(this.secondWord,
                              Constants.MISSPELLING_SEPARATOR,
                              this.firstWord);
  }
}
