package bestie.command;

import bestie.tasks.Deadline;
import bestie.tasks.Task;
import bestie.tasks.ToDoList;
import bestie.utils.Ui;

/**
 * Deadline Command class representing deadline command executed by the user.
 */
public class DeadlineCommand extends Command {
    private String taskName;
    private String dueDate;
    private ToDoList list;

    /**
     * Constructs deadline command.
     */
    public DeadlineCommand(String taskName, String dueDate, ToDoList list) {
        super("deadline");
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.list = list;
    }

    /**
     * Executes the deadline command by creating a new deadline instance and adding it to the list.
     *      Gives the user information about the deadline added.
     *
     * @return String representing the information about the added deadline.
     */
    @Override
    public String execute() {
        Task deadline = new Deadline(taskName, dueDate);
        list.addTask(deadline);
        return Ui.taskAddedMessage(deadline, list);
    }
}
