package mainarea.structureplease.dictionaryscene;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import mainarea.structureplease.Data;
import mainarea.structureplease.OpeningController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListViewController {
    @FXML
    public ListView<DictionaryEntry> myListView;
    public Node vBox;
    private String input, selectedKey;
    private Data dictionaryData;
    private static ListViewController instance;
    private DictionarySceneController mainScene;
    private DictionaryContentController dc;
    private final String[] targetKeys  = {"translationEnglish", "translationFilipino"};


    public void initialize(){
        instance = this;
        dictionaryData = OpeningController.getOpeningController().getDictionaryData();
        input = "";

        myListView.setCellFactory(param -> new ListCell<DictionaryEntry>() {
            private final VBox vbox = new VBox(5); // VBox with spacing between labels

            @Override
            protected void updateItem(DictionaryEntry item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null); // Clear the cell if it's empty
                } else {
                    // Create labels for each piece of information
                    Label wordLabel = new Label(item.getWord());
                    Label englishLabel = new Label("English: " + item.getTranslationEnglish());
                    Label filipinoLabel = new Label("Filipino: " + item.getTranslationFilipino());

                    wordLabel.setFont(Font.font(null, 24));
                    englishLabel.setFont(Font.font(null, 12)); // Set font size to 12 for English label
                    filipinoLabel.setFont(Font.font(null, 12));

                    // Clear and add labels to VBox
                    vbox.getChildren().clear();
                    vbox.getChildren().addAll(wordLabel, englishLabel, filipinoLabel);

                    setGraphic(vbox); // Use VBox as the graphic for the cell
                }
            }
        });

        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DictionaryEntry>() {
            @Override
            public void changed(ObservableValue<? extends DictionaryEntry> observableValue, DictionaryEntry oldValue, DictionaryEntry newValue) {
                if (newValue != null) {
                    dc = DictionaryContentController.getDictionaryContentController();
                    String selectedKey = newValue.getWord() + "\n"
                            + newValue.getTranslationEnglish() + "\n"
                            + newValue.getTranslationFilipino();

                    mainScene = DictionarySceneController.getDictionarySceneController();
                    vBox.setVisible(false);
                    mainScene.getDictionaryContent().setVisible(true);

                    System.out.println("This is the input before passing: " + selectedKey);
                    dc.setInput(newValue.getWord());
                    dc.displayDictionary();
                }
            }
        });

    }
    public void setKey(String key){
        this.input = key;
    }
    //creates items for the listview
    public ArrayList<DictionaryEntry> createListViewItems(){
        input = input.toLowerCase();
        System.out.println(input);
        //stores the results from search (keys)
        ArrayList<DictionaryEntry> list = new ArrayList<>();

        //gets the map
        Map<String, Map<String,String>> map = dictionaryData.getDictionary();

        //iterates through the dictionary map
        for (Map.Entry<String , Map<String, String>> mapElements: map.entrySet()){

            //takes the map within the dictionary map (inner map)
            Map<String, String> innerMap = mapElements.getValue();

            //takes the current key of the dictionary map
            String key = mapElements.getKey();

            //if key contains input adds the key to the List
            //ex: Bienvenidos contains Bie
            if (key.toLowerCase().contains(input)){
                String translationEnglish = innerMap.get("translationEnglish");
                String translationFilipino = innerMap.get("translationFilipino");
                list.add(new DictionaryEntry(key, translationEnglish, translationFilipino));
            }
            else { //if key does not contain or is not equal to the input, checks for the inner map (english translation/ filipino translation)
                //iterates through the inner map
                for (Map.Entry<String, String> innerElements: innerMap.entrySet()){

                    //gets the current value of the inner map
                    String innerMapValue = innerElements.getValue();

                    //gets the current key of the inner map
                    String innerMapKey = innerElements.getKey();

                    //check isMatch method below
                    //if innerKey matches the 'translationEnglish', 'translationFilipino'
                    boolean match = isMatch(innerMapKey, targetKeys);
                    //for checking only
                    if (match && innerMapValue.toLowerCase().contains(",")){
                        String[] split = innerMapValue.split(", ");
                        for (int i = 0; i <= split.length - 1; i++){
                            if (split[i].toLowerCase().startsWith(input)){
                                String translationEnglish = innerMap.get("translationEnglish");
                                String translationFilipino = innerMap.get("translationFilipino");
                                list.add(new DictionaryEntry(key, translationEnglish, translationFilipino));
                            }
                        }
                    }
                    // Check if 'match' is true and if 'stringValue' contains or equals the 'input'
                    else if (match && innerMapValue.toLowerCase().startsWith(input)|| innerMapValue.toLowerCase().equals(input)){

//                        System.out.println("this is the string value: "+ stringValue);
//                        System.out.println("this is the input: " + input);

                        //ads the key of the dictionary map (the original map) to the List
                        String translationEnglish = innerMap.get("translationEnglish");
                        String translationFilipino = innerMap.get("translationFilipino");
                        list.add(new DictionaryEntry(key, translationEnglish, translationFilipino));
                    }
                }
            }

        }
        return list;
    }

    //for testing only

    //if the current key matches the target keys to be iterated the map
    public boolean isMatch(String key, String[]array){

        //iterates through the targetKeys list
        for (String element: array){
            //if the current key of the inner map (innerKey) is
            if (key.equals(element))return true;
        }
        return false;
    }

    public void updateListViewItems() {
        myListView.getItems().clear();
        ArrayList<DictionaryEntry> words = createListViewItems();
        myListView.getItems().addAll(words);

    }

    public static ListViewController getInstance() {
        return instance;
    }
}
