package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Access and modification to local directory where content of Duke is stored.
 */
public class Storage {
    private final String filepath;
    private static final Path FILEPATH_HELP = Path.of("data/help.txt");

    Storage(String filepath) {
        this.filepath = filepath;
    }

    protected TaskList loadExistingTasks() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(new File(filepath));
        while (s.hasNext()) {
            String taskText = s.nextLine();
            char taskType = taskText.charAt(3);
            if (taskType == 'T') {
                tasks.add(new Todo(taskText));
            } else if (taskType == 'D') {
                tasks.add(new Deadline(taskText));
            } else if (taskType == 'E') {
                tasks.add(new Event(taskText));
            }
        }
        return new TaskList(tasks);
    }

    protected static String loadHelpTextFile() {
        try {
            return Files.readString(FILEPATH_HELP);
        } catch (IOException ex) {
            return "Help Instruction File Missing";
        }
    }

    /**
     * Saves a given instance of a Task List to the user's local directory.
     * @param tasks TaskList of tasks
     */
    protected void saveExistingTasks(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filepath);
            fw.write(tasks.enumerateList());
            fw.close();
        } catch (IOException err) {
            System.out.println(":( TaskList not saved: " + err.getMessage());
        }
    }
}
