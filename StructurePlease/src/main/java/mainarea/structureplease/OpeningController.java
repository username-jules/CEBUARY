package mainarea.structureplease;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OpeningController {
    private Stage stage;
    private Scene scene;
    private Parent root;
//    static instance niya kasi hindi nagana if OpeningController op = new OpeningController
    //may getter method siya sa baba
    private static OpeningController openingController;

    //parang constructor siya
    public void initialize(){
        openingController = this;
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


    //getter method para makuha ng other classes. So way lang siya para magkaron ng connection yung other classes
    //dito kasi nga di pwede mag instantiate
    public static OpeningController getOpeningController() {
        return openingController;
    }
}
