package mainarea.structureplease;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class MainPageController {
    @FXML
    public Node wordID;
    public Node dictionaryID;
    public Node translationID;
    public Node chabaFlashID;
    private ImageView wordpopIMGLink, dictionaryIMGLink, translatorIMGLink, chabaflashIMGLink, chabiNormalIMGLink;
    private Image wordpopIMG, dictionaryIMG, translatorIMG, chabaflashIMG, chabiNormalIMG;

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
    }
}
