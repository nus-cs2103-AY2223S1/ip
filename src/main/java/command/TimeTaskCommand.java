package command;
import java.time.LocalDateTime;

import task.Deadline;
import task.Event;
import task.Task;
import tasklist.TaskList;
import utility.Storage;
import utility.Ui;

/**
 * Represents command for TimeTask keyword
 */
public class TimeTaskCommand extends Command{
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //create new task here
        Task newTask;
        if (keyword.equals("event")) {
            newTask = new Event(super.getInput(), time); 
        } else {
            newTask = new Deadline(super.getInput(), time); 
        }

        String message = tasks.addTask(newTask);
        ui.add(tasks.numOfTasks(), message);
        storage.save(newTask);
    }
}
