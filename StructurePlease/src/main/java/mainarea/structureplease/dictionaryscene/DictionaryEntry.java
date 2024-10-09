package mainarea.structureplease.dictionaryscene;

public class DictionaryEntry {
    private final String word;
    private final String translationEnglish;
    private final String translationFilipino;

    public DictionaryEntry(String word, String translationEnglish, String translationFilipino){
        this.word = word;
        this.translationEnglish = translationEnglish;
        this.translationFilipino = translationFilipino;
    }

    public String getWord() {
        return word;
    }

    public String getTranslationEnglish() {
        return translationEnglish;
    }

    public String getTranslationFilipino() {
        return translationFilipino;
    }
}
