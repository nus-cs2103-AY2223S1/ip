package commands;

import common.ChatResponse;
import dukeexceptions.DukeException;
import dukeexceptions.InsufficientArgumentsException;
import tasklist.TaskList;

/**
 * Represents a Find command to be executed.
 */
public class FindCommand extends Command {
    private final String[] args;

    public FindCommand(String[] args) {
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
            throw new InsufficientArgumentsException("Find command");
        }
    }

    /**
     * Executes Find Command.
     *
     * @param taskList The taskList relevant to the command.
     * @return String with messages from execution.
     */
    @Override
    public String execute(TaskList taskList) {
        TaskList results = new TaskList();
        String target = this.args[0];

        for (int i = 0; i < taskList.size(); i++) {
            String taskRepresentation = taskList.get(i).toString();
            if (taskRepresentation.contains(target)) {
                results.addTask(taskList.get(i));

            }
        }

        return ChatResponse.returnChatFindResults(results);
    }
}
