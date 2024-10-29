package mainarea.structureplease;

import javafx.fxml.FXML;
import javafx.scene.text.Font;
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
    @FXML
    public Text word, enunciation, classification, transEng, exCebuano, exEnglish,
            englishLabel, exampleEnglish, exampleCebuano, headerText;
    private int count;
    private LoadDictionary dictionary;
    private String[]keys;
    private String key = "";

    public void initialize(){
        dictionary = new LoadDictionary();
        Font myFont = Font.loadFont(getClass().getResource("/fonts/MADECarvingSoftPERSONALUSE-Bold.otf").toExternalForm(), 48);
        headerText.setFont(myFont);
        Path projectDirectory = Paths.get("").toAbsolutePath();
        String fileName = "word-of-the-day.txt";
        Path filePath = projectDirectory.resolve(fileName);
        createAndWriteFile(filePath);
        processFile(filePath);

        displayDictionary();
    }

    public void displayDictionary(){
        String fontBold = "/fonts/MADECarvingSoftPERSONALUSE-Bold.otf";
        String fontRegular = "/fonts/MADECarvingSoftPERSONALUSE-Regular.otf";
        setTextFont(word,fontBold,48);
        setTextFont(enunciation,fontRegular,24);
        setTextFont(classification,fontRegular,24);
        setTextFont(englishLabel, fontBold, 20);
        setTextFont(transEng,fontRegular,24);
        setTextFont(exampleCebuano, fontBold, 20);
        setTextFont(exCebuano,fontRegular, 24);
        setTextFont(exampleEnglish, fontBold, 20);
        setTextFont(exEnglish,fontRegular, 24);
        word.setText(key);
        enunciation.setText(dictionary.getEnunciation(key));
        classification.setText(dictionary.getClassification(key));
        transEng.setText(dictionary.getTransEng(key));
        exCebuano.setText(dictionary.getExCeb(key));
        exEnglish.setText(dictionary.getExEng(key));

    }
    private void setTextFont(Text text, String fontPath, int fontSize){
        Font myFont = Font.loadFont(getClass().getResource(fontPath).toExternalForm(), fontSize);
        text.setFont(myFont);
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String[] getKeys() {
        Set<String> set = dictionary.getDictionary().keySet();
        String []array = set.toArray(new String[0]);
        return array;
    }
}
