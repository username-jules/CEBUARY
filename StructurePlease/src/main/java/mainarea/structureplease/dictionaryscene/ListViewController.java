package mainarea.structureplease.dictionaryscene;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import mainarea.structureplease.Data;
import mainarea.structureplease.OpeningController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListViewController {
    @FXML
    public ListView<String> myListView;
    public Node vBox;
    private String input, selectedKey;
    private Data dictionaryData;
    private static ListViewController instance;
    private DictionarySceneController mainScene;
    private DictionaryContentController dc;


    public void initialize(){
        instance = this;
        dictionaryData = OpeningController.getOpeningController().getDictionaryData();

        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                dc = DictionaryContentController.getDictionaryContentController();
                selectedKey = myListView.getSelectionModel().getSelectedItem();

                mainScene = DictionarySceneController.getDictionarySceneController();
                vBox.setVisible(false);
                mainScene.getDictionaryContent().setVisible(true);


                System.out.println("this is the input before passing: " + selectedKey);
                dc.setInput(selectedKey);
                dc.displayDictionary();

            }
        });

    }
    public void setKey(String key){
        this.input = key;
    }


    //creates items for the listview
    public ArrayList<String> createListViewItems(){
        input = input.toLowerCase();
        System.out.println(input);
        //stores the results from search (keys)
        ArrayList<String> list = new ArrayList<>();

        //gets the map
        Map<String, Map<String,String>> dictionary = dictionaryData.getDictionary();

        //iterates through the dictionary map
        for (Map.Entry<String , Map<String, String>> entry: dictionary.entrySet()){

            //takes the map within the dictionary map (inner map)
            Map<String, String> innerMap = entry.getValue();

            //takes the current key of the dictionary map
            String key = entry.getKey();

            //if key contains input adds the key to the List
            //ex: Bienvenidos contains Bie
            if (key.toLowerCase().contains(input)){
                list.add(key);
            }
            else { //if key does not contain or is not equal to the input, checks for the inner map (english translation/ filipino translation)
                //iterates through the inner map
                for (Map.Entry<String, String> innerEntry: innerMap.entrySet()){

                    //gets the current value of the inner map
                    String stringValue = innerEntry.getValue();

                    //gets the current key of the inner map
                    String innerKey = innerEntry.getKey();

                    //check isMatch method below
                    //if innerKey matches the 'translationEnglish', 'translationFilipino'
                    boolean match = isMatch(innerKey);
                    //for checking only
                    if (match && stringValue.toLowerCase().contains(",")){
                        String[] split = stringValue.split(", ");
                        for (int i = 0; i <= split.length - 1; i++){
                            if (split[i].toLowerCase().startsWith(input)){
                                list.add(key);
                            }
                        }
                    }
                    // Check if 'match' is true and if 'stringValue' contains or equals the 'input'
                    else if (match && stringValue.toLowerCase().startsWith(input)|| stringValue.toLowerCase().equals(input)){

//                        System.out.println("this is the string value: "+ stringValue);
//                        System.out.println("this is the input: " + input);

                        //ads the key of the dictionary map (the original map) to the List
                        list.add(key);
                    }
                }
            }

        }
        return list;
    }

    //for testing only
    public void printValues(){
        ArrayList<String> words = createListViewItems();

        for (String elements: words){
            System.out.println(elements);
        }
    }

    //if the current key matches the target keys to be iterated the map
    public boolean isMatch(String key){
        String[] targetKeys  = {"translationEnglish", "translationFilipino"};

        //iterates through the targetKeys list
        for (String element: targetKeys){
            //if the current key of the inner map (innerKey) is
            if (key.equals(element))return true;
        }
        return false;
    }

    public void updateListViewItems(){
        myListView.getItems().clear();
        ArrayList<String> words = createListViewItems();
        myListView.getItems().addAll(words);

    }

    public static ListViewController getInstance() {
        return instance;
    }
}
