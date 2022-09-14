package Duke.Commands;

import Duke.Tasks.TaskList;

public class ListTasksCommand extends UserCommand {

    public ListTasksCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public String execute() {
        String output = "These are all the tasks in your list: \n";
        output += this.tasks.showTasks();
        return output;
    }
}
