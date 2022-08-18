import java.util.Scanner;
import java.util.ArrayList;

/**
 * The Duke class that instantiates instances of duke
 *
 * Duke is a chatbot that performs different actions
 * based on commands provided by user
 */
public class Duke {
    // Name of Bot
    private static final String BOT_NAME = "Duke";

    // Initialise variables
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Greeting Method for ChatBot.
     *
     * Prints Greetings Message.
     */
    private void greetings() {
        System.out.printf("Hello! I'm the Magical ChatBot, %s!%n", BOT_NAME);
        System.out.println("What can I help you with today?\n");
    }

    /**
     * Greeting Method for ChatBot.
     *
     * Prints Farewell Message.
     */
    private void farewell() {
        System.out.println("\n-----------------------------------------");
        System.out.println("Bye Bye! Hope to see you again soon!");
        System.out.println("-----------------------------------------\n");
    }

    /**
     * Method to print an exception
     *
     * @param e An exception
     */
    private void printErr(Exception e) {
        System.out.println("\n-----------------------------------------");
        System.out.println(e.getMessage());
        System.out.println("-----------------------------------------\n");
    }

    /**
     * List method for ChatBot
     * List and prints out tasks stored in duke
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
     * Mark method for ChatBot
     * Marks task as done and prints it if it exist
     *
     * @param index index of task in list of tasks
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
            } else throw new DukeOutOfBoundException();
        } catch (DukeOutOfBoundException e) {
            printErr(e);
        }
    }

    /**
     * Unmark method for ChatBot
     * Unmarks task as done and prints it if it exist
     *
     * @param index index of task in list of tasks
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
            } else throw new DukeOutOfBoundException();
        } catch (DukeOutOfBoundException e) {
            printErr(e);
        }
    }

    /**
     * Delete method for ChatBot
     * Delete the task at given index and print
     *
     * @param index index of task in list of tasks
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
            } else throw new DukeOutOfBoundException();
        } catch (DukeOutOfBoundException e) {
            printErr(e);
        }
    }

    /**
     * Todo method for ChatBot
     * Adds a Todo Task to the list of tasks and print
     *
     * @param description todo details
     */
    private void addTodo(String description) {
        try {
            if (description.isEmpty() || description.substring(1).isEmpty()) throw new DukeEmptyToDoException();
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
     * Deadline method for ChatBot
     * Adds a Deadline Task to the list of tasks
     *
     * @param description deadline details
     */
    private void addDeadline(String description) {
        try {
            if (description.isEmpty() || description.substring(1).isEmpty()) throw new DukeEmptyDeadlineException();
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
     * Event method for ChatBot
     * Adds a Event Task to the list of tasks
     *
     * @param description Event details
     */
    private void addEvent(String description) {
        try {
            if (description.isEmpty() || description.substring(1).isEmpty()) throw new DukeEmptyEventException();
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
     * The main Method that runs the Duke programme
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
                } else throw new DukeInvalidCommandException();
            } catch (DukeInvalidCommandException e) {
                duke.printErr(e);
            }
            command = duke.sc.next();
            description = duke.sc.nextLine();
        }

        duke.farewell();
    }
}