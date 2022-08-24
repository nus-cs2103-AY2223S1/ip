import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Scanner getScannerForTasksFile() throws CustomMessageException {
        File tasksFile = new File(filePath);
        if (tasksFile.exists()) {
            try {
                return new Scanner(tasksFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        throw new CustomMessageException("Unable to load file");
    }

    public void writeToDisk(String stringToWrite) {
        try {
            File tasksFile = new File(filePath);
            if ((tasksFile.exists() && tasksFile.delete()) || !tasksFile.exists()) {
                FileWriter fileWriter = new FileWriter(filePath);
                try {
                    fileWriter.write(stringToWrite);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fileWriter.close();
            } else {
                System.out.println("Unable to save your data");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
