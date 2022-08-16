import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> taskArray = new ArrayList<Task>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________");
        String strInput = sc.nextLine();
        while (!strInput.equals("")) {
            if ((strInput.equalsIgnoreCase("list"))) {
                System.out.println("____________________________________________________________\n"
                        + " Here are the tasks in your list:"
                        + enumerateList()
                        + "\n____________________________________________________________");
            } else if (strInput.contains("unmark")) {
                unmarkTheTask(Integer.parseInt(strInput.substring(7)));
            } else if (strInput.contains("mark")) {
                markTheTask(Integer.parseInt(strInput.substring(5)));
            } else {
                addToList(strInput);
            }
            strInput = sc.nextLine();
        }
        sc.close();
    }

    /**
     * Adds a new Task object to taskArray
     *
     * @param s Input from user.
     */
    public static void addToList(String s) {
        taskArray.add(new Task(s));
        System.out.println("____________________________________________________________\n"
                + " added: "
                + s
                + "\n____________________________________________________________");
    }

    /**
     * Returns a string representation of the list.
     *
     * @return String.
     */
    public static String enumerateList() {
        //StringBuilder over string concat for better performance
        StringBuilder s = new StringBuilder(" ");
        for (int i = 1; i <= taskArray.size(); i++) {
            s.append("\n " + i
                    + ".[" + taskArray.get(i - 1).getStatusIcon() + "] "
                    + taskArray.get(i - 1).description);
        }
        return s.toString();
    }

    /**
     * Mark the task done.
     *
     * @param taskID Task's number.
     */
    public static void markTheTask(int taskID) {
        Task t = taskArray.get(taskID - 1);
        System.out.println("____________________________________________________________\n"
                + " Nice! I've marked this task as done:\n"
                + "  [X] "
                + t.description
                + "\n____________________________________________________________");
        t.toggleIsDone(true);
    }

    /**
     * Mark the task as not done.
     *
     * @param taskID Task's number.
     */
    public static void unmarkTheTask(int taskID) {
        Task t = taskArray.get(taskID - 1);
        System.out.println("____________________________________________________________\n"
                + " OK, I've marked this task as not done yet:\n"
                + "  [ ] "
                + t.description
                + "\n____________________________________________________________");
        t.toggleIsDone(false);
    }
}
