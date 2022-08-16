import java.lang.reflect.Array;
import java.util.ArrayList;

public class EventHandler {
    static void addItem(String input, ArrayList<Task> list) {
        Task tempTask = new Task(input);
        list.add(tempTask);
        UserInterface.addItemMessage(input);
    }
    public static void markTask(int taskIndex, ArrayList<Task> list) {
        list.get(taskIndex).markAsDone();
        UserInterface.markTaskMessage(list, taskIndex);
    }

    public static void unmarkTask(int taskIndex, ArrayList<Task> list) {
        list.get(taskIndex).markAsUndone();
        UserInterface.unmarkTaskMessage(list, taskIndex);
    }
}
