/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private ArrayList<String> wordList;
    private HashSet<String> wordSet;
    private HashMap<String, ArrayList<String>> lettersToWord;
    private ArrayList<Character> alphabets;
    private HashMap<Integer, ArrayList<String>> sizeToWords;
    private Integer wordLength;

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        wordList = new ArrayList<String>();
        wordSet = new HashSet<String>();
        sizeToWords = new HashMap<Integer, ArrayList<String>>();
        lettersToWord = new HashMap<String, ArrayList<String>>();
        wordLength = DEFAULT_WORD_LENGTH;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordSet.add(word);
            wordList.add(word);
            String sortedWord = sortLetters(word);
            if(lettersToWord.containsKey(sortedWord)) {
                ArrayList<String> tempAnagrams = lettersToWord.get(sortedWord);
                tempAnagrams.add(word);
                lettersToWord.put(sortedWord, tempAnagrams);
            } else {
                ArrayList<String> tempAnagrams = new ArrayList<String>();
                tempAnagrams.add(word);
                lettersToWord.put(sortedWord, tempAnagrams);
            }
            if(sizeToWords.containsKey(word.length())) {
                ArrayList<String> tempList = sizeToWords.get(word.length());
                tempList.add(word);
                sizeToWords.put(word.length(), tempList);
            } else {
                ArrayList<String> tempList = new ArrayList<String>();
                tempList.add(word);
                sizeToWords.put(word.length(), tempList);
            }
        }
    }

    public boolean isGoodWord(String word, String base) {
        if(wordSet.contains(word) && !word.contains(base)) {
            return true;
        } else {
            return false;
        }
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        for(String word : wordSet) {
            if(word.length() == targetWord.length()) {
                if(sortLetters(word).equals(sortLetters(targetWord))) {
                    result.add(word);
                }
            }
        }
        return result;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        alphabets = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
        for(Character alpha : alphabets) {
            String modifiedWord = word + alpha;
            String modifiedSortedWord = sortLetters(modifiedWord);
            if(lettersToWord.containsKey(modifiedSortedWord)) {
                ArrayList<String> tempList = lettersToWord.get(modifiedSortedWord);
                if(tempList.contains(modifiedWord)) {
                    result.add(modifiedWord);
                }
            }
        }
        return result;
    }

    public String sortLetters(String word) {
        String sortedWord;
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        sortedWord = new String(chars);
        return sortedWord;
    }

    public String pickGoodStarterWord() {
        Integer sizeLimit = random.nextInt(MAX_WORD_LENGTH - DEFAULT_WORD_LENGTH);
        ArrayList<String> tempList = sizeToWords.get(sizeLimit+DEFAULT_WORD_LENGTH);
        return tempList.get(random.nextInt(tempList.size()));
    }
}
