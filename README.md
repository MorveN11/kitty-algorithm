# Manuel Morales - Kitty Algorithm

## Questions

***1. Write a brief explanation of why you have chosen the DP algorithm to solve the problem.***

I Choose LCS to define in the two paragraphs have misspellings, because we have this constraint:

* Your program should consider a pair of words to indicate a misspelling if they share 55%
  or more of their letters and these shared letters appear in the same order in both words.
  "These shared letters appear in the same order in both words" is the key to use LCS.

I Choose LCS to get the longest common subsequence in the compare of the sentences of the
paragraphs, and after that I use backtracking to get the misspelling words, because the order of
the sentence is important to get the misspelling words, and the plagiarism percentage.

**2. Identify the time complexity of your solution.***

* The time complexity of all the solution is O(n * m * p * q) where n is the size of the first
  paragraph,
  m is the size of the second paragraph, p is the average size of the first sentence and q is the
  average size of the second sentence.

***Is there a non-dynamic programming solution? if so, please explain the idea and compare it
with your solution***

Yes, there is a non-dynamic programming solution, but it is not efficient. The idea is to use a
brute force algorithm to compare all the possible substrings of the two words and check if they
are or not a misspelling.
