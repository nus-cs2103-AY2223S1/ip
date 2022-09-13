package duke.command;

import duke.ClientList;
import duke.DukeException;
import duke.Instructions;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDos;

/**
 * Represents a command adding a new task into task list.
 */
public class AddTaskCommand extends Command {
    private static final boolean NOT_DONE = false;
    private static final String TODO_TIMING = "";
    private final String task;
    private final Instructions type;
    private final String timing;

    /**
     * Constructs Add User Command objects,
     * to add new tasks.
     *
     * @param task task in String.
     */
    public AddTaskCommand(String task) {
        this.task = task;
        this.type = Instructions.todo;
        this.timing = TODO_TIMING;
    }

    /**
     * Constructs Add User Command objects,
     * to add tasks from saved task file.
     *
     * @param task task in String.
     * @param instruction specific instruction.
     * @param timing timing in String.
     */
    public AddTaskCommand(String task, Instructions instruction, String timing) {
        this.task = task;
        this.type = instruction;
        this.timing = timing;
    }

    /**
     * Adds new task into task list and saves it in save file.
     *
     * @param taskList task list.
     * @return String representation of how task list changed.
     * @throws DukeException if timing is of the wrong format.
     */
    @Override
    public String execute(TaskList taskList, ClientList clientList) throws DukeException {
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
            throw new DukeException("Unknown Error");
        }
        taskList.add(newTask);
        SaveTaskListCommand.of().execute(taskList, clientList);
        return CommandOutputs.showAdd(taskList, newTask);
    }
}
