import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StorageConverter {

    public static String tasksToString(TaskList tasks) {

        List<String> tasksStr = new ArrayList<>();
        for (Task task : tasks) {
            tasksStr.add(task.toStorageString());
        }
        String result = String.join("===", tasksStr);
        return result;

    }

    public static TaskList stringToTasks(Storage storage) {

        TaskList tasks = new TaskList(storage);

        String tasksStr = null;
        try {
            tasksStr = storage.readFromFile();
        } catch (IOException e) {
            System.out.println("File is corrupted. Please delete the tasks file and retry.");
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        String[] tasksStrArr = tasksStr.split("===");

        for (String taskStr : tasksStrArr) {
            if (!taskStr.isEmpty()) {
                try {
                    tasks.add(Task.createTaskFromStorageString(taskStr));
                } catch (TaskNoNameException | IllegalTaskTypeException e) {
                    System.out.println("File is corrupted. Please delete the tasks file and retry.");
                    System.out.println(e.getMessage());
                    System.exit(-1);
                }
            }
        }

        return tasks;

    }

}
