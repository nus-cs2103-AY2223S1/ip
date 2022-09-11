package duke;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.storage.Storage;
import duke.task.Task;

/**
 * Handles the interactions with the user.
 */
public class Ui {
    private String response;
    private Response currentResponse;

    public Ui() {
    }

    /**
     * Creates a welcome message for chatbot
     */
    public void showWelcome(Storage storage) {
        boolean isNewUser = storage.checkIfNewUser();
        Response response;
        if (isNewUser) {
            response = new Response(Constants.NEW_WELCOME_MESSAGE, false, false);
        } else {
            response = new Response(Constants.WELCOME_MESSAGE, false, false);
        }
        setResponse(response);
    }

    /**
     * Prints out message to user interface.
     * @param message text to be printed.
     */
    public void printMessage(String message) {
        assert (message != null) : "Null message";
        Response response = new Response(message, false, true);
        setResponse(response);
        System.out.println(message);
    }

    /**
     * Converts list containing tasks to string for printing.
     * @param list list of user's saved tasks.
     * @return current saved tasks in string form.
     */
    public String listToString(List<Task> list) {
        if (list.size() == 0) {
            return Constants.LIST_EMPTY_MESSAGE;
        }
        assert (list.size() >= 0) : "Negative list size" + list.size();
        StringBuilder output = new StringBuilder();
        output.append(Constants.LIST_MESSAGE);
        String listInString = IntStream.range(0, list.size())
                .mapToObj(i -> String.format("%d. %s", i + 1, list.get(i)))
                .collect(Collectors.joining("\n"));
        output.append(listInString);
        return output.toString();
    }

    /**
     * Converts list containing tasks with specific text to string for printing.
     * @param list list of user's saved tasks.
     * @param text keyword to find in task description.
     * @return matching tasks in string form.
     */
    public String matchingTasklistToString(List<Task> list, String text) {
        boolean foundMatchingTask = false;
        if (list.size() == 0) {
            response = Constants.LIST_EMPTY_MESSAGE;
            return response;
        }
        assert (list.size() >= 0) : "Negative list size" + list.size();
        StringBuilder output = new StringBuilder();
        output.append(Constants.MATCHING_TASK_MESSAGE + "\"" + text + "\":\n");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDescription().contains(text)) {
                output.append(i + 1).append(". ").append(list.get(i)).append("\n");
                foundMatchingTask = true;
            }
        }
        if (!foundMatchingTask) {
            response = Constants.NOMATCHING_TASK_MESSAGE + "\"" + text + "\" :(";
            return response;
        }
        response = output.toString();
        return response;
    }

    public Response getResponse() {
        return currentResponse;
    }
    public void setResponse(Response response) {
        this.currentResponse = response;
    }

    /**
     * Shows error message if there is an error in loading task list.
     */
    public void showLoadingError() {
        Response response = new Response(Constants.LOAD_TASK_ERROR_MESSAGE, false, true);
        setResponse(response);
        System.out.println(response);
    }

    /**
     * Prints out error messages to user interface.
     * @param message error text to be printed.
     */
    public void showError(String message) {
        assert (message != null) : "Null error message";
        Response response = new Response(message, false, true);
        setResponse(response);
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

        String[] allCommands = {"list", "bye", "todo", "deadline", "event", "mark", "unmark", "delete", "find", "help"};
        boolean isInvalidCommand = !Arrays.asList(allCommands).contains(mainCommand);
        if (isInvalidCommand) {
            throw new DukeException(mainCommand + Constants.INVALID_COMMAND_MESSAGE);
        }

        //Check if commands that require description or date are valid
        String[] commandsWithDescription = {"todo", "deadline", "event", "find"};
        boolean commandNeedsDescription = Arrays.asList(commandsWithDescription).contains(mainCommand);
        if (commandNeedsDescription) {
            boolean isMissingDescription = commandSegments.length <= 1;
            if (isMissingDescription) {
                throw new DukeException(Constants.MISSING_DESCRIPTION_MESSAGE);
            }

            switch (mainCommand) {
            case "todo":
            case "find":
                break;
            case "deadline":
                String[] deadlineSegments = commandSegments[1].split("/by", 2);

                //Handles missing date in input
                if (deadlineSegments.length < 2) {
                    throw new DukeException(Constants.MISSING_DATE_MESSAGE);
                }

                //Handles invalid date format in input
                String by = deadlineSegments[1].trim();
                try {
                    LocalDate date = LocalDate.parse(by);
                } catch (Exception ex) {
                    throw new DukeException(Constants.INVALID_DATE_MESSAGE);
                }
                break;
            case "event":
                String[] eventSegments = commandSegments[1].split("/at", 2);
                if (eventSegments.length < 2) {
                    throw new DukeException(Constants.MISSING_DATE_MESSAGE);
                }
                break;
            default:
                throw new DukeException(mainCommand + Constants.INVALID_COMMAND_MESSAGE);
            }
        }
    }
}
