# Manuel Morales - Kitty Algorithm

## Questions

***1. Write a brief explanation of why you have chosen the DP algorithm to solve the problem.***

I Choose LCS to define in the two paragraphs have misspellings, because we have this constraint:

* Your program should consider a pair of words to indicate a misspelling if they share 55%
  or more of their letters and these shared letters appear in the same order in both words.
  "These shared letters appear in the same order in both words" is the key to use LCS.

**2. Identify the time complexity of your solution.***

The time complexity of the solution to find the misspellings words is O(n * m), where n is the
length of
the first word and m is the length of the second word.

***Is there a non-dynamic programming solution? if so, please explain the idea and compare it
with your solution***

Yes, there is a non-dynamic programming solution, but it is not efficient. The idea is to use a
brute force algorithm to compare all the possible substrings of the two words and check if they
are or not a misspelling.
