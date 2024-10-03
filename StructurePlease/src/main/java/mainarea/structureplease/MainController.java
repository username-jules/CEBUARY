package mainarea.structureplease;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private SceneHelper sceneHelper = new SceneHelper(); // Initialize helper class

    @FXML private Node wordID;
    @FXML private Node dictionaryID;
    @FXML private Node translationID;

    public void switchToOpening(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("opening-scene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainpage-scene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showWordPop() {
        sceneHelper.showWordPop(wordID, dictionaryID, translationID);  // Use helper class method
    }

    public void showDictionary() {
        sceneHelper.showDictionary(wordID, dictionaryID, translationID);  // Use helper class method
    }

    public void showTranslation() {
        sceneHelper.showTranslation(wordID, dictionaryID, translationID);  // Use helper class method
    }
}
