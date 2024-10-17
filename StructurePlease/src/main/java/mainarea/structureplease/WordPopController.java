package mainarea.structureplease;

import javafx.scene.text.Text;
import mainarea.structureplease.dictionaryloader.LoadDictionary;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

public class WordPopController {
    public Text word, enunciation, classification, translationEnglish,
            translationFilipino, exampleEnglish, exampleFilipino, exampleChavacano, exampleSentences;
    private int count;
    private LoadDictionary dictionary;
    private String[]keys;
    private String key = "";

    public WordPopController(){
        dictionary = new LoadDictionary();
        Path projectDirectory = Paths.get("").toAbsolutePath();
        String fileName = "word-of-the-day.txt";
        Path filePath = projectDirectory.resolve(fileName);
        createAndWriteFile(filePath);
        processFile(filePath);

        System.out.println(key);

    }

    public void initialize(){
        dictionary = new LoadDictionary();
        Path projectDirectory = Paths.get("").toAbsolutePath();
        String fileName = "word-of-the-day.txt";
        Path filePath = projectDirectory.resolve(fileName);
        createAndWriteFile(filePath);
        processFile(filePath);

        displayDictionary();
    }
public void displayDictionary(){

    String enun = dictionary.getPronunciation(key);
    String classif = dictionary.getClassification(key);
    String transEn = dictionary.getTransEng(key);
    String transFil = dictionary.getTransFil(key);
    String exampleEn = dictionary.getExEng(key);
    String exampleFil = dictionary.getExFil(key);
    String exampleChav = dictionary.getExCha(key);

    word.setText(key);
    enunciation.setText(enun);
    classification.setText(classif);
    translationEnglish.setText("In English: "+ transEn);
    translationFilipino.setText("In Filipino: " +transFil);
    exampleEnglish.setText("English: "+ exampleEn);
    exampleFilipino.setText("Filipino: " + exampleFil);
    exampleChavacano.setText("Chavacano: " + exampleChav);
}

    private void processFile(Path filePath){
        LocalDate currentDate = LocalDate.now();
        String stringCurrentDate = currentDate.toString();
        keys = getKeys();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))){
            String line;
            if ((line = br.readLine()) == null){
                writeFileValues(filePath, count, stringCurrentDate);
            }
            else {

                if (line.contains(", ")){
                    String toSplit = line;
                    String[] split = toSplit.split(", ");
                    count = Integer.parseInt(split[0]);
                    String fileDate = split[1];
                    System.out.println(fileDate);

                    if (!stringCurrentDate.equals(fileDate)){
                        clearTextFile(filePath);
                        count +=1;
                        if (count == keys.length-1) count = 0;
                        writeFileValues(filePath, count, stringCurrentDate);
                    }
                    key = keys[count];
                }


            }


        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void clearTextFile(Path filePath){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), false))){
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeFileValues(Path filePath, int count, String date){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), true))){
            writer.write(count + ", "+ date);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private static void createAndWriteFile(Path filePath) {
        try {
            // Create the file if it doesn't exist
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
                System.out.println("File created: " + filePath);
            } else {
                System.out.println("File already exists: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String[] getKeys() {
        HashMap<String, HashMap<String, String>> dictionaryMap = dictionary.getDictionary();
        Set<String> set = dictionaryMap.keySet();
        String []array = set.toArray(new String[0]);
        return array;
    }
}
