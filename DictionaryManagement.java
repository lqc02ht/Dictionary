import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Formatter;
/**
 * This is a public class DictionaryManagement.
 */

public class DictionaryManagement {

    /**
     * This is a method to get the Dictionary from File.
     * @throws UnsupportedEncodingException
     */

    public static void insertFromFile() {
        try {
            File file = new File("dictionaries.txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNext()) {
                String newWordTarget = "";
                String newWordType = "";
                String newPronunciation = "";
                String newWordExplain = "";
                if (sc.hasNext()) newWordTarget = sc.next();
                if (sc.hasNext()) newWordType = sc.next();
                if (sc.hasNext()) newPronunciation = sc.next();
                if (sc.hasNext()) newWordExplain = sc.next();
                if (sc.hasNextLine()) newWordExplain += sc.nextLine();
                Dictionary.addWord(newWordTarget, newWordType, newPronunciation, newWordExplain);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist!");
        }
    }

    /**
     * This is a method to insert words from commandline.
     * @throws UnsupportedEncodingException
     */

    public static void insertFromCommandline() {
        System.out.println("Type the number of words you want to insert or type 0 to cancel"
        + " return:");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        if (number == 0) return;
        System.out.println("Type all the words following by it's display, type, pronunciation and"
        + " explanation you want to insert respectively or type 0 to cancel and return:");
        for (int i = 0; i < number; i++) {
            String newWordTarget = sc.next();
            while (Dictionary.contains(newWordTarget) != -1) {
                System.err.println("The word you type has already exist! Please type again the"
                + " word" + (i + 1) + " : or type 0 to cancel and return");
                newWordTarget = sc.next();
                if (newWordTarget.equals("0")) return;
            }
            String newWordType = sc.next();
            if (newWordType.equals("0")) return;
            String newPronunciation = sc.next();
            if (newPronunciation.equals("0")) return;
            String newWordExplain = sc.next() + sc.nextLine();
            if (newWordExplain.equals("0")) return;
            Dictionary.addWord(newWordTarget, newWordType, newPronunciation, newWordExplain);
            System.out.println("Ok! Your words has successfully been inserted into"
                + " the dictionary. Type anything to return:");
            sc.next();
        }
    }

    /**
     * This is a method to remove a word from Dictionary.
     * @param wordTarget is the word you want to delete
     */
     
    public static void deleteFromCommandline(String wordTarget) {
        if (Dictionary.contains(wordTarget) != -1) {
            Dictionary.removeWord(Dictionary.contains(wordTarget));
        }
    }

    /**
     * This is a method to edit a word.
     * @param oldWordTarget is the word you want to change.
     * @param wordTarget is the word display of the new word you want to change
     * @param wordType is the type of the new word you want to change
     * @param pronunciation is the pronunciation of the word you edit
     * @param wordExplain is the explanation of the new word you want to change
     */

    public static void editFromCommandline(String oldWordTarget, String wordTarget, String wordType
    , String pronunciation, String wordExplain) {
        if (Dictionary.contains(oldWordTarget) != -1) {
            Dictionary.removeWord(Dictionary.contains(oldWordTarget));
            Dictionary.addWord(wordTarget, wordType, pronunciation, wordExplain);
        }
    }

    /**
     * This is a method to export Dictionary to file "dictionaries.txt".
     */
    
    public static void dictionaryExportToFile() {
        try {
            Formatter f = new Formatter("dictionaries.txt");
            for (int i = 0; i < Dictionary.numberOfWords() - 1; i++) {
                f.format("%s\t%s\t%s\t%s", Dictionary.at(i).getWordTarget(), Dictionary.at(i)
                .getWordType(), Dictionary.at(i).getPronunciation(), Dictionary.at(i)
                .getWordExplain() + "\r\n");         
            }
            f.format("%s\t%s\t%s", Dictionary.at(Dictionary.numberOfWords() - 1).getWordTarget()
            , Dictionary.at(Dictionary.numberOfWords() - 1).getWordType(), Dictionary
            .at(Dictionary.numberOfWords() - 1).getWordExplain());
            f.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error!");
        }
	}
}