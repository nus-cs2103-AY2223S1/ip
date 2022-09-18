package duke;

import java.lang.reflect.InvocationTargetException;

/**
 * Represents a Parser to parse the input and return an Object corresponding to it.
 */
public class Parser {
    /**
     * Returns a Task which results from parsing the data input from Storage.
     *
     * @param input Data input from Storage.
     * @return Task corresponding to the data input from Storage.
     */
    public static Task parseStorageTask(String input) throws DukeException {
        String[] inputParts = input.split(", ");
        String type = inputParts[0];
        boolean isDone = inputParts[1] == "X";
        String description = inputParts[2];
        switch (type) {
        case "T":
            return new ToDo(description, isDone);
        case "D":
            return new Deadline(description, isDone, inputParts[3]);
        case "E":
            return new Event(description, isDone, inputParts[3]);
        default:
            return new Task(""); // error
        }
    }

    /**
     * Returns a Command which results from parsing the user input.
     *
     * @param input Text input from user.
     * @return Command corresponding to the user input.
     */
    public static Command parseInput(String input) throws DukeException {
        try {
            assert input != null;
            String[] inputParts;
            inputParts = splitFirst(input, " ");
            assert inputParts.length <= 2;
            switch (inputParts[0]) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "free":
                return new FreeCommand();
            case "mark":
                return new MarkCommand(Integer.parseInt(inputParts[1]) - 1, true);
            case "unmark":
                return new MarkCommand(Integer.parseInt(inputParts[1]) - 1, false);
            case "find":
                return new FindCommand(inputParts[1]);
            case "todo":
                return new AddCommand('T', inputParts[1]);
            case "deadline":
                inputParts = splitFirst(inputParts[1], " /by ");
                return new AddCommand('D', inputParts[0], inputParts[1]);
            case "event":
                inputParts = splitFirst(inputParts[1], " /at ");
                return new AddCommand('E', inputParts[0], inputParts[1]);
            case "delete":
                return new DeleteCommand(Integer.parseInt(inputParts[1]) - 1);
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Incorrect syntax!");
        }
    }

    /**
     * Returns a String array with at most 2 elements, which results from splitting the String once with the substring (regex).
     *
     * @param string Original String.
     * @param regex Substring used to split the String.
     * @return String array with at most 2 elements, which results from splitting the String once with the substring.
     */
    public static String[] splitFirst(String string, String regex) {
        return string.split(regex, 2);
    }
}
