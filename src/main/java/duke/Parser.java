package duke;

/**
 * This class handles the parsing of the user's CLI inputs.
 *
 * @author Andrew Lo Zhi Sheng
 * @version CS2103T AY22/23 Semester 1
 */
public class Parser {
    private TaskList taskList;

    /**
     * Constructor for an instance of Parser.
     *
     * @param taskList The list of Tasks to be updated with the user inputs.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the command word from the given input line.
     *
     * @param inputText the user's input in the console
     *
     * @return The current command
     *
     * @throws DukeException if the input doesn't have a valid command word
     */
    public Command getCommand(String inputText) throws DukeException {
        int firstSpaceIdx = inputText.indexOf(" ");
        String commandName = firstSpaceIdx == -1
                             ? inputText
                             : inputText.substring(0, firstSpaceIdx);
        Command result = Command.get(commandName);

        // Check if command is valid
        if (result == null) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        return result;
    }

    /**
     * Executes a given command.
     *
     * @param command the command to be executed
     * @param inputText the user-entered text
     *
     * @return true if the program needs to exit
     *         false otherwise
     *
     * @throws DukeException if supplied arguments are invalid
     */
    public boolean executeCommand(Command command, String inputText) throws DukeException {
        int firstSpaceIdx = inputText.indexOf(" ");

        // Determine the action to perform
        switch (command) {
        case BYE:
            return true;
        case LIST:
            this.taskList.printItems();
            break;
        case MARK:
            int markindex = Integer.parseInt(inputText.substring(firstSpaceIdx + 1));
            this.taskList.mark(markindex);
            break;
        case UNMARK:
            int unmarkindex = Integer.parseInt(inputText.substring(firstSpaceIdx + 1));
            this.taskList.unmark(unmarkindex);
            break;
        case DELETE:
            int deleteindex = Integer.parseInt(inputText.substring(firstSpaceIdx + 1));
            this.taskList.delete(deleteindex);
            break;
        case DEADLINE:
            String deadlineInfo = inputText.substring(firstSpaceIdx + 1);
            this.taskList.addDeadline(deadlineInfo);
            break;
        case TODO:
            String todoInfo = inputText.substring(firstSpaceIdx + 1);
            this.taskList.addTodo(todoInfo);
            break;
        case EVENT:
            String eventInfo = inputText.substring(firstSpaceIdx + 1);
            this.taskList.addEvent(eventInfo);
            break;
        case LISTALLONDATE:
            String listInfo = inputText.substring(firstSpaceIdx + 1);
            this.taskList.printAllOnDate(listInfo);
            break;
        case FIND:
            String key = inputText.substring(firstSpaceIdx + 1);
            this.taskList.find(key);
            break;
        default:
            break;
        }
        return false;
    }
}
