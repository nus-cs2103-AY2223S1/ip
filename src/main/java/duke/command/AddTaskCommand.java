package duke.command;

import duke.*;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
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
     * Constructor for AddUserCommand class.
     *
     * @param task task in String.
     */
    public AddTaskCommand(String task) {
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
    public AddTaskCommand(String task, Instructions instruction, String timing) {
        this.task = task;
        this.type = instruction;
        this.timing = timing;
    }

    /**
     * Adds new task into task list and saves it in save file.
     *
     * @param taskList task list.
     * @param commandOutputs user interface of program.
     * @param storage files storing task list.
     * @return String response of Duke regarding user input.
     * @throws DukeException if timing is of the wrong format.
     */
    @Override
    public String execute(TaskList taskList, CommandOutputs commandOutputs, Storage storage, ClientList clientList) throws DukeException {
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
        new SaveCommand().execute(taskList, commandOutputs, storage, clientList);
        return commandOutputs.showAdd(taskList, newTask);
    }
}
