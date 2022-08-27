package ip.command;

import ip.Storage;
import ip.TaskList;
import ip.task.Task;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList) {
        int i = 1;
        for (Task task : taskList.tasks) {
            System.out.println(i + ". " + task);
            i++;
        }
        System.out.println(taskList);
    }
}
