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
    private String chosenInputLanguage, chosenOutputLanguage, inputWord, outputWord;
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

        // Add key release listener for automatic translation in textArea1
        textArea1.setOnKeyReleased(event -> translateWord(null));
        choiceBox2.setOnAction(event -> translateWord(null));
    }


    // Method to handle translation logic
    public void translateWord(ActionEvent event) {
        inputWord = textArea1.getText().toLowerCase();
        outputWord = textArea2.getText().toLowerCase();

        if (inputWord.isEmpty()) {
            textArea2.clear();
            return;
        }
        chosenInputLanguage = choiceBox1.getValue();
        chosenOutputLanguage = choiceBox2.getValue();

        identifyTheLanguage(inputWord, outputWord);
        translatingTheWords(inputWord, chosenOutputLanguage);

    }

    private String identifyTheLanguage(String word, String wordOutput) {
        word = inputWord;
        wordOutput = outputWord;

        Map<String, Map<String, String>> mainMap = dictionaryData.getDictionary();

        // Loop through the mainMap entries
        for (Map.Entry<String, Map<String, String>> mainMapElements : mainMap.entrySet()) {

            // Get the current key (e.g., 'bienvenidos')
            mainKey = mainMapElements.getKey().toLowerCase();
//            System.out.println("Checking word: " + mainKey);

            // Check if the word matches the mainKey
            if (word.equals(mainKey)) {
//                System.out.println("Word found: " + mainKey);
                choiceBox1.setValue("Chavacano");

                // Stop searching once the word is found
                return mainKey;

            } else if (!word.equals(mainKey)) {
                innerMap = mainMapElements.getValue();
                // Iterate through the innerMap to find the translation based on input language
                for (Map.Entry<String, String> innerMapElements : innerMap.entrySet()) {
                    String innerKey = innerMapElements.getKey();
                    String innerValue = innerMapElements.getValue().toLowerCase();

                    boolean isMatch = isMatch(innerValue,inputWord, innerKey);

                    if (word.equals(innerValue) || isMatch) {

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

        // for the output to automatically identify
        for (Map.Entry<String, Map<String, String>> mainMapElements : mainMap.entrySet()) {
            mainKey = mainMapElements.getKey();

            if (wordOutput.equals(mainKey)) {
                choiceBox1.setValue("Chavacano");

                return mainKey;

            } else if (!wordOutput.equals(mainKey)) {
                innerMap = mainMapElements.getValue();
                for (Map.Entry<String, String> innerMapElements : innerMap.entrySet()) {
                    String innerKey = innerMapElements.getKey();
                    String innerValue = innerMapElements.getValue().toLowerCase();

                    if (wordOutput.equals(innerValue)) {

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

        return "Translation not found";
    }

    private String translatingTheWords(String wordTranslate, String outputLanguage) {
        wordTranslate = inputWord;
        outputLanguage = chosenOutputLanguage;

        Map<String, Map<String, String>> mainMap = dictionaryData.getDictionary();

        // Loop through the mainMap entries
        for (Map.Entry<String, Map<String, String>> mainMapElements : mainMap.entrySet()) {

            // Get the current key (e.g., 'bienvenidos')
            mainKey = mainMapElements.getKey().toLowerCase();
            Map<String, String> innerMap = mainMapElements.getValue();

            for (Map.Entry<String, String> innerMapElements : innerMap.entrySet()) {
                innerKeyy = innerMapElements.getKey();
                innerValuee = innerMapElements.getValue().toLowerCase();

                if (wordTranslate.equals(innerValuee)) {
                    switch (innerKeyy){
                        case "translationEnglish":
                            if (chosenOutputLanguage.equals("Filipino")){
                                String filipinoTrans = innerMap.get("translationFilipino");
                                if (innerValuee.toLowerCase().contains(",")) {
                                    isMatch(innerValuee,wordTranslate,innerKeyy);
                                } else
                                textArea2.setText(filipinoTrans);
                            }
                            else if (chosenOutputLanguage.equals("Chavacano")) {
                                String chavacanoTrans = innerMap.get("alternateChavacano");
                                    if(chavacanoTrans.equals("(N/A)") || chavacanoTrans.equals("N/A")) {
                                        textArea2.setText(mainKey);
                                    } else
                                        textArea2.setText(chavacanoTrans);
                            } else textArea2.setText(wordTranslate);

                            break;
                        case "translationFilipino":
                            if (chosenOutputLanguage.equals("English")){
                                String englishTrans = innerMap.get("translationEnglish");
                                textArea2.setText(englishTrans);
                            }
                            else if (chosenOutputLanguage.equals("Chavacano")) {
                                String chavacanoTrans = innerMap.get("alternateChavacano");
                                if(chavacanoTrans.equals("(N/A)") || chavacanoTrans.equals("N/A")) {
                                    textArea2.setText(mainKey);
                                } else
                                    textArea2.setText(chavacanoTrans);
                            } else textArea2.setText(wordTranslate);
                    }
                }
            }

            if (innerValuee.contains(",")){
                boolean match = isMatch(innerValuee, wordTranslate,innerKeyy);
                if (match) {
                    chavacanoTranslation(wordTranslate);
                }
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
                            if (chavacanoTrans.equals("(N/A)") || chavacanoTrans.equals("N/A")) {
                                textArea2.setText(mainKey);
                            } else textArea2.setText(chavacanoTrans);
                        }
                }

            }

        }

        return "Translation not found";
    }


    private boolean isMatch(String innerMapValue, String input, String key){
        String[] split = innerMapValue.split(", ");
        String translations = "";

        for (int i = 0; i <= split.length - 1; i++){
            if (split[i].toLowerCase().startsWith(input)){
                return true;
            }
        }

        return false;
    }

    private void chavacanoTranslation(String wordTranslate){
        switch (innerKeyy){
            case "translationEnglish":
                if (chosenOutputLanguage.equals("Filipino")){
                    String filipinoTrans = innerMap.get("translationFilipino");
                    textArea2.setText(filipinoTrans);
                }
                else if (chosenOutputLanguage.equals("Chavacano")) {
                    String chavacanoTrans = innerMap.get("alternateChavacano");
                    if(chavacanoTrans.equals("(N/A)") || chavacanoTrans.equals("N/A")) {
                        textArea2.setText(mainKey);
                    } else
                        textArea2.setText(chavacanoTrans);
                } else textArea2.setText(wordTranslate);

                break;
            case "translationFilipino":
                if (chosenOutputLanguage.equals("English")){
                    String englishTrans = innerMap.get("translationEnglish");
                    textArea2.setText(englishTrans);
                }
                else if (chosenOutputLanguage.equals("Chavacano")) {
                    String chavacanoTrans = innerMap.get("alternateChavacano");
                    if(chavacanoTrans.equals("(N/A)") || chavacanoTrans.equals("N/A")) {
                        textArea2.setText(mainKey);
                    } else
                        textArea2.setText(chavacanoTrans);
                } else textArea2.setText(wordTranslate);
        }
    }
}