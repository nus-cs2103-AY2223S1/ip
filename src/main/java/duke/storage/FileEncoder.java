package duke.storage;

import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileEncoder {
    /**
     * Encode the tasks from task list and write it to file.
     *
     * @param dataFile File to be saved to.
     * @param taskList Task list to be saved.
     * @throws IOException If error occurs when trying to write to file.
     */
    static void encodeFile(File dataFile, TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(dataFile);
        for (Task task : taskList.view()) {
            encodeLine(fileWriter, task);
        }
        fileWriter.close();
    }

    /**
     * Encode task in file format.
     *
     * @param fileWriter File to be saved to.
     * @param task Task to be saved.
     * @throws IOException If error occurs when trying to write to file.
     */
    private static void encodeLine(FileWriter fileWriter, Task task) throws IOException {
        fileWriter.write(task.convertToFileFormat() + System.lineSeparator());
    }
}
