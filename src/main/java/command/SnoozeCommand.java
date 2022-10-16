package command;

import task.Task;

/**
 *  A class which encapsulates the snooze command of Duke.
 *  @author  Chen Guanzhou
 *  @version v2
 */
public class SnoozeCommand extends Command {
    Task snoozeTask;

    public SnoozeCommand(Task snoozeTask) {
        this.snoozeTask = snoozeTask;
    }

    /**
     * Executes the snooze command and delays the task by 1 day.
     * @return Duke's response which is the snoozed task to be passed into the Dialog Box.
     */
    @Override
    public String execute() {
        return snoozeTask.snooze();
    }
}
