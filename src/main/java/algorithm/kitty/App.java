package algorithm.kitty;

import algorithm.kitty.plagiarism.PlagiarismDetector;

/**
 * Main class.
 */
public class App {

  /**
   * Main method.
   *
   * @param args arguments
   */
  public static void main(String[] args) {
    PlagiarismDetector firstExample = new PlagiarismDetector("src/main/resources/exp-1-txt-1.txt",
                                                             "src/main/resources/exp-1-txt-2.txt");
    System.out.println(firstExample);
    PlagiarismDetector secondExample = new PlagiarismDetector("src/main/resources/exp-2-txt-1.txt",
                                                              "src/main/resources/exp-2-txt-2.txt");
    System.out.println(secondExample);
    PlagiarismDetector thirdExample = new PlagiarismDetector("src/main/resources/exp-3-txt-1.txt",
                                                             "src/main/resources/exp-3-txt-2.txt");
    System.out.println(thirdExample);
    PlagiarismDetector fourthExample = new PlagiarismDetector("src/main/resources/exp-4-txt-1.txt",
                                                              "src/main/resources/exp-4-txt-2.txt");
    System.out.println(fourthExample);
  }
}
