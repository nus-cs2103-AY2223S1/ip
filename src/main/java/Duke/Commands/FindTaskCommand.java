package Duke.Commands;

import Duke.Exceptions.DukeException;
import Duke.Tasks.TaskList;

public class FindTaskCommand extends UserCommand{
    private String keyword;

    public FindTaskCommand(TaskList tasks, String keyword) {
        super(tasks);
        this.keyword = keyword;
    }

    @Override
    public String execute() throws DukeException {
        String output = "These are all the tasks found by " + keyword + ":\n";
        TaskList foundTasks = this.tasks.findTask(this.keyword);
        output += foundTasks.showTasks();
        return output;
    }
}
