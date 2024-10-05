package mainarea.structureplease;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class OpeningController {
    private Stage stage;
    private Scene scene;
    private Parent root;
//    static instance niya kasi hindi nagana if OpeningController op = new OpeningController
    //may getter method siya sa baba
    @FXML
    public Text chavacaKnowText;
    public Button continueButton;
    public Text unlock;

    private static OpeningController openingController;
    private Data dictionaryData;

    //parang constructor siya
    public void initialize(){
        dictionaryData = new Data();
        dictionaryData.loadDictionary("/data/dictionary.txt");

        openingController = this;
        fontIntializer1();
    }

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

    public void fontIntializer1(){
        Font texts = Font.loadFont(getClass().getResource("/fonts/MADECarvingSoftPERSONALUSE-Regular.otf").toExternalForm(), 32);
        Font chavacaknow = Font.loadFont(getClass().getResource("/fonts/MADECarvingSoftPERSONALUSE-Bold.otf").toExternalForm(), 62);

//        chavacaKnowText.setFont(chavacaknow);
//        continueButton.setFont(texts);
//        unlock.setFont(texts);

    }

    //getter method para makuha ng other classes. So way lang siya para magkaron ng connection yung other classes
    //dito kasi nga di pwede mag instantiate
    public static OpeningController getOpeningController() {
        return openingController;

    }

    public Data getDictionaryData() {
        return dictionaryData;
    }
}
