package mainarea.cebuary.dictionaryscene;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mainarea.cebuary.dictionaryloader.LoadDictionary;

public class DictionaryContentController {
    public Text word, enunciation, classification, transEng, exCebuano, exEnglish,
            englishLabel, exampleEnglish, exampleCebuano;
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
        String fontBold = "/fonts/MADECarvingSoftPERSONALUSE-Bold.otf";
        String fontRegular = "/fonts/MADECarvingSoftPERSONALUSE-Regular.otf";
        setTextFont(word,fontBold,48);
        setTextFont(enunciation,fontRegular,24);
        setTextFont(classification,fontRegular,24);
        setTextFont(englishLabel, fontBold, 20);
        setTextFont(transEng,fontRegular,24);
        setTextFont(exampleCebuano, fontBold, 20);
        setTextFont(exCebuano,fontRegular, 24);
        setTextFont(exampleEnglish, fontBold, 20);
        setTextFont(exEnglish,fontRegular, 24);
        word.setText(input);
        enunciation.setText(dictionary.getEnunciation(input));
        classification.setText(dictionary.getClassification(input));
        transEng.setText(dictionary.getTransEng(input));
        exCebuano.setText(dictionary.getExCeb(input));
        exEnglish.setText(dictionary.getExEng(input));

    }
    private void setTextFont(Text text, String fontPath, int fontSize){
        Font myFont = Font.loadFont(getClass().getResource(fontPath).toExternalForm(), fontSize);
        text.setFont(myFont);
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
