package duke.command;

import duke.component.TaskList;
import duke.exception.DukeException;
import duke.task.ToDo;

public class ToDoCommand extends Command {

    public ToDoCommand(String content, TaskList tasks) {
        super(content, tasks);
    }

    @Override
    public String run() throws DukeException {
        return this.tasks.addTask(new ToDo(this.content));
    }
}