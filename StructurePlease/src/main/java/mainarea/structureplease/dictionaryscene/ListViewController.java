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
import mainarea.structureplease.dictionaryloader.DATA2;
import mainarea.structureplease.dictionaryloader.LoadDictionary;

import java.util.*;

public class ListViewController {
    @FXML
    public ListView<DictionaryEntry> myListView;
    public Node vBox;
    private String input, selectedKey;
    private DATA2 dictionary;
    private static ListViewController instance;
    private DictionarySceneController mainScene;
    private DictionaryContentController dc;

    public void initialize(){
        instance = this;
        dictionary = new DATA2("/data/content.txt");
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
                    Label definitionLabel = new Label(item.getDefinition());

                    wordLabel.setFont(Font.font(null, 24));
                    definitionLabel.setFont(Font.font(null, 12)); // Set font size to 12 for English label

                    // Clear and add labels to VBox
                    vbox.getChildren().clear();
                    vbox.getChildren().addAll(wordLabel, definitionLabel);

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
                            + newValue.getDefinition() + "\n";

                    mainScene = DictionarySceneController.getDictionarySceneController();
                    vBox.setVisible(false);
                    mainScene.getDictionaryContent().setVisible(true);
//
//                    System.out.println("This is the input before passing: " + selectedKey);
                    dc.setInput(newValue.getWord());
                    dc.displayDictionary();
                }
            }
        });

    }
    public void input(String key){
        this.input = key;
    }

    //creates items for the listview
    public ArrayList<DictionaryEntry> createListViewItems() {
        input = removeSpaces(input.toLowerCase());

//        System.out.println(input);
        //stores the results from search (keys)
        ArrayList<DictionaryEntry> list = new ArrayList<>();

        //gets the map
        Map<String, String> map = dictionary.getDictionary();
        boolean isMatch = false;

        //iterates through the dictionary map
        for (Map.Entry<String, String> mapElements : map.entrySet()) {

            //takes the map within the dictionary map (inner map)

            //takes the current key of the dictionary map
            String key = removeSpaces(mapElements.getKey());
            String value = removeSpaces(mapElements.getValue());

            //if key contains input adds the key to the List
            //ex: Bienvenidos contains Bie
            if (key.toLowerCase().startsWith(input)) {
                listViewEntry(key, list);
                isMatch = true;
            }
            if (!isMatch) {
                String[] valueSplit = value.split("[.;,]+");
                for (String split : valueSplit) {
                    if (split.startsWith(input)) {
                        listViewEntry(key, list);
                    }
                    if (input.startsWith(split)) {
                        listViewEntry(key, list);
                    }
                }

            }

        }
        return list;
    }

    // Method to remove all spaces from a string
    public static String removeSpaces(String input) {
        if (input == null) {
            return null; // Return null if input is null
        }
        return input.replaceAll("\\s+", ""); // Remove all whitespace characters
    }
    private void listViewEntry(String key, ArrayList<DictionaryEntry> list){
        String definition = dictionary.getDictionary().get(key);

        list.add(new DictionaryEntry(key, definition));
    }
    public void updateListViewItems() {
        myListView.getItems().clear();
        ArrayList<DictionaryEntry> words = createListViewItems();

        Collections.sort(words, new Comparator<DictionaryEntry>() {
            @Override
            public int compare(DictionaryEntry o1, DictionaryEntry o2) {
                return o1.getWord().compareToIgnoreCase(o2.getWord());
            }
        });
        myListView.getItems().addAll(words);

    }

    public static ListViewController getInstance() {
        return instance;
    }
}
