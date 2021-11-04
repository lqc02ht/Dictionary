package dictionary.dictionarypro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    @FXML
    private TextField searchBox;

    @FXML
    private ListView<String> resultSearch;

    @FXML
    private Label wordTargetLabel;

    @FXML
    private Label wordTypeLabel;

    @FXML
    private Label wordPronunciationLabel;

    @FXML
    private Label wordExplainLabel;

    private DictionaryManagement dictionaryManagement = new DictionaryManagement();

    Word selectedWord;

    public void searchWord(KeyEvent e) {
        ObservableList<String> result;
        ArrayList<String> findOut = new ArrayList<>();
        String target = searchBox.getText().toLowerCase();
        if (e.getCode().isLetterKey()) {
            target += e.getText();
        }
        if (!target.equals("")) {
            for (int i = 0; i < Dictionary.numberOfWords(); i++) {
                if (Dictionary.at(i).getWordTarget().indexOf(target) == 0) {
                    findOut.add(Dictionary.at(i).getWordTarget());
                }
            }
            result = FXCollections.observableArrayList(findOut);
            resultSearch.setItems(result);
        } else {
            findOut.clear();
            result = FXCollections.observableArrayList(findOut);
            resultSearch.setItems(result);
        }
    }

    public void viewDetail(MouseEvent e) {
        String selectedString = resultSearch.getSelectionModel().getSelectedItem();
        selectedWord = Dictionary.at(Dictionary.contains(selectedString));
        wordTargetLabel.setText(selectedWord.getWordTarget());
        wordTypeLabel.setText(selectedWord.getWordType());
        wordPronunciationLabel.setText(selectedWord.getPronunciation());
        wordExplainLabel.setText(selectedWord.getWordExplain());
    }

    public void speak() {
        Sound.speak(wordTargetLabel.getText());
    }

    public void remove() {
        if (selectedWord != null) {
            Dictionary.removeWord(Dictionary.contains(selectedWord.getWordTarget()));
            resultSearch.getItems().remove(selectedWord.getWordTarget());
            ignore();
        }
    }

    public void ignore() {
        wordTargetLabel.setText("");
        wordTypeLabel.setText("");
        wordPronunciationLabel.setText("");
        wordExplainLabel.setText("");
        selectedWord = null;
        resultSearch.getSelectionModel().clearSelection();
    }

    public void add() {
        Dialog<Word> addNew = new Dialog<>();
        addNew.setTitle("Add a new word");
        addNew.setHeaderText("Enter the word to insert:");

        ButtonType confirm = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        addNew.getDialogPane().getButtonTypes().addAll(confirm, ButtonType.CANCEL);

        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(20, 150, 10, 10));

        TextField newWordTarget = new TextField();
        newWordTarget.setPromptText("Word target");
        TextField newWordType = new TextField();
        newWordType.setPromptText("Word type");
        TextField newPronunciation = new TextField();
        newPronunciation.setPromptText("Pronunciation");
        TextField newWordExplain = new TextField();
        newWordExplain.setPromptText("Word Explain");

        pane.add(new Label("Target:"), 0, 0);
        pane.add(new Label("Type:"), 0, 1);
        pane.add(new Label("Pronunciation:"), 0, 2);
        pane.add(new Label("Explain:"), 0, 3);
        pane.add(newWordTarget, 1, 0);
        pane.add(newWordType, 1, 1);
        pane.add(newPronunciation, 1, 2);
        pane.add(newWordExplain, 1, 3);

        Node addButton = addNew.getDialogPane().lookupButton(confirm);
        addButton.setDisable(true);

        newWordTarget.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty());
        });
        newWordType.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty());
        });
        newPronunciation.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty());
        });
        newWordExplain.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty());
        });

        addNew.getDialogPane().setContent(pane);

        addNew.setResultConverter(dialogButton -> {
            if (dialogButton == confirm) {
                Dictionary.addWord(newWordTarget.getText(), newWordType.getText()
                        , newPronunciation.getText(), newWordExplain.getText());
                return new Word(newWordTarget.getText(), newWordType.getText()
                        , newPronunciation.getText(), newWordExplain.getText());
            }
            return null;
        });

        addNew.show();
    }

    public void change() {
        if (selectedWord != null) {
            Dialog<Word> changeAWord = new Dialog<>();
            changeAWord.setTitle("Change the word " + selectedWord.getWordTarget());
            changeAWord.setHeaderText("Enter the word to edit:");

            ButtonType confirm = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            changeAWord.getDialogPane().getButtonTypes().addAll(confirm, ButtonType.CANCEL);

            GridPane pane = new GridPane();
            pane.setHgap(10);
            pane.setVgap(10);
            pane.setPadding(new Insets(20, 150, 10, 10));

            TextField newWordTarget = new TextField();
            newWordTarget.setPromptText("Word target");
            newWordTarget.setText(selectedWord.getWordTarget());
            TextField newWordType = new TextField();
            newWordType.setPromptText("Word type");
            newWordType.setText(selectedWord.getWordType());
            TextField newPronunciation = new TextField();
            newPronunciation.setPromptText("Pronunciation");
            newPronunciation.setText(selectedWord.getPronunciation());
            TextField newWordExplain = new TextField();
            newWordExplain.setPromptText("Word Explain");
            newWordExplain.setText(selectedWord.getWordExplain());

            pane.add(new Label("Target:"), 0, 0);
            pane.add(new Label("Type:"), 0, 1);
            pane.add(new Label("Pronunciation:"), 0, 2);
            pane.add(new Label("Explain:"), 0, 3);
            pane.add(newWordTarget, 1, 0);
            pane.add(newWordType, 1, 1);
            pane.add(newPronunciation, 1, 2);
            pane.add(newWordExplain, 1, 3);

            Node changeButton = changeAWord.getDialogPane().lookupButton(confirm);
            changeButton.setDisable(false);

            newWordTarget.textProperty().addListener((observable, oldValue, newValue) -> {
                changeButton.setDisable(newValue.trim().isEmpty());
            });
            newWordType.textProperty().addListener((observable, oldValue, newValue) -> {
                changeButton.setDisable(newValue.trim().isEmpty());
            });
            newPronunciation.textProperty().addListener((observable, oldValue, newValue) -> {
                changeButton.setDisable(newValue.trim().isEmpty());
            });
            newWordExplain.textProperty().addListener((observable, oldValue, newValue) -> {
                changeButton.setDisable(newValue.trim().isEmpty());
            });


            changeAWord.getDialogPane().setContent(pane);
            changeAWord.setResultConverter(dialogButton -> {
                if (dialogButton == confirm) {
                    dictionaryManagement.editWord(selectedWord.getWordTarget()
                            , newWordTarget.getText(), newWordType.getText()
                            , newPronunciation.getText(), newWordExplain.getText());
                    ignore();
                    return new Word(newWordTarget.getText(), newWordType.getText()
                            , newPronunciation.getText(), newWordExplain.getText());
                }
                return null;
            });
            changeAWord.show();
        }
    }

    public void save() {
        dictionaryManagement.dictionaryExportToFile();
    }

    public void usingAPI() {
        String target = searchBox.getText();
        String explain  = "";
        try {
            explain = TranslatorUsingAPI.translate(target);
        } catch (IOException e) {
            System.out.println(e);
        }
        ignore();
        wordTargetLabel.setText(target);
        if (!explain.equals("NS")) {
            wordExplainLabel.setText(explain);
        } else {
            wordExplainLabel.setText(target);
        }

    }
}
