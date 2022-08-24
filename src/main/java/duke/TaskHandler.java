package duke;
public class TaskHandler {
    private TaskList taskList;
    public TaskHandler(TaskList taskList) {
        this.taskList = taskList;
    }

    static void addTask(String input) {
        TaskList.add(input);
    }

    static void deleteTask(String input) {
        TaskList.delete(input);
    }
    public static void markChild(String input) {
        int index = Integer.parseInt(input.substring(5)) - 1;
        TaskList.markChild(index);
    }

    public static void unmarkChild(String input) {
        int index = Integer.parseInt(input.substring(7)) - 1;
        TaskList.unmarkChild(index);
    }
}
