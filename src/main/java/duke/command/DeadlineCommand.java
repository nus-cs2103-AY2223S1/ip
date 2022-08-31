package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

/**
 * Deadline command is a command that creates a new Deadline.
 *
 * @author Eugene Tan
 */
public class DeadlineCommand extends Command {
    private String description;
    private LocalDate doBy;

    /**
     * Constructor of DeadlineCommand.
     *
     * @param description The deadline description.
     * @param doBy The deadline date.
     */
    public DeadlineCommand(String description, LocalDate doBy) {
        super();
        this.description = description;
        this.doBy = doBy;
    }

    /**
     * Creates a new Deadline with given description and date.
     *
     * @param tasks Tasklist containing the tasks
     * @param ui Ui handling the user interaction
     * @param storage Storage to store data
     * @return Add deadline message
     */
    @Override
    public String run(TaskList tasks, Ui ui, Storage storage) {
        String task = tasks.addDeadline(this.description, this.doBy);
        storage.save(tasks.getTaskListInString());
        return ui.printAdd(task,tasks.getSize());
    }
}
