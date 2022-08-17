import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    /** ArrayList of type Task */
    private static ArrayList<Task> taskArray = new ArrayList<>();

    public static void main(String[] args) throws MultipleTasksException, NullTaskException, InvalidCommandException {
        Scanner sc = new Scanner(System.in);
        System.out.println("____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________");
        try {
            while (sc.hasNextLine()) {
                String strInput = sc.nextLine();
                if ((strInput.equalsIgnoreCase("list"))) {
                    System.out.println("____________________________________________________________\n"
                            + " Here are the tasks in your list:"
                            + enumerateList()
                            + "\n____________________________________________________________");
                } else if (isATodo(strInput)){
                    checkForNullTask(strInput.substring(4), "todo");
                    checkForMultipleTasks(strInput.substring(4));
                    addToList(new Todo(strInput.substring(5)));
                } else if (isADeadline(strInput)) {
                    checkForNullTask(strInput.substring(8), "deadline");
                    checkForMultipleTasks(strInput.substring(8));
                    addToList(new Deadline(strInput.substring(9, strInput.indexOf("/") - 1),
                            strInput.substring(strInput.indexOf("/by") + 4)));
                } else if (isAEvent(strInput)) {
                    checkForNullTask(strInput.substring(5), "event");
                    checkForMultipleTasks(strInput.substring(5));
                    addToList(new Event(strInput.substring(6, strInput.indexOf("/") - 1),
                            strInput.substring(strInput.indexOf("/at") + 4)));
                } else if (strInput.contains("delete") && strInput.substring(0,6).equals("delete")) {
                    deleteFromList(Integer.parseInt(strInput.substring(7)));
                } else if (strInput.contains("unmark") && strInput.substring(0,6).equals("unmark")) {
                    unmarkTheTask(Integer.parseInt(strInput.substring(7)));
                } else if (strInput.contains("mark") && strInput.substring(0,4).equals("mark")) {
                    markTheTask(Integer.parseInt(strInput.substring(5)));
                } else {
                    throw new InvalidCommandException("Cannot recognise the command.");
                }
            }
        } catch (MultipleTasksException e) {
            System.out.println(e.toString());
        } catch (NullTaskException e) {
            System.out.println(e.toString());
        } catch (InvalidCommandException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Checks if there is task description.
     *
     * @param description task description.
     * @param typeOfTask todo, deadline, or event.
     * @throws NullTaskException if there is no task description.
     */
    public static void checkForNullTask(String description, String typeOfTask) throws NullTaskException {
        if (description.isBlank()) {
            throw new NullTaskException("No task description.", typeOfTask);
        }
    }

    /**
     * Checks if there are multiple tasks in one input.
     *
     * @param s user input.
     * @throws MultipleTasksException if there are multiple tasks in one input.
     */
    public static void checkForMultipleTasks(String s) throws MultipleTasksException {
        if (s.contains("todo") ||s.contains("deadline") || s.contains("event")) {
            throw new MultipleTasksException("Multiple task detected.");
        }
    }

    /**
     * Check if is a todo task.
     *
     * @param s
     * @return boolean. True if is a todo task.
     */
    public static boolean isATodo(String s) {
        return s.contains("todo") && s.substring(0,4).equals("todo");
    }

    /**
     * Check if is a task with deadline.
     *
     * @param s
     * @return boolean. True if is a deadline task.
     */
    public static boolean isADeadline(String s) {
        return s.contains("deadline") && s.substring(0,8).equals("deadline");
    }

    /**
     * Check if is a event task.
     * @param s
     * @return boolean. True if is a event task.
     */
    public static boolean isAEvent(String s) {
        return s.contains("event") && s.substring(0, 5).equals("event");
    }

    /**
     * Deletes a Task object from taskArray
     *
     * @param taskID Task index.
     */
    public static void deleteFromList(int taskID) {
        Task task = taskArray.get(taskID - 1);
        taskArray.remove(taskID - 1);
        System.out.println("____________________________________________________________\n"
                + " Noted. I've removed this task:\n  "
                + task.toString()
                + "\n Now you have " + taskArray.size() + " tasks in the list"
                + "\n____________________________________________________________");
    }

    /**
     * Adds a new Task object to taskArray
     *
     * @param t Task.
     */
    public static void addToList(Task t) {
        taskArray.add(t);
        System.out.println("____________________________________________________________\n"
                + " Got it. I've added this task:\n  "
                + t.toString()
                + "\n Now you have " + taskArray.size() + " tasks in the list"
                + "\n____________________________________________________________");
    }

    /**
     * Returns a string representation of the list.
     *
     * @return String.
     */
    public static String enumerateList() {
        //StringBuilder over string concat for better performance
        StringBuilder s = new StringBuilder("");
        for (int i = 1; i <= taskArray.size(); i++) {
            s.append("\n " + i
                    + "." + taskArray.get(i - 1).toString());
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
