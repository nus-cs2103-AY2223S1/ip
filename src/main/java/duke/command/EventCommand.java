package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

import java.time.LocalDate;

/**
 * Command to create an event task.
 *
 * @author dexter-sim
 * @version 0.1
 */
public class EventCommand extends Command {
    private String description;
    private LocalDate at;

    /**
     * Creates an event command.
     *
     * @param description The description for the event task.
     * @param at The date for the event task.
     */
    public EventCommand(String description, LocalDate at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Executes the command to create an event task and add it to specified task list.
     * It calls the ui to print the event task and the storage to save the new task list.
     *
     * @param storage Storage handling the file IO.
     * @param taskList A list of tasks.
     * @param ui A ui to handle printing output.
     * @return A string from the result of execution.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            String output = "";
            output += ui.printAddTask(taskList.addTask(new Event(description, at)));
            output += ui.printSizeOfList(taskList.getSize());
            storage.save(taskList.getTasks());
            return output;
        } catch (DukeException e) {
            return ui.printErrorMessage(e);
        }
    }
}
