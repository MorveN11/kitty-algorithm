package algorithm.kitty.plagiarism.classes;

import algorithm.kitty.utilities.Constants;

/**
 * Class to count the number of words in a sentence.
 */
public class Count {

  private int initCount;

  private int count;

  public Count() {
    this.initCount = Constants.DEFAULT_COUNT;
    this.count = Constants.DEFAULT_COUNT;
  }

  public void incrementCount() {
    this.count++;
    this.initCount++;
  }

  public void resetCount() {
    this.count = this.initCount;
  }

  public boolean isCountZero() {
    return this.count <= 0;
  }

  public int getCount() {
    return this.count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  @Override
  public String toString() {
    return "%s".formatted(this.count);
  }
}
