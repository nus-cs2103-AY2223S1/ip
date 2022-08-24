import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public void deleteTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index - 1);

        String taskOrTasks = tasks.size() == 1 ? "task" : "tasks";
        System.out.println(Style.INDENTATION + "Noted. I've removed this task:");
        System.out.println(Style.INDENTATION + Style.HALF_INDENTATION + task);
        System.out.println(Style.INDENTATION + "Now you have " + tasks.size() + " "
                + taskOrTasks + " in the list.\n");
    }

    public Task markTask(int index) {
        Task task = getTask(index);
        task.markAsDone();
        return task;
    }

    public Task unmarkTask(int index) {
        Task task = getTask(index);
        task.unmark();
        return task;
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(Style.INDENTATION + (i + 1)  + "." + getTask(i + 1));
        }
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }


    public int getNumberOfTasks() {
        return tasks.size();
    }

}
