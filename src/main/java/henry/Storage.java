package henry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Storage is responsible for loading the task list from
 * the file, and also writing any changes to the task
 * list to the file.
 */
public class Storage {

    private final String filePath;
    private List<Task> tasks;

    /**
     * When a Storage class is initialised, it tries to
     * read the file at the specified filepath. If the file exists,
     * the tasks will be loaded from the file. If not, a new file will be
     * created at the filepath.
     *
     * @param filePath the filepath where the task list is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            File savedList = new File(filePath);
            if (savedList.createNewFile()) {
                tasks = new ArrayList<>();
            } else {
                tasks = parseTasksFromFile(savedList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the loaded list of tasks. Storage first parses
     * tasks from a given file, which is then stored.
     *
     * @return a TaskList containing tasks loaded from a file
     */
    public List<Task> load() {
        return tasks;
    }

    private List<Task> parseTasksFromFile(File savedList) throws FileNotFoundException {
        Scanner s = new Scanner(savedList);
        List<Task> tasks = new ArrayList<>();
        while (s.hasNextLine()) {
            tasks.add(Task.parseTask(s.nextLine()));
        }
        return tasks;
    }

    /**
     * Appends text to the file.
     *
     * @param textToAdd the text to be appended to the file
     */
    public void appendToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd + "\n");
        fw.close();
    }
}
