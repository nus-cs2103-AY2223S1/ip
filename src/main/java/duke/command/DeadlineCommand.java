package duke.command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import duke.exception.DeadlineException;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeadlineCommand implements Command {
    private final String description;

    public DeadlineCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList)
            throws DukeException {
        List<String> deadlineInfo = Arrays.stream(description.split("/by", 2))
                .map(String::trim)
                .filter(info -> !info.isEmpty())
                .collect(Collectors.toList());

        if (deadlineInfo.size() < 2) {
            throw new DeadlineException();
        }

        Task newTask = new Deadline(deadlineInfo.get(0), deadlineInfo.get(1));
        taskList.addTask(newTask);
        ui.printTaskCreationSuccessMessage(newTask,
                taskList.getTaskListSize());
        storage.saveTasksInStorage(taskList.toStorageRepresentation());
    }
}
