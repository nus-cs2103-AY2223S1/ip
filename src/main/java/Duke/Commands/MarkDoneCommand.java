package Duke.Commands;

import Duke.Exceptions.TaskNotExistException;
import Duke.Tasks.TaskList;
import Duke.Tasks.Task;

public class MarkDoneCommand extends UserCommand {
    private int index;

    public MarkDoneCommand(int index, TaskList tasks) {
        super(tasks);
        this.index = index;
    }

    @Override
    public String execute() throws TaskNotExistException {
        Task MarkedTask;
        try {
            MarkedTask = this.tasks.markAsDone(this.index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotExistException();
        }
        String output = String.format("Nice! You have successfully marked the task as done: \n%s\n", MarkedTask);

        return output;

    }


}
