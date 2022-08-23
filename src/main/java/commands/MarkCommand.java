package commands;

import tasklist.TaskList;
import common.Ui;
import dukeexceptions.DukeException;
import dukeexceptions.IllegalIndexException;

public class MarkCommand extends Command {
    private String[] args;

    public MarkCommand(String[] args) {
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
        taskList.markTask(index);
        Ui.printMarkTask(taskList.get(index));
    }
}