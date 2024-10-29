package mainarea.structureplease.dictionaryloader;
import java.util.HashMap;
import java.util.Set;

public class LoadDictionary {
    private Data data;
    private HashMap<String, HashMap<String, String>> dictionary;
    public LoadDictionary(){
        data = new Data("/data/cebuano.txt");
        dictionary = data.getData();
    }

    public String getClassification(String key){
        HashMap<String, String> innerHashMap = dictionary.get(key);

        return innerHashMap.get("classification");
    }


    public String getTransEng(String key){
        HashMap<String, String> innerHashMap = dictionary.get(key);

        return innerHashMap.get("transEng");
    }

    public String getTransFil(String key){
        HashMap<String, String> innerHashMap = dictionary.get(key);

        return innerHashMap.get("transFil");
    }
    public String getExCeb(String key){
        HashMap<String, String> innerHashMap = dictionary.get(key);

        return innerHashMap.get("exCeb");
    }

    public String getExEng(String key){
        HashMap<String, String> innerHashMap = dictionary.get(key);

        return innerHashMap.get("exEng");
    }

    public String getExCha(String key){
        HashMap<String, String> innerHashMap = dictionary.get(key);

        return innerHashMap.get("exCha");
    }
    public String getEnunciation(String key){
        HashMap<String, String> innerHashMap = dictionary.get(key);

        return innerHashMap.get("enunciation");
    }
    public HashMap<String, HashMap<String, String>> getDictionary() {
        return dictionary;
    }

}
