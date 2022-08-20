package duke;

/**
 * Represents a parser that parse user command.
 */
public class Parser {

    public enum userCommand {
        BYE, LIST, UNMARK, MARK, TODO, DEADLINE, EVENT, DELETE
    }

    /**
     * Handles user's input into program.
     *
     * @param input User input into program.
     */
    public static Command parse(String input) throws DukeException {
        String[] inputList = input.split(" ");
        Task newListItem;
        switch (userInputToCommand(inputList[0])) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case UNMARK:
            return new UnmarkCommand(getIntegerInUserInput(inputList));
        case MARK:
            return new MarkCommand(getIntegerInUserInput(inputList));
        case TODO:
            newListItem = new ToDo(getToDoDescription(inputList, input));
            return new AddCommand(newListItem);
        case DEADLINE:
            newListItem = new Deadline(getDeadlineDescription(inputList, input),
                    getDeadlineBy(inputList, input));
            return new AddCommand(newListItem);
        case EVENT:
            newListItem = new Event(getEventDescription(inputList, input),
                    getEventAt(inputList, input));
            return new AddCommand(newListItem);
        case DELETE:
            return new DeleteCommand(getIntegerInUserInput(inputList));
        }
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Gets the todo description from user input.
     *
     * @param inputList user input after spliting by " ".
     * @param input user input.
     */
    public static String getToDoDescription(String[] inputList, String input) {
        if (inputList.length >= 2) {
            return input.split(" ", 2)[1];
        }
        return " ";
    }

    /**
     * Gets the Deadline's description from user input.
     *
     * @param inputList user input after spliting by " ".
     * @param input user input.
     */
    public static String getDeadlineDescription(String[] inputList, String input) {
        if (inputList.length >= 2) {
            String[] descriptionWithBy = input.split(" ", 2);
            return descriptionWithBy[1].split(" /by ", 2)[0];
        }
        return " ";
    }

    /**
     * Gets the Deadline's by from user input.
     *
     * @param inputList user input after spliting by " ".
     * @param input user input.
     */
    public static String getDeadlineBy(String[] inputList, String input) {
        if (inputList.length > 2) {
            String[] descriptionWithBy = input.split(" ", 2);
            return descriptionWithBy[1].split(" /by ", 2)[1];
        }
        return " " ;
    }

    /**
     * Gets the Event's description from user input.
     *
     * @param inputList user input after spliting by " ".
     * @param input user input.
     */
    public static String getEventDescription(String[] inputList, String input) {
        if (inputList.length >= 2) {
            String[] descriptionWithAt = input.split(" ", 2);
            return descriptionWithAt[1].split(" /at ", 2)[0];
        }
        return " ";

    }

    /**
     * Gets the Event's at from user input.
     *
     * @param inputList user input after spliting by " ".
     * @param input user input.
     */
    public static String getEventAt(String[] inputList, String input) {
        if (inputList.length > 2) {
            String[] descriptionWithAt = input.split(" ", 2);
            return descriptionWithAt[1].split(" /at ", 2)[1];
        }
        return " ";
    }

    /**
     * Gets the user integer input from user string input.
     *
     * @param inputList user input after spliting by " ".
     * @throws DukeException if input list length > 2 or input list length < 2.
     */
    public static int getIntegerInUserInput(String[] inputList) throws DukeException{
        if (inputList.length > 2) {
            throw new DukeException("Please provide only 1 task number!");
        } else if (inputList.length < 2) {
            throw new DukeException("Please provide a task number!");
        }
        try {
            return Integer.parseInt(inputList[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide an actual number!");
        }
    }

    /**
     * Converts string user input command into enum command to be used in switch.
     *
     * @param userInputCommand string user input command.
     * @throws DukeException if user input command is not any valid command.
     */
    public static userCommand userInputToCommand(String userInputCommand) throws DukeException {
        try {
            return userCommand.valueOf(userInputCommand.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}
