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
    //parang constructor siya
    public void initialize(){

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
        Font chavacaknow = Font.loadFont(getClass().getResource("/fonts/MADECarvingSoftPERSONALUSE-Bold.otf").toExternalForm(), 52);

        chavacaKnowText.setFont(chavacaknow);
        continueButton.setFont(texts);
        addButtonDesign(continueButton);
        unlock.setFont(texts);

    }

    //getter method para makuha ng other classes. So way lang siya para magkaron ng connection yung other classes
    //dito kasi nga di pwede mag instantiate
    public static OpeningController getOpeningController() {
        return openingController;

    }


    private void addButtonDesign(Button buttonDesign) {
        buttonDesign.setOnMouseEntered(event -> buttonDesign.setStyle("-fx-background-color: #860606; -fx-background-radius: 5em;"));
        buttonDesign.setOnMouseExited(event -> buttonDesign.setStyle("-fx-background-color:  #E02929; -fx-background-radius: 5em;"));
        buttonDesign.setOnMousePressed(event -> buttonDesign.setStyle("-fx-background-color: #860606; -fx-background-radius: 5em;"));
        buttonDesign.setOnMouseReleased(event -> buttonDesign.setStyle("-fx-background-color: #860606; -fx-background-radius: 5em;"));
    }
}
