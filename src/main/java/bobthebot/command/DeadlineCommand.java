package bobthebot.command;

import bobthebot.tasks.Deadline;
import bobthebot.tasks.Task;
import bobthebot.tasks.ToDoList;
import bobthebot.utils.Storage;
import bobthebot.utils.Ui;

public class DeadlineCommand extends Command {
    private String taskName;
    private String dueDate;
    private ToDoList list;

    public DeadlineCommand(String taskName, String dueDate, ToDoList list) {
        super("deadline");
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.list = list;
    }

    @Override
    public String execute() {
        Task deadline = new Deadline(taskName, dueDate);
        list.addTask(deadline);
        return Ui.taskAddedMessage(deadline, list);
    }
}
