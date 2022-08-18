import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public void add(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(index));
    }

    public void markTaskAsUndone(int index) {
        tasks.get(index).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.get(index));
    }

    public void delete(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        System.out.println("Noted. I've removed this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
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
