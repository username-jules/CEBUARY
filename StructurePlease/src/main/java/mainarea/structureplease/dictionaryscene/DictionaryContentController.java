package mainarea.structureplease.dictionaryscene;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import mainarea.structureplease.OpeningController;
import mainarea.structureplease.dictionaryloader.DATA2;
import mainarea.structureplease.dictionaryloader.LoadDictionary;

public class DictionaryContentController {
    public Text word, enunciation, classification, transEng, exCebuano, exEnglish;
    public Node mainWindow;
    public Button returnButton;
    private String input;

    private static DictionaryContentController dictionaryContentController;
    private DictionarySceneController dictionarySceneController;
    private ListViewController listViewController;

    private LoadDictionary dictionary;

    public void initialize(){
        dictionaryContentController = this;
        dictionary = new LoadDictionary();
        listViewController = ListViewController.getInstance();


    }
    public void displayDictionary(){
        word.setText(input);
        enunciation.setText(dictionary.getEnunciation(input));
        classification.setText(dictionary.getClassification(input));
        transEng.setText(dictionary.getTransEng(input));
        exCebuano.setText(dictionary.getExCeb(input));
        exEnglish.setText(dictionary.getExEng(input));

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
