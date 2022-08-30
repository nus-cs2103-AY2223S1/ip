package duke;

import duke.exception.DukeEmptyException;

public class Parser {
    
    private String[] formattedInput;

    /**
     * Creates a Parser object.
     */
    public Parser() {
    }

    /**
     * Parses a given input into 2 parts, mainly the keyword and the task description.
     *
     * @param input The full input from the user
     * @return The given input split into keyword at index 0 and the task at index 1
     */
    protected String[] parseInput(String input)  {
        formattedInput = input.split(" ", 2);
        return formattedInput;
    }

    /**
     * Retrieves the keyword given the user input.
     *
     * @param input The full input from the user
     * @return The keyword
     */
    protected String getKeyword(String input) {
        parseInput(input);
        return formattedInput[0];
    }

    /**
     * Returns the number that is after the keyword in the user input.
     *
     * @param input The task description that is retrieve from the user input
     * @return The index of the task that is required
     */
    
    protected int getTaskIndex(String[] input) {
        return Integer.parseInt(input[1]) - 1;
    }
    
    protected void checkArg(String[] input) throws DukeEmptyException {
        if (input.length < 2) {
            throw new DukeEmptyException(input[0]);
        }
    }
}
