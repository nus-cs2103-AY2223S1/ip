/**
 * A list that keeps track of what the user has input.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */

// Level 2
import java.util.Scanner;

public class List {
    /**
     * The list that keeps track of what the user has input.
     */
    private final static Task[] list = new Task[100];

    /**
     * The number/integer that keeps track of the next index in the list that is not filled.
     */
    private static int index = 0;

    /**
     * The method that initialises and runs the list.
     */
    public static void run() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String text = sc.nextLine();
            Scanner temp = new Scanner(text);
            if (temp.hasNext("mark")) {
                String mark = temp.next();
                int i = temp.nextInt();
                if (i > 0) {
                    if (list[i - 1] != null) {
                        Task t = list[i - 1];
                        t.mark();
                    }
                }
                temp.close();
            } else if (temp.hasNext("unmark")) {
                String unmark = temp.next();
                int i = temp.nextInt();
                if (i > 0) {
                    if (list[i - 1] != null) {
                        Task t = list[i - 1];
                        t.unmark();
                    }
                }
                temp.close();
            } else {
                if (temp.hasNext("deadline")) {
                    temp.useDelimiter("deadline\\s*|\\s*/by\\s*");
                    String description = temp.next();
                    String by = temp.next();
                    Deadline d = new Deadline(description, by);
                    temp.close();
                    List.add(d);
                } else if (temp.hasNext("event")) {
                    temp.useDelimiter("event\\s*|\\s*/at\\s*");
                    String description = temp.next();
                    String at = temp.next();
                    Event e = new Event(description, at);
                    temp.close();
                    List.add(e);
                } else if (temp.hasNext("todo")) {
                    temp.useDelimiter("todo\\s*");
                    String description = temp.next();
                    Todo t = new Todo((description));
                    temp.close();
                    List.add(t);
                } else {
                    if (text.equals("bye")) {
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    } else if (text.equals("list")) {
                        List.printList();
                        List.run();
                    } else {
                        Task task = new Task(text);
                        List.add(task);
                    }
                }
            }
        }
        sc.close();
    }
    /**
     * Helper function that returns a string
     *
     * @return task/tasks depending on the number of existing tasks in the list.
     */
    private static String taskString() {
        if (index <= 1) {
            return " task ";
        } else {
            return " tasks ";
        }
    }
    /**
     * The method that adds a Task to the list.
     */
    public static void add(Task t) {
        list[index] = t;
        index++;
        System.out.println("Got it. I've added this task:\n" + t.toString() + "\n" + "Now you have " + index + taskString() + "in the list.");
    }

    public static void printList() {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                System.out.println(i + 1 + ". " + list[i].toString());
            }
        }
    }

}
