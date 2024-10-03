package mainarea.structureplease;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class DictionarySceneController {
    @FXML
    public Text word, enunciation, classification, alternateChavacano, translationEnglish,
            translationFilipino, exampleEnglish, exampleFilipino, exampleChavacano;

    public void initialize(){
        alternateChavacano.setText("• " + alternateChavacano.getText());
        translationEnglish.setText("• " + translationEnglish.getText());
        translationFilipino.setText("• " + translationFilipino.getText());
        exampleEnglish.setText("• " + exampleEnglish.getText());
        exampleFilipino.setText("• " + exampleFilipino.getText());
        exampleChavacano.setText("• " + exampleChavacano.getText());
    }
}
