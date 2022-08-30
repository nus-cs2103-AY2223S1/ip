package Commands;

import DataStruct.TaskList;


public class FindTasksCommand extends Command {
    private TaskList tasks;
    private String keyword;

    public FindTasksCommand(TaskList tasks, String keyword) {
        this.tasks = tasks;
        this.keyword = keyword;
    }

    @Override
    public String execute() {
        return this.tasks.findTasks(keyword);
    }
}
