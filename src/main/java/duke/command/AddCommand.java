package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Command that adds a task to the list.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class AddCommand extends Command {
    private String type;
    private String description;
    private String date;

    /**
     * Instantiates the command with information of the task to be added.
     *
     * @param type Type of task: Todo, Deadline or Event
     * @param description Description of the task.
     * @param date Date of deadline if applicable.
     */
    public AddCommand(String type, String description, String date) {
        this.type = type;
        this.description = description;
        this.date = date;
    }

    /**
     * Returns whether command is an ExitCommand.
     *
     * @return Whether the command will cause the Duke program to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command by adding the task to the list of tasks.
     *
     * @param taskList List of tasks being operated on.
     * @param ui UI that prints corresponding responses.
     * @param storage Storage for saving purposes if applicable.
     * @throws DukeException If the input task is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (this.type.equals("todo")) {
            Todo t = new Todo(description);
            taskList.add(t);
            ui.showAdd(t, taskList.getTaskArrayList().size());
        } else if (this.type.equals("deadline")) {
            Deadline d = new Deadline(this.description, this.date);
            taskList.add(d);
            ui.showAdd(d, taskList.getTaskArrayList().size());
        } else {
            Event e = new Event(this.description, this.date);
            taskList.add(e);
            ui.showAdd(e, taskList.getTaskArrayList().size());
        }
    }
}
