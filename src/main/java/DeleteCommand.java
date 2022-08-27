import java.io.IOException;
import java.util.Scanner;

public class DeleteCommand extends Command {
    int taskNo;

    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        Task t = taskList.getTask(taskNo);
        taskList.delete(taskNo);
        Task.minusTaskCount();
        System.out.printf("Noted. I've removed this task:\n" +
                "  %s\n" +
                "Now you have %d tasks in the list.\n", t, Task.taskCount);
    }
}
