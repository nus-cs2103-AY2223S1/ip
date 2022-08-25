package duke.chatbot.storage;

import duke.chatbot.data.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TaskFileSaver {
    private final File file;

    protected TaskFileSaver(File file) {
        this.file = file;
    }

    public void saveTaskList(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append(taskList.encodeAll());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
