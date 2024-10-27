package mainarea.structureplease;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import mainarea.structureplease.dictionaryloader.DATA2;
import mainarea.structureplease.dictionaryloader.LoadDictionary;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

public class WordPopController {
    @FXML
    public Text word, definition;
    private int count;
    private DATA2 dictionary;
    private String[]keys;
    private String key = "";


    public void initialize(){
        dictionary = new DATA2("/data/content.txt");
        Path projectDirectory = Paths.get("").toAbsolutePath();
        String fileName = "word-of-the-day.txt";
        Path filePath = projectDirectory.resolve(fileName);
        createAndWriteFile(filePath);
        processFile(filePath);

        displayDictionary();
    }
    public void displayDictionary(){
            word.setText(key);
            definition.setText(dictionary.getDictionary().get(key));

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
        HashMap<String, String> dictionaryMap = dictionary.getDictionary();
        Set<String> set = dictionaryMap.keySet();
        String []array = set.toArray(new String[0]);
        return array;
    }
}
