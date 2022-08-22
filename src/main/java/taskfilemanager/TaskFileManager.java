package taskfilemanager;

import task.Task;
import task.TaskList;

import java.io.File;

public class TaskFileManager {
    private final TaskFileLoader fileLoader;
    private final TaskFileSaver fileSaver;

    private TaskFileManager(File file) {
        fileLoader = new TaskFileLoader(file);
        fileSaver = new TaskFileSaver(file);
    }

    public static TaskFileManager of(String path) {
        try {
            File data = new File("data");
            if (!data.exists()) {
                data.mkdir();
            }

            File file = new File("data/" + path);
            if (!file.exists()) {
                file.createNewFile();
            }

            return new TaskFileManager(file);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TaskList getTaskList() {
        return fileLoader.getTaskList();
    }

    public void save(TaskList taskList) {
        fileSaver.saveTaskList(taskList);
    }
}
