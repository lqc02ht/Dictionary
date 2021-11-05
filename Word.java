/**
 * This is a public class Word.
 */

public class Word {
    
    /**
     * This is a private String wordTarget property which represent the word.
     */
    
    private String wordTarget;

    /**
     * This is a private String wordType property which represent the word
     * type.
     */

    private String wordType;
    
    /**
     * This is a private String property represents the pronunciation of the word.
     */

    private String pronunciation;

    /**
     * This is a private String wordExplain property which represent the word's
     * meaning.
     */
    
    private String wordExplain;

    

    /**
     * This is a constructor with no parameter.
     */

    public Word() {
    }

    /**
     * This is the constructor for word.
     * @param wordTarget is the word display
     * @param wordType is the type of the word
     * @param pronunciation is the pronunciation of the word
     * @param wordExplain is the word explanation
     */

    public Word(String wordTarget, String wordType, String pronunciation, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordType = wordType;
        this.pronunciation = pronunciation;
        this.wordExplain = wordExplain;
    }

    
    /**
     * This is the method return the display of the word.
     * @return wordTarget is the word display you want to get
     */
    
    public String getWordTarget() {
        return wordTarget;
    }

    /**
     * This is the getter method for word type.
     * @return the type of the word
     */
    
    public String getWordType() {
        return wordType;
    }

    /**
     * This is the getter method for pronunciation.
     * @return pronunciation of the word
     */
    
    public String getPronunciation() {
        return pronunciation;
    }

    /**
     * This is the method return the meaning of the word.
     * @return wordExplain is the word's explanation
     */
    
    public String getWordExplain() {
        return wordExplain;
    }
    
    /**
     * This is the setter method for wordTarget.
     * @param wordTarget is the Targeted word you want set
     */
    
    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    /**
     * This is the setter method for wordType.
     * @param wordType is the type of word you want to set
     */

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }
    /**
     * This is the setter method for pronunciation.
     * @param pronunciation is word's pronunciation
     */
    
    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    /**
     * This is the setter method for wordExplain.
     * @param wordExplain is the explanation of the word you want to set
     */

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }


    /**
     * This is the setter method for word with all of it's attributes.
     * @param wordTarget is the word target we want to set
     * @param wordType is the type of word we want to set
     * @param pronunciation is the pronunciation of the word we want to set
     * @param wordExplain is the word explanation we want to set
     */

    public void setWord(String wordTarget, String wordType, String pronunciation
    , String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordType = wordType;
        this.pronunciation = pronunciation;
        this.wordExplain = wordExplain;
    }

    /**
     * This is the method which override the toString() method in class Object.
     * @return the word in format
     */

    @Override

    public String toString() {
        return  wordTarget + '\t' + wordType + '\t' + pronunciation + '\t' + wordExplain;
    }

    /**
     * This is a equals method to compare if a word is equals in all respective properties.
     * @param wordTarget is the display of the word
     * @param wordType is the type of the word
     * @param pronunciation is the pronunciation of the word
     * @param wordExplain is the explanation of the word
     * @return true if the word is equals in all respective properties, otherwise false.
     */

    public boolean equals(String wordTarget, String wordType, String pronunciation
    , String wordExplain) {
        return this.wordTarget.equals(wordTarget) && this.wordType.equals(wordType)
        && this.pronunciation.equals(pronunciation) && this.wordExplain.equals(wordExplain);
    }
}