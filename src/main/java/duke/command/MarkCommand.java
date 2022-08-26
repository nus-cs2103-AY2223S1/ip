package duke.command;

import duke.logic.TaskList;
import duke.exception.IllegalTaskException;

public class MarkCommand extends Command {
    private TaskList taskList;
    private int query;

    public MarkCommand(TaskList taskList, int query) throws IllegalTaskException {
        this.taskList = taskList;
        if (this.taskList.exists(query)) {
            this.query = query;
        } else {
            throw new IllegalTaskException("Task does not exist.");
        }
    }

    @Override
    public void run() {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.markTask(query).toString());
    }
}