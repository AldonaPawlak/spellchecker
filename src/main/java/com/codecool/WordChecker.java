package com.codecool;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * ICS 23 Summer 2004
 * Project #5: Lost for Words
 *
 * Implement your word checker here.  A word checker has two responsibilities:
 * given a word list, answer the questions "Is the word 'x' in the wordlist?"
 * and "What are some suggestions for the misspelled word 'x'?"
 *
 * WordChecker uses a class called WordList that I haven't provided the source
 * code for.  WordList has only one method that you'll ever need to call:
 *
 *     public boolean lookup(String word)
 *
 * which returns true if the given word is in the WordList and false if not.
 */

public class WordChecker
{
    private WordList wordList;
    /**
     * Constructor that initializes a new WordChecker with a given WordList.
     *
     * @param wordList Initial word list to check against.
     * @see WordList
     */
    public WordChecker(WordList wordList)
    {
        this.wordList = wordList;
    }


    /**
     * Returns true if the given word is in the WordList passed to the
     * constructor, false otherwise.
     *
     * @param word Word to chack against the internal word list
     * @return bollean indicating if the word was found or not.
     */
    public boolean wordExists(String word)
    {
        return wordList.lookup(word);
    }


    /**
     * Returns an ArrayList of Strings containing the suggestions for the
     * given word.  If there are no suggestions for the given word, an empty
     * ArrayList of Strings (not null!) should be returned.
     *
     * @param word String to check against
     * @return A list of plausible matches
     */
    public List<String> getSuggestions(String word)
    {
        List<String> suggestions = new ArrayList<>();
        suggestions.addAll(adjacentPairSwap(word));
        suggestions.addAll(insertLetter(word));
        suggestions.addAll(deleteEachLetter(word));
        suggestions.addAll(replaceLetter(word));
        suggestions.addAll(splitChars(word));
        return suggestions;
    }

    private List<String> adjacentPairSwap(String word) {
        List<String> suggestions = new ArrayList<>();
        String swapped;
        char[] letters = word.toCharArray();
        for (int i = 0; i < letters.length - 1; i++) {
            letters = word.toCharArray();
            char temp = letters[i];
            letters[i] = letters[i + 1];
            letters[i + 1] = temp;
            swapped = String.valueOf(letters);
            if (wordList.lookup(swapped)) {
                suggestions.add(swapped);
            }
        }
        return suggestions;
    }

    private List<String> insertLetter(String word) {
        List<String> suggestions = new ArrayList<>();
        for (int i = 0; i <= word.length(); i++){
            for (char c = 'a'; c <= 'z'; ++c){
                String wordWithInjectedLetter = new StringBuilder(word).insert(i, c).toString();
                if(!suggestions.contains(wordWithInjectedLetter) && wordExists(wordWithInjectedLetter)){
                    suggestions.add(wordWithInjectedLetter);
                }
            }
        }
        return suggestions;
    }

    private List<String> deleteEachLetter(String word) {
        List<String> suggestions = new ArrayList<>();
        char[] letters = word.toCharArray();
        for (int i = 0; i < letters.length; i++){
            String removed = new StringBuilder(word).deleteCharAt(i).toString();
               if(!suggestions.contains(removed) && wordExists(removed)){
                suggestions.add(removed);
            }
        }
        return suggestions;
    }

    private List<String> replaceLetter(String word) {
        List<String> suggestions = new ArrayList<>();
        for (int i = 0; i < word.length(); i++){
            for (char c = 'a'; c <= 'z'; c++){
                String wordWithReplacedLetter = word.replace(word.charAt(i), c);
                if (wordExists(wordWithReplacedLetter)){
                    suggestions.add(wordWithReplacedLetter);
                }
            }
        }
        return suggestions;
    }

    private List<String> splitChars(String word) {
        List<String> suggestions = new ArrayList<>();
        for (int i = 1; i < word.length(); i++){
            String firstWordPart = word.substring(0, i);
            String secondWordPart = word.substring(i);
            if (wordExists(firstWordPart) && wordExists(secondWordPart)){
                suggestions.add(firstWordPart + " " + secondWordPart);
            }
        }
        return suggestions;
    }
}