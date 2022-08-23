package duke.command;

import duke.*;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

public class AddUserCommand extends Command {
    private static final boolean NOTDONE = false;
    private static final String TODO_TIMING = "";
    private final String task;
    private final Instructions type;
    private final String timing;

    public AddUserCommand(String task) {
        super(false);
        this.task = task;
        this.type = Instructions.todo;
        this.timing = TODO_TIMING;
    }


    public AddUserCommand(String task, Instructions instruction, String timing) {
        super(false);
        this.task = task;
        this.type = instruction;
        this.timing = timing;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        Task newTask;
        switch (type) {
        case todo:
            newTask = new ToDos(task, NOTDONE);
            break;
        case deadline:
            newTask = new Deadlines(task, timing, NOTDONE);
            break;
        case event:
            newTask = new Events(task, timing, NOTDONE);
            break;
        default:
            newTask = null; //Should never be reached
        }
        taskList.add(newTask);
        ui.showAdd(taskList, newTask);
        new SaveCommand().execute(taskList, ui, storage);
    }
}
