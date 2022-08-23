package Commands;

import TaskList.TaskList;
import common.Ui;
import dukeExceptions.DukeException;
import dukeExceptions.IllegalIndexException;
import dukeExceptions.MissingDescriptionException;

public class UnmarkCommand extends Command {
    private String[] args;

    public UnmarkCommand(String[] args) {
        this.args = args;
    }

    public static void validateArguments(String[] args) throws DukeException {
        if (args.length < 1) {
            throw new DukeException("Missing index!");
        }
        try {
            int index = Integer.parseInt(args[0])-1;
            if (index < 0) {
                throw new IllegalIndexException();
            }
        } catch (NumberFormatException e) {
            throw new DukeException(e.toString());
        }
    }

    @Override
    public void execute(TaskList taskList) {
        int index = Integer.parseInt(args[0])-1;
        taskList.unmarkTask(index);
        Ui.printUnmarkTask(taskList.get(index));
    }
}
