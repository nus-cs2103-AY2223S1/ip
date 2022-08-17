import java.util.Scanner;

public class Duke {

    /** Array containing Task objects */
    private static Task[] taskArray = new Task[100];

    /** Number of Task objects in the list */
    private static int numOfTasks = 0;

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
                } else if (strInput.contains("todo") && strInput.substring(0,4).equals("todo")){
                    checkForNullTask(strInput.substring(4), "todo");
                    checkForMultipleTasks(strInput.substring(4));
                    addToList(new Todo(strInput.substring(5)));
                } else if (strInput.contains("deadline") && strInput.substring(0,8).equals("deadline")) {
                    checkForNullTask(strInput.substring(8), "deadline");
                    checkForMultipleTasks(strInput.substring(8));
                    addToList(new Deadline(strInput.substring(9, strInput.indexOf("/") - 1),
                            strInput.substring(strInput.indexOf("/by") + 4)));
                } else if (strInput.contains("event") && strInput.substring(0,5).equals("event")) {
                    checkForNullTask(strInput.substring(5), "event");
                    checkForMultipleTasks(strInput.substring(5));
                    addToList(new Event(strInput.substring(6, strInput.indexOf("/") - 1),
                            strInput.substring(strInput.indexOf("/at") + 4)));
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

    private static void checkForNullTask(String description, String typeOfTask) throws NullTaskException {
        if (description.isBlank()) {
            throw new NullTaskException("No task description.", typeOfTask);
        }
    }

    private static void checkForMultipleTasks(String s) throws MultipleTasksException {
        if (s.contains("todo") ||s.contains("deadline") || s.contains("event")) {
            throw new MultipleTasksException("Multiple task detected.");
        }
    }

    /**
     * Adds a new Task object to taskArray
     *
     * @param t Task.
     */
    public static void addToList(Task t) {
        taskArray[numOfTasks] = t;
        numOfTasks += 1;
        System.out.println("____________________________________________________________\n"
                + " Got it. I've added this task:\n  "
                + t.toString()
                + "\n Now you have " + numOfTasks + " tasks in the list"
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
        for (int i = 1; i <= numOfTasks; i++) {
            s.append("\n " + i
                    + "." + taskArray[i - 1].toString());
        }
        return s.toString();
    }
}
