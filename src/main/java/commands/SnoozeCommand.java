package commands;

import java.time.LocalDate;

import exception.FredException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Snooze command to change the date of a task
 */
public class SnoozeCommand extends Command {

    protected int taskIndex;
    protected LocalDate date;

    /**
     * Create SnoozeCommand
     * @param taskIndex Index of task to be snoozed
     * @param date Date that task is to be snoozed to
     */
    public SnoozeCommand(int taskIndex, LocalDate date) {
        isExit = false;
        this.taskIndex = taskIndex;
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FredException {
        ui.storeMessage(tasks.snooze(taskIndex, date));
    }
}
