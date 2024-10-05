package mainarea.structureplease.dictionaryscene;

import javafx.scene.text.Text;
import mainarea.structureplease.Data;
import mainarea.structureplease.OpeningController;

public class DictionaryContentController {
    public Text word, enunciation, classification, translationEnglish,
            translationFilipino, exampleEnglish, exampleFilipino, exampleChavacano, exampleSentences;

    private Data dictionaryData;
    private OpeningController op;
    private String input;
    private static DictionaryContentController dictionaryContentController;

    public void initialize(){
        dictionaryContentController = this;
        input = "Deserted";
        dictionaryData = OpeningController.getOpeningController().getDictionaryData();
        displayDictionary();
    }
    public void displayDictionary(){
        String key = dictionaryData.getKeyByValue(input);

        String enun = dictionaryData.getDetails(key,"enunciation");
        String classif = dictionaryData.getDetails(key,"classification");
        String transEn = dictionaryData.getDetails(key,"translationEnglish");
        String transFil = dictionaryData.getDetails(key,"translationFilipino");
        String exampleEn = dictionaryData.getDetails(key,"exampleEnglish");
        String exampleFil = dictionaryData.getDetails(key,"exampleFilipino");
        String exampleChav = dictionaryData.getDetails(key,"exampleChavacano");

        word.setText(key);
        enunciation.setText(enun);
        classification.setText(classif);
        translationEnglish.setText("In English: "+ transEn);
        translationFilipino.setText("In Filipino: " +transFil);
        exampleEnglish.setText("English: "+ exampleEn);
        exampleFilipino.setText("Filipino: " + exampleFil);
        exampleChavacano.setText("Chavacano: " + exampleChav);
    }

    public void setInput(String input){
        this.input = input;
    }

    public static DictionaryContentController getDictionaryContentController() {
        return dictionaryContentController;
    }
}
