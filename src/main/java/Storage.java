import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class to store, load, and save data into or from
 * a specific file as compact lists.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor method.
     *
     * @param filePath The file path specified
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Processes the input file into a list of array of strings
     * for the parser to handle afterwards.
     *
     * @return List of array of strings from the lines in the file path specified
     * @throws IOException If there is an I/O issue, e.g. file not found
     */
    public ArrayList<String[]> load() throws IOException {
        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        ArrayList<String[]> loadResult = new ArrayList<String[]>();
        while (s.hasNext()) {
            String[] lineSplit = s.nextLine().split("\\|");
            loadResult.add(lineSplit);
        }
        return loadResult;
    }

    /**
     * Creates the file if one doesn't exist.
     * The mkdirs() method can be used with a different
     * context since it doesn't create a file but a
     * directory instead.
     *
     * @throws IOException If there is an I/O issue
     */
    public void create() throws IOException {
        String[] pathList = this.filePath.split("/");
        String currentPath = "";
        for (int i = 0; i < pathList.length - 1; i++) {
            currentPath += pathList[i];
            File directory = new File(currentPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
            currentPath += "/";
        }
        File newFile = new File(this.filePath);
        newFile.createNewFile();
    }

    /**
     * Writes the current task list into the storage again.
     *
     * @param taskList The list of tasks
     * @throws IOException If any I/O issue happens
     */
    public void writeTaskListToFile(ArrayList<Task> taskList)
            throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : taskList) {
            fw.write(task + System.lineSeparator());
        }
        fw.close();
    }
}