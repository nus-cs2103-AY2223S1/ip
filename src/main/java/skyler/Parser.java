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
     * @return 1 if user has more commands and 0 if user has completed commands.
     * @throws EmptyDescriptionException If no input task is detected after command.
     * @throws TaskNotRecognisedException If command is not recognised.
     */
    public int parse(String command) throws EmptyDescriptionException, TaskNotRecognisedException {
        if (command.equals("bye")) {
            return 0;
        } else if (command.equals("list")) {
            taskList.list();
            return 1;
        } else if (command.startsWith("mark")) {
            int item = Integer.parseInt(command.substring(command.length() - 1));
            taskList.mark(item);
            return 1;
        } else if (command.startsWith("unmark")) {
            int item = Integer.parseInt(command.substring(command.length() - 1));
            taskList.unmark(item);
            return 1;
        } else if (command.startsWith("todo")) {
            String[] arr = command.split(" ", 2);
            if (command.trim().equals("todo")) {
                throw new EmptyDescriptionException();
            }
            taskList.todo(arr[1]);
            return 1;
        } else if (command.startsWith("deadline")) {
            String[] arr = command.split(" ", 2);
            if (command.trim().equals("deadline")) {
                throw new EmptyDescriptionException();
            }
            taskList.deadline(arr[1]);
            return 1;
        } else if (command.startsWith("event")) {
            String[] arr = command.split(" ", 2);
            if (command.trim().equals("event")) {
                throw new EmptyDescriptionException();
            }
            taskList.event(arr[1]);
            return 1;
        } else if (command.startsWith("delete")) {
            int item = Integer.parseInt(command.substring(command.length() - 1));
            taskList.delete(item);
            return 1;
        } else {
            throw new TaskNotRecognisedException();
        }
    }
}
