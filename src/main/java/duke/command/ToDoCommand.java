package duke.command;

import duke.main.TaskList;
import duke.task.ToDo;

public class ToDoCommand extends AddCommand {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Add new tasks.
     *
     * @param taskList
     */
    @Override
    public void add(TaskList taskList) {
        taskList.add(new ToDo(description));
    }
}
