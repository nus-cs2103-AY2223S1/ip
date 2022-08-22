import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Represents a robot that takes in tasks given by user through CLI, and other requests such as
 * listing all tasks, deleting tasks, adding tasks and marking tasks.
 *
 * @author Elgin
 */
public class Duke {
    /** All Tasks */
    private TaskList tasks;

    /** Storage for tasks. */
    private Storage storage;

    /**
     * Constructor for Duke.
     *
     * @param filePath Path to storage file from root folder (e.g. 'src/data/duke.txt').
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts serving the user in CLI.
     *
     */
    private void run() {
        System.out.println(Duke.formatText("Hello! I'm Duke\n" + "What can I do for you?"));

        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();

            // Service has ended
            if (userInput.equals("bye")) {
                System.out.println(Duke.formatText("Bye. Hope to see you again soon!"));
                break;
            }

            // List all the tasks.
            if (userInput.equals("list")) {
                this.tasks.listItems();
                continue;
            }

            // Mark item as done.
            if (userInput.startsWith("mark ")) {
                try {
                    this.tasks.markOrUnmark(userInput.substring(4), true);
                    continue;
                } catch (DukeException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }

            // Unmark an item (becomes not done).
            if (userInput.startsWith("unmark ")) {
                try {
                    this.tasks.markOrUnmark(userInput.substring(6), false);
                    continue;
                } catch (DukeException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }

            // Deletes a task from tasks.
            if (userInput.startsWith("delete ")) {
                try {
                    this.tasks.deleteItem(userInput.substring(6));
                    continue;
                } catch (DukeException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }

            // Add item to tasks.
            try {
                if (userInput.equals("todo") || userInput.equals("deadline") || userInput.equals("event")) {
                    throw new DukeException("The description of a " + userInput + " cannot be empty.");
                } else if (userInput.startsWith("todo ")) {
                    this.tasks.addToDo(userInput.substring(5));
                } else if (userInput.startsWith("deadline ")) {
                    this.tasks.addDeadline(userInput.substring(9));
                } else if (userInput.startsWith("event ")) {
                    this.tasks.addEvent(userInput.substring(6));
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Your date format has to be in the form 'yyyy-mm-dd'");
            }
        }

        this.storage.save(this.tasks);
    }

    /**
     * Styles a given text with indentation and wraps the text around horizontal lines.
     *
     * @param text String that needs to be styled.
     * @return Formatted String that has proper indentation and wrapped around horizontal lines.
     */
    protected static String formatText(String text) {
        final String horizontalLine = "\t---------------------------------------------------------------------\n";

        String[] lines = text.split("\\r?\\n");
        StringBuilder formattedText = new StringBuilder(horizontalLine);
        for (String line : lines) {
            formattedText.append("\t").append(line).append("\n");
        }

        return formattedText + horizontalLine;
    }

    /**
     * Runs when program is first executed.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("src/data/duke.txt").run();
    }
}
