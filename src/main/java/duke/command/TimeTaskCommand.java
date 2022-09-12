package duke.command;
import java.time.LocalDateTime;

import duke.command.Command;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Represents command for TimeTask keyword
 */
public class TimeTaskCommand extends Command {
    private LocalDateTime time;

    /**
     * Instantiates a new TimeTask command
     */
    private final String keyword;
    public TimeTaskCommand(String input, String keyword, LocalDateTime time) {
        super(input);
        this.time = time;
        this.keyword = keyword;
    }

    /**
     * Executes the TimeTask command
     *
     * @param tasks The list containing all the tasks
     * @param ui User interface for printing the message
     * @param storage To write and read from a text file
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        //create new task here
        Task newTask;
        if (keyword.equals("event")) {
            newTask = new Event(super.getInput(), time);
        } else {
            newTask = new Deadline(super.getInput(), time);
        }

        String message = tasks.addTask(newTask);
        String output = ui.add(tasks.numOfTasks(), message);
        storage.save(newTask);
        return output;
    }
}
