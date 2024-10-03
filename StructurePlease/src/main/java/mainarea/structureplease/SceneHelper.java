package mainarea.structureplease;

import javafx.scene.Node;

public class SceneHelper {

    public void showWordPop(Node wordID, Node dictionaryID, Node translationID) {
        wordID.setVisible(true);
        dictionaryID.setVisible(false);
        translationID.setVisible(false);
    }

    public void showDictionary(Node wordID, Node dictionaryID, Node translationID) {
        dictionaryID.setVisible(true);
        wordID.setVisible(false);
        translationID.setVisible(false);
    }

    public void showTranslation(Node wordID, Node dictionaryID, Node translationID) {
        translationID.setVisible(true);
        dictionaryID.setVisible(false);
        wordID.setVisible(false);
    }
}
