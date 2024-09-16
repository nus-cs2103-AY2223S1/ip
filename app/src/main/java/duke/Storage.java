package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * A Storage handler for file-based data saving.
 */
public class Storage {
    private final String fileName;

    /**
     * Creates a new storage handler.
     * @param fileName Where the file is located.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Saves a task list to the file.
     * @param taskList The task list to be saved.
     */
    public void save(TaskList taskList) {
        try {
            PrintWriter saveFile = new PrintWriter(fileName);
            for (int i = 0; i < taskList.size(); ++i) {
                saveFile.println(taskList.getTask(i).getEncoded());
            }
            saveFile.close();
        } catch (IOException e) {
            System.out.println("<couldn't save to file!>");
        }
    }

    /**
     * Loads a task list from the file.
     * @return The loaded task list.
     */
    public TaskList load() {
        TaskList tasks = new TaskList();
        Scanner saved;
        try {
            saved = new Scanner(new File(fileName));
        } catch (FileNotFoundException ignored) {
            return tasks;
        }

        while (saved.hasNextLine()) {
            try {
                tasks.add(Task.fromEncoded(saved.nextLine()));
            } catch (DateTimeParseException ignored) {
                // ignored
            }
        }
        saved.close();

        return tasks;
    }
}
