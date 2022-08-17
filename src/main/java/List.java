/**
 * A list that keeps track of what the user has input.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */

// Level 2

import java.util.Scanner;
import java.util.ArrayList;

public class List {
    /**
     * The list that keeps track of what the user has input.
     */
    private final static ArrayList<Task> list = new ArrayList<>(100);

    /**
     * The number/integer that keeps track of the next index in the list that is not filled.
     */
    private static int index = 0;

    //Exception Messages

    private static final String TODO_NO_DESC = "☹ OOPS!!! The description of a todo cannot be empty.";
    private static final String DEADLINE_NO_DESC = "☹ OOPS!!! The description of a deadline cannot be empty.";
    private static final String DEADLINE_NO_TIME = "☹ OOPS!!! The date/time of a deadline cannot be empty.";
    private static final String EVENT_NO_DESC = "☹ OOPS!!! The description of an event cannot be empty.";
    private static final String EVENT_NO_TIME = "☹ OOPS!!! The date/time of an event cannot be empty.";
    private static final String NO_INDEX = "☹ OOPS!!! The index of the task to be marked/unmarked/deleted cannot be empty.";
    private static final String INVALID_INDEX = "☹ OOPS!!! The index of the task to be marked/unmarked/deleted must be valid/within range.";
    private static final String UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    /**
     * The method that initialises and runs the list.
     */
    public static void run() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                String text = sc.nextLine();
                Scanner temp = new Scanner(text);
                if (temp.hasNext("mark")) {
                    String mark = temp.next();
                    if (!temp.hasNextInt()) {
                        throw new DukeException(NO_INDEX);
                    } else {
                        int i = temp.nextInt();
                        if ((i > 0) && (list.get(i - 1) != null)) {
                            Task t = list.get(i - 1);
                            t.mark();
                        } else {
                            throw new DukeException(INVALID_INDEX);
                        }
                        temp.close();
                    }
                } else if (temp.hasNext("unmark")) {
                    String unmark = temp.next();
                    if (!temp.hasNextInt()) {
                        throw new DukeException(NO_INDEX);
                    } else {
                        int i = temp.nextInt();
                        if ((i > 0) && (list.get(i - 1) != null)) {
                            Task t = list.get(i - 1);
                            t.unmark();
                        } else {
                            throw new DukeException(INVALID_INDEX);
                        }
                        temp.close();
                    }
                } else if (temp.hasNext("deadline")) {
                    temp.useDelimiter("deadline\\s*|\\s*/by\\s*");
                    if (!temp.hasNext()) {
                        throw new DukeException(DEADLINE_NO_DESC);
                    }
                    String description = temp.next();
                    if (!temp.hasNext()) {
                        throw new DukeException(DEADLINE_NO_TIME);
                    }
                    String by = temp.next();
                    Deadline d = new Deadline(description, by);
                    temp.close();
                    List.add(d);
                } else if (temp.hasNext("event")) {
                    temp.useDelimiter("event\\s*|\\s*/at\\s*");
                    if (!temp.hasNext()) {
                        throw new DukeException(EVENT_NO_DESC);
                    }
                    String description = temp.next();
                    if (!temp.hasNext()) {
                        throw new DukeException(EVENT_NO_TIME);
                    }
                    String at = temp.next();
                    Event e = new Event(description, at);
                    temp.close();
                    List.add(e);
                } else if (temp.hasNext("todo")) {
                    temp.useDelimiter("todo\\s*");
                    if (!temp.hasNext()) {
                        throw new DukeException(TODO_NO_DESC);
                    }
                    String description = temp.next();
                    Todo t = new Todo((description));
                    temp.close();
                    List.add(t);
                } else if (temp.hasNext("delete")) {
                    temp.useDelimiter("delete\\s*");
                    if (!temp.hasNextInt()) {
                        throw new DukeException(NO_INDEX);
                    } else {
                        int i = temp.nextInt();
                        if ((i > 0) && (list.get(i - 1) != null)) {
                            List.deleteTask(i - 1);
                        } else {
                            throw new DukeException(INVALID_INDEX);
                        }
                        temp.close();
                    }
                } else {
                    if (text.equals("bye")) {
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    } else if (text.equals("list")) {
                        List.printList();
                        List.run();
                    } else {
                        throw new DukeException(UNKNOWN_COMMAND);
                    }
                }
            } catch (DukeException e){
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }
    /**
     * Helper function that returns a string.
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
        list.add(t);
        index++;
        System.out.println("Got it. I've added this task:\n" + t.toString() + "\n" + "Now you have " + index + taskString() + "in the list.");
    }

    /**
     * Helper function that prints existing list to screen.
     */
    public static void printList() {
        list.forEach(t -> System.out.println(list.indexOf(t) + 1 + ". " + list.get(list.indexOf(t)).toString()));
    }

    /**
     * The method that deletes a Task from the list.
     */
    public static void deleteTask(int index) {
        System.out.println("Noted. I've removed this task:\n" + list.get(index).toString() + "\n" + "Now you have " + (List.index - 1) + taskString() + "in the list.");
        List.index--;
        list.remove(index);
    }
}
