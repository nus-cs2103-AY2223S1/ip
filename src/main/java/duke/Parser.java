package duke;

/**
 * A class that parse the user input into the respective commands.
 */
public class Parser {

    /**
     * Returns the type of command that is given by the user.
     * <p>
     * The function reads the first word of the input and will return the respective command types.
     *
     * @param userText the text inputted by the user
     * @return the type of command given by the user
     */
    public static CommandType parse(String userText) {
        String commandWord = userText.split(" ", 2)[0];

        if (commandWord.equals("bye")) {
            return CommandType.BYE;
        } else if (commandWord.equals("list")) {
            return CommandType.LIST;
        } else if (commandWord.equals("mark")) {
            return CommandType.MARK;
        } else if (commandWord.equals("unmark")) {
            return CommandType.UNMARK;
        } else if (commandWord.equals("todo")) {
            return CommandType.TODO;
        } else if (commandWord.equals("deadline")) {
            return CommandType.DEADLINE;
        } else if (commandWord.equals("event")) {
            return CommandType.EVENT;
        } else if (commandWord.equals("delete")) {
            return CommandType.DELETE;
        } else if (commandWord.equals("find")) {
            return CommandType.FIND;
        } else {
            return CommandType.UNABLE;
        }
    }
}
