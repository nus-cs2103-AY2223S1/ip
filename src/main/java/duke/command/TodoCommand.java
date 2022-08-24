package duke.command;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;
import duke.task.Task;
import duke.task.ToDo;

public class TodoCommand extends Command{
    private String taskDetails;

    public TodoCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    @Override
    public void execute(Storage storage, UI ui, TaskList taskList) {
        Task task = new ToDo(taskDetails);
        taskList.add(task);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
        storage.save(taskList.list());
    }
}
