import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    private static final Scanner sc = new Scanner(System.in);

    // Initialising todoList to hold Tasks
    private static final ArrayList<Task> todoList = new ArrayList<>();

    public static void main(String[] args) {

        // Welcome message
        String logo = " ____        _        \n" +
                      "|  _ \\ _   _| | _____ \n" +
                      "| | | | | | | |/ / _ \\\n" +
                      "| |_| | |_| |   <  __/\n" +
                      "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
        String input = sc.nextLine();

        while (!input.equals("bye")) {

            System.out.println("    ____________________________________________________________");

            // Fetch and display todoList
            if (input.equals("list")) {
                if (todoList.size() == 0) System.out.println(" ☹ OOPS!!! Seems like your list is empty.");
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.println("     " + (i + 1) + ". " + todoList.get(i).toString());
                }
                System.out.println("    ____________________________________________________________\n");
                input = sc.nextLine();
            }

            // Mark a task
            if (input.startsWith("mark")) {
                try {
                    if (!checkValid(input)) throw new DukeException(" ☹ OOPS!!! Please enter task to mark.");
                    int taskID = Integer.parseInt(input.substring(5)) - 1;
                    todoList.get(taskID).mark();
                }  catch (DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    input = sc.nextLine();
                }
            }

            // Unmark a task
            if (input.startsWith("unmark")) {
                try {
                    if (!checkValid(input)) throw new DukeException(" ☹ OOPS!!! Please enter task to unmark.");
                    int taskID = Integer.parseInt(input.substring(7)) - 1;
                    todoList.get(taskID).unmark();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    input = sc.nextLine();
                }
            }

            // Delete a task
            if (input.startsWith("delete")) {
                try {
                    if (!checkValid(input)) throw new DukeException(" ☹ OOPS!!! Please enter task to delete.");
                    int taskID = Integer.parseInt(input.substring(7)) - 1;
                    delete(taskID);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    input = sc.nextLine();
                }
            }

            // Add todo to todoList
            if (input.startsWith("todo")) {
                try {
                    if (!checkValid(input))
                        throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                    Todo todo = new Todo(input);
                    addTask(todo);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    input = sc.nextLine();
                }
            }

            // Add deadline to todoList
            if (input.startsWith("deadline")) {
                try {
                    if (!checkValid(input))
                        throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                    Deadline deadline = new Deadline(input);
                    addTask(deadline);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    input = sc.nextLine();
                }
            }

            // Add event to todoList
            if (input.startsWith("event")) {
                try {
                    if (!checkValid(input))
                        throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                    Event event = new Event(input);
                    addTask(event);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    input = sc.nextLine();
                }
            }

            else {
                try {
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    input = sc.nextLine();
                }
            }
        }

        // Goodbye message
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Adds task to todoList.
     *
     * @param task the task to add to todoList
     */
    public static void addTask(Task task) {
        todoList.add(task);
        System.out.println("    " + " Got it. I've added this task: ");
        System.out.println("       " + task.toString());
        System.out.println("    " + " Now you have " + todoList.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Deletes task from todoList.
     *
     * @param index index of the task to delete
     */
    public static void delete(int index) {
        Task task = todoList.get(index);
        todoList.remove(index);
        System.out.println("    " + " Got it. I've removed this task: ");
        System.out.println("       " + task.toString());
        System.out.println("    " + " Now you have " + todoList.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Checks if the input is in a valid format.
     *
     * @param input the input string to be checked
     *
     * @return True if the format is valid and False otherwise
     */
    public static boolean checkValid(String input) {
        String[] str = input.split(" ");
        return (str.length != 1) &&  (str.length != 0);
    }
}
