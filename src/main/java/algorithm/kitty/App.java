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
    PlagiarismDetector exercise1 = new PlagiarismDetector("src/main/resources/exp-1-txt-1.txt",
                                                          "src/main/resources/exp-1-txt-2.txt");
    System.out.println(exercise1);
    PlagiarismDetector exercise2 = new PlagiarismDetector("src/main/resources/exp-2-txt-1.txt",
                                                          "src/main/resources/exp-2-txt-2.txt");
    System.out.println(exercise2);
    PlagiarismDetector exercise3 = new PlagiarismDetector("src/main/resources/exp-3-txt-1.txt",
                                                          "src/main/resources/exp-3-txt-2.txt");
    System.out.println(exercise3);
  }
}
