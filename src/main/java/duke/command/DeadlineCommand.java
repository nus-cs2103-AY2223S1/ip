package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    private String description;
    private LocalDate localDate;
    private LocalTime localTime;

    /**
     * Constructor for the DeadlineCommand Object.
     * @param description The description of the deadline.
     * @param localDate The date of the deadline.
     * @param localTime The time of the deadline.
     */
    public DeadlineCommand(String description, LocalDate localDate, LocalTime localTime) {
        this.description = description;
        this.localDate = localDate;
        this.localTime = localTime;
    }

    /**
     * Creates a deadline task and add it into the task list and display a message
     * that the deadline task has been added.
     * @param task The TaskList object of the chatbot.
     * @param ui The Ui object of the chatbot.
     * @param storage The storage object of the chatbot.
     */
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        Deadline currDeadline = new Deadline(this.description, this.localDate, this.localTime);
        task.addTask(currDeadline);
        ui.displayAddTask(currDeadline);
        ui.displayNumOfTasks(task.getTaskSize());
        ui.displaySeparator();
    }

    /**
     * Return true if the command is exit command.
     * @return Return true if the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
