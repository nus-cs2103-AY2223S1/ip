import java.lang.reflect.Array;
import java.util.ArrayList;

public class EventHandler {
    static void addItem(String input, ArrayList<? super Task> list) {
        Task tempTask = new Task(input);
        list.add(tempTask);
        UserInterface.addTaskMessage(tempTask, list.size());
    }

    static void addTodo(String input, ArrayList<? super Task> list) {
        ToDo tempTask = new ToDo(input);
        list.add(tempTask);
        UserInterface.addTaskMessage(tempTask, list.size());
    }

    static void addDeadline(String input, ArrayList<? super Task> list) {
        int endAt = input.indexOf("/");
        String description = input.substring(9, endAt);
        String by = input.substring(endAt + 4);

        Deadline tempTask = new Deadline(description, by);
        list.add(tempTask);
        UserInterface.addTaskMessage(tempTask, list.size());
    }

    static void addEvent(String input, ArrayList<? super Task> list) {
        int endAt = input.indexOf("/");
        String description = input.substring(9, endAt);
        String at = input.substring(endAt + 4);

        Event tempTask = new Event(description, at);
        list.add(tempTask);
        UserInterface.addTaskMessage(tempTask, list.size());
    }

    public static <T extends Task> void markTask(int taskIndex, ArrayList<T> list) {
        list.get(taskIndex).markAsDone();
        UserInterface.markTaskMessage(list, taskIndex);
    }

    public static <T extends Task> void unmarkTask(int taskIndex, ArrayList<T> list) {
        list.get(taskIndex).markAsUndone();
        UserInterface.unmarkTaskMessage(list, taskIndex);
    }
}
