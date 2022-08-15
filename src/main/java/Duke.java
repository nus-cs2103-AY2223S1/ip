import java.util.Scanner;

public class Duke {
    /** List of items stored by Duke. */
    private static final Task[] tasks = new Task[100];

    private static void greetUser() {
        String message = Duke.formatText("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(message);
    }

    private static void startService() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();

            // End service.
            if (userInput.equals("bye")) {
                System.out.println(Duke.formatText("Bye. Hope to see you again soon!"));
                break;
            }

            // List items in list.
            if (userInput.equals("list")) {
                StringBuilder string = new StringBuilder();
                for (int i = 0; i < Task.totalTasks; i++) {
                    int itemIndex = i + 1;

                    string.append(itemIndex).append(".").append(Duke.tasks[i]).append("\n");
                }

                System.out.println(Duke.formatText("Here are the tasks in your list\n" + string));
                continue;
            }

            // Mark item as done or undone.
            try {
                if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
                    String[] userInputWords = userInput.split(" ");
                    if (userInputWords.length != 2) {
                        throw new DukeException("Usage 'mark index'");
                    }
                    String action = userInputWords[0];
                    int index = Integer.parseInt(userInputWords[1]);

                    if (index < 1 || index > Task.totalTasks) {
                        throw new DukeException("Out of bounds index, choose valid index!");
                    }

                    if (action.equals("mark")) {
                        Duke.tasks[index - 1].markAsDone();
                        System.out.println(Duke.formatText("Nice! I've marked this task as done:\n" + Duke.tasks[index - 1]));
                    } else if (action.equals("unmark")) {
                        Duke.tasks[index - 1].unmark();
                        System.out.println(Duke.formatText("OK, I've marked this task as not done yet:\n" + Duke.tasks[index - 1]));
                    } else {
                        throw new DukeException("Sorry, I don't know what that means");
                    }

                    continue;
                }
            } catch (DukeException e) {
                System.out.println(e);
                continue;
            } catch (NumberFormatException e) {
                System.out.println("Index has to be an integer");
                continue;
            }

            // Add item to list.
            try {
                if (userInput.startsWith("todo")) {
                    if (userInput.trim().length() == 4) {
                        throw new DukeException("The description of a todo cannot by empty.");
                    }
                    Duke.tasks[Task.totalTasks++] = new ToDo(userInput.substring(5));
                } else if (userInput.startsWith("deadline")) {
                    if (userInput.length() <= 8) {
                        throw new DukeException("The description of a deadline cannot be empty");
                    }
                    String details = userInput.substring(9);

                    String[] detailsFragments = details.split(" /by");
                    if (detailsFragments.length != 2) {
                        throw new DukeException("Missing /by in usage, deadline cannot have an empty date");
                    }

                    Duke.tasks[Task.totalTasks++] = new Deadline(detailsFragments[0], detailsFragments[1]);
                } else if (userInput.startsWith("event")) {
                    if (userInput.length() <= 5) {
                        throw new DukeException("The description of an event cannot be empty");
                    }
                    String details = userInput.substring(6);

                    String[] detailsFragments = details.split(" /at");
                    if (detailsFragments.length != 2) {
                        throw new DukeException("Missing /at in usage, event date cannot be empty");
                    }

                    Duke.tasks[Task.totalTasks++] = new Event(detailsFragments[0], detailsFragments[1]);
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e);
                continue;
            }

            System.out.println(Duke.formatText("Got it. I've added this task:\n" + "  " +
                    Duke.tasks[Task.totalTasks - 1] + "\n" +
                    "Now you have " + Task.totalTasks + " tasks in the list."));
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
