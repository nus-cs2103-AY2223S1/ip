package duke.command;

import duke.TaskList;

public class ReminderCommand extends Command {
    public ReminderCommand() {
        super();
    }

    @Override
    public String executeWithMessage(TaskList tasks) {
        return tasks.getReminder();
    }
}