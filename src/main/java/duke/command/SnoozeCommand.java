package duke.command;

import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

public class SnoozeCommand extends Command {
    private int idx;
    private int amount;
    private char type;

    public SnoozeCommand(int idx, int amount, char type) {
        this.idx = idx;
        this.amount = amount;
        this.type = type;
    }

    private static LocalDateTime addTime(LocalDateTime time, int amount, char type) throws DukeException {
        switch (type) {
            case 'M':
                return time.plusMonths(amount);
            case 'D':
                return time.plusDays(amount);
            case 'h':
                return time.plusHours(amount);
            case 'm':
                return time.plusMinutes(amount);
            default:
                throw new DukeException(Message.INVALID_SNOOZE_TYPE);
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.get(this.idx - 1);
        LocalDateTime incrementedTime = addTime(task.getTime(), this.amount, this.type);
        task.setTime(incrementedTime);
        storage.save(tasks);
        return Ui.getTaskStatusString(Message.SNOOZED, task);
    }
}
