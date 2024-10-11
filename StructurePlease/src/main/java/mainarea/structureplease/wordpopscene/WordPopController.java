package mainarea.structureplease.wordpopscene;

import mainarea.structureplease.Data;
import mainarea.structureplease.OpeningController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class WordPopController {
    private OpeningController openingC;
    private Data dictionaryData;

    public WordPopController(){
        Path documentsPath = getDocumentsPath();
        String fileName = "word-of-the-day.txt";
        Path filePath = documentsPath.resolve(fileName);
        createAndWriteFile(filePath);
        processFile(filePath);

    }

//    public void initialize(){
//        openingC = OpeningController.getOpeningController();
//        dictionaryData = openingC.getDictionaryData();
//
//
//
//    }

    private static Path getDocumentsPath() {
        // Get the user's home directory or USERPROFILE
        String userHome = System.getProperty("user.home");
        if (userHome == null || userHome.isEmpty()) {
            userHome = System.getenv("USERPROFILE");
        }

        // Construct the path to the Documents folder
        if (userHome != null) {
            return Paths.get(userHome, "Documents");
        }
        return null; // Return null if no valid path is found
    }
    private void getDictionaryKey(String key){

    }

    private void processFile(Path filePath){
        String key = "test";
        LocalDate currentDate = LocalDate.now();
        String stringCurrentDate = currentDate.toString();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))){
            String line;
            if ((line = br.readLine()) == null){
                writeFileValues(filePath, key, stringCurrentDate);
            }
            else {

                if (line.contains(", ")){
                    String toSplit = line;
                    String[] split = toSplit.split(", ");
                    String fileKey = split[0];
                    String fileDate = split[1];
                    System.out.println(fileDate);

                    if (!stringCurrentDate.equals(fileDate)){
                        key = "iloveu";
                        writeFileValues(filePath, key, stringCurrentDate);
                    }
                    if (stringCurrentDate.equals(fileDate)){
                        key = fileKey;
                        System.out.println("the key is: " + key);
                    }

                }


            }


        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void writeFileValues(Path filePath, String key, String date){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), true))){
            writer.write(key + ", "+ date);
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

    public static void main(String[] args) {
        new WordPopController();
    }
}
