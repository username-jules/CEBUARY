package mainarea.structureplease.dictionaryloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class DataTest {
    private HashMap<String, HashMap<String , String >> data;

    public DataTest(String filePath){
        data = createHashMap(filePath);
    }
    //creates and returns a hashmap
    private HashMap<String, HashMap<String, String>> createHashMap(String filePath){
        InputStream inputStream = getClass().getResourceAsStream(filePath);
        HashMap<String, HashMap<String, String>> data = new HashMap<>();
        String[] innerKeys;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))){
            //reads the first line
            String firstLine = br.readLine();

            //store the first line as keys of the inner hashmap
            innerKeys = firstLine.split("\\|");
            String currentLine;

            //reads all remaining lines
            while ((currentLine = br.readLine()) != null){
                //creates a temporary array that holds the values
                String[] values = currentLine.split("\\|");
                //inner hashmap
                HashMap<String, String> innerHashMap = new HashMap<>();

                //adds keys and values to the innerhashmap
                for (int i = 0; i < values.length - 1; i++){
                    innerHashMap.put(innerKeys[i], values[i+1]);
                }
                //index 0 holds the key of the main hashmap
                data.put(values[0], innerHashMap);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public HashMap<String, HashMap<String, String>> getData() {
        return data;
    }

}
