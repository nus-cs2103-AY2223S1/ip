package commands;

import common.ChatResponse;
import dukeexceptions.DukeException;
import dukeexceptions.IllegalIndexException;
import tasklist.TaskList;

/**
 * Unmark command to be executed.
 */
public class UnmarkCommand extends Command {
    private final String[] args;

    public UnmarkCommand(String[] args) {
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
        try {
            int index = Integer.parseInt(args[0]) - 1;
            if (index < 0) {
                throw new IllegalIndexException();
            }
        } catch (NumberFormatException e) {
            throw new DukeException(e.toString());
        }
    }

    /**
     * Executes Unmark Command.
     *
     * @param taskList The taskList relevant to the command.
     * @return String with messages from execution.
     */
    @Override
    public String execute(TaskList taskList) {
        int index = Integer.parseInt(args[0]) - 1;
        taskList.unmarkTask(index);
        return ChatResponse.returnChatUnmarkTask(taskList.get(index));
    }
}
