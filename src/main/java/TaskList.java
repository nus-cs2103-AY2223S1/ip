import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public void add(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public boolean markTaskAsDone(int index) {
        return this.tasks.get(index).markAsDone();
    }

    public boolean markTaskAsUndone(int index) {
        return this.tasks.get(index).markAsUndone();
    }

    public String getTask(int index) {
        return tasks.get(index).toString();
    }

    public int size() {
        return tasks.size();
    }

    public void list() {
        Task[] x = new Task[tasks.size()];
        Task[] tasksArray = tasks.toArray(x);
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= tasksArray.length; i++) {
            Task task = tasksArray[i - 1];
            System.out.println(i + "." + task.toString());
        }
    }
}
