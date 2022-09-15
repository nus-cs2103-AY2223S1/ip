package commands;

import common.ChatResponse;
import dukeexceptions.DukeException;
import dukeexceptions.IllegalIndexException;
import tasklist.TaskList;
import tasks.Task;

/**
 * Represents a Delete command.
 */
public class DeleteCommand extends Command {
    private final String[] args;

    public DeleteCommand(String[] args) {
        this.args = args;
    }

    /**
     * Validates the passed arguments before executing the command.
     *
     * @param args Arguments to validate.
     * @throws DukeException Exception to be thrown if validation fails.
     */
    public static void validateArguments(String[] args) throws DukeException {
        assert args.length > 0 : "No arguments entered into validateArguments";

        if (args.length < 1) {
            throw new DukeException("Missing index!");
        }
        int index = Integer.parseInt(args[0]) - 1;
        if (index < 0) {
            throw new IllegalIndexException();
        }
        try {
            Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new DukeException(e.toString());
        }
    }

    @Override
    public String execute(TaskList taskList) {
        int index = Integer.parseInt(args[0]) - 1;
        Task toDelete = taskList.get(index);
        taskList.deleteTask(index);
        return ChatResponse.returnChatDeleteTask(toDelete, taskList);
    }
}
