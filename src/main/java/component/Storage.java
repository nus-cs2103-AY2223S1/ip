package component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Storage class that manages loading and saving of data.
 */
public class Storage {
    protected Path filePath;

    /**
     * Constructs a Storage object according to the path given.
     * @param filePath Path of the saved file
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
        try {
            Files.createDirectories(this.filePath);
        } catch (IOException exception) {
            System.out.println("Catch exception");
            System.out.println(exception);
        }
    }

    /**
     * Loads task data from TXT file in directory
     * @return text array from which the document was loaded
     */
    public ArrayList<String> load() {
        BufferedReader bufferedReader = null;
        ArrayList<String> result = new ArrayList<>();
        try {
            String currentLine;
            bufferedReader = new BufferedReader(new FileReader(this.filePath.toString()
                    + File.separator + "task-list.txt"));
            while ((currentLine = bufferedReader.readLine()) != null) {
                result.add(currentLine);
            }
        } catch (IOException exception) {
            saveData(new TaskList());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException exception) {
                System.out.println(exception);
            }
        }
        return result;
    }

    /**
     * Saves the current TaskList to a TXT file called "task-list.txt"
     * @param list TaskList object that stores the current tasks
     */
    public void saveData(TaskList list) {
        ArrayList<String> textArray = list.extractToStringArray();
        try {
            File file = createFile(this.filePath, "task-list.txt");
            OutputStream os = new FileOutputStream(file);
            String text = String.join("\n", textArray);
            byte[] bytes = text.getBytes();
            os.write(bytes);
            os.close();
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    /**
     * Creates file object at the current directory
     * @param path Path of the directory
     * @param filename name of the file
     * @return File object
     */
    public File createFile(Path path, String filename) {
        File file = new File(path.toString(), filename);
        return file;
    }
}