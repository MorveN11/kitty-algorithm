package algorithm.kitty.plagiarism.records.plagiarism;

import algorithm.kitty.plagiarism.records.misspelling.Misspelling;
import java.util.List;

/**
 * Class to represent the plagiarism between two texts.
 */
public record Plagiarism(int wordsMatched, List<Misspelling> misspellings) {

}
