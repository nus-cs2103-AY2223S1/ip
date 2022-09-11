package ado;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ado.storage.Storage;
import ado.task.Task;

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
     * Prints out message to from user to CLI.
     * @param message text to be printed.
     */
    public void printMessage(String message) {
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
}

