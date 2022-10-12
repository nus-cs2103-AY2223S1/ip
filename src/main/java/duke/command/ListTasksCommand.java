package duke.command;

import duke.task.TaskList;

public class ListTasksCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "No tasks available.";
        }
        return String.format("Here are the tasks in your list:\n%s",
                tasks);
    }
}
