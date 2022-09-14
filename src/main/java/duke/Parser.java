package duke;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Accepts String arguments to convert them to their corresponding class types for the operation of Duke
 */
public class Parser {
    private final static String EXIT = "bye";

    enum Keyword {
        bye, list, mark, unmark, todo, deadline, event, delete, find, help
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
     * @param commandText String description of a Command
     * @return A Command object based on the description
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
        return (index != -1) ? command.substring(0, index).toLowerCase() : command;
    }

    private static String getCommandContent(String command) {
        int index = command.indexOf(' ');
        return (index != -1) ? command.substring(index).trim() : "";
    }

    /**
     * Extracts out the date embedded within a given String description of a Task where applicable.
     * @param desc String description of a Task object
     * @return LocalDate object corresponding to a Task where available, else LocalDate object
     * corresponding to Date of initialisation
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
        return Arrays.stream(Keyword.values())
                .anyMatch(kw -> kw.name().equals(keyword));
    }

    /**
     * Perform a Boolean check on whether a given String is a Task command keyword.
     * @param keyword Keyword of a command
     * @return true if given keyword is a Task keyword, else false
     */
    protected static boolean isTaskKeyword(String keyword) {
        return Arrays.stream(TaskKeyword.values())
                .anyMatch(tk -> tk.name().equals(keyword));
    }

    /**
     * Perform a Boolean check on whether a given String is a Modify command keyword.
     * @param keyword Keyword of a command
     * @return true if given keyword is a Modify keyword, else false
     */
    protected static boolean isModifyKeyword(String keyword) {
        return Arrays.stream(ModifyKeyword.values())
                .anyMatch(mk -> mk.name().equals(keyword));
    }

    /**
     * Perform a Boolean check on whether a given String is a Modify command keyword.
     * @param keyword Keyword of a command
     * @return true if given keyword is a Access keyword, else false
     */
    protected static boolean isAccessKeyword(String keyword) {
        return Arrays.stream(AccessKeyword.values())
                .anyMatch(ak -> ak.name().equals(keyword));
    }

    /**
     * Perform a Boolean check on whether a given String is a Bye command keyword.
     * @return true if given keyword is a Bye keyword, else false
     */
    protected static boolean isByeKeyword(String keyword){
        return keyword.equals(EXIT);
    }

    /**
     * Checks whether a given Task command is valid.
     * @param keyword String Description of Task keyword
     * @param content String Description of Task content
     * @throws DukeException if content of Task command is empty
     */
    protected static void validateTaskCommand(String keyword, String content) throws DukeException {
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
