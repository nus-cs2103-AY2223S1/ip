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
 *
 */
public class DeadlineCommand extends Command{
    private String event;
    private LocalDate time;

    public DeadlineCommand(String event, LocalDate time) {
        super();
        this.event = event;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline e = new Deadline(this.event, this.time);
        tasks.addTask(e);
        storage.saveToFile(tasks.saveList());
        ui.printAddTask(e, tasks.getSize());
    }
}
