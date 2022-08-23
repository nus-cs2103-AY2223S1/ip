package duke.chatbot.storage;

import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;

public class Storage {
    private final TaskFileLoader fileLoader;
    private final TaskFileSaver fileSaver;

    private Storage(File file) {
        fileLoader = new TaskFileLoader(file);
        fileSaver = new TaskFileSaver(file);
    }

    public static Storage of(String path) {
        try {
            File data = new File("data");
            if (!data.exists()) {
                data.mkdir();
            }

            File file = new File("data/" + path);
            if (!file.exists()) {
                file.createNewFile();
            }

            return new Storage(file);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TaskList getTaskList() throws InvalidInputException, FileNotFoundException {
        return fileLoader.getTaskList();
    }

    public void save(TaskList taskList) {
        fileSaver.saveTaskList(taskList);
    }
}
