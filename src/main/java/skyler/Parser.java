package skyler;

/**
 * Represents an interpreter that makes sense of user commands
 */
public class Parser {

    private TaskList taskList;

    /**
     * Creates a parser object
     *
     * @param taskList TaskList to be manipulated with user commands.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
        assert taskList != null : "TaskList should be initialised";
    }

    /**
     * Returns status number based on input command detected
     *
     * @param command User command.
     * @return Skyler's response to the user's command.
     * @throws EmptyDescriptionException If no input task is detected after command.
     * @throws TaskNotRecognisedException If command is not recognised.
     */
    public String parse(String command) throws EmptyDescriptionException, TaskNotRecognisedException {
        if (checkIsCommandType(command, "help")) {
            return taskList.help();
        } else if (checkIsCommandType(command, "list")) {
            return taskList.list();
        } else if (checkIsCommandType(command, "mark")) {
            int itemID = getItemID(command);
            return taskList.mark(itemID);
        } else if (checkIsCommandType(command, "unmark")) {
            int itemID = getItemID(command);
            return taskList.unmark(itemID);
        } else if (checkIsCommandType(command, "todo")) {
            String[] commandArguments = getCommandArguments(command);
            return taskList.addTodo(commandArguments[1]);
        } else if (checkIsCommandType(command, "deadline")) {
            String[] commandArguments = getCommandArguments(command);
            return taskList.addDeadline(commandArguments[1]);
        } else if (checkIsCommandType(command, "event")) {
            String[] commandArguments = getCommandArguments(command);
            return taskList.addEvent(commandArguments[1]);
        } else if (checkIsCommandType(command, "delete")) {
            int itemID = getItemID(command);
            return taskList.delete(itemID);
        } else if (checkIsCommandType(command, "find")) {
            String[] commandArguments = getCommandArguments(command);
            return taskList.findTask(commandArguments[1]);
        } else if (checkIsCommandType(command, "reschedule")) {
            String[] commandArguments = getMultipleCommandArguments(command);
            return taskList.rescheduleTask(Integer.parseInt(commandArguments[1]), commandArguments[2]);
        } else {
            throw new TaskNotRecognisedException();
        }
    }

    private boolean checkIsCommandType(String command, String type) throws EmptyDescriptionException {
        if (isCommandTypeWithDescription(type) && command.trim().equals(type)) {
            throw new EmptyDescriptionException();
        }
        return command.startsWith(type);
    }

    private boolean isCommandTypeWithDescription(String type) {
        return !type.equals("list") && !type.equals("help");
    }

    private int getItemID(String command) {
        return Integer.parseInt(command.substring(command.length() - 1));
    }

    private String[] getCommandArguments(String command) {
        return command.split(" ", 2);
    }

    private String[] getMultipleCommandArguments(String command) {
        return command.split(" ", 3);
    }
}
