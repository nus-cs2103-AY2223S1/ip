package duke;

public class Parser {

    static final int TLENGTH = 5;
    static final int ELENGTH = 6;
    static final int DLENGTH = 9;
    static final int MARK_LENGTH = 5;
    static final int UNMARK_LENGTH = 7;
    static final int DEL_LENGTH = 7;
    static final String[] ADD_COMMANDS = {"todo", "event", "deadline"};

    private static String getFirstWord(String input) {
        return input.split(" ")[0].toLowerCase();
    }

    private static boolean isListCommand(String input) {
        return getFirstWord(input).equals("list");
    }

    private static boolean isExitCommand(String input) {
        return getFirstWord(input).equals("bye");
    }

    private static boolean isAddTodoCommand(String input) {
        return getFirstWord(input).equals("todo");
    }

    private static boolean isAddEventCommand(String input) {
        return getFirstWord(input).equals("event");
    }

    private static boolean isAddDeadlineCommand(String input) {
        return getFirstWord(input).equals("deadline");
    }

    private static boolean isDeleteCommand(String input) {
        return getFirstWord(input).equals("delete");
    }

    private static boolean isMarkCommand(String input) {
        return getFirstWord(input).equals("mark");
    }

    private static boolean isUnmarkCommand(String input) {
        return getFirstWord(input).equals("unmark");
    }

    /**
     * @param fullCommand input Command from the user.
     * @return Task object
     */
    public static Task commandToTask(String fullCommand) {
        if (isAddTodoCommand(fullCommand)) {
            String desc = fullCommand.substring(TLENGTH);
            return new Todo(desc);
        } else if (isAddEventCommand(fullCommand)) {
            String desc = fullCommand.substring(ELENGTH).split(" /at ")[0];
            String time = fullCommand.substring(ELENGTH).split(" /at ")[1];
            return new Event(desc, time);
        } else if (isAddDeadlineCommand(fullCommand)) {
            String desc = fullCommand.substring(DLENGTH).split(" /by ")[0];
            String time = fullCommand.substring(DLENGTH).split(" /by ")[1];
            return new Deadline(desc, time);
        } else {
            System.out.println("not planned task parser");
            return null;
        }
    }

    private static boolean isAddCommand(String fullCommand) {
        for (String addCommands : ADD_COMMANDS) {
            if (fullCommand.toLowerCase().contains(addCommands)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param fullCommand input Command from the user.
     * @return Command to be executed .
     * @throws DukeException When the command does not parse properly.
     */
    public static Command parse(String fullCommand) throws DukeException {

        if (isAddCommand(fullCommand)) {
            return new AddCommand(fullCommand);
        } else if (isListCommand(fullCommand)) {
            return new ListCommand();
        } else if (isExitCommand(fullCommand)) {
            return new ExitCommand();
        } else if (isDeleteCommand(fullCommand)) {
            return new DeleteCommand(Integer.parseInt(fullCommand.substring(DEL_LENGTH)));
        } else if (isMarkCommand(fullCommand)) {
            return new MarkCommand(Integer.parseInt(fullCommand.substring(MARK_LENGTH)));
        } else if (isUnmarkCommand(fullCommand)) {
            return new UnmarkCommand(Integer.parseInt(fullCommand.substring(UNMARK_LENGTH)));
        } else {
            throw new DukeException("Parsing error");
        }
    }

}
