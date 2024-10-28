package mainarea.structureplease.dictionaryscene;

public class DictionaryEntry {
    private final String cebuanoWord;
    private final String engTrans;

    public DictionaryEntry(String CebuanoWord, String engTrans){
        this.cebuanoWord = CebuanoWord;
        this.engTrans = engTrans;
    }

    public String getCebuanoWord() {
        return cebuanoWord;
    }

    public String getEngTrans() {
        return engTrans;
    }
}
