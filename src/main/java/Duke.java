import java.util.*;
import java.util.ArrayList;

public class Duke {
    /**
     * Counter field that shows how many tasks there are in a list
     */
    public static int counter = 0;

    /**
     * Prints the message when a user types "bye"
     */
    public static void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the list when user types "list"
     *
     * @param arr The arraylist containing all the tasks
     */
    public static void listMessage(ArrayList<Task> arr) {
        for (int i = 1; i < counter + 1; i++) {
            System.out.println((i) + ". " + arr.get(i - 1));
        }
    }

    /**
     * Marks a task as completed
     *
     * @param str The users input
     * @param arr The arraylist containing all the tasks
     */
    public static void markMessage(String str, ArrayList<Task> arr) throws DukeException{
        char index = str.charAt(5);
        int number = Integer.parseInt(String.valueOf(index));
        if(number >= counter) {
            throw new DukeException("No such task.");
        }
        Task t = arr.get(number - 1);
        t.mark();
        System.out.println("Nice! I've marked this task as done:\n  [X] " + t.description);
    }

    /**
     * Marks a task as incomplete
     *
     * @param str The users input
     * @param arr The arraylist containing all the tasks
     */
    public static void unmarkMessage(String str, ArrayList<Task> arr) throws DukeException {
        char index = str.charAt(7);
        int number = Integer.parseInt(String.valueOf(index));
        if(number >= counter) {
            throw new DukeException("No such task.");
        }
        Task t = arr.get(number - 1);
        t.unmark();
        System.out.println("OK, I've marked this task as not done yet:\n  [ ] " + t.description);
    }

    /**
     * Adds a Todo to the list
     *
     * @param str The users input
     * @param arr The arraylist containing all the tasks
     */
    public static void todoMessage(String str, ArrayList<Task> arr) throws DukeException{
        if(str.length() > 5) {
            String description = str.substring(5);
            Todo d = new Todo(description);
            arr.add(counter, d);
            counter++;
            System.out.println("Got it. I've added this task:\n " + d +
                    "\nNow you have " + counter + " tasks in the list.");
        } else {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Adds a Deadline to the list
     *
     * @param str The users input
     * @param arr The arraylist containing all the tasks
     */
    public static void deadlineMessage(String str, ArrayList<Task> arr) throws DukeException{
        if(str.length() > 9) {
            int sepPos = str.indexOf("/");
            if (sepPos != -1) {
                String description = str.substring(9, sepPos);
                String by = str.substring(sepPos + 4);
                Deadline l = new Deadline(description, by);
                arr.add(counter, l);
                counter++;
                System.out.println("Got it. I've added this task:\n " + l +
                        "\nNow you have " + counter + " tasks in the list.");
            } else {
                throw new DukeException("You have to include the deadline");
            }
        } else {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
    }

    /**
     * Adds a Event to the list
     *
     * @param str The users input
     * @param arr The arraylist containing all the tasks
     */
    public static void eventMessage(String str, ArrayList<Task> arr) throws DukeException {
        if (str.length() > 6) {
            int sepPos = str.indexOf("/");
            if (sepPos != -1) {
                String description = str.substring(6, sepPos);
                String at = str.substring(sepPos + 4);
                Event e = new Event(description, at);
                arr.add(counter, e);
                counter++;
                System.out.println("Got it. I've added this task:\n " + e +
                        "\nNow you have " + counter + " tasks in the list.");
            } else {
                throw new DukeException("You have to include the timings of an event");
            }
        } else {
            throw new DukeException("The description of a event cannot be empty.");
        }
    }

    /**
     * Deletes a task from the list
     *
     * @param str The users input
     * @param arr The arraylist containing all the tasks
     */
    public static void deleteMessage(String str, ArrayList<Task> arr) throws DukeException {
        if (str.length() > 7) {
            char index = str.charAt(7);
            int number = Integer.parseInt(String.valueOf(index));
            if(number >= counter) {
                throw new DukeException("No such task.");
            }
            Task t = arr.get(number - 1);
            arr.remove(number - 1);
            counter--;
            System.out.println("Noted. I've removed this task: \n " + t + "\nNow you have "
                    + counter + " tasks in the list.");
        }
    }

    
    
    public static void main(String[] args) throws DukeException {
        System.out.println("Hello! I'm SmartBot\nWhat can I do for you?");
        ArrayList<Task> lst = new ArrayList<Task>();;
        Scanner sc = new Scanner(System.in);
        while(true) {
            String first = sc.nextLine();
            try {
                if (first.equals("bye")) {
                    byeMessage();
                    break;
                } else if (first.equals("list")) {
                    listMessage(lst);
                } else if (first.length() == 6 && first.substring(0, 4).equals("mark")) {
                    markMessage(first, lst);
                } else if (first.length() == 8 && first.substring(0, 6).equals("unmark")) {
                    unmarkMessage(first, lst);
                } else if (first.length() >= 4 && first.substring(0, 4).equals("todo")) {
                    todoMessage(first, lst);
                } else if (first.length() >= 8 && first.substring(0, 8).equals("deadline")) {
                    deadlineMessage(first, lst);
                } else if (first.length() >= 5 && first.substring(0, 5).equals("event")) {
                    eventMessage(first, lst);
                } else if (first.length() == 8 && first.substring(0, 6).equals("delete")) {
                    deleteMessage(first, lst);
                }
                else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException ex) {
                    System.out.println(ex);
            }
        }
    }
}
