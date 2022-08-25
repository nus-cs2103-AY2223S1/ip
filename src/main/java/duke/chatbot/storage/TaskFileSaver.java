package duke.chatbot.storage;

import duke.chatbot.data.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A file saver which saves a list of tasks onto a file.
 */
public class TaskFileSaver {
    /** The file to save the list of tasks on */
    private final File file;

    protected TaskFileSaver(File file) {
        this.file = file;
    }

    /**
     * Saves the list of tasks onto the file.
     * @param taskList The list of tasks to be stored.
     */
    public void saveTaskList(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append(taskList.encodeAll());

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
