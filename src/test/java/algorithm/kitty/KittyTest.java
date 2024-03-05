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
    PlagiarismDetector firstExample = new PlagiarismDetector("src/main/resources/exp-1-txt-1.txt",
                                                             "src/main/resources/exp-1-txt-2.txt");
    List<Misspelling> misspellings = new ArrayList<>(List.of(new Misspelling("printed",
                                                                             "printd"),
                                                             new Misspelling("information",
                                                                             "informaton")));
    assertEquals(firstExample.getMaxPlagiarismPercentage(),
                 95.83000183105469);
    assertEquals(firstExample.getMisspellings(),
                 misspellings);
    PlagiarismDetector secondExample = new PlagiarismDetector("src/main/resources/exp-2-txt-1.txt",
                                                              "src/main/resources/exp-2-txt-2.txt");
    misspellings = new ArrayList<>();
    assertEquals(secondExample.getMaxPlagiarismPercentage(),
                 33.33000183105469);
    assertEquals(secondExample.getMisspellings(),
                 misspellings);
    PlagiarismDetector thirdExample = new PlagiarismDetector("src/main/resources/exp-3-txt-1.txt",
                                                             "src/main/resources/exp-3-txt-2.txt");
    misspellings = new ArrayList<>(List.of(new Misspelling("Mathematics",
                                                           "matematicas")));
    assertEquals(thirdExample.getMaxPlagiarismPercentage(),
                 80.00);
    assertEquals(thirdExample.getMisspellings(),
                 misspellings);
    PlagiarismDetector fourthExample = new PlagiarismDetector("src/main/resources/exp-4-txt-1.txt",
                                                              "src/main/resources/exp-4-txt-2.txt");
    misspellings = new ArrayList<>(List.of(new Misspelling("Richard",
                                                           "Richar")));
    assertEquals(fourthExample.getMaxPlagiarismPercentage(),
                 50.00);
    assertEquals(fourthExample.getMisspellings(),
                 misspellings);
  }
}
