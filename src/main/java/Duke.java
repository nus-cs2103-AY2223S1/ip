import java.util.Scanner;

public class Duke {

    /** Array containing Task objects */
    private static Task[] taskArray = new Task[100];

    /** Number of Task objects in the list */
    private static int numOfTasks = 0;

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
            } else if (strInput.contains("todo")){
                addToList(new Todo(strInput.substring(5)));
            } else if (strInput.contains("deadline")) {
                addToList(new Deadline(strInput.substring(9, strInput.indexOf("/") - 1),
                        strInput.substring(strInput.indexOf("/by") + 4)));
            } else if (strInput.contains("event")) {
                addToList(new Event(strInput.substring(6, strInput.indexOf("/") - 1),
                        strInput.substring(strInput.indexOf("/at") + 4)));
            }
            strInput = sc.nextLine();
        }
        sc.close();
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
