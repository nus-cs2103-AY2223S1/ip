package pluto.command;

import java.io.IOException;
import java.time.LocalDateTime;

import pluto.PlutoException;
import pluto.Storage;
import pluto.TaskList;
import pluto.Ui;
import pluto.task.Task;

/**
 * Command to reschedule time of an event.
 */
public class RescheduleCommand extends Command {
    private int idx;
    private LocalDateTime newTime;

    /**
     * Initializes global variables.
     * @param idx Index of task to be rescheduled.
     * @param newTime New date.
     */
    public RescheduleCommand(int idx, LocalDateTime newTime) {
        this.idx = idx;
        this.newTime = newTime;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PlutoException {
        LocalDateTime currDate = tasks.getTask(idx).getDate();
        tasks.updateTime(idx, newTime);
        try {
            storage.rewriteFile(tasks);
            Task t = tasks.getTask(idx);
            return ui.rescheduleUi(t);
        } catch (IOException e) {
            tasks.updateTime(idx, currDate);
            throw new PlutoException("OOPS!!! Couldn't reschedule task. Try again!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof RescheduleCommand) {
            RescheduleCommand other = (RescheduleCommand) o;
            return this.idx == other.idx && this.newTime.equals(other.newTime);
        }
        return false;
    }
}
