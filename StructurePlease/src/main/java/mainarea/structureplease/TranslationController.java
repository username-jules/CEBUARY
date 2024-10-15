package mainarea.structureplease;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.net.URL;
import java.util.IllegalFormatCodePointException;
import java.util.Map;
import java.util.ResourceBundle;

public class TranslationController implements Initializable {

    @FXML private ChoiceBox<String> choiceBox1;
    @FXML private ChoiceBox<String> choiceBox2;
    @FXML private TextArea textArea1;
    @FXML private TextArea textArea2;

    @FXML private Text translatorText;
    private Data dictionaryData;
    private String chosenInputLanguage, chosenOutputLanguage, inputWord, outputWord;
    private String mainKey, mainK, innerKey, innerValue, innerVal;
    private Map<String, String> innerMap;
    private String[] choiceBoxLanguages = {"Filipino", "English", "Chavacano"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Font headerText = Font.loadFont(getClass().getResource("/fonts/MADECarvingSoftPERSONALUSE-Bold.otf").toExternalForm(), 48);

        translatorText.setFont(headerText);

        dictionaryData = OpeningController.getOpeningController().getDictionaryData();

        // Set up choiceBox values
        choiceBox1.getItems().addAll(choiceBoxLanguages);
        addCheckBoxDesign(choiceBox1);
        choiceBox1.setValue("Chavacano");

        choiceBox2.getItems().addAll(choiceBoxLanguages);
        addCheckBoxDesign(choiceBox2);
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

    private void identifyTheLanguage(String word, String wordOutput) {
        word = inputWord;
        wordOutput = outputWord;

        Map<String, Map<String, String>> mainMap = dictionaryData.getDictionary();

        // Loop through the mainMap entries
        for (Map.Entry<String, Map<String, String>> mainMapElements : mainMap.entrySet()) {

            mainKey = mainMapElements.getKey().toLowerCase();

            if (word.equals(mainKey)) {
                choiceBox1.setValue("Chavacano");

                // Stop searching once the word is found

            } else if (!word.equals(mainKey)) {
                innerMap = mainMapElements.getValue();
                // Iterate through the innerMap to find the translation based on input language
                for (Map.Entry<String, String> innerMapElements : innerMap.entrySet()) {
                    innerKey = innerMapElements.getKey();
                    innerValue = innerMapElements.getValue().toLowerCase();

                    // Checking it matches regardless of the ","
                    boolean isMatch = isMatch(innerValue,inputWord);

                    if (word.equals(innerValue) || isMatch) {
                        switch (innerKey) {
                            case "translationFilipino":
                                choiceBox1.setValue("Filipino");
                                break;
                            case "translationEnglish":
                                choiceBox1.setValue("English");
                                break;
                        }
                    }
                }
            }
        }

        // for the output to automatically identify
        for (Map.Entry<String, Map<String, String>> mainMapElements : mainMap.entrySet()) {
            mainKey = mainMapElements.getKey();

            if (wordOutput.equals(mainKey)) {
                choiceBox1.setValue("Chavacano");

            } else if (!wordOutput.equals(mainKey)) {
                innerMap = mainMapElements.getValue();
                for (Map.Entry<String, String> innerMapElements : innerMap.entrySet()) {
                    innerKey = innerMapElements.getKey();
                    innerValue = innerMapElements.getValue().toLowerCase();

                    if (wordOutput.equals(innerValue)) {

                        switch (innerKey) {
                            case "translationFilipino":
                                choiceBox1.setValue("Filipino");
                                break;
                            case "translationEnglish":
                                choiceBox1.setValue("English");
                                break;
                        }

                    }
                }
            }
        }

    }

    private void translatingTheWords(String words, String outputLanguage) {
        words = inputWord;
        outputLanguage = chosenOutputLanguage;

        Map<String, Map<String, String>> mainMap = dictionaryData.getDictionary();
        boolean isFound = false;

        // Loop through the mainMap entries
        for (Map.Entry<String, Map<String, String>> mainMapElements : mainMap.entrySet()) {

            // Get the current key (e.g., 'bienvenidos')
            mainK = mainMapElements.getKey();
            mainKey = mainK.toLowerCase();

            //the inner hashmap
            Map<String, String> innerMap = mainMapElements.getValue();

            //if a match is not found, run the condition
            if (!isFound){
                //if the input is equal to the main key
                if (words.equals(mainKey)) {
                    switch (outputLanguage) {
                        case "English":
                            String englishTrans = innerMap.get("translationEnglish");
                            textArea2.setText(englishTrans);
                            break;
                        case "Filipino":
                            String filipinoTrans = innerMap.get("translationFilipino");
                            textArea2.setText(filipinoTrans);
                            break;
                        case "Chavacano":
                            textArea2.setText(mainK);
                            break;
                    }
                    //match found, ends the loop
                    isFound = true;
                }

                //if the input is not equals to the main key, run the condition
                if (!words.equals(mainKey)){
                    //iterate throught the inner hashmap
                    for (Map.Entry<String, String> innerMapElements : innerMap.entrySet()) {
                        innerKey = innerMapElements.getKey();
                        innerVal = innerMapElements.getValue();
                        innerValue = innerVal.toLowerCase();

                        //if there is no found match, run the condition
                        if (!isFound) {

                            //if the input is equal to the innervalue
                            if (words.equals(innerValue)) {

                                //the key of the current inner value
                                switch (innerKey) {
                                    // basically if it connects to english then it will automatically translate tagalog
                                    case "translationEnglish":
                                        // and this is the choicebox area to just specify if the chosen if filipino then it will print the filipino
                                        // translation of that word
                                        if (chosenOutputLanguage.equals("Filipino")) {
                                            String filipinoTrans = innerMap.get("translationFilipino");
                                            textArea2.setText(filipinoTrans);
                                            // else if the choicebox is specified with chavacano then it should just print the mainK
                                            // mainK = chavacano automatically
                                        } else if (chosenOutputLanguage.equals("Chavacano")) {
                                            textArea2.setText(mainK);
                                        }
                                        // else, kung english to english it should just print the inner value
                                        else textArea2.setText(innerVal);
                                        isFound = true;
                                        break;
                                    case "translationFilipino":
                                        if (chosenOutputLanguage.equals("English")) {
                                            String englishTrans = innerMap.get("translationEnglish");
                                            textArea2.setText(englishTrans);
                                        } else if (chosenOutputLanguage.equals("Chavacano")) {
                                            textArea2.setText(mainK);
                                        } else textArea2.setText(innerVal);
                                        isFound = true;
                                        break;
                                }
                                //if the inner value has a comma, separates the values, and
                                // checks if the word is equal to one of those values
                            } else if (innerValue.contains(",")) {
                                boolean match = isMatch(innerValue, words);
                                if (match) {
                                    chavacanoTranslation();
                                    textArea2.setText(innerValue);
                                    isFound = true;

                                    if (outputLanguage.equals("English")) {
                                        String translation = innerMap.get("translationEnglish");
                                        textArea2.setText(translation);
                                    } else if (outputLanguage.equals("Filipino")) {
                                        String translation = innerMap.get("translationFilipino");
                                        textArea2.setText(translation);
                                    } else {
                                        String translation = mainK;
                                        textArea2.setText(translation);
                                    }
                                }
                                //if there are no matches
                            } else {
                                textArea2.setText(" ");
                            }
                        }


                    }
                }
            }
        }
    }


    private boolean isMatch(String innerMapValue, String input){
        String[] split = innerMapValue.split(", ");

        for (int i = 0; i <= split.length - 1; i++){
            if (split[i].toLowerCase().equals(input)){
                return true;
            }
        }

        return false;
    }

    private void chavacanoTranslation(){
        switch (innerKey){
            case "translationEnglish":
                if (chosenOutputLanguage.equals("Filipino")){
                    String filipinoTrans = innerMap.get("translationFilipino");
                    textArea2.setText(filipinoTrans);
                }
                else if (chosenOutputLanguage.equals("Chavacano")) {
                        textArea2.setText(mainK);
                } else textArea2.setText(mainK);

                break;
            case "translationFilipino":
                if (chosenOutputLanguage.equals("English")){
                    String englishTrans = innerMap.get("translationEnglish");
                    textArea2.setText(englishTrans);
                }
                else if (chosenOutputLanguage.equals("Chavacano")) {
                        textArea2.setText(mainK);
                } else textArea2.setText(mainK);
        }
    }

    private void addCheckBoxDesign(ChoiceBox<String> choiceBoxDesign) {
        // Set style for the ChoiceBox itself
        choiceBoxDesign.setStyle(
                "-fx-background-color: #FFFFFF;" +  // White background for visibility and interaction
                        "-fx-font-family: 'MADE Carving Bold';" +  // Correct font family
                        "-fx-font-size: 15px;" +  // Font size
                        "-fx-padding: 5;" +  // Padding for text spacing
                        "-fx-arrow-size: 14;"  // Size of the dropdown arrow
        );
    }



}