package mainarea.structureplease.dictionaryscene;

public class DictionaryEntry {
    private final String cebuanoWord;
    private final String enunciation;
    private final String engTrans;

    public DictionaryEntry(String CebuanoWord, String enunciation, String engTrans){
        this.cebuanoWord = CebuanoWord;
        this.enunciation = enunciation;
        this.engTrans = engTrans;
    }

    public String getCebuanoWord() {
        return cebuanoWord;
    }

    public String getEngTrans() {
        return engTrans;
    }

    public String getEnunciation() {
        return enunciation;
    }
}
