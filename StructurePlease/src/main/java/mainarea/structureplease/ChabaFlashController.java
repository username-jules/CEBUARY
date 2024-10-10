package mainarea.structureplease;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChabaFlashController implements Initializable {

    // PROGRESS BAR
    @FXML private ProgressBar chabaFlashProgressBar;

    @FXML private Text chabaflashText;
    private double progress = 0.1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Font headerText = Font.loadFont(getClass().getResource("/fonts/MADECarvingSoftPERSONALUSE-Bold.otf").toExternalForm(), 48);
        chabaflashText.setFont(headerText);
        ffImagesInitializer();
        insertingImages();
        chabaFlashProgressBar.setStyle("-fx-accent: red;");
    }

    // IMAGES
    private List<Image> imageList =new ArrayList<>();
    private int currentIndex = 0;
    @FXML public ImageView funfactImgView;
    public ImageView chabiStandingIMGView;
    private Image funfact1, funfact2, funfact3, funfact4,funfact5,funfact6,funfact7,funfact8,funfact9,funfact10, chaviImg;

    public void insertingImages(){
        imageList.add(funfact1);
        imageList.add(funfact2);
        imageList.add(funfact3);
        imageList.add(funfact4);
        imageList.add(funfact5);
        imageList.add(funfact6);
        imageList.add(funfact7);
        imageList.add(funfact8);
        imageList.add(funfact9);
        imageList.add(funfact10);

        if (!imageList.isEmpty()) {
            funfactImgView.setImage(imageList.get(currentIndex)); // Display the first image
        }
    }

    @FXML
    public void backFunction(ActionEvent event) {
        if (currentIndex > 0) {
            currentIndex--;
            funfactImgView.setImage(imageList.get(currentIndex));

            progress -= 0.1;
            chabaFlashProgressBar.setProgress(progress);
        }
    }

    @FXML
    public void forwardFunction(ActionEvent event) {
        if (currentIndex < imageList.size() - 1) {
            currentIndex++;
            funfactImgView.setImage(imageList.get(currentIndex));

            progress += 0.1;
            chabaFlashProgressBar.setProgress(progress);
        }
    }

    public void ffImagesInitializer() {
        chaviImg = new Image(getClass().getResourceAsStream("/images/chabiStand.png"));
        chabiStandingIMGView.setImage(chaviImg);

        funfact1 = new Image(getClass().getResourceAsStream("/FFimg/FunFact10.png"));
        funfact2 = new Image(getClass().getResourceAsStream("/FFimg/FunFact2.png"));
        funfact3 = new Image(getClass().getResourceAsStream("/FFimg/FunFact3.png"));
        funfact4 = new Image(getClass().getResourceAsStream("/FFimg/FunFact4.png"));
        funfact5 = new Image(getClass().getResourceAsStream("/FFimg/FunFact5.png"));
        funfact6 = new Image(getClass().getResourceAsStream("/FFimg/FunFact6.png"));
        funfact7 = new Image(getClass().getResourceAsStream("/FFimg/FunFact7.png"));
        funfact8 = new Image(getClass().getResourceAsStream("/FFimg/FunFact8.png"));
        funfact9 = new Image(getClass().getResourceAsStream("/FFimg/FunFact9.png"));
        funfact10 = new Image(getClass().getResourceAsStream("/FFimg/FunFact1.png"));
    }


}
