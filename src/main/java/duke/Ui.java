package duke;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    public Ui() {};

    public static String showWelcome() {
       return "Hello, what can I do for you?";
    }

    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    public String showTaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n\t" + task;
    }

    public String showTaskUnmarked(Task task) {
       return "OK, I've marked this task as not done yet:\n\t" + task;
    }

    public String showTaskAdded(Task task) {
        return "Got it. I've added this task:\n\t" + task;
    }

    public String showTaskDeleted(Task task) {
        return "Noted. I've removed this task:\n\t" + task;
    }

    public String showTaskCount(int count) {
        if (count == 1) {
            return "Now you have 1 task in the list.";
        } else {
            return "Now you have " + (count) + " tasks in the list.";
        }
    }

    public String showFoundTasks(TaskList tasks) {
        if (tasks.getCount() == 0) {
            return "No results found!";
        } else {
            String result = "Matching results:";
            for (int i = 0; i < tasks.getCount(); i++) {
                result += "\n\t" + (i + 1) + "." + tasks.get(i);
            }
            return result;
        }
    }
}
