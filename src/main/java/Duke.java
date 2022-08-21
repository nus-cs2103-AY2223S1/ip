import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

public class Duke {
    /** List of items. */
    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Startup message (When program is first booted).
     *
     */
    private static void greetUser() {
        String message = Duke.formatText("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(message);
    }

    /**
     * Prints all items in a list format that is stored.
     *
     */
    private static void listItems() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < Duke.tasks.size(); i++) {
            int itemIndex = i + 1;

            string.append(itemIndex).append(".").append(Duke.tasks.get(i)).append("\n");
        }

        System.out.println(Duke.formatText("Here are the tasks in your list\n" + string));
    }

    /**
     * Marks a Task as done, or unmarks a task.
     *
     * @param userInput The index of task to be marked as done, preceded by an empty space.
     * @param isMarkDone If true, mark task as done, else, unmark task.
     * @throws DukeException If index is not given, or index <= 1 or index >= tasks.size().
     * @throws NumberFormatException If index given by user cannot be casted into an integer.
     */
    private static void markTask(String userInput, boolean isMarkDone) throws DukeException, NumberFormatException {
        if (userInput.trim().length() == 0) {
            throw new DukeException("Index of mark cannot be empty!");
        }

        int index = Integer.parseInt(userInput.trim());

        if (index < 1 || index > Duke.tasks.size()) {
            throw new DukeException("Invalid index, please provide a valid input");
        }

        if (!isMarkDone) {
            Duke.tasks.get(index - 1).unmark();
            System.out.println(Duke.formatText("OK, I've marked this task as not done yet:\n" +
                    Duke.tasks.get(index - 1)));
        } else {
            Duke.tasks.get(index - 1).markAsDone();
            System.out.println(Duke.formatText("Nice! I've marked this task as done:\n" + Duke.tasks.get(index - 1)));
        }
    }

    /**
     * Creates one To Do task and adds it to the array list.
     *
     * @param description The task description.
     */
    private static void createToDoTask(String description) {
        Duke.tasks.add(new ToDo(description));

        System.out.println(Duke.formatText("Got it. I've added this task:\n" + "  " +
                Duke.tasks.get(Duke.tasks.size() - 1) + "\n" +
                "Now you have " + Duke.tasks.size() + " tasks in the list."));
    }

    /**
     * Creates one Deadline and adds it to the array list.
     *
     * @param userInput The description of the task, and deadline.
     * @throws DukeException If userInput is not in the form "description /by deadline".
     */
    private static void createDeadline(String userInput) throws DukeException {
        String[] detailsFragments = userInput.split(" /by");
        if (detailsFragments.length != 2) {
            throw new DukeException("Usage description /by deadline");
        }

        Duke.tasks.add(new Deadline(detailsFragments[0], detailsFragments[1]));

        System.out.println(Duke.formatText("Got it. I've added this task:\n" + "  " +
                Duke.tasks.get(Duke.tasks.size() - 1) + "\n" +
                "Now you have " + Duke.tasks.size() + " tasks in the list."));
    }

    /**
     * Creates one Event and adds it to the array list.
     *
     * @param userInput The description of the task, and event time.
     * @throws DukeException If userInput is not in the form "description /at time".
     */
    private static void createEvent(String userInput) throws DukeException {
        String[] detailsFragments = userInput.split(" /at");
        if (detailsFragments.length != 2) {
            throw new DukeException("Usage description /at time");
        }

        Duke.tasks.add(new Event(detailsFragments[0], detailsFragments[1]));

        System.out.println(Duke.formatText("Got it. I've added this task:\n" + "  " +
                Duke.tasks.get(Duke.tasks.size() - 1) + "\n" +
                "Now you have " + Duke.tasks.size() + " tasks in the list."));
    }

    /**
     * Deletes a task from the tasks array list.
     *
     * @param userInput The index of item to delete, preceded by an empty space.
     * @throws DukeException If index is empty or out of bounds from the array list.
     * @throws NumberFormatException If index cannot be casted into an integer.
     */
    private static void deleteItem(String userInput) throws DukeException, NumberFormatException {
        String index = userInput.trim();
        if (index.length() == 0) {
            throw new DukeException("Index cannot be empty!");
        }

        int deleteIndex = Integer.parseInt(index);

        if (deleteIndex < 1 || deleteIndex > Duke.tasks.size()) {
            throw new DukeException("Invalid index, choose a valid item index!");
        }

        System.out.println(Duke.formatText("Noted. I've removed this task:\n  " +
                Duke.tasks.get(deleteIndex - 1) + "\n" +
                "Now you have " + (Duke.tasks.size() - 1) + " tasks in the list"));

        Duke.tasks.remove(deleteIndex - 1);
    }

    /**
     * Preloads tasks from data/duke.txt file.
     *
     * @throws FileNotFoundException If file cannot be opened by Scanner.
     */
    private static void preloadTasks() throws FileNotFoundException {
        String rootDir = System.getProperty("user.dir");
        Path filePath = Paths.get(rootDir, "src", "data", "duke.txt");
        File file = new File(filePath.toString());

        if (file.exists() && !file.isDirectory()) {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] details = line.split(" \\| ");

                // Loads tasks into array list.
                try {
                    if (details[0].equals("T")) {
                        Duke.tasks.add(new ToDo(details[2], details[1].equals("1")));
                    } else if (details[0].equals("D")) {
                        Duke.tasks.add(new Deadline(details[2], details[3], details[1].equals("1")));
                    } else if (details[0].equals("E")) {
                        Duke.tasks.add(new Event(details[2], details[3], details[1].equals("1")));
                    } else {
                        throw new DukeException("File contains lines that cannot be validated as a Task.");
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Starts serving the user in CLI.
     *
     */
    private static void startService() {
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
                Duke.listItems();
                continue;
            }

            // Mark item as done.
            if (userInput.startsWith("mark ")) {
                try {
                    Duke.markTask(userInput.substring(4), true);
                    continue;
                } catch (DukeException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }

            // Unmark an item (becomes not done).
            if (userInput.startsWith("unmark ")) {
                try {
                    Duke.markTask(userInput.substring(6), false);
                    continue;
                } catch (DukeException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }

            // Deletes a task from tasks.
            if (userInput.startsWith("delete ")) {
                try {
                    Duke.deleteItem(userInput.substring(6));
                    continue;
                } catch (DukeException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }

            // Add item to tasks.
            try {
                if (userInput.trim().equals("todo") || userInput.trim().equals("deadline") ||
                        userInput.trim().equals("event")) {
                    throw new DukeException("The description of a " + userInput + " cannot be empty.");
                } else if (userInput.startsWith("todo ")) {
                    Duke.createToDoTask(userInput.substring(5));
                } else if (userInput.startsWith("deadline ")) {
                    Duke.createDeadline(userInput.substring(9));
                } else if (userInput.startsWith("event ")) {
                    Duke.createEvent(userInput.substring(6));
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Styles a given text with indentation and wraps the text around horizontal lines.
     *
     * @param text String that needs to be styled.
     * @return Formatted String that has proper indentation and wrapped around horizontal lines.
     */
    protected static String formatText(String text) {
        final String HORIZONTAL_LINE = "\t---------------------------------------------------------------------\n";

        String[] lines = text.split("\\r?\\n");
        StringBuilder formattedText = new StringBuilder(HORIZONTAL_LINE);
        for (String line : lines) {
            formattedText.append("\t").append(line).append("\n");
        }

        return formattedText + HORIZONTAL_LINE;
    }

    public static void main(String[] args) {
        Duke.greetUser();

        try {
            Duke.preloadTasks();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        Duke.startService();
    }
}
