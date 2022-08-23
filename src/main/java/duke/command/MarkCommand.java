package duke.command;

import duke.MessagePrinter;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public class MarkCommand extends Command {
    int idTask;
    public MarkCommand(int idTask) {
        super(Action.MARK);
        this.idTask = idTask;
    }

    @Override
    public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
        String successMsg = "Nice! I've marked this task as done:";
        Task task = taskList.get(idTask - 1);
        task.markAsDone();
        messagePrinter.printMessage(successMsg + "\n" + task);
    }

    @Override
    public String getFormat() {
        return "mark [ID of task]";
    }

    @Override
    public boolean isTerminated() {
        return false;
    }
}
