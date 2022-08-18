import java.util.ArrayList;
import java.util.List;

public class Task {
    protected String description;
    protected boolean isDone;
    private static List<Task> taskList = new ArrayList<>();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public static void markAsDone(String action, int taskIndex) {
        try {
            Task task = taskList.get(taskIndex - 1);
            task.isDone = action.equals("mark");
            System.out.println(
                    (action.equals("mark")
                            ? "OK, I've marked this task as not done yet: \n"
                            : "Nice! I've marked this task as done: \n")
                            + task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task index does not exist");
        }
    }

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
    }

    public static void delete(int taskIndex) {
        try {
            Task task = taskList.remove(taskIndex - 1);
            System.out.println(
                    "Noted. I've removed this task:\n "
                            + task 
                            + "\nNow you have " + taskList.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task index not found");
        }
    }

    public void addNewTask() {
        taskList.add(this);
        System.out.println(
                "Got it. I've added this task: \n "
                        + this
                        + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
