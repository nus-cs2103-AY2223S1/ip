package duke.command;

import duke.MessagePrinter;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public class MarkCommand extends Command {
    private final int idTask;

    protected MarkCommand(int idTask) {
        super(Action.MARK);
        this.idTask = idTask;
    }

    public int getIdTask() {
        return idTask;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MarkCommand) {
            MarkCommand c = (MarkCommand) obj;
            return this.idTask == c.getIdTask();
        }
        return false;
    }
}
