package mainarea.structureplease.dictionaryscene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import mainarea.structureplease.OpeningController;

public class DictionarySceneController {
    @FXML
    public TextField searchBar;
    public Node listView;
    public Node vBox;
    public Node dictionaryContent;
    private String input;
    private String searchBarInput;
    private static DictionarySceneController dictionarySceneController;
    private static DictionaryContentController dc;
    private ListViewController listViewController;

    public void initialize(){
        dictionarySceneController = this;
        listViewController = ListViewController.getInstance();
        dc = DictionaryContentController.getDictionaryContentController();

        dictionaryContent.setVisible(false);
        listViewController.updateListViewItems();
    }

    public static DictionarySceneController getDictionarySceneController() {
        return dictionarySceneController;
    }


    public Node getDictionaryContent() {
        return dictionaryContent;
    }

    public Node getListView() {
        return listView;
    }

    public void updateListView(ActionEvent actionEvent) {
        if (dictionaryContent.isVisible()){
            dictionaryContent.setVisible(false);
            listView.setVisible(true);
        }else {
            listView.setVisible(true);
        }
        searchBarInput = searchBar.getText();
        System.out.println(searchBarInput);
//        System.out.println("this is the input from the search bar: " + searchBarInput);
        listViewController.input(searchBarInput);
        listViewController.myListViewItems(searchBarInput);
//        listViewController.printValues();
        listViewController.updateListViewItems();
    }
}
