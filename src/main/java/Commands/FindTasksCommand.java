package Commands;

import DataStruct.TaskList;


public class FindTasksCommand {
    private TaskList tasks;
    private String keyword;

    public FindTasksCommand(TaskList tasks, String keyword) {
        this.tasks = tasks;
        this.keyword = keyword;
    }

    public String execute() {
        return this.tasks.findTasks(keyword);
    }
}
