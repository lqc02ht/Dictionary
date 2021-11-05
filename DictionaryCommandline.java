import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * This is a public class DictionaryCommandline.
 */

public class DictionaryCommandline {

    /**
     * This is the method to clear the console.
     */

    public static void cls() {
        // for (int i = 0; i < 50; i++) {
        //     System.out.println();
        // }
        System.out.println("\033[H\033[2J");
    }

    /**
     * This is a public void showAllWords method.
     */
     
    public static void showAllWords() {
        if (Dictionary.numberOfWords() == 0) {
            System.err.println("Sorry! We don't have any word available now!");
        }
        else {
            for (int pageNum = 0; pageNum < Dictionary.numberOfWords() / 20; pageNum ++) {
                for (int i = pageNum * 20; i < pageNum * 20 + 20 && i < Dictionary.numberOfWords()
                ; i++) {
                        System.out.println(Dictionary.at(i) + "\n");
                    }
                if (pageNum == 0) {
                    System.out.println("Please type 2 to go to the next page or type 0 to"
                    + " return:");
                } else if (pageNum == Dictionary.numberOfWords() / 20 - 1) {
                    System.out.println("Please type 1 to go to the previous page or type any other"
                    + " number to return:");
                } else {
                    System.out.println("Please type 1 to go to the previous page, type 2 to go to"
                    + " the next page or type 0 to return:");
                }
                Scanner sc = new Scanner(System.in);
                int x = sc.nextInt();
                if (x == 0) {
                    return;
                } else if (x == 1) {
                    pageNum -= 2;
                }
                cls();
            }
        }
        System.out.println("Please type anything to return:");
        Scanner sc = new Scanner(System.in);
        sc.next();
    }

    /**
     * This is a public void dictionarySearcher method.
     */
    
    public static void dictionarySearcher() {
        System.out.println("Type in the word you want to search or type 0 to return:");
        Scanner sc = new Scanner(System.in);
        String result = "";
        String target = sc.next().toLowerCase();
        if (target.equals("0")) {
            return;
        }
        for (int i = 0; i < Dictionary.numberOfWords(); i++) {
            if (Dictionary.at(i).getWordTarget().toLowerCase().indexOf(target) == 0) {
                result += Dictionary.at(i) + "\n";
            }
        }
        while (result.equals("")) {
            System.err.println("Sorry! There is no such word begin with " + target + " in the"
            + " dictionary! Please retype the word or type 0 to return:");
            target = sc.next().toLowerCase();
            if (target.equals("0")) return;
            for (int i = 0; i < Dictionary.numberOfWords(); i++) {
                if (Dictionary.at(i).getWordTarget().toLowerCase().indexOf(target) == 0) {
                    result += Dictionary.at(i) + "\n";
                }
            }
        }
        System.out.println("OK! We've found these words begin with " + target + ':' + '\n'
        + result);
        System.out.println("Please type anything to return:");
        sc.next();
    }

    
    /**
     * This is a public void dictionaryAdvanced method.
     * @param action is the action we want to perform
     */ 
    
