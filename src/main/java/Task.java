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

    /**
     * The toString method of the Task class.
     *
     * @return The string representation of the Task object.
     */
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

    /**
     * The method to list out the tasks that are present in taskList.
     */
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

    public static void deleteTask(int num) {
        if (num > taskList.size() || num == 0) {
            System.out.println("____________________________________________________________");
            System.out.println("There's no such task!");
            System.out.println("____________________________________________________________");
            return;
        }
        Task removed = taskList.remove(num - 1);
        System.out.println("____________________________________________________________");
        System.out.println("Ok, I've removed this task:\n  "
                + removed.toString() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list :)");
        System.out.println("____________________________________________________________");
    }
}



