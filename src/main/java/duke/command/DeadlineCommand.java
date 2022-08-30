/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

/**
 * public class DeadlineCommand that handles deadline.
 */
public class DeadlineCommand extends Command{
    private String event;
    private LocalDate time;

    /**
     * public constructor for DeadlineCommand.
     * @param event the name of deadline event.
     * @param time the time of the deadline.
     */
    public DeadlineCommand(String event, LocalDate time) {
        super();
        this.event = event;
        this.time = time;
    }

    /**
     * public method execute to execute command.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline e = new Deadline(this.event, this.time);
        tasks.addTask(e);
        storage.saveToFile(tasks.saveList());
        return ui.printAddTask(e, tasks.getSize());
    }
}
