package duke.commands;

import java.time.LocalDateTime;

import duke.exceptions.DukeException;
import duke.gui.GuiText;
import duke.tasks.Task;
import duke.tasks.TaskWithDateTime;
import duke.tools.SessionManager;
import duke.tools.Storage;
import duke.tools.TaskList;

public class SnoozeCommand implements Command {

    private int index;
    private LocalDateTime dateTime;

    public SnoozeCommand(int index, LocalDateTime dateTime) {
        this.index = index;
        this.dateTime = dateTime;
    }

    @Override
    public String execute() throws DukeException {
        TaskList taskList = SessionManager.getTaskList();
        Storage storage = SessionManager.getStorage();
        Task task = taskList.getTask(index);
        if (task instanceof TaskWithDateTime) {
            ((TaskWithDateTime) task).setDateTime(dateTime);
            storage.writeToFile(taskList);
            return GuiText.formatSnooze(index, ((TaskWithDateTime) task));
        } else {
            throw new DukeException("Exception: This task does not have a date and time.");
        }
    }
}
