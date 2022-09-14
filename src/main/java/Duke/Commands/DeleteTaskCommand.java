package Duke.Commands;

import Duke.Exceptions.TaskNotExistException;


import Duke.Tasks.Task;
import Duke.Tasks.TaskList;

public class DeleteTaskCommand extends UserCommand {

    private int index;

    public DeleteTaskCommand(int i, TaskList tasks) {
        super(tasks);
        this.index = i;
    }


    @Override
    public String execute() throws TaskNotExistException {
        Task removedTask;
        try {
            removedTask = this.tasks.deleteTask(this.index); //  -1 in the delete task itself
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotExistException();
        }

        String output = String.format("Nice! You have successfully removed this task:\n%s\n", removedTask);
        return output;
    }
}
