package algorithm.kitty.plagiarism;

import algorithm.kitty.plagiarism.enums.Direction;
import algorithm.kitty.plagiarism.paragraph.ParagraphConverter;
import algorithm.kitty.plagiarism.records.misspelling.Misspelling;
import algorithm.kitty.plagiarism.records.plagiarism.Plagiarism;
import algorithm.kitty.utilities.Constants;
import algorithm.kitty.utilities.FileConverter;
import java.util.ArrayList;
import java.util.List;

/**
 * PlagiarismDetector class that detects plagiarism between two paragraphs.
 */
public class PlagiarismDetector {

  private final List<List<String>> firstParagraph;

  private final List<List<String>> secondParagraph;

  private List<Misspelling> misspellings = new ArrayList<>();

  private float maxPlagiarismPercentage = Constants.DEFAULT_PERCENTAGE;

  /**
   * PlagiarismDetector constructor that initializes the first and second paragraphs.
   *
   * @param firstPath  the path of the first paragraph.
   * @param secondPath the path of the second paragraph.
   */
  public PlagiarismDetector(String firstPath, String secondPath) {
    String firstText = FileConverter.fileToString(firstPath);
    String secondText = FileConverter.fileToString(secondPath);
    this.firstParagraph = ParagraphConverter.getParagraph(firstText);
    this.secondParagraph = ParagraphConverter.getParagraph(secondText);
    this.detectPlagiarism();
  }

  private float getPlagiarismPercentage(int matches, int size) {
    return (float) (matches * Constants.PERCENTAGE) / size;
  }

  private float roundToTwoDigits(float number) {
    return Math.round(number * Constants.PERCENTAGE) / Constants.TWO_DIGIT_ROUND;
  }

  private boolean checkEqualOrMisspellingWords(String firstWord, String secondWord) {
    Misspelling misspelling = new Misspelling(firstWord,
                                              secondWord);
    return firstWord.equals(secondWord) || misspelling.isMisspelling();
  }

  /**
   * This function returns the misspelling words of the LCS of this two sentences. Time complexity:
   * O(min(n, m)) where n is the size of the first Sentence and m the size of the second Sentence.
   *
   * @param backtrack      the backtrack matrix.
   * @param firstSentence  the first sentence.
   * @param secondSentence the second sentence.
   * @return the misspelling words of the LCS of this two sentences.
   */
  private List<Misspelling> getMisspellingWords(Direction[][] backtrack,
                                                List<String> firstSentence,
                                                List<String> secondSentence) {
    List<Misspelling> misspellingWords = new ArrayList<>();
    int firstSentenceIndex = firstSentence.size();
    int secondSentenceIndex = secondSentence.size();
    while (firstSentenceIndex > 0 && secondSentenceIndex > 0) {
      if (backtrack[firstSentenceIndex][secondSentenceIndex] == Direction.DIAGONAL) {
        String firstSentenceWord = firstSentence.get(firstSentenceIndex - 1);
        String secondSentenceWord = secondSentence.get(secondSentenceIndex - 1);
        firstSentenceIndex--;
        secondSentenceIndex--;
        if (!firstSentenceWord.equalsIgnoreCase(secondSentenceWord)) {
          Misspelling misspelling = new Misspelling(firstSentenceWord,
                                                    secondSentenceWord);
          misspellingWords.add(0,
                               misspelling);
        }
      } else if (backtrack[firstSentenceIndex][secondSentenceIndex] == Direction.UP) {
        firstSentenceIndex--;
      } else {
        secondSentenceIndex--;
      }
    }
    return misspellingWords;
  }

