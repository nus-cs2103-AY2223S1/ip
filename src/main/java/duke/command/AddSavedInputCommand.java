package duke.command;

import duke.*;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

public class AddSavedInputCommand extends Command {
    private static final Instructions TODO_INSTRUCTION = Instructions.todo;
    private static final String TODO_TIMING = "";
    private final String task;
    private final Instructions type;
    private final String timing;
    private final boolean done;

    public AddSavedInputCommand(String task, boolean done) {
        super(false);
        this.task = task;
        this.type = TODO_INSTRUCTION;
        this.timing = TODO_TIMING;
        this.done = done;
    }

    public AddSavedInputCommand(String task, Instructions instruction, String timing, boolean done) {
        super(false);
        this.task = task;
        this.type = instruction;
        this.timing = timing;
        this.done = done;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        Task newTask;
        switch (type) {
        case todo:
            newTask = new ToDos(task, done);
            break;
        case deadline:
            newTask = new Deadlines(task, timing, done);
            break;
        case event:
            newTask = new Events(task, timing, done);
            break;
        default:
            newTask = null; //Should never be reached
        }
        taskList.add(newTask);
        new SaveCommand().execute(taskList, ui, storage);
    }
}
