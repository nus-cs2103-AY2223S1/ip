package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

import java.time.LocalDate;

/**
 * Command to create a deadline task.
 *
 * @author dexter-sim
 * @version 0.1
 */
public class DeadlineCommand extends Command {
    private String description;
    private LocalDate by;

    /**
     * Creates a deadline command.
     *
     * @param description The description for the deadline task.
     * @param by The date for the deadline task.
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command to create a deadline task and add it to specified task list.
     * It calls the ui to print the deadline task and the storage to save the new task list.
     *
     * @param storage Storage handling the file IO.
     * @param taskList A list of tasks.
     * @param ui A ui to handle printing output.
     * @return A string from the result of execution.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        assert storage != null;
        assert taskList != null;
        assert ui != null;
        try {
            String output = "";
            output += ui.printAddTask(taskList.addTask(new Deadline(description, by)));
            output += ui.printSizeOfList(taskList.getSize());
            storage.save(taskList.getTasks());
            return output;
        } catch (DukeException e) {
            return ui.printErrorMessage(e);
        }
    }
}
