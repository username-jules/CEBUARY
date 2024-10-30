package mainarea.cebuary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

import java.io.IOException;

public class MainPageController {
    @FXML
    public Node wordID;
    public Node dictionaryID;
    public Node translationID;
    public Node chabaFlashID;

    @FXML
    public Button wordPopButton;
    public Button dictionaryButton;
    public Button translationButton;
    public Button chabaFlashButton;
    public Button chavitoButton;

    private OpeningController openingController;

    public void initialize(){
        openingController = OpeningController.getOpeningController();
        Font chavitoFont = Font.loadFont(getClass().getResource("/fonts/MADECarvingSoftPERSONALUSE-Bold.otf").toExternalForm(), 30);

        addButtonDesign(wordPopButton);
        addButtonDesign(dictionaryButton);
        addButtonDesign(translationButton);
        addButtonDesign(chabaFlashButton);
        addButtonDesign(chavitoButton);
        chavitoButton.setFont(chavitoFont);
    }

    public void switchToOpening(ActionEvent event) throws IOException {
        openingController.switchToOpening(event);
    }

    public void showWordPop() {
        wordID.setVisible(true);
        dictionaryID.setVisible(false);
        translationID.setVisible(false);
        chabaFlashID.setVisible(false);
    }

    public void showDictionary() {
        wordID.setVisible(false);
        dictionaryID.setVisible(true);
        translationID.setVisible(false);
        chabaFlashID.setVisible(false);
    }

    public void showTranslation() {
        wordID.setVisible(false);
        dictionaryID.setVisible(false);
        translationID.setVisible(true);
        chabaFlashID.setVisible(false);

    }

    public void showChabaFlash() {
        chabaFlashID.setVisible(true);
        wordID.setVisible(false);
        dictionaryID.setVisible(false);
        translationID.setVisible(false);
    }


    private void addButtonDesign(Button buttonDesign) {
        Font buttonFonts = Font.loadFont(getClass().getResource("/fonts/MADECarvingSoftPERSONALUSE-Bold.otf").toExternalForm(), 28);
        buttonDesign.setFont(buttonFonts);
        buttonDesign.setOnMouseEntered(event -> buttonDesign.setStyle("-fx-background-color: #FDBCBC; -fx-background-radius: 40; -fx-text-fill: black;"));
        buttonDesign.setOnMouseExited(event -> buttonDesign.setStyle("-fx-background-color: transparent; -fx-background-radius: 40; -fx-text-fill: black;"));
        buttonDesign.setOnMousePressed(event -> buttonDesign.setStyle("-fx-background-color: #FDBCBC; -fx-background-radius: 40; -fx-text-fill: black;"));
        buttonDesign.setOnMouseReleased(event -> buttonDesign.setStyle("-fx-background-color: #FDBCBC; -fx-background-radius: 40; -fx-text-fill: black;"));
    }


}
