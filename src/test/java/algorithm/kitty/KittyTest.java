package algorithm.kitty;

import static org.junit.jupiter.api.Assertions.assertEquals;

import algorithm.kitty.plagiarism.PlagiarismDetector;
import algorithm.kitty.plagiarism.records.misspelling.Misspelling;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class KittyTest {

  @Test
  void testPlagiarism() {
    PlagiarismDetector exercise1 = new PlagiarismDetector("src/main/resources/exp-1-txt-1.txt",
                                                          "src/main/resources/exp-1-txt-2.txt");
    List<Misspelling> misspellings = new ArrayList<>(List.of(new Misspelling("printed",
                                                                             "printd"),
                                                             new Misspelling("information",
                                                                             "informaton")));
    assertEquals(exercise1.getMaxPlagiarismPercentage(),
                 95.83000183105469);
    assertEquals(exercise1.getMisspellings(),
                 misspellings);
    PlagiarismDetector exercise2 = new PlagiarismDetector("src/main/resources/exp-2-txt-1.txt",
                                                          "src/main/resources/exp-2-txt-2.txt");
    misspellings = new ArrayList<>();
    assertEquals(exercise2.getMaxPlagiarismPercentage(),
                 33.33000183105469);
    assertEquals(exercise2.getMisspellings(),
                 misspellings);
    PlagiarismDetector exercise3 = new PlagiarismDetector("src/main/resources/exp-3-txt-1.txt",
                                                          "src/main/resources/exp-3-txt-2.txt");
    misspellings = new ArrayList<>(List.of(new Misspelling("Mathematics",
                                                           "matematicas")));
    assertEquals(exercise3.getMaxPlagiarismPercentage(),
                 80.00);
    assertEquals(exercise3.getMisspellings(),
                 misspellings);
  }
}
