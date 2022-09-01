package kirby.commands;

import java.util.ArrayList;

import kirby.HandleTime;
import kirby.Storage;
import kirby.TaskList;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.tasks.Task;
import kirby.ui.Ui;

/**
 * GetCommand class handles the command to get all the tasks on a specified date.
 */
public class GetCommand extends Command {
    private String inputString;

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
        if (inputString.split(" ").length != 2) {
            throw new KirbyMissingArgumentException("get");
        }
        String inputDate = inputString.split(" ")[1];
        if (!HandleTime.isValidDate(inputDate)) {
            throw new KirbyMissingArgumentException("get");
        }
        ArrayList<Task> res = HandleTime.getTaskByDate(tasks.getList(), inputDate);
        if (res.size() < 1) {
            return "No tasks found!";
        } else {
            String resListPara = "Here are the tasks: \n";
            for (Task re : res) {
                resListPara += re.toString() + "\n";
            }
            return resListPara;
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
