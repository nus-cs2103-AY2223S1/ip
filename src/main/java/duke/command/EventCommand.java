package duke.command;

import java.time.LocalDateTime;

import duke.Storage;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
/**
 * The EventCommand class adds an Event task into taskList.
 */
public class EventCommand extends Command {
    private String description;
    private LocalDateTime at;

    /**
     * Constructor for a DeadlineCommand.
     * @param description description of event.
     * @param at date of task.
     */
    public EventCommand(String description, LocalDateTime at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task event = new Event(description, at);
        taskList.add(event);

        String message = ui.printAddTask(event) + '\n';
        message += ui.printSizeOfList(taskList.size());
        storage.save(taskList);
        return message;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
