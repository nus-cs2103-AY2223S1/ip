package duke.command;

import java.time.LocalDateTime;

import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
/**
 * The DeadlineCommand class adds a Deadline task into taskList.
 */
public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    /**
     * Constructor for a DeadlineCommand.
     * @param description description of deadline.
     * @param by due date of task.
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task deadline = new Deadline(description, by);
        taskList.add(deadline);

        String message = ui.printAddTask(deadline) + '\n';
        message += ui.printSizeOfList(taskList.size());
        storage.save(taskList);
        return message;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
