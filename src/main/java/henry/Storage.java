package henry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import util.TaskUtils;

/**
 * Storage is responsible for loading the task list from
 * the file, and also writing any changes to the task
 * list to the file.
 */
public class Storage {

    // The text file is created in the same directory as the jar file.
    private static final String FILE_PATH =
        Paths.get(System.getProperty("user.dir"), "henryData", "henry.txt").toString();
    private static final String DIR_PATH = Paths.get(System.getProperty("user.dir"), "henryData").toString();
    private List<Task> tasks;

    /**
     * When a Storage class is initialised, it tries to
     * read the file at the specified filepath. If the file exists,
     * the tasks will be loaded from the file. If not, a new file will be
     * created at the filepath.
     */
    public Storage() {

        try {
            File savedList = new File(FILE_PATH);
            File directory = new File(DIR_PATH);
            if (!directory.exists()) {
                directory.mkdir();
            }
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
        Scanner scanner = new Scanner(savedList);
        List<Task> tasks = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isBlank()) {
                break;
            }
            Task parsed = TaskUtils.parseTask(line);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime dateTime = parsed.getLocalDateTime();
            if (!dateTime.isBefore(now)) {
                tasks.add(parsed);
            }
        }

        return tasks;
    }

    /**
     * Appends text to the file.
     *
     * @param textToAdd the text to be appended to the file
     */
    public void appendToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(textToAdd + "\n");
        fw.close();
    }
}
