package duke;

import java.time.LocalDate;

/**
 * Accepts String arguments to convert them to their corresponding class types for the operation of Duke
 */
public class Parser {
    private final static String EXIT = "bye";

    enum Keyword {
        bye, list, mark, unmark, todo, deadline, event, delete, find
    }

    enum TaskKeyword {
        todo, deadline, event
    }

    enum ModifyKeyword {
        mark, unmark, delete
    }

    enum AccessKeyword {
        find, list
    }

    /**
     * Reads a String that describes a command to create a Command object accordingly after checking for validity.
     * @param commandText String description of a Command.
     * @return A Command object based on the description.
     * @throws DukeException if String description is not a valid command
     */
    public static Command parse(String commandText) throws DukeException {
        String keyword = getCommandKey(commandText);
        String content = getCommandContent(commandText);
        validateKeyword(keyword);
        return new Command(keyword, content);
    }

    private static String getCommandKey(String command) {
        int index = command.indexOf(' ');
        if (index == -1) {
            return command;
        }
        return command.substring(0, index);
    }

    private static String getCommandContent(String command) {
        int index = command.indexOf(' ');
        if (index == -1) {
            return "";
        }
        return command.substring(index).trim();
    }

    /**
     * Check whether a given Command instance is consists of the keyword bye (non case-sensitive).
     * @return true if the command instance is a bye command, false otherwise.
     */
    protected static boolean isByeCommand(String command){
        return command.substring(0, 3).equalsIgnoreCase(EXIT);
    }

    /**
     * Extracts out the date embedded within a given String description of a Task where applicable.
     * @param desc String description of a Task object.
     * @return LocalDate object corresponding to a Task where available, else LocalDate object
     * corresponding to Date of initialisation.
     */
    protected static LocalDate formatDate(String desc) {
        int index = desc.indexOf('/');
        if (index > 0) {
            int year = Integer.parseInt(desc.substring(index + 1, index + 5));
            int month = Integer.parseInt(desc.substring(index + 5, index + 7));
            int date = Integer.parseInt(desc.substring(index + 7, index + 9));
            return LocalDate.of(year, month, date);
        }
        return LocalDate.now();
    }

    private static boolean isKeyword(String keyword) {
        for (Keyword k : Keyword.values()) {
            if (k.name().equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Perform a Boolean check on whether a given String is a Task command.
     * @param keyword Keyword of a command.
     * @return true if given keyword is a Task keyword, else false.
     */
    protected static boolean isTaskKeyword(String keyword) {
        for (TaskKeyword tk : TaskKeyword.values()) {
            if (tk.name().equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Perform a Boolean check on whether a given String is a Modify command.
     * @param keyword Keyword of a command.
     * @return true if given keyword is a Modify keyword, else false.
     */
    protected static boolean isModifyKeyword(String keyword) {
        for (ModifyKeyword mk : ModifyKeyword.values()) {
            if (mk.name().equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Perform a Boolean check on whether a given String is a Modify command.
     * @param keyword Keyword of a command.
     * @return true if given keyword is a Modify keyword, else false.
     */
    protected static boolean isAccessKeyword(String keyword) {
        for (AccessKeyword ak : AccessKeyword.values()) {
            if (ak.name().equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether a given Task command is valid.
     * @param keyword String Description of Task keyword.
     * @param content String Description of Task content.
     * @throws DukeException if content of Task command is empty.
     */
    protected static void validateTaskCommand (String keyword, String content) throws DukeException {
        if (content.isBlank()) {
            throw new DukeException(String.format("The description of a %s cannot be empty", keyword));
        }
    }

    private static void validateKeyword(String keyword) throws DukeException {
        if (!isKeyword(keyword) || keyword.isBlank()) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