    public static boolean dictionaryAdvanced(int action) {
        Scanner sc = new Scanner(System.in);
        String confirm;
        switch (action) {
            case 1:
                dictionarySearcher();
                cls();
                return true;
            case 2:
                showAllWords();
                cls();
                return true;
            case 3:
                DictionaryManagement.insertFromCommandline();
                cls();
                return true;
            case 4:
                System.out.println("Type in the word you want to edit or type 0 to return:");
                String wordTarget = sc.next();
                if (wordTarget.equals("0")) {
                    cls();
                    return true;
                }
                while (Dictionary.contains(wordTarget) == -1) {
                    System.err.println("Sorry! There is no such word in the dictionary!"
                            + " Please type in another word or type 0 to return if"
                            + " you've typed in correctly:");
                    wordTarget = sc.next();
                    if (wordTarget.equals("0")) {
                        cls();
                        return true;
                    }
                }
                int m = Dictionary.contains(wordTarget);
                System.out.println("Ok! Hear is the word with its meaning"
                                + " we've found:");
                System.out.println(Dictionary.at(m));
                System.out.println("Confirm edit! Do you sure to edit this word"
                        + " from the dictionary? It cannot be undone! Type YES to"
                        + " confirm or type anything else to cancel and return:");
                confirm = sc.next();
                if (confirm.equals("YES")) {
                    System.out.println("Now type in the new word with its display, type"
                    + ", pronunciation and meaning respectively or type 0 to cancel and return:");
                    String newWordTarget = sc.next();
                    if (newWordTarget.equals("0")) {
                        cls();
                        return true;
                    }
                    String wordType = sc.next();
                    if (wordType.equals("0")) {
                        cls();
                        return true;
                    }
                    String pronunciation = sc.next();
                    if (pronunciation.equals("0")) {
                        cls();
                        return true;
                    }
                    String wordExplain = sc.next() + sc.nextLine();
                    if (wordExplain.equals("0")) {
                        cls();
                        return true;
                    }
                    DictionaryManagement.editFromCommandline(wordTarget, newWordTarget, wordType
                    , pronunciation, wordExplain);
                    System.out.println("Ok! The word has been changed to:\n" + newWordTarget + '\t'
                    + wordType + '\t' + pronunciation + '\t' + wordExplain);
                }
                System.out.println("Please type anything to return:");
                sc.next();
                cls();
                return true;
            case 5:
                if (Dictionary.numberOfWords() == 0) {
                    System.err.println("Sorry! There is no word in the dictionary right"
                            + " now!");
                    System.out.println("Please type anything to return:");
                    sc.next();
                    cls();
                    return true;
                }
                System.out.println("Type in the word you want to delete or type 0 to"
                        + " return:");
                String word = sc.next();
                if (word.equals("0")) {
                    cls();
                    return true;
                }
                while (Dictionary.contains(word) == -1) {
                    System.err.println("Sorry! There is no such word in the dictionary!"
                            + " Please type in another word or type 0 to return if"
                            + " you've typed in correctly:");
                    word = sc.next();
                    if (word.equals("0")) {
                        cls();
                        return true;
                    }
                }
                System.out.println("Confirm delete! Do you sure to delete this word"
                        + " from the dictionary? It cannot be undone! Type YES to"
                        + " confirm or type 0 to cancel and return:");
                confirm = sc.next();
                if (confirm.equals("YES")) {
                    DictionaryManagement.deleteFromCommandline(word);
                    System.out.println("OK! Word " + word + " has been removed"
                            + " successfully from the dictionary!");
                }
                System.out.println("Please type anything to return:");
                sc.next();
                cls();
                return true;
            case 0:
                System.out.println("Thank you for using the dictionary! Goodbye!");
                return false;
            default:
                System.err.println("Invalid operation! Please type again correctly!");
                int x = sc.nextInt();
                cls();
                dictionaryAdvanced(x);
                return true;
        }
    }

    /**
     * This is the main method.
     */

    public static void main(String[] args) {
        DictionaryManagement.insertFromFile();
        System.out.println("Welcome to the Dictionary! Please select the desire"
                + " operation:");
        System.out.println("Type 1 to search for a word.");
        System.out.println("Type 2 show all the words available in the"
                + " dictionary.");
        System.out.println("Type 3 to insert a number of words into the"
                + " dictionary.");
        System.out.println("Type 4 to edit a word in the dictionary.");
        System.out.println("Type 5 to delete a word in the dictionary.");
        System.out.println("Type 0 to end the dictionary program.");
        Scanner sc = new Scanner(System.in);
        try {
            int action = sc.nextInt();
            while (dictionaryAdvanced(action)) {
                System.out.println("\nPlease type in any of the following number to continue using"
                + " the dictionary or type 0 to end the dictionary program:");
                System.out.println("Type 1 to search for a word.");
                System.out.println("Type 2 show all the words available in the"
                        + " dictionary.");
                System.out.println("Type 3 to insert a number of words into the"
                        + " dictionary.");
                System.out.println("Type 4 to edit a word in the dictionary.");
                System.out.println("Type 5 to delete a word in the"
                        + " dictionary.");
                System.out.println("Type 0 to end the dictionary program.");
                action = sc.nextInt();
            };
        } catch (InputMismatchException e){
            System.err.println("You've entered alphabetical character!");
        }
        DictionaryManagement.dictionaryExportToFile();
    }
}