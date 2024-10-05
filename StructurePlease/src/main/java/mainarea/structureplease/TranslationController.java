package mainarea.structureplease;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslationController implements Initializable {

    @FXML private ChoiceBox<String> choiceBox1;
    @FXML private ChoiceBox<String> choiceBox2;
    @FXML private TextArea textArea1;

    private String[] choiceBoxLanguages = {"Filipino", "English", "Chavacano"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choiceBox1.getItems().addAll(choiceBoxLanguages);
        // if site language is english then the value should be english, if not
        // then it should be filipino
        choiceBox1.setValue("English");
        choiceBox2.getItems().addAll(choiceBoxLanguages);
        // automatically in chavacano
        choiceBox2.setValue("Chavacano");

        choiceBox1.setOnAction(this::getChoiceBoxValue);

    }

    public void getChoiceBoxValue(ActionEvent event) {
        String choiceBox1Value = choiceBox1.getValue();

        if (choiceBox1Value == "Filipino") {
            textArea1.setText("LOE");
        } else if (choiceBox1Value == "English") {
            textArea1.setText("HI");
        } else {
            textArea1.setText("HOLA");
        }
    }
}
