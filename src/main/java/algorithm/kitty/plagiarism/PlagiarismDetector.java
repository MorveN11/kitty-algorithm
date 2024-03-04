package algorithm.kitty.plagiarism;

import algorithm.kitty.plagiarism.classes.Count;
import algorithm.kitty.plagiarism.classes.utilities.CountUtility;
import algorithm.kitty.plagiarism.paragraph.ParagraphConverter;
import algorithm.kitty.plagiarism.paragraph.sentence.SentenceUtility;
import algorithm.kitty.plagiarism.records.misspelling.Misspelling;
import algorithm.kitty.plagiarism.records.plagiarism.Plagiarism;
import algorithm.kitty.utilities.Constants;
import algorithm.kitty.utilities.FileConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * PlagiarismDetector class that detects plagiarism between two paragraphs.
 */
public class PlagiarismDetector {

  private final List<Map<String, Count>> firstParagraph;

  private final List<Map<String, Count>> secondParagraph;

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

  private boolean invalidWordsCount(Count firstCount, Count secondCount) {
    return firstCount.isCountZero() || secondCount.isCountZero();
  }

  private float getPlagiarismPercentage(int matches, int size) {
    return (float) (matches * Constants.PERCENTAGE) / size;
  }

  private float roundToTwoDigits(float number) {
    return Math.round(number * Constants.PERCENTAGE) / Constants.TWO_DIGIT_ROUND;
  }

  /**
   * Compare the two sentences and detect plagiarism. Time complexity: O(n*m).
   *
   * @param firstSentence  the first sentence.
   * @param secondSentence the second sentence.
   * @return the plagiarism between the two sentences.
   */
  private Plagiarism compareSentences(Map<String, Count> firstSentence,
                                      Map<String, Count> secondSentence) {
    int wordsMatched = Constants.DEFAULT_MATCHES;
    List<Misspelling> tmpMisspellings = new ArrayList<>();
    for (Map.Entry<String, Count> fistParagraphSentenceEntry : firstSentence.entrySet()) {
      String firstParagraphWord = fistParagraphSentenceEntry.getKey();
      Count firstParagraphWordCount = fistParagraphSentenceEntry.getValue();
      for (Map.Entry<String, Count> secondParagraphSentenceEntry : secondSentence.entrySet()) {
        String secondParagraphWord = secondParagraphSentenceEntry.getKey();
        Count secondParagraphCount = secondParagraphSentenceEntry.getValue();
        if (this.invalidWordsCount(firstParagraphWordCount,
                                   secondParagraphCount)) {
          continue;
        }
        if (firstParagraphWord.equalsIgnoreCase(secondParagraphWord)) {
          int diff = CountUtility.getMinCount(firstParagraphWordCount,
                                              secondParagraphCount);
          wordsMatched += diff;
          continue;
        }
        Misspelling misspelling = new Misspelling(firstParagraphWord,
                                                  secondParagraphWord);
        if (!misspelling.verifyPossibleMisspelling()) {
          continue;
        }
        tmpMisspellings.add(misspelling);
        int diff = CountUtility.getMinCount(firstParagraphWordCount,
                                            secondParagraphCount);
        wordsMatched += diff;
      }
    }
    return new Plagiarism(wordsMatched,
                          tmpMisspellings);
  }

  /**
   * Detect plagiarism between the two paragraphs. Time complexity: O(n*m). Where n is the size of
   * the first paragraph and m is the size of the second paragraph.
   */
  private void detectPlagiarism() {
    int totalMatches = Constants.DEFAULT_MATCHES;
    int totalSecondParagraphSize = Constants.DEFAULT_SIZE;
    List<Misspelling> paragraphMisspellings = new ArrayList<>();
    for (Map<String, Count> secondParagraphSentence : this.secondParagraph) {
      int maxMatches = Constants.DEFAULT_MATCHES;
      int sentenceSize = SentenceUtility.getSentenceSize(secondParagraphSentence);
      List<Misspelling> sentenceMisspellings = new ArrayList<>();
      for (Map<String, Count> firstParagraphSentence : this.firstParagraph) {
        Plagiarism plagiarism = compareSentences(firstParagraphSentence,
                                                 secondParagraphSentence);
        maxMatches = Math.max(maxMatches,
                              plagiarism.wordsMatched());
        if (maxMatches == plagiarism.wordsMatched()) {
          sentenceMisspellings = new ArrayList<>(plagiarism.misspellings());
        }
        SentenceUtility.resetSentence(firstParagraphSentence);
        SentenceUtility.resetSentence(secondParagraphSentence);
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
