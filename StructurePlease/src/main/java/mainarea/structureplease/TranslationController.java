package mainarea.structureplease;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mainarea.structureplease.dictionaryloader.LoadDictionary;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TranslationController implements Initializable {

    @FXML private ChoiceBox<String> choiceBox1;
    @FXML private ChoiceBox<String> choiceBox2;
    @FXML private TextArea textArea1;
    @FXML private TextArea textArea2;
    @FXML private Text translatorText;

    private LoadDictionary dictionary;
    private String chosenOutputLanguage;
    private String inputWord;
    private static final String[] choiceBoxLanguages = {"English", "Cebuano"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Font headerText = Font.loadFont(getClass().getResource("/fonts/MADECarvingSoftPERSONALUSE-Bold.otf").toExternalForm(), 48);
        Font textAreaFont = Font.loadFont(getClass().getResource("/fonts/MADECarvingSoftPERSONALUSE-Regular.otf").toExternalForm(), 24);
        translatorText.setFont(headerText);
        textArea1.setFont(textAreaFont);
        textArea2.setFont(textAreaFont);
        dictionary = new LoadDictionary();

        // Initialize choice boxes
        setupChoiceBox(choiceBox1, "English");
        setupChoiceBox(choiceBox2, "Cebuano");

        // Add listeners for choiceBox selections and text area input
        choiceBox1.setOnAction(this::processWord);
        textArea1.setOnKeyReleased(event -> processWord(null));
        choiceBox2.setOnAction(event -> processWord(null));
    }

    private void setupChoiceBox(ChoiceBox<String> choiceBox, String defaultValue) {
        choiceBox.getItems().addAll(choiceBoxLanguages);
        choiceBox.setValue(defaultValue);
        addCheckBoxDesign(choiceBox);
    }

    public void processWord(ActionEvent event) {
        inputWord = textArea1.getText().toLowerCase().trim();
        chosenOutputLanguage = choiceBox2.getValue();

        if (inputWord.isEmpty()) {
            textArea2.clear();
            return;
        }

        // Identify the language of the input and output words
        identifyLanguage(inputWord, choiceBox1);
        translateInput(inputWord, chosenOutputLanguage);
    }

    private void identifyLanguage(String word, ChoiceBox<String> choiceBox) {
        dictionary.getDictionary().forEach((key, value) -> {
            String mainKeyLowerCase = key.toLowerCase();

            if (word.equals(mainKeyLowerCase)) {
                choiceBox.setValue("Cebuano");
                choiceBox2.setValue("English");
            } else {
                value.forEach((innerKey, innerValue) -> {
                    if (hasMatch(innerValue.toLowerCase(), word)) {
                        if ("transEng".equals(innerKey)){
                            choiceBox.setValue("English");
                            choiceBox2.setValue("Cebuano");
                        }
                    }
                });
            }
        });
    }

    private void translateInput(String word, String outputLanguage) {
        dictionary.getDictionary().forEach((key, value) -> {
            String mainKeyLowerCase = key.toLowerCase();

            if (word.equals(mainKeyLowerCase)) {
                setTranslation(outputLanguage, key);
            } else if (outputLanguage.equals("Cebuano")) {
                String english = dictionary.getTransEng(key);
                if (hasMatch(english, word)){
                    textArea2.setText(key);
                }

            } else {
                value.forEach((innerKey, innerValue) -> {
                    if (hasMatch(innerValue.toLowerCase(), word)) {
                        setTranslation(outputLanguage, key);
                    }
                });
            }
        });
    }

    private void setTranslation(String outputLanguage, String mainKey) {
        switch (outputLanguage) {
            case "English":
                textArea2.setText(dictionary.getTransEng(mainKey));
                break;
            case "Cebuano":
                textArea2.setText(mainKey);
                break;
            default:
                textArea2.clear();
        }
    }

    private boolean hasMatch(String innerValue, String input) {
        for (String value : innerValue.split("[/()]")) {
            if (value.trim().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
    private void setTextFont(Text text, String fontPath, int fontSize){
        Font myFont = Font.loadFont(getClass().getResource(fontPath).toExternalForm(), fontSize);
        text.setFont(myFont);
    }
    private void addCheckBoxDesign(ChoiceBox<String> choiceBoxDesign) {
        choiceBoxDesign.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-font-family: 'MADE Carving Bold';" +
                        "-fx-font-size: 15px;" +
                        "-fx-padding: 5;" +
                        "-fx-arrow-size: 14;"
        );
    }
}
