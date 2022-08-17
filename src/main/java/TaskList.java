import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;
    public static final String DONE = "Nice! I've marked this task as done:\n";
    public static final String UNDONE =  "OK, I've marked this task as not done yet:\n";
    public static final String ADD = "Got it. I've added this task:\n";
    public static final String NUMBER_START = "Now you have ";
    public static final String NUMBER_END = " tasks in the list.";


    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder allTasks = new StringBuilder();
        for (int i = 1; i <= this.tasks.size(); i++) {
            allTasks.append(i + ". " + this.tasks.get(i - 1));
            if (i < this.tasks.size()) {
                allTasks.append("\n");
            }
        }
        return allTasks.toString();
    }

    public String addTask(Task task) {
        this.tasks.add(task);
        return ADD + task.toString() + "\n" + NUMBER_START + this.tasks.size() + NUMBER_END;
    }

    public String markTask(int index) {
        return DONE + this.tasks.get(index).markAsDone();
    }

    public String unmarkTask(int index) {
        return UNDONE + this.tasks.get(index).markAsUndone();
    }
}