  /**
   * Compare the two sentences and returns the total matches of the two Sentences and also all the
   * misspelling words of those. Time complexity: O(n*m) where n is the size of the first sentence
   * and m the size of the second sentence.
   *
   * @param firstSentence  the first sentence.
   * @param secondSentence the second sentence.
   * @return the plagiarism between the two sentences.
   */
  private Plagiarism compareSentences(List<String> firstSentence, List<String> secondSentence) {
    int firstSentenceSize = firstSentence.size();
    int secondSentenceSize = secondSentence.size();
    int[][] dp = new int[firstSentenceSize + 1][secondSentenceSize + 1];
    Direction[][] backtrack = new Direction[firstSentenceSize + 1][secondSentenceSize + 1];
    for (int i = 1; i <= firstSentenceSize; i++) {
      for (int j = 1; j <= secondSentenceSize; j++) {
        if (dp[i - 1][j] >= dp[i][j - 1]) {
          dp[i][j] = dp[i - 1][j];
          backtrack[i][j] = Direction.UP;
        } else {
          dp[i][j] = dp[i][j - 1];
          backtrack[i][j] = Direction.LEFT;
        }
        String firstSentenceWord = firstSentence.get(i - 1).toLowerCase();
        String secondSentenceWord = secondSentence.get(j - 1).toLowerCase();
        if (checkEqualOrMisspellingWords(firstSentenceWord,
                                         secondSentenceWord)) {
          dp[i][j] = Math.max(dp[i][j],
                              dp[i - 1][j - 1] + 1);
          backtrack[i][j] = Direction.DIAGONAL;
        }
      }
    }
    int longestCommonSubsequence = dp[firstSentenceSize][secondSentenceSize];
    List<Misspelling> misspellingWords = getMisspellingWords(backtrack,
                                                             firstSentence,
                                                             secondSentence);
    return new Plagiarism(longestCommonSubsequence,
                          misspellingWords);
  }

  /**
   * This function detect the plagiarism of two paragraphs. Time Complexity: O(n*m*p*q) Where n is
   * the size of the first paragraph, m is the size of the second paragraph, p is the average size
   * of the first sentence and q is the average size of the second sentence.
   */
  private void detectPlagiarism() {
    int totalMatches = Constants.DEFAULT_MATCHES;
    int totalSecondParagraphSize = Constants.DEFAULT_SIZE;
    List<Misspelling> paragraphMisspellings = new ArrayList<>();
    for (List<String> secondParagraphSentence : this.secondParagraph) {
      int maxMatches = Constants.DEFAULT_MATCHES;
      int sentenceSize = secondParagraphSentence.size();
      List<Misspelling> sentenceMisspellings = new ArrayList<>();
      for (List<String> firstParagraphSentence : this.firstParagraph) {
        Plagiarism plagiarism = compareSentences(firstParagraphSentence,
                                                 secondParagraphSentence);
        if (plagiarism.wordsMatched() > maxMatches) {
          sentenceMisspellings = new ArrayList<>(plagiarism.misspellings());
        }
        maxMatches = Math.max(maxMatches,
                              plagiarism.wordsMatched());
      }
      totalMatches += maxMatches;
      totalSecondParagraphSize += sentenceSize;
      paragraphMisspellings.addAll(sentenceMisspellings);
    }
    float plagiarismPercentage = this.getPlagiarismPercentage(totalMatches,
                                                              totalSecondParagraphSize);
    this.maxPlagiarismPercentage = this.roundToTwoDigits(plagiarismPercentage);
    this.misspellings = new ArrayList<>(paragraphMisspellings);
  }

  private String getMisspellingsString() {
    if (this.misspellings.isEmpty()) {
      return Constants.NOT_MISSPELLING;
    }
    StringBuilder misspellingsString = new StringBuilder();
    for (int i = 0; i < this.misspellings.size(); i++) {
      Misspelling misspelling = this.misspellings.get(i);
      misspellingsString.append(misspelling.toString());
      if (i != this.misspellings.size() - 1) {
        misspellingsString.append(Constants.MISSPELLING_ARRAY_SEPARATOR);
      }
    }
    return misspellingsString.toString();
  }

  private String getMaxPlagiarismPercentageString() {
    String plagiarismPercentage = String.format(Constants.PERCENTAGE_FORMAT,
                                                this.maxPlagiarismPercentage);
    return """
            %s%s""".formatted(plagiarismPercentage,
                              Constants.PERCENTAGE_SYMBOL);
  }

  public List<Misspelling> getMisspellings() {
    return this.misspellings;
  }

  public float getMaxPlagiarismPercentage() {
    return this.maxPlagiarismPercentage;
  }

  @Override
  public String toString() {
    return """
            • %s
            • %s""".formatted(this.getMaxPlagiarismPercentageString(),
                              this.getMisspellingsString());
  }
}
