package duke.command;

import duke.exception.IllegalTaskException;
import duke.logic.TaskList;

public class DeleteCommand extends Command {
    private TaskList taskList;
    private int query;

    public DeleteCommand(TaskList taskList, int query) throws IllegalTaskException {
        this.taskList = taskList;
        if (this.taskList.exists(query)) {
            this.query = query;
        } else {
            throw new IllegalTaskException("Task does not exist.");
        }
    }

    @Override
    public void run() {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.remove(query).toString());
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
    }

}