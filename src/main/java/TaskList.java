import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     *
     * @param task The task that should be added to the list.
     * @return A string that tells the user if the tasks has been added.
     */
    public String add(Task task) {
        tasks.add(task);
        String message = "\tGot it! I have added this task:\n\t\t" + task;
        String numOfTasks = String.format("\n\tNow you have %d %s in the list!", tasks.size(),
                tasks.size() < 2 ? "task" : "tasks");
        return message + numOfTasks;
    }

    /**
     * To list down all the tasks that are added to the list.
     * @return A list of all the tasks added.
     */
    public String list() {
        int len = tasks.size();
        StringBuilder stringBuilder = new StringBuilder("\tHere are the tasks in your list :D");
        for (int i = 0; i < len; i++) {
            int index = i + 1;
            String task = "\n\t" + index + ". " + tasks.get(i);
            stringBuilder.append(task);
        }
        return stringBuilder.toString();
    }

    /**
     * To delete the task specified.
     * @return A string that notifies users that the task has been deleted.
     */
    public String delete(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        String message = String.format("\tNoted, I have removed this task:\n\t\t%s", task);
        tasks.remove(taskNumber - 1);
        return message;
    }

    public int size() {
        return this.tasks.size();
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }
}
