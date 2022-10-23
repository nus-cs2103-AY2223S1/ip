package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Access and modification to local directory where content of Duke is stored.
 */
public class Storage {
    private final String filepath;
    private static final char INDEX_TASKTYPE = 4;

    Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Creates a TaskList containing all outstanding tasks in local directory when Duke is launched.
     *
     * @return TaskList of outstanding tasks.
     * @throws FileNotFoundException invalid filepath of outstanding tasks.
     */
    protected TaskList loadExistingTasks() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(new File(filepath));
        while (s.hasNext()) {
            String taskText = s.nextLine();
            char taskType = taskText.charAt(INDEX_TASKTYPE);
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

    /**
     * Saves a given instance of a Task List to the user's local directory.
     *
     * @param tasks TaskList of tasks.
     */
    protected void saveExistingTasks(TaskList tasks) {
        try {
            File file = new File(filepath);
            File dir = new File(file.getParent());
            if (!dir.exists()) {
                dir.mkdir();
            }
            FileWriter fw = new FileWriter(filepath);
            fw.write(tasks.enumerateList());
            fw.close();
        } catch (IOException err) {
            System.out.println(":( TaskList not saved: " + err.getMessage());
        }
    }
}
