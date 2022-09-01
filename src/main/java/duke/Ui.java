package duke;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import duke.task.Task;

/**
 * Handles the interactions with the user.
 */
public class Ui {
    static final String CHATBOX_NAME = "Ado";
    static final String PARTITION = "<><><><><><><><><><><><><><><><><><><><><><><><><><><><>";
    private String response;

    public Ui() {
    }

    /**
     * Creates a welcome message for chatbot
     */
    public void showWelcome() {
        response = "Yo! I'm Ado, what can I do for you?";
        //printMessage(startMessage);
    }

    String readCommand() throws DukeException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        validate(input.toLowerCase());
        return input;
    }

    /**
     * Prints out partition line to user interface.
     */
    public void showLine() {
        System.out.println(PARTITION);
    }

    /**
     * Prints out message to user interface.
     * @param message text to be printed.
     */
    public void printMessage(String message) {
        response = message;
        System.out.println(response);
    }

    /**
     * Converts list containing tasks to string for printing.
     * @param list list of user's saved tasks.
     * @return current saved tasks in string form.
     */
    public String listToString(List<Task> list) {
        if (list.size() == 0) {
            return "List is empty ~\n";
        }
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list: \n");
        for (int i = 0; i < list.size(); i++) {
            output.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        response = output.toString();
        return response;
    }

    /**
     * Converts list containing tasks with specific text to string for printing.
     * @param list list of user's saved tasks.
     * @param text keyword to find in task description.
     * @return matching tasks in string form.
     */
    public String listToStringWithText(List<Task> list, String text) {
        boolean foundMatchingTask = false;
        if (list.size() == 0) {
            response = "List is empty ~\n";
            return response;
        }
        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks containing \"" + text + "\":\n");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDescription().contains(text)) {
                output.append(i + 1).append(". ").append(list.get(i)).append("\n");
                foundMatchingTask = true;
            }
        }
        if (!foundMatchingTask) {
            response = "No matching tasks with \"" + text + "\" :(";
            return response;
        }
        response = output.toString();
        return response;
    }

    public String getResponse() {
        return response;
    }

    /**
     * Shows error message if there is an error in loading task list.
     */
    public void showLoadingError() {
        response = "Error in loading task :( New task list created!";
        System.out.println("Error in loading task :( New task list created!");
    }

    /**
     * Prints out error messages to user interface.
     * @param message error text to be printed.
     */
    public void showError(String message) {
        response = message;
        System.out.println(message);
    }

    /**
     * Checks if user's input is valid for execution.
     * @param input user's input.
     * @throws DukeException If user's input does not meet the task detail requirements.
     */
    void validate(String input) throws DukeException {
        String[] commandSegments = input.split(" ", 2);
        String mainCommand = commandSegments[0].toLowerCase().trim();

        String[] allCommands = {"list", "bye", "todo", "deadline", "event", "mark", "unmark", "delete", "find"};
        if (!Arrays.asList(allCommands).contains(mainCommand)) {
            //handles invalid commands
            throw new DukeException(mainCommand + "? I don't know what that means\n");
        }
        String[] commandsWithDescription = {"todo", "deadline", "event", "find"};
        if (Arrays.asList(commandsWithDescription).contains(mainCommand)) {

            if (commandSegments.length <= 1) {
                throw new DukeException("The description of a " + mainCommand + " cannot be empty.\n");
            }

            switch (mainCommand) {
            case "todo":
            case "find":
                break;
            case "deadline":
                String[] deadlineSegments = commandSegments[1].split("/by", 2);
                if (deadlineSegments.length < 2) {
                    throw new DukeException("The date of deadline cannot be empty.\n");
                }
                String description = deadlineSegments[0];
                String by = deadlineSegments[1].trim();
                try {
                    LocalDate date = LocalDate.parse(by);
                } catch (Exception ex) {
                    throw new DukeException("Put date after /by in terms of yyyy-MM-dd");
                }
                break;
            case "event":
                String[] eventSegments = commandSegments[1].split("/at", 2);
                if (eventSegments.length < 2) {
                    throw new DukeException("The date of event cannot be empty.\n");
                }
                break;
            default:
                throw new DukeException(mainCommand + "? I don't know what that means\n");
            }
        }
    }
}
