package mainarea.structureplease.dictionaryscene;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import mainarea.structureplease.Data;
import mainarea.structureplease.OpeningController;
import mainarea.structureplease.dictionaryloader.LoadDictionary;

public class DictionaryContentController {
    public Text word, enunciation, classification, translationEnglish,
            translationFilipino, exampleEnglish, exampleFilipino, exampleChavacano, exampleSentences;

    private Data dictionaryData;
    public Node mainWindow;
    public Button returnButton;
    private String input;

    private static DictionaryContentController dictionaryContentController;
    private DictionarySceneController dictionarySceneController;
    private ListViewController listViewController;

    private LoadDictionary dictionary;

    public void initialize(){
        dictionaryContentController = this;
        dictionaryData = OpeningController.getOpeningController().getDictionaryData();
        dictionary = new LoadDictionary();
        listViewController = ListViewController.getInstance();


    }
    public void displayDictionary(){
        System.out.println("the is the input that was passed: " + input);

        String enun = dictionary.getPronunciation(input);
        String classif = dictionary.getClassification(input);
        String transEn = dictionary.getTransEng(input);
        String transFil = dictionary.getTransFil(input);
        String exampleEn = dictionary.getExEng(input);
        String exampleFil = dictionary.getExFil(input);
        String exampleChav = dictionary.getExCha(input);

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

    public void returnToListView(){
        dictionarySceneController = DictionarySceneController.getDictionarySceneController();
        listViewController.input("");
        listViewController.updateListViewItems();
        dictionarySceneController.getListView().setVisible(true);
        mainWindow.setVisible(false);
    }


    public static DictionaryContentController getDictionaryContentController() {
        return dictionaryContentController;
    }
}
