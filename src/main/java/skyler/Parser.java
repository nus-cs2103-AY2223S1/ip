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
        if (command.equals("list")) {
            return taskList.list();
        } else if (command.startsWith("mark")) {
            int itemID = Integer.parseInt(command.substring(command.length() - 1));
            return taskList.mark(itemID);
        } else if (command.startsWith("unmark")) {
            int itemID = Integer.parseInt(command.substring(command.length() - 1));
            return taskList.unmark(itemID);
        } else if (command.startsWith("todo")) {
            String[] commandArguments = command.split(" ", 2);
            if (command.trim().equals("todo")) {
                throw new EmptyDescriptionException();
            }
            return taskList.addTodo(commandArguments[1]);
        } else if (command.startsWith("deadline")) {
            String[] commandArguments = command.split(" ", 2);
            if (command.trim().equals("deadline")) {
                throw new EmptyDescriptionException();
            }
            return taskList.addDeadline(commandArguments[1]);
        } else if (command.startsWith("event")) {
            String[] commandArguments = command.split(" ", 2);
            if (command.trim().equals("event")) {
                throw new EmptyDescriptionException();
            }
            return taskList.addEvent(commandArguments[1]);
        } else if (command.startsWith("delete")) {
            int itemID = Integer.parseInt(command.substring(command.length() - 1));
            return taskList.delete(itemID);
        } else if (command.startsWith("find")) {
            String[] commandArguments = command.split(" ", 2);
            if (command.trim().equals("find")) {
                throw new EmptyDescriptionException();
            }
            return taskList.findTask(commandArguments[1]);
        } else {
            throw new TaskNotRecognisedException();
        }
    }
}
