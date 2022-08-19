import java.util.Scanner;
import java.util.ArrayList;

/**
 * The Duke class that instantiates instances of duke.
 *
 * Duke is a ChatBot that performs different actions
 * based on commands provided by user.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Duke {
    // Name of Bot
    private static final String BOT_NAME = "Duke";

    // Initialise variables
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Prints Greetings Message.
     */
    private void greetings() {
        System.out.printf("Hello! I'm the Magical ChatBot, %s!%n", BOT_NAME);
        System.out.println("What can I help you with today?\n");
    }

    /**
     * Prints Farewell Message.
     */
    private void farewell() {
        System.out.println("\n-----------------------------------------");
        System.out.println("Bye Bye! Hope to see you again soon!");
        System.out.println("-----------------------------------------\n");
    }

    /**
     * Prints exception message.
     *
     * @param e An exception.
     */
    private void printErr(Exception e) {
        System.out.println("\n-----------------------------------------");
        System.out.println(e.getMessage());
        System.out.println("-----------------------------------------\n");
    }

    /**
     * Prints tasks in List.
     */
    private void list() {
        System.out.println("\n-----------------------------------------");
        System.out.println("Here are the tasks in your list");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
        System.out.println("-----------------------------------------\n");
    }

    /**
     * Marks task as done.
     * If task exists at given index, mark task as done and print result.
     * Else, print error message.
     *
     * @param index index of task in list.
     */
    private void mark(int index) {
        try {
            if (0 <= index && index < tasks.size()) {
                Task task = tasks.get(index);
                task.markAsDone();

                System.out.println("\n-----------------------------------------");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("    " + task);
                System.out.println("-----------------------------------------\n");
            } else {
                throw new DukeOutOfBoundException();
            }
        } catch (DukeOutOfBoundException e) {
            printErr(e);
        }
    }

    /**
     * Unmarks task as not done.
     * If task exists at given index, unmark task and print result.
     * Else, print error message.
     *
     * @param index index of task in list.
     */
    private void unmark(int index) {
        try {
            if (0 <= index && index < tasks.size()) {
                Task task = tasks.get(index);
                task.markAsUndone();

                System.out.println("\n-----------------------------------------");
                System.out.println("Ok! I've marked this task as not done yet:");
                System.out.println("    " + task);
                System.out.println("-----------------------------------------\n");
            } else {
                throw new DukeOutOfBoundException();
            }
        } catch (DukeOutOfBoundException e) {
            printErr(e);
        }
    }

    /**
     * Delete task in list.
     * If task exists at given index, Delete task and print result.
     * Else, print error message.
     *
     * @param index index of task in list.
     */
    private void delete(int index) {
        try {
            if (0 <= index && index < tasks.size()) {
                Task task = tasks.get(index);
                System.out.println("\n-----------------------------------------");
                System.out.println("Noted. I've removed this task:");
                System.out.println("    " + task);
                tasks.remove(index);
                System.out.println("-----------------------------------------\n");
            } else {
                throw new DukeOutOfBoundException();
            }
        } catch (DukeOutOfBoundException e) {
            printErr(e);
        }
    }

    /**
     * Adds Todo to the list.
     * If description is valid, add Todo to list and print result.
     * Else, print error message.
     *
     * @param description Description of Todo task.
     */
    private void addTodo(String description) {
        try {
            if (description.isEmpty() || description.substring(1).isEmpty()) {
                throw new DukeEmptyToDoException();
            }

            ToDo todo = new ToDo(description.substring(1));
            tasks.add(todo);

            System.out.println("\n-----------------------------------------");
            System.out.println("Got it. I've added this task:");
            System.out.println(todo);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("-----------------------------------------\n");
        } catch (DukeEmptyToDoException e) {
            printErr(e);
        }
    }

    /**
     * Adds Deadline to the list.
     * If description is valid, add Deadline to list and print result.
     * Else, print error message.
     *
     * @param description Description of Deadline.
     */
    private void addDeadline(String description) {
        try {
            if (description.isEmpty() || description.substring(1).isEmpty()) {
                throw new DukeEmptyDeadlineException();
            }

            String[] details = description.substring(1).split(" /by ");
            Deadline deadline = new Deadline(details[0], details[1]);
            tasks.add(deadline);

            System.out.println("\n-----------------------------------------");
            System.out.println("Got it. I've added this task:");
            System.out.println(deadline);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("-----------------------------------------\n");
        } catch (DukeEmptyDeadlineException e) {
            printErr(e);
        }
    }

    /**
     * Adds Event to the list.
     * If description is valid, add Event to list and print result.
     * Else, print error message.
     *
     * @param description Description of Event.
     */
    private void addEvent(String description) {
        try {
            if (description.isEmpty() || description.substring(1).isEmpty()) {
                throw new DukeEmptyEventException();
            }

            String[] details = description.substring(1).split(" /at ");
            Event event = new Event(details[0], details[1]);
            tasks.add(event);

            System.out.println("\n-----------------------------------------");
            System.out.println("Got it. I've added this task:");
            System.out.println(event);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("-----------------------------------------\n");
        } catch (DukeEmptyEventException e) {
            printErr(e);
        }
    }

    /**
     * Returns the Duke ChatBot.
     *
     * @param args arguments (if any).
     */
    public static void main(String[] args) {
        // Initialise variables
        Duke duke = new Duke();

        duke.greetings();

        String command = duke.sc.next();
        String description = duke.sc.nextLine();

        while (!command.toLowerCase().equals("bye")) {
            try {
                if (command.toLowerCase().equals("list")) {
                    duke.list();
                } else if (command.toLowerCase().equals("mark")) {
                    int index = Integer.parseInt(description.substring(1)) - 1;
                    duke.mark(index);
                } else if (command.toLowerCase().equals("unmark")) {
                    int index = Integer.parseInt(description.substring(1)) - 1;
                    duke.unmark(index);
                } else if (command.toLowerCase().equals("delete")) {
                    int index = Integer.parseInt(description.substring(1)) - 1;
                    duke.delete(index);
                } else if (command.toLowerCase().equals("todo")) {
                    duke.addTodo(description);
                } else if (command.toLowerCase().equals("deadline")) {
                    duke.addDeadline(description);
                } else if (command.toLowerCase().equals("event")) {
                    duke.addEvent(description);
                } else {
                    throw new DukeInvalidCommandException();
                }
            } catch (DukeInvalidCommandException e) {
                duke.printErr(e);
            }

            command = duke.sc.next();
            description = duke.sc.nextLine();
        }

        duke.farewell();
    }
}