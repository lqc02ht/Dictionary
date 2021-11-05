import java.util.ArrayList;
import java.util.List;
/**
 * This is a public class Dictionary.
 */

public class Dictionary {
    
    /**
     * This is a private constructor.
     */

    private Dictionary() {
    }
    
    /**
     * This is a private static words property which stores all the words in the
     * dictionary.
     */
    
    private static List<Word> words = new ArrayList<>();

    /**
     * Get the value of words.
     * @return the value of words
     */

    public static List<Word> getWords() {
        return words;
    }

    /**
     * Set the value of words.
     * @param words new value of words
     */

    public static void setWords(List<Word> words) {
        Dictionary.words = words;
    }

    /**
     * Get the size of dictionary (the number of words in the dictionary).
     * @return number of words in dictionary
     */

    public static int numberOfWords() {
        return words.size();
    }

    /**
     * Get the index of word in Dictionary.
     * @param target is wordTarget you want to get
     * @return index of word or -1 if Dictionary dont have this word
     */

    public static int contains(String target) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWordTarget().equals(target)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This is a method to add a word to the dictionary.
     * @param wordTarget is the word display of the word you want to add
     * @param wordType is the type of the word you want to add
     * @param pronunciation is pronunciation of the word you want to add
     * @param wordExplain is the explanation of the word you want to add
     */

    public static void addWord(String wordTarget, String wordType, String pronunciation, String wordExplain) {
        if (words.isEmpty()) {
            words.add(new Word(wordTarget, wordType, pronunciation, wordExplain));
        } else if (contains(wordTarget) == -1) {
            boolean b = false;
            int n = words.size();
            words.add(new Word());
            for (int i = 0; i < n; i++) {
               if (words.get(i).getWordTarget().compareTo(wordTarget) > 0) {
                   for (int j = n; j >= i + 1; j--) {
                       words.get(j).setWord(words.get(j - 1).getWordTarget(),
                        words.get(j - 1).getWordType(), words.get(j - 1).getPronunciation()
                        , words.get(j - 1).getWordExplain());
                   }
                   words.get(i).setWord(wordTarget, wordType, pronunciation, wordExplain);
                   b = true;
                   break;
               }
            }
            if (!b) words.get(n).setWord(wordTarget, wordType, pronunciation, wordExplain);
        }
    }

    /**
     * Remove a word in the dictionary at index.
     * @param index is index of the word you want to remove
     */

    public static void removeWord(int index) {
        if (index >= 0 && index < words.size()) {
            words.remove(index);
        }
    }

    /**
     * Get the word at index.
     * @param index is index of the word you want to get
     * @return word at index if index is in the words index range otherwise return null
     */

    public static Word at(int index) {
        if (index >= 0 && index < words.size()) {
            return words.get(index);
        } else {
            return null;
        }
    }
}