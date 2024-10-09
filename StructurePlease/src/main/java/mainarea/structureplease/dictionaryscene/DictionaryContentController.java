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
        dictionaryData = OpeningController.getOpeningController().getDictionaryData();

    }
    public void displayDictionary(){
        System.out.println("the is the input that was passed: " + input);

        String enun = dictionaryData.getDetails(input,"enunciation");
        String classif = dictionaryData.getDetails(input,"classification");
        String transEn = dictionaryData.getDetails(input,"translationEnglish");
        String transFil = dictionaryData.getDetails(input,"translationFilipino");
        String exampleEn = dictionaryData.getDetails(input,"exampleEnglish");
        String exampleFil = dictionaryData.getDetails(input,"exampleFilipino");
        String exampleChav = dictionaryData.getDetails(input,"exampleChavacano");

        word.setText(input);
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
