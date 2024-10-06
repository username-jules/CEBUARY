package mainarea.structureplease.dictionaryscene;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import mainarea.structureplease.Data;
import mainarea.structureplease.OpeningController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListViewController {
    @FXML
    public ListView<String> myListView;
    private String input;
    private Data dictionaryData;
    private static ListViewController instance;

    public void initialize(){
        instance = this;
        dictionaryData = OpeningController.getOpeningController().getDictionaryData();
    }
    public void setKey(String key){
        this.input = key;
    }


    //creates items for the listview
    public List<String> createItems(){
        input = input.toLowerCase();
        System.out.println(input);
        //stores the results from search (keys)
        List<String> words = new ArrayList<>();

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
                words.add(key);
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
//                            System.out.println("this is the current split " + split[i]);
                            if (split[i].toLowerCase().startsWith(input)){
                                words.add(key);
                            }
                        }
                    }
                    // Check if 'match' is true and if 'stringValue' contains or equals the 'input'
                    else if (match && stringValue.toLowerCase().startsWith(input)|| stringValue.toLowerCase().equals(input)){

//                        System.out.println("this is the string value: "+ stringValue);
//                        System.out.println("this is the input: " + input);

                        //ads the key of the dictionary map (the original map) to the List
                        words.add(key);
                    }
                }
            }

        }
        return words;
    }

    //for testing only
    public void printValues(){
        List<String> words = createItems();

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

    public static ListViewController getInstance() {
        return instance;
    }
}
