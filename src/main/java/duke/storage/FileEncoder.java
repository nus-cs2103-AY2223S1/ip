package duke.storage;

import duke.task.Task;
import duke.task.TaskList;

import java.io.FileWriter;
import java.io.IOException;

public class FileEncoder {
    /**
     * Encode the tasks from task list and write it to file.
     *
     * @param taskList Task list to be saved.
     * @throws IOException If error occurs when trying to write to file.
     */
    static void encodeFile(FileWriter fileWriter, TaskList taskList) throws IOException {
        for (Task task : taskList.view()) {
            encodeLine(fileWriter, task);
        }
        fileWriter.close();
    }

    /**
     * Encode task in file format for easier parsing during decoding.
     *
     * @param task Task to be saved.
     * @throws IOException If error occurs when trying to write to file.
     */
    private static void encodeLine(FileWriter fileWriter, Task task) throws IOException {
        fileWriter.write(task.convertToFileFormat() + System.lineSeparator());
    }
}
