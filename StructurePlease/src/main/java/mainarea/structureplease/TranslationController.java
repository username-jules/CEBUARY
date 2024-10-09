package mainarea.structureplease;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class TranslationController implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox1;
    @FXML
    private ChoiceBox<String> choiceBox2;
    @FXML
    private TextArea textArea1;
    @FXML
    private TextArea textArea2;

    private Data dictionaryData;
    private String chosenInputLanguage, chosenOutputLanguage, inputWord;
    private String mainKey;
    private String[] choiceBoxLanguages = {"Filipino", "English", "Chavacano"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dictionaryData = OpeningController.getOpeningController().getDictionaryData();

        // Set up choiceBox values
        choiceBox1.getItems().addAll(choiceBoxLanguages);
        choiceBox1.setValue("Chavacano");

        choiceBox2.getItems().addAll(choiceBoxLanguages);
        choiceBox2.setValue("English");

        // Add listeners for choiceBox selections
        choiceBox1.setOnAction(this::translateWord);
        choiceBox2.setOnAction(this::translateWord);

        // Add key release listener for automatic translation in textArea1
        textArea1.setOnKeyReleased(event -> translateWord(null));
    }

    // Method to handle translation logic
    public void translateWord(ActionEvent event) {
        inputWord = textArea1.getText();

        if (inputWord.isEmpty()) {
            textArea2.clear();
            return;
        }

        chosenInputLanguage = choiceBox1.getValue();
        chosenOutputLanguage = choiceBox2.getValue();

        String translatingWord = identifyTheLanguage(inputWord, chosenInputLanguage);

    }

    private String identifyTheLanguage(String word, String inputLanguage) {
        word = inputWord;
        inputLanguage = chosenInputLanguage;

        Map<String, Map<String, String>> mainMap = dictionaryData.getDictionary();

        // Loop through the mainMap entries
        for (Map.Entry<String, Map<String, String>> mainMapElements : mainMap.entrySet()) {

            // Get the current key (e.g., 'bienvenidos')
            mainKey = mainMapElements.getKey();
            System.out.println("Checking word: " + mainKey);

            // Check if the word matches the mainKey
            if (word.equals(mainKey)) {
                System.out.println("Word found: " + mainKey);
                choiceBox1.setValue("Chavacano");

                // Stop searching once the word is found


            } else if (!word.equals(mainKey)) {
                Map<String, String> innerMap = mainMapElements.getValue();
                // Iterate through the innerMap to find the translation based on input language
                for (Map.Entry<String, String> innerMapElements : innerMap.entrySet()) {
                    String innerKey = innerMapElements.getKey();
                    String innerValue = innerMapElements.getValue();

                    if (word.equals(innerValue)){
                        System.out.println(innerValue);
                        System.out.println(innerKey);
                    }
                }
            }

            // If no match is found, return null or a default message
            System.out.println("Word not found");
            return null;
        }
        return word;
    }
}