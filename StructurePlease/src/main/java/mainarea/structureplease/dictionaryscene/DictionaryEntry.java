package mainarea.structureplease.dictionaryscene;

public class DictionaryEntry {
    private final String word;
    private final String definition;

    public DictionaryEntry(String word, String definition){
        this.word = word;
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }
}
