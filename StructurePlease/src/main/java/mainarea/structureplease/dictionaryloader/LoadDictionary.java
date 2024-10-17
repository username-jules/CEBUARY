package mainarea.structureplease.dictionaryloader;
import java.util.HashMap;

public class LoadDictionary {
    private Data data;
    private HashMap<String, HashMap<String, String>> dictionary;
    public LoadDictionary(){
        data = new Data("/data/dataset.txt");
        dictionary = data.getData();
    }

    public String getClassification(String key){
        HashMap<String, String> innerHashMap = dictionary.get(key);

        return innerHashMap.get("classification");
    }
    public String getPronunciation(String key){
        HashMap<String, String> innerHashMap = dictionary.get(key);

        return innerHashMap.get("pronunciation");
    }

    public String getTransEng(String key){
        HashMap<String, String> innerHashMap = dictionary.get(key);

        return innerHashMap.get("transEng");
    }

    public String getTransFil(String key){
        HashMap<String, String> innerHashMap = dictionary.get(key);

        return innerHashMap.get("transFil");
    }

    public String getExEng(String key){
        HashMap<String, String> innerHashMap = dictionary.get(key);

        return innerHashMap.get("exEng");
    }
    public String getExFil(String key){
        HashMap<String, String> innerHashMap = dictionary.get(key);

        return innerHashMap.get("exFil");
    }
    public String getExCha(String key){
        HashMap<String, String> innerHashMap = dictionary.get(key);

        return innerHashMap.get("exCha");
    }

    public HashMap<String, HashMap<String, String>> getDictionary() {
        return dictionary;
    }
}
