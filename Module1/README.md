The following work has been left on the student to develop:

***pickGoodStarterWord***

If your game is working, proceed to implement pickGoodStarterWord to make the game more interesting. Pick a random starting point in the wordList array and check each word in the array until you find one that has at least MIN_NUM_ANAGRAMS anagrams. Be sure to handle wrapping around to the start of the array if needed. Run your app again to make sure it's working.

***Refactoring***

At this point, the game is functional but can be quite hard to play if you start off with a long base word. To avoid this, let's refactor AnagramDictionary to give words of increasing length.

This refactor starts in the constructor where, in addition to populating wordList, you should also store each word in a HashMap (let's call it sizeToWords) that maps word length to an ArrayList of all words of that length. This means, for example, you should be able to get all four-letter words in the dictionary by calling sizeToWords.get(4).

You should also create a new member variable called wordLength and default it to DEFAULT_WORD_LENGTH. Then in pickGoodStarterWord, restrict your search to the words of length wordLength, and once you're done, increment wordLength (unless it's already at MAX_WORD_LENGTH) so that the next invocation will return a larger word.

***Extensions***

This activity (like all future activities) contains some optional extensions. If time permits, we would like each group to attempt at least one extension, either from the list below or one that you have invented yourselves.

Two-letter mode: switch to allowing the user to add two letters to form anagrams
Optimize word selection by removing words that do not have enough anagrams from the pool of possible starter words. Note that those words should still remain in wordSet since they can still be used as anagrams to other words.
Two-word mode: Allow the user to add one letter to a pair of words to form two new valid words.
