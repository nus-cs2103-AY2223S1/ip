package duke.command;

import duke.MessagePrinter;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public class UnmarkCommand extends Command {
    private int idTask;

    protected UnmarkCommand(int idTask) {
        super(Action.UNMARK);
        this.idTask = idTask;
    }

    @Override
    public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
        String successMsg = "OK, I've marked this task as not done yet:";
        Task task = taskList.get(idTask - 1);
        task.markAsNotDone();
        messagePrinter.printMessage(successMsg + "\n" + task);
    }

    @Override
    public String getFormat() {
        return "unmark [ID of task]";
    }

    @Override
    public boolean isTerminated() {
        return false;
    }
}
