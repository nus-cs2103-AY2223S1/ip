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
            int item = Integer.parseInt(command.substring(command.length() - 1));
            return taskList.mark(item);
        } else if (command.startsWith("unmark")) {
            int item = Integer.parseInt(command.substring(command.length() - 1));
            return taskList.unmark(item);
        } else if (command.startsWith("todo")) {
            String[] arr = command.split(" ", 2);
            if (command.trim().equals("todo")) {
                throw new EmptyDescriptionException();
            }
            return taskList.addTodo(arr[1]);
        } else if (command.startsWith("deadline")) {
            String[] arr = command.split(" ", 2);
            if (command.trim().equals("deadline")) {
                throw new EmptyDescriptionException();
            }
            return taskList.addDeadline(arr[1]);
        } else if (command.startsWith("event")) {
            String[] arr = command.split(" ", 2);
            if (command.trim().equals("event")) {
                throw new EmptyDescriptionException();
            }
            return taskList.addEvent(arr[1]);
        } else if (command.startsWith("delete")) {
            int item = Integer.parseInt(command.substring(command.length() - 1));
            return taskList.delete(item);
        } else if (command.startsWith("find")) {
            String[] arr = command.split(" ", 2);
            if (command.trim().equals("find")) {
                throw new EmptyDescriptionException();
            }
            return taskList.findTask(arr[1]);
        } else {
            throw new TaskNotRecognisedException();
        }
    }
}
