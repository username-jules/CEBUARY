package mainarea.structureplease.dictionaryscene;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import mainarea.structureplease.Data;
import mainarea.structureplease.OpeningController;

public class DictionarySceneController {
    @FXML
    public TextField searchBar;
    public Node listView;
    public Node vBox;
    public Node dictionaryContent;
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

        dictionaryContent.setVisible(false);
        listViewController.updateListViewItems();
    }

    public static DictionarySceneController getDictionarySceneController() {
        return dictionarySceneController;
    }

    public Data getDictionaryData() {
        return dictionaryData;
    }

    public Node getDictionaryContent() {
        return dictionaryContent;
    }

    public Node getListView() {
        return listView;
    }

    public void passInput(KeyEvent keyEvent) {

        if (keyEvent.isControlDown() && keyEvent.getCharacter().equalsIgnoreCase("s")) {
            System.out.println("Ctrl+S pressed. Save function triggered.");
            keyEvent.consume(); // Consume the event to prevent further processing if needed
            return; // Exit the method after handling Ctrl+S
        } else if (keyEvent.isControlDown() && keyEvent.getCharacter().equalsIgnoreCase("z")) {
            System.out.println("Ctrl+Z pressed. Undo function triggered.");
            keyEvent.consume();
            return; // Exit the method after handling Ctrl+Z
        }

        if (dictionaryContent.isVisible()){
            dictionaryContent.setVisible(false);
            listView.setVisible(true);
        }else {
            listView.setVisible(true);
        }
        searchBarInput = searchBar.getText();
        System.out.println("this is the input from the search bar: " + searchBarInput);
        listViewController.setKey(searchBarInput);
        listViewController.createListViewItems();
//        listViewController.printValues();
        listViewController.updateListViewItems();
    }
}
