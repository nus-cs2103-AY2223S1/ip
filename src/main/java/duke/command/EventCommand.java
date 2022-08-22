package duke.command;

import duke.exception.DukeException;
import duke.exception.EventException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EventCommand implements Command {
    private final String description;

    public EventCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        List<String> eventInfo = Arrays.stream(this.description.split("/at", 2))
                .map(String::trim)
                .filter(info -> !info.isEmpty())
                .collect(Collectors.toList());

        if (eventInfo.size() < 2) {
            throw new EventException();
        }

        Task newTask = new Event(eventInfo.get(0), eventInfo.get(1));
        taskList.addTask(newTask);
        ui.printTaskCreationSuccessMessage(newTask, taskList.getTaskListSize());
        storage.saveTasksInStorage(taskList.toStorageRepresentation());
    }
}
