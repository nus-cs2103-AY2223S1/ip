package duke.command;

import java.io.IOException;
import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Command that adds a task to the list.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class AddCommand extends Command {

    private final String type;
    private final String description;
    private final String date;

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
     * @throws IOException If there is an issue saving the list to Storage.
     * @throws DukeException If an invalid task is created.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        ArrayList<Task> list = taskList.getTaskArrayList();
        Task task;

        boolean isTodo = this.type.equals("todo");
        boolean isDeadline = this.type.equals("deadline");
        boolean isEvent = this.type.equals("event");
        assert (isTodo || isDeadline || isEvent) : "Type must be one of the three: todo, deadline, event";

        if (isTodo) {
            task = new Todo(description);
        } else if (isDeadline) {
            task = new Deadline(this.description, this.date);
        } else if (isEvent) {
            task = new Event(this.description, this.date);
        } else {
            String errorMessage = "Task to be added is invalid. Please input a valid task.";
            throw new DukeException(errorMessage);
        }

        taskList.add(task);
        storage.save(list);
        return ui.showAdd(task, list.size());
    }
}
