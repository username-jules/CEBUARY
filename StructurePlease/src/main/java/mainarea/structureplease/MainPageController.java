package mainarea.structureplease;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.IOException;

public class MainPageController {
    @FXML
    public Node wordID;
    public Node dictionaryID;
    public Node translationID;
    public Node chabaFlashID;
    private ImageView wordpopIMGLink, dictionaryIMGLink, translatorIMGLink, chabaflashIMGLink, chabiNormalIMGLink, chabiThinkingIMGLink;
    private Image wordpopIMG, dictionaryIMG, translatorIMG, chabaflashIMG, chabiNormalIMG, chabiThinkingIMG;
    @FXML
    public Button wordPopButton;
    public Button dictionaryButton;
    public Button translationButton;
    public Button chabaFlashButton;
    public Button chavitoButton;

    private OpeningController openingController;

    public void initialize(){
        openingController = OpeningController.getOpeningController();
        Font chavitoFont = Font.loadFont(getClass().getResource("/fonts/MADECarvingSoftPERSONALUSE-Bold.otf").toExternalForm(), 38);

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

    public void initializingImgs(){
        wordpopIMG = new Image(getClass().getResourceAsStream("/resources/images/wordpopimg.png"));
        wordpopIMGLink.setImage(wordpopIMG);

        dictionaryIMG = new Image(getClass().getResourceAsStream("/resources/images/dictionaryimg.png"));
        dictionaryIMGLink.setImage(dictionaryIMG);

        translatorIMG = new Image(getClass().getResourceAsStream("/resources/images/translatorimg.png"));
        translatorIMGLink.setImage(translatorIMG);

        chabaflashIMG = new Image(getClass().getResourceAsStream("/resources/images/chabaflashimg.png"));
        chabaflashIMGLink.setImage(chabaflashIMG);

        chabiNormalIMG = new Image(getClass().getResourceAsStream("/resources/images/chabiNormal.png"));
        chabiNormalIMGLink.setImage(chabiNormalIMG);

        chabiThinkingIMG = new Image(getClass().getResourceAsStream("/resources/images/chabiThinking.png"));
        chabiThinkingIMGLink.setImage(chabiThinkingIMG);
    }

    //DI NAGANA !!! FOR HOVER// OR WHEN A BUTTON IS PRESSED
//    public void onPressed(){
//        wordPopButton.setBackground(Background.fill(Color.web("#F92727")));
//    }

    private void addButtonDesign(Button buttonDesign) {
        Font buttonFonts = Font.loadFont(getClass().getResource("/fonts/MADECarvingSoftPERSONALUSE-Bold.otf").toExternalForm(), 28);
        buttonDesign.setFont(buttonFonts);
        buttonDesign.setOnMouseEntered(event -> buttonDesign.setStyle("-fx-background-color: #FDBCBC; -fx-background-radius: 40; -fx-text-fill: black;"));
        buttonDesign.setOnMouseExited(event -> buttonDesign.setStyle("-fx-background-color: transparent; -fx-background-radius: 40; -fx-text-fill: black;"));
        buttonDesign.setOnMousePressed(event -> buttonDesign.setStyle("-fx-background-color: #FDBCBC; -fx-background-radius: 40; -fx-text-fill: black;"));
        buttonDesign.setOnMouseReleased(event -> buttonDesign.setStyle("-fx-background-color: #FDBCBC; -fx-background-radius: 40; -fx-text-fill: black;"));
    }


}
