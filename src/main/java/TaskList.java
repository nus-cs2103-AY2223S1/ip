import java.util.ArrayList;

public class TaskList {
    /**
     * Class to store the list of Tasks
     */
    private ArrayList<Task> taskList = new ArrayList<>();

    public void add(Task task) {
        taskList.add(task);
    }
    public void printTasks() {
        int index = 0;
        Task item;
        while (index < taskList.size()) {
            item = taskList.get(index);
            System.out.println((index + 1) + "." + item.toString());
            index++;
        }
    }

    public void mark(int index) {
        taskList.get(index).markAsDone();
        Messages.mark();
    }

    public void unmark(int index) {
        taskList.get(index).markAsUndone();
        Messages.unmark();
    }
}
