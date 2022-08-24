import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {
    private static final String home = System.getProperty("user.home");
    private static final String dir = home + "/Desktop/Y2S1/CS2103T/Week 2 iP/ip/src/main/duke.txt";
    private final File dukeFile = new File(dir);
    private FileWriter fileWriter;
    private Scanner scanner;
    private final Messager messager = new Messager();

    public Storage() {
        try {
            if (dukeFile.createNewFile()) {
                messager.message("Storage: New File duke.txt has been created");
            } else {
                messager.message("Storage: duke.txt is already in the dir");
            };
        } catch (IOException e){
            messager.message(e);
        }

    }

    public void writeToFile(Object obj) {
        try {
            fileWriter = new FileWriter(dir);
            fileWriter.write(obj.toString());
            messager.message("Storage: duke.txt updated");
            fileWriter.close();
        } catch (IOException e) {
            messager.message(e);
        }
    }

    public String readFromFile() {
        String data = "";
        try {
            scanner = new Scanner(dukeFile);
            while (scanner.hasNextLine()) {
                data += scanner.nextLine() + '\n';
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            messager.message(e);
        }
        return data;
    }

    public static void main(String[] args) {
        Storage s = new Storage();
        System.out.println(s.readFromFile());
    }
}
