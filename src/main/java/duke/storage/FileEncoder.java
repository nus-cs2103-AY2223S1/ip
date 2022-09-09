package duke.storage;

import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileEncoder {
    static void encodeFile(File dataFile, TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(dataFile);
        for (Task task : taskList.view()) {
            encodeLine(fileWriter, task);
        }
        fileWriter.close();
    }

    private static void encodeLine(FileWriter fileWriter, Task task) throws IOException {
        fileWriter.write(task.convertToFileFormat() + System.lineSeparator());
    }
}
