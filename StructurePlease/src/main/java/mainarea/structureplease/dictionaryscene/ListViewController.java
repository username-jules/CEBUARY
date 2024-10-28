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
import mainarea.structureplease.dictionaryloader.LoadDictionary;

import java.util.*;

public class ListViewController {
    @FXML
    public ListView<DictionaryEntry> myListView;
    public Node vBox;
    private String input, selectedKey;
    private LoadDictionary dictionary;
    private static ListViewController instance;
    private DictionarySceneController mainScene;
    private DictionaryContentController dc;

    public void initialize(){
        instance = this;
        dictionary = new LoadDictionary();
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
                    Label wordLabel = new Label(item.getCebuanoWord());
                    Label enunciationLabel = new Label(item.getEnunciation());
                    Label englishLabel = new Label("In English: " + item.getEngTrans());

                    wordLabel.setFont(Font.font(null, 24));
                    enunciationLabel.setFont(Font.font(null, 12)); // Set font size to 12 for English label
                    englishLabel.setFont(Font.font(null, 12)); // Set font size to 12 for English label

                    // Clear and add labels to VBox
                    vbox.getChildren().clear();
                    vbox.getChildren().addAll(wordLabel, enunciationLabel, englishLabel);

                    setGraphic(vbox); // Use VBox as the graphic for the cell
                }
            }
        });

        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DictionaryEntry>() {
            @Override
            public void changed(ObservableValue<? extends DictionaryEntry> observableValue, DictionaryEntry oldValue, DictionaryEntry newValue) {
                if (newValue != null) {
                    dc = DictionaryContentController.getDictionaryContentController();
                    String selectedKey = newValue.getCebuanoWord() + "\n"
                            + newValue.getEngTrans() + "\n";

                    mainScene = DictionarySceneController.getDictionarySceneController();
                    vBox.setVisible(false);
                    mainScene.getDictionaryContent().setVisible(true);
//
//                    System.out.println("This is the input before passing: " + selectedKey);
                    dc.setInput(newValue.getCebuanoWord());
                    dc.displayDictionary();
                }
            }
        });

    }
    public void input(String key){
        this.input = key;
    }

    //creates items for the listview
//    public ArrayList<DictionaryEntry> createListViewItems() {
//        input = removeSpaces(input.toLowerCase());
//
////        System.out.println(input);
//        //stores the results from search (keys)
//        ArrayList<DictionaryEntry> list = new ArrayList<>();
//
//        //gets the map
//        Map<String, String> map = dictionary.getDictionary();
//        boolean isMatch = false;
//
//        //iterates through the dictionary map
//        for (Map.Entry<String, String> mapElements : map.entrySet()) {
//
//            //takes the map within the dictionary map (inner map)
//
//            //takes the current key of the dictionary map
//            String key = removeSpaces(mapElements.getKey());
//            String value = removeSpaces(mapElements.getValue());
//
//            //if key contains input adds the key to the List
//            //ex: Bienvenidos contains Bie
//            if (key.toLowerCase().startsWith(input)) {
//                listViewEntry(key, list);
//                isMatch = true;
//            }
//            if (!isMatch) {
//                String[] valueSplit = value.split("[.;,]+");
//                for (String split : valueSplit) {
//                    if (split.startsWith(input)) {
//                        listViewEntry(key, list);
//                    }
//                    if (input.startsWith(split)) {
//                        listViewEntry(key, list);
//                    }
//                }
//
//            }
//
//        }
//        return list;
//    }

    public ArrayList<DictionaryEntry> myListViewItems(String userInput){
        ArrayList<DictionaryEntry> results = new ArrayList<>();
        HashMap<String, HashMap<String, String>> myMap = dictionary.getDictionary();
        for (Map.Entry<String, HashMap<String, String>> mainMap : myMap.entrySet()){
            String key = mainMap.getKey();
            if (key.contains(userInput)){
                listViewEntry(key, results);
            }
            if (!key.equals(userInput)){
                String engTrans = dictionary.getTransEng(key);
                String[] split = engTrans.split("/");
                for (String word: split){
                    if (word.equals(userInput)){
                        listViewEntry(key, results);
                        break;
                    }
                }
            }

        }
        return results;
    }

    // Method to remove all spaces from a string
    public static String removeSpaces(String input) {
        if (input == null) {
            return null; // Return null if input is null
        }
        return input.replaceAll("\\s+", ""); // Remove all whitespace characters
    }
    private void listViewEntry(String key, ArrayList<DictionaryEntry> list){
        String engTrans = dictionary.getTransEng(key);
        String enunciation = dictionary.getEnunciation(key);
        list.add(new DictionaryEntry(key, enunciation, engTrans));
    }
    public void updateListViewItems() {
        myListView.getItems().clear();
        ArrayList<DictionaryEntry> words = myListViewItems(input);

        Collections.sort(words, new Comparator<DictionaryEntry>() {
            @Override
            public int compare(DictionaryEntry o1, DictionaryEntry o2) {
                return o1.getCebuanoWord().compareToIgnoreCase(o2.getCebuanoWord());
            }
        });
        myListView.getItems().addAll(words);

    }

    public static ListViewController getInstance() {
        return instance;
    }
}
