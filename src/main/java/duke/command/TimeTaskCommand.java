package duke.command;
import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Represents command for TimeTask keyword which will
 * create a new task that has time element such as
 * an Event or a Deadline task
 */
public class TimeTaskCommand extends Command {
    private LocalDateTime time;

    /**
     * Instantiates a new TimeTask command
     */
    private final String keyword;
    public TimeTaskCommand(String input, String keyword, LocalDateTime time) {
        super(input);
        assert keyword != null : "keyword for a TimeTask cannot be null";
        assert time != null : "time for a TimeTask cannot be null";
        this.time = time;
        this.keyword = keyword;
    }

    /**
     * Executes the TimeTask command which will
     * create a new task that has time element in it such as
     * an Event or a Deadline task
     *
     * @param tasks The list containing all the tasks
     * @param ui User interface for printing the message
     * @param storage To write and read from a text file
     * @return Returns a String format that contains a new TimeTask object created to be printed by gui
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
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
        } catch (DukeException e) {
            return ui.showLoadingError(e.getMessage());
        }

    }
}
