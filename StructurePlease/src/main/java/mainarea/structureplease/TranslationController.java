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
    private String mainKey, innerKeyy, innerValuee;
    private Map<String, String> innerMap;
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
//        choiceBox2.setOnAction(this::translateWord);

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

        identifyTheLanguage(inputWord, chosenInputLanguage);
        translatingTheWords(inputWord, chosenOutputLanguage);

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
                return mainKey;

            } else if (!word.equals(mainKey)) {
                innerMap = mainMapElements.getValue();
                // Iterate through the innerMap to find the translation based on input language
                for (Map.Entry<String, String> innerMapElements : innerMap.entrySet()) {
                    String innerKey = innerMapElements.getKey();
                    String innerValue = innerMapElements.getValue();

                    if (word.equals(innerValue)) {
                        System.out.println("this is the inner value " + innerValue);
                        System.out.println("this is the inner key " + innerKey);

                        switch (innerKey) {
                            case "translationFilipino":
                                choiceBox1.setValue("Filipino");
                                break;
                            case "translationEnglish":
                                choiceBox1.setValue("English");
                                break;
                            case "alternateChavacano":
                                choiceBox1.setValue("Chavacano");
                                break;
                        }

                        return innerValue;
                    }
                }
            }
        }
        System.out.println("Word not found");
        return "Translation not found";
    }

    private String translatingTheWords(String wordTranslate, String outputLanguage) {
        wordTranslate = inputWord;
        outputLanguage = chosenOutputLanguage;

        Map<String, Map<String, String>> mainMap = dictionaryData.getDictionary();

        // Loop through the mainMap entries
        for (Map.Entry<String, Map<String, String>> mainMapElements : mainMap.entrySet()) {

            // Get the current key (e.g., 'bienvenidos')
            mainKey = mainMapElements.getKey();
            Map<String, String> innerMap = mainMapElements.getValue();
            for (Map.Entry<String, String> innerMapElements : innerMap.entrySet()) {
                innerKeyy = innerMapElements.getKey();
                innerValuee = innerMapElements.getValue();
            }
// if chavacano ung language
            if (wordTranslate.equals(mainKey)) {
                switch (outputLanguage) {
                    case "English":
                        for (Map.Entry<String, String> innerMapElements : innerMap.entrySet()) {
                            String englishTrans = innerMap.get("translationEnglish");
                            System.out.println(englishTrans);
                            textArea2.setText(englishTrans);
                        }
                        break;
                    case "Filipino":
                        for (Map.Entry<String, String> innerMapElements : innerMap.entrySet()) {
                            String filipinoTrans = innerMap.get("translationFilipino");
                            System.out.println(filipinoTrans);
                            textArea2.setText(filipinoTrans);
                        }
                        break;
                    case "Chavacano":
                        for (Map.Entry<String, String> innerMapElements : innerMap.entrySet()) {
                            String chavacanoTrans = innerMap.get("alternateChavacano");
                            if (chavacanoTrans.equals("(N/A)")) {
                                textArea2.setText(mainKey);
                            } else textArea2.setText(chavacanoTrans);
                        }
                }
// if english ung language

            } else if (wordTranslate.equals(innerValuee)) {
                System.out.println(innerValuee);
                System.out.println(innerKeyy);
                switch (innerKeyy){
                    case "translationEnglish":
                        for (Map.Entry<String, String> innerMapElements : innerMap.entrySet()) {
                            String englishTrans = innerMap.get("translationEnglish");
                            System.out.println("english trans to: " + englishTrans);
                            textArea2.setText(englishTrans);
                        }
                        break;
                    case "translationFilipino":
                        for (Map.Entry<String, String> innerMapElements : innerMap.entrySet()) {
                            String filipinoTrans = innerMap.get("translationFilipino");
                            System.out.println("filo trans" + filipinoTrans);
                            textArea2.setText(filipinoTrans);
                        }
                        break;
                }
            }

        }

        return "Translation not found";
    }
}