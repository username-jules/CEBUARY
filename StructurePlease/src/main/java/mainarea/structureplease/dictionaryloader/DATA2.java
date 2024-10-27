package mainarea.structureplease.dictionaryloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DATA2 {
    private HashMap <String, String>dictionary;

    public DATA2(String fileName){
        dictionary = createHashMap(fileName);
    }

    private HashMap<String, String> createHashMap(String fileName){
        HashMap<String, String> dictionary = new HashMap<>();
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            String line;

            while ((line = reader.readLine()) != null){
                String []splitLine = line.split("\\|");
                dictionary.put(splitLine[0], splitLine[1]);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return dictionary;
    }
    public String getKeyByValue(String value) {
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null; // Return null if the value is not found
    }
    public HashMap<String, String> getDictionary() {
        return dictionary;
    }
}
