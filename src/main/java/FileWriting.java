import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriting {
    private final static String FILE_PATH = "src\\main\\java\\SavedTask.txt";

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private static void emptyFile(String filePath) throws IOException {
        new FileWriter(filePath, false).close();
    }

    public static void save(Task task) {
        try {
            appendToFile(FILE_PATH, task.toSave() + System.lineSeparator());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }
    

    public static void update(List<Task> taskList) {
        try {
            emptyFile(FILE_PATH);
            for (int i = 0; i < taskList.size(); i++) {
                appendToFile(FILE_PATH, taskList.get(i).toSave() + System.lineSeparator());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }


    public static void main(String[] args) {
        
        
        try {
            emptyFile(FILE_PATH);
            appendToFile(FILE_PATH, "first line" + System.lineSeparator());
            appendToFile(FILE_PATH, "second line" + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}