import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void createTask(Task task) {
        taskList.add(task);
        String message = "Got it. I've added this task: \n"
                + task.toString() + "\nNow you have " + taskList.size() + " tasks in the list.";
        Ui.formatMessage(message);
    }

    public void createTaskSilently(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        String message = "Done! I have deleted this task:\n  " + taskList.get(index)
                + "\nNow you have " + (taskList.size() - 1) + " tasks in the list.";
        Ui.formatMessage(message);
        taskList.remove(index);
    }

    public int getSize() {
        return this.taskList.size();
    }

    public Task getTask(int i) {
        return this.taskList.get(i);
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
