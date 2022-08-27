package duke.command;

import duke.DukeException;
import duke.Instructions;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

/**
 * Represents a command adding a new task into task list.
 */
public class AddUserCommand extends Command {
    private static final boolean NOT_DONE = false;
    private static final String TODO_TIMING = "";
    private final String task;
    private final Instructions type;
    private final String timing;

    /**
     * Constructor for AddUserCommand class.
     *
     * @param task task in String.
     */
    public AddUserCommand(String task) {
        super(false);
        this.task = task;
        this.type = Instructions.todo;
        this.timing = TODO_TIMING;
    }

    /**
     * Constructor for AddUserCommand class.
     *
     * @param task task in String.
     * @param instruction specific instruction.
     * @param timing timing in String.
     */
    public AddUserCommand(String task, Instructions instruction, String timing) {
        super(false);
        this.task = task;
        this.type = instruction;
        this.timing = timing;
    }

    /**
     * Adds new task into task list and saves it in save file.
     *
     * @param taskList task list.
     * @param ui user interface of program.
     * @param storage files storing task list.
     * @throws DukeException if timing is of the wrong format.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task newTask;
        switch (type) {
        case todo:
            newTask = new ToDos(task, NOT_DONE);
            break;
        case deadline:
            newTask = new Deadlines(task, timing, NOT_DONE);
            break;
        case event:
            newTask = new Events(task, timing, NOT_DONE);
            break;
        default:
            newTask = null; //Should never be reached
        }
        taskList.add(newTask);
        ui.showAdd(taskList, newTask);
        new SaveCommand().execute(taskList, ui, storage);
    }
}
