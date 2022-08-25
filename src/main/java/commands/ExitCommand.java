package commands;

import exceptions.DukeException;
import input.Input;
import task.TaskModel;

/**
 * The command to exit the program
 * Usage: bye
 */
public class ExitCommand extends Command {
    private TaskModel taskModel;

    /**
     * Initialise ExitCommand
     * @param taskModel Task model to use
     */
    public ExitCommand(TaskModel taskModel) {
        super("bye");
        this.taskModel = taskModel;
    }

    @Override
    public CommandResponse run(Input input) throws DukeException {
        taskModel.save();
        return new CommandResponse("Bye. See you again soon!", true);
    }
}
