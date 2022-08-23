package commands;

import tasklist.TaskList;
import tasks.Task;
import common.Ui;
import dukeexceptions.DukeException;
import dukeexceptions.IllegalIndexException;

public class DeleteCommand extends Command {
    private String[] args;

    public DeleteCommand(String[] args) {
        this.args = args;
    }

    public static void validateArguments(String[] args) throws DukeException {
        if (args.length < 1) {
            throw new DukeException("Missing index!");
        }
        int index = Integer.parseInt(args[0])-1;
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
    public void execute(TaskList taskList) {
        int index = Integer.parseInt(args[0]) - 1;
        Task toDelete = taskList.get(index);
        taskList.deleteTask(index);
        Ui.printDeleteTask(toDelete, taskList);
    }
}