package duke.command;
import duke.MessagePrinter;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public class DeleteCommand extends Command {
    int idTask;
    public DeleteCommand(int idTask) {
        super(Action.DELETE);
        this.idTask = idTask;
    }

    @Override
    public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
        String successMsg = "Noted. I've removed this task:";
        Task task = taskList.remove(idTask - 1);
        successMsg = successMsg + "\n" + task + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
        messagePrinter.printMessage(successMsg);
    }

    @Override
    public String getFormat() {
        return "delete [ID of Task]";
    }

    @Override
    public boolean isTerminated() {
        return false;
    }
}
