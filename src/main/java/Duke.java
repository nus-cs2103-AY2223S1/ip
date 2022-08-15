import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /** List of items stored by Duke. */
    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Duke's startup message (When program is first booted).
     *
     */
    private static void greetUser() {
        String message = Duke.formatText("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(message);
    }

    /**
     * Duke prints all items in a list format that it stores.
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
     * Duke marks a task as done or unmarks a task (Depends on userInput),
     * and updates the array list of tasks.
     *
     * @param userInput The input given by user (In the format "mark 2" or "unmark 2").
     * @throws DukeException For unsupported userInput
     * @throws NumberFormatException When a user's input second argument cannot be casted into an integer.
     */
    private static void markOrUnmarkTask(String userInput) throws DukeException, NumberFormatException {
        String[] userInputWords = userInput.split(" ");
        if (userInputWords.length != 2) {
            throw new DukeException("Usage 'mark/unmark index'");
        }

        String action = userInputWords[0];
        int index = Integer.parseInt(userInputWords[1]);

        if (index < 1 || index > Duke.tasks.size()) {
            throw new DukeException("Out of bounds, choose a valid index!");
        }

        if (action.equals("mark")) {
            Duke.tasks.get(index - 1).markAsDone();
            System.out.println(Duke.formatText("Nice! I've marked this task as done:\n" + Duke.tasks.get(index - 1)));
        } else if (action.equals("unmark")) {
            Duke.tasks.get(index - 1).unmark();
            System.out.println(Duke.formatText("OK, I've marked this task as not done yet:\n" + Duke.tasks.get(index - 1)));
        } else {
            throw new DukeException("Sorry, I don't know what that means");
        }
    }

    /**
     * Duke creates one To Do task and adds it to the array list.
     *
     * @param userInput The input given by the user (Format: todo taskDescription).
     * @throws DukeException For unsupported userInput
     */
    private static void createToDoTask(String userInput) throws DukeException {
        if (userInput.trim().length() == 4) {
            throw new DukeException("The description of a todo cannot by empty.");
        }

        Duke.tasks.add(new ToDo(userInput.substring(5)));
    }

    /**
     * Duke creates one Deadline and adds it to the array list.
     *
     * @param userInput The input given by the user (Format: deadline buy milk /by June 6th).
     * @throws DukeException For unsupported userInput
     */
    private static void createDeadline(String userInput) throws DukeException {
        if (userInput.length() <= 8) {
            throw new DukeException("The description of a deadline cannot be empty");
        }
        String details = userInput.substring(9);

        String[] detailsFragments = details.split(" /by");
        if (detailsFragments.length != 2) {
            throw new DukeException("Missing /by in usage, deadline cannot have an empty date");
        }

        Duke.tasks.add(new Deadline(detailsFragments[0], detailsFragments[1]));
    }

    /**
     * Duke creates one Event and adds it to the array list.
     *
     * @param userInput The input given by the user (Format: event orbital /at June 10 2-4pm).
     * @throws DukeException For unsupported userInput
     */
    private static void createEvent(String userInput) throws DukeException {
        if (userInput.length() <= 5) {
            throw new DukeException("The description of an event cannot be empty");
        }
        String details = userInput.substring(6);

        String[] detailsFragments = details.split(" /at");
        if (detailsFragments.length != 2) {
            throw new DukeException("Missing /at in usage, event date cannot be empty");
        }

        Duke.tasks.add(new Event(detailsFragments[0], detailsFragments[1]));
    }

    /**
     * Duke deletes a task from the tasks array list.
     *
     * @param userInput The input given by the user (Format: delete 3).
     * @throws DukeException For unsupported userInput.
     */
    private static void deleteItem(String userInput) throws DukeException, NumberFormatException {
        String[] userInputWords = userInput.split(" ");
        if (userInputWords.length != 2) {
            throw new DukeException("Usage 'delete index'");
        }

        int deleteIndex = Integer.parseInt(userInputWords[1]);

        if (deleteIndex < 1 || deleteIndex > Duke.tasks.size()) {
            throw new DukeException("Invalid index, choose a valid item index!");
        }

        System.out.println(Duke.formatText("Noted. I've removed this task:\n  " + Duke.tasks.get(deleteIndex - 1) + "\n"
            + "Now you have " + (Duke.tasks.size() - 1) + " tasks in the list"));

        Duke.tasks.remove(deleteIndex - 1);
    }

    /**
     * Called to make Duke start servicing the user from CLI.
     *
     */
    private static void startService() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();

            // End service.
            if (userInput.equals("bye")) {
                System.out.println(Duke.formatText("Bye. Hope to see you again soon!"));
                break;
            }

            if (userInput.equals("list")) {
                Duke.listItems();
                continue;
            }

            // Mark item as done or undone.
            if (userInput.startsWith("mark ") || userInput.startsWith("unmark ")) {
                try {
                    Duke.markOrUnmarkTask(userInput);
                } catch (DukeException e) {
                    System.out.println(e);
                    continue;
                } catch (NumberFormatException e) {
                    System.out.println("Index has to be an integer");
                    continue;
                }

                continue;
            }

            // Delete task from the array list.
            if (userInput.startsWith("delete ")) {
                try {
                    Duke.deleteItem(userInput);
                    continue;
                } catch (DukeException e) {
                    System.out.println(e);
                    continue;
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }

            // Add item to list.
            try {
                if (userInput.startsWith("todo ")) {
                    Duke.createToDoTask(userInput);
                } else if (userInput.startsWith("deadline ")) {
                    Duke.createDeadline(userInput);
                } else if (userInput.startsWith("event ")) {
                    Duke.createEvent(userInput);
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e);
                continue;
            }

            System.out.println(Duke.formatText("Got it. I've added this task:\n" + "  " +
                    Duke.tasks.get(Duke.tasks.size() - 1) + "\n" +
                    "Now you have " + Duke.tasks.size() + " tasks in the list."));
        }
    }

    /**
     * Styles a given text with indentation and wraps the text around horizontal lines.
     *
     * @param text String that needs to be styled.
     * @return Formatted String that has proper indentation and wrapped around horizontal lines.
     */
    protected static String formatText(String text) {
        final String HORIZONTAL_LINE = "\t--------------------------------------------------\n";

        String[] lines = text.split("\\r?\\n");
        StringBuilder formattedText = new StringBuilder(HORIZONTAL_LINE);
        for (String line : lines) {
            formattedText.append("\t").append(line).append("\n");
        }

        return formattedText + HORIZONTAL_LINE;
    }

    public static void main(String[] args) {
        Duke.greetUser();
        Duke.startService();
    }
}
