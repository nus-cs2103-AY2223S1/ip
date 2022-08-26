package Duke;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public static List<Task> taskList;
    private static List<Task> tempList = new ArrayList<>();

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void markDone(int index) {
        taskList.get(index).markAsDone();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public static List<Task> getTempList() {
        return tempList;
    }

    public static void searchKeyword(String description) {
        tempList.clear();
        for (Task task : taskList) {
            if (task.getDescription().contains(description)) {
                tempList.add(task);
            }
        }
    }
}
