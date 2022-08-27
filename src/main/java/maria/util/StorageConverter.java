package maria.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import maria.Parser;
import maria.TaskManager;
import maria.task.Task;
import maria.task.TaskList;

public class StorageConverter {

    public static String tasksToString(TaskList tasks) {

        List<String> tasksStr = new ArrayList<>();
        for (Task task : tasks) {
            tasksStr.add(task.toStorageString());
        }
        String result = String.join("===", tasksStr);
        return result;

    }

    public static void stringToTasks(TaskManager taskManager) {

        String tasksStr = null;
        try {
            tasksStr = taskManager.getStorage().readFromFile();
        } catch (IOException e) {
            System.out.println("File is corrupted. Please delete the tasks file and retry.");
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        String[] tasksStrArr = tasksStr.split("===");

        for (String taskStr : tasksStrArr) {
            if (!taskStr.isEmpty()) {
                Parser.parseStorage(taskStr).execute(taskManager);
            }
        }

    }

}
