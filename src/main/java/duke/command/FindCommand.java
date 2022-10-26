package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.DukeTask;
import duke.tasklist.TaskList;

/**
 * Encapsulates searching for keywords in a TaskList.
 *
 * @author Kartikeya
 */
public class FindCommand implements Command {
    private final String[] input;

    /**
     * Constructor for a FindCommand object.
     *
     * @param input split strings input by user
     */
    public FindCommand(String[] input) {
        this.input = input;
    }

    /**
     * {@inheritDoc}
     * Finds tasks matching input string, and shows output to user.
     */
    @Override
    public String execute(TaskList itemList, Storage storage) throws DukeException {
        return executeFindItems(input, itemList);
    }

    /**
     * Executes the find method on the given TaskList, and then converts the output to a
     * string suitable for the user interface before returning it.
     *
     * @param input array of strings input by user
     * @return the string indicating a list of tasks whose descriptions match the input
     * @throws DukeException if no items were found
     */
    private String executeFindItems(String[] input, TaskList itemList) throws DukeException {
        String parsedInput = joinInput(input);
        ArrayList<DukeTask> foundItems = itemList.find(parsedInput);
        if (foundItems.size() == 0) {
            throw new DukeException("No items were found.");
        }
        String parsedOutput = joinOutput(foundItems);
        return parsedOutput;
    }

    /**
     * Converts input array of strings into a singular string.
     *
     * @param input array of strings input by user
     * @return the string that represents user input
     */
    private String joinInput(String[] input) {
        StringBuilder parsedInput = new StringBuilder();
        for (int i = 1; i < input.length; i++) {
            parsedInput.append(input[i]);
            if (i + 1 != input.length) {
                parsedInput.append(" ");
            }
        }
        return parsedInput.toString();
    }

    /**
     * Converts output ArrayList of DukeTasks into a string representation.
     *
     * @param foundItems list of items whose descriptions match input
     * @return the string that represents the output
     */
    private String joinOutput(ArrayList<DukeTask> foundItems) {
        StringBuilder parsedOutput =
                new StringBuilder("Here are the matching tasks in your list: \n");
        for (int i = 0; i < foundItems.size(); i++) {
            if (i != 0) {
                parsedOutput.append("\n");
            }
            parsedOutput.append("      ").append(foundItems.get(i).toString());
        }
        return parsedOutput.toString();
    }
}
