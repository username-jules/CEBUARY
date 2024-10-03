package mainarea.structureplease;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

import java.io.IOException;

public class MainPageController {
    @FXML
    public Node wordID;
    public Node dictionaryID;
    public Node translationID;

    private OpeningController openingController;

    public void initialize(){
        openingController = OpeningController.getOpeningController();
    }

    public void switchToOpening(ActionEvent event) throws IOException {
        openingController.switchToOpening(event);
    }

    public void showWordPop() {
        wordID.setVisible(true);
        dictionaryID.setVisible(false);
        translationID.setVisible(false);
    }

    public void showDictionary() {
        wordID.setVisible(false);
        dictionaryID.setVisible(true);
        translationID.setVisible(false);
    }

    public void showTranslation() {
        wordID.setVisible(false);
        dictionaryID.setVisible(false);
        translationID.setVisible(true);
    }
}
