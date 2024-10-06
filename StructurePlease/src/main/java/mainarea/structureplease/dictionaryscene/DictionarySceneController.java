package mainarea.structureplease.dictionaryscene;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import mainarea.structureplease.Data;
import mainarea.structureplease.OpeningController;

import java.util.List;

public class DictionarySceneController {
    @FXML
    public TextField searchBar;
    private String input;
    private String searchBarInput;
    private Data dictionaryData;
    private static DictionarySceneController dictionarySceneController;
    private static DictionaryContentController dc;
    private ListViewController listViewController;
    public void initialize(){
        dictionarySceneController = this;
        listViewController = ListViewController.getInstance();
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

    public void passInput(KeyEvent keyEvent) {
        searchBarInput = searchBar.getText();
        System.out.println("this is the input from the search bar: " + searchBarInput);
        listViewController.setKey(searchBarInput);
        listViewController.createItems();
        listViewController.printValues();
    }
}
