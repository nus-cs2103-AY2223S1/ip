package kirby.commands;

import java.util.ArrayList;

import kirby.Storage;
import kirby.TaskList;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.tasks.Task;
import kirby.time.HandleTime;
import kirby.ui.Ui;

/**
 * GetCommand class handles the command to get all the tasks on a specified date.
 */
public class GetCommand extends Command {
    private static final int GET_COMMAND_LENGTH = 2;
    private static String EMPTY_TASK_MESSAGE = "No tasks found!";
    private final String inputString;

    /**
     * Constructor for the class DeadlineCommand.
     *
     * @param inputString Arguments of a command.
     */
    public GetCommand(String inputString) {
        this.inputString = inputString;
    }

    /**
     * {@inheritDoc}
     * Lists down the list of tasks on a specified date if the date is valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        if (inputString.split(" ").length != GET_COMMAND_LENGTH) {
            throw new KirbyMissingArgumentException("get");
        }
        String inputDate = inputString.split(" ")[1];
        if (!HandleTime.isValidDate(inputDate)) {
            throw new KirbyMissingArgumentException("get");
        }
        ArrayList<Task> res = HandleTime.getTaskByDate(tasks.getList(), inputDate);
        if (res.size() < 1) {
            return EMPTY_TASK_MESSAGE;
        } else {
            StringBuilder getTaskList = new StringBuilder("Here are the tasks: \n");
            for (Task re : res) {
                getTaskList.append(re.toString()).append("\n");
            }
            return getTaskList.toString();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
