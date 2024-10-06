package mainarea.structureplease;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {
    //sample
    //exampleChavacano
    //exampleEnglish
    //enunciation
    //alternateChavacano
    //translationFilipino
    //translationEnglish
    //classification
    private Map<String, Map<String, String>> dictionary;

    public Data(){
        dictionary = new HashMap<>();
    }

    public void loadDictionary(String fileName){
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))){
            String line;
            Map<String, String> wordDetails = new HashMap<>();
            String chavacanoWord = "";
            while ((line = br.readLine()) != null){
                //reads first line and splits it into two
                if (chavacanoWord.isEmpty()){
                    chavacanoWord = line;
                }
                else if(line.equals("--")){
                    //insert to main hashmap
                    dictionary.put(chavacanoWord, wordDetails);
                    //resets nested hashmap and chavacanoword
                    wordDetails = new HashMap<>();
                    chavacanoWord = "";

                    //translations
                }else{
                    String[] definitions = line.split("\\|");
                    wordDetails.put(definitions[0], definitions[1]);
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //return all values of the inner hashmap
    public Map<String, String> getWordDetails(String word) {
        return dictionary.get(word);
    }

    //returns the key if the value is provided
    public String getKeyByValue(String word){
        //iterate over the dictionary hashmap
        for (Map.Entry<String, Map<String, String>> entry : dictionary.entrySet()) {
            //sets a hashmap equal to the value of each key (hashmap)
            Map<String, String> innerMap = entry.getValue();

            //iterate over the inner hashmap
            for (Map.Entry<String, String> innerEntry : innerMap.entrySet()) {
                //the value of the inner hashmap
                String stringValue = innerEntry.getValue();

                //checks if the current value is equal to the provided value, then returns the key of the main hashmap if true
                if (stringValue.contains(word) || stringValue.equals(word)) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    //returns detail in a list
    public List<String> getListDetails(String key){
        Map<String, String> wordDetails = dictionary.get(key);
        List<String> values = new ArrayList<>();
        for (String value: wordDetails.values()){
            values.add(value);
        }
        return values;
    }

    public String getDetails (String word, String key){
        Map<String, String> wordDetails = dictionary.get(word);
        String detail = wordDetails.get(key);
        return detail;
    }

    public Map<String, Map<String, String>> getDictionary() {
        return dictionary;
    }
}
