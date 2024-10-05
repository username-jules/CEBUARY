package mainarea.structureplease.dictionaryscene;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import mainarea.structureplease.Data;
import mainarea.structureplease.OpeningController;

public class DictionarySceneController {
    @FXML
    public TextField searchBar;
    private String input;
    private Data dictionaryData;
    private static DictionarySceneController dictionarySceneController;
    private static DictionaryContentController dc;
    public void initialize(){
        dictionarySceneController = this;
        dc = DictionaryContentController.getDictionaryContentController();
        dictionaryData = OpeningController.getOpeningController().getDictionaryData();
        input = "Plea";
        dc.setInput(input);
        dc.displayDictionary();
    }


    public static DictionarySceneController getDictionarySceneController() {
        return dictionarySceneController;
    }

    public Data getDictionaryData() {
        return dictionaryData;
    }
}
