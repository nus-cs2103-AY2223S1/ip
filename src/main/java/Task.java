import java.util.ArrayList;
import java.util.List;

public class Task {
    private String item;
    private boolean done;
    public static List<Task> taskList = new ArrayList<>();

    public Task(String item) {
        this.item = item;
        this.done = false;
    }

    @Override
    public String toString() {
        if (!done) {
            return "[ ] " + item;
        } else {
            return "[X] " + item;
        }
    }

    private void setDone() {
        this.done = true;
    }

    private void setUnDone() {
        this.done = false;
    }

    public static void printTaskList() {
        System.out.println("Here are your tasks");
        System.out.println("____________________________________________________________");
        int i = 1;
        for (Task t : taskList) {
            System.out.println(i + "." + t.toString());
            i++;
        }
        System.out.println("____________________________________________________________");
    }

    public static void mark(int number) {
        if (number > taskList.size() || number == 0) {
            System.out.println("____________________________________________________________");
            System.out.println("There's no such task!");
            System.out.println("____________________________________________________________");
            return;
        }
        taskList.get(number - 1).setDone();
        System.out.println("____________________________________________________________");
        System.out.println("Ok! I've marked it as done.\n  "
                + taskList.get(number - 1).toString());
        System.out.println("____________________________________________________________");
    }

    public static void unMark(int number) {
        if (number > taskList.size() || number == 0) {
            System.out.println("____________________________________________________________");
            System.out.println("There's no such task!");
            System.out.println("____________________________________________________________");
            return;
        }
        taskList.get(number - 1).setUnDone();
        System.out.println("____________________________________________________________");
        System.out.println("Ok! I've marked it as undone.\n  "
                + taskList.get(number - 1).toString());
        System.out.println("____________________________________________________________");
    }

    public static void addTask(Task task) {
        taskList.add(task);
        System.out.println("____________________________________________________________");
        System.out.println("Got it! I've added this task:\n  "
                + task.toString() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list :)");
        System.out.println("____________________________________________________________");
    }
}



