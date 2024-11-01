package mainarea.cebuary.dictionaryscene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DictionarySceneController {
    @FXML
    public TextField searchBar;
    public Text headerText;
    public Node listView;
    public Node vBox;
    public Node dictionaryContent;
    private String input;
    private String searchBarInput;
    private static DictionarySceneController dictionarySceneController;
    private static DictionaryContentController dc;
    private ListViewController listViewController;

    public void initialize(){
        Font myFont = Font.loadFont(getClass().getResource("/fonts/MADECarvingSoftPERSONALUSE-Bold.otf").toExternalForm(), 48);
        headerText.setFont(myFont);
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


    public void searchOnType(){
        updateListViewItems();
    }
    public void searchOnButtonClick(ActionEvent actionEvent) {
        updateListViewItems();
    }
    private void updateListViewItems(){
        if (dictionaryContent.isVisible()){
            dictionaryContent.setVisible(false);
            listView.setVisible(true);
        }else {
            listView.setVisible(true);
        }
        searchBarInput = searchBar.getText();

        listViewController.input(searchBarInput);
        listViewController.myListViewItems(searchBarInput);

        listViewController.updateListViewItems();
    }
}
