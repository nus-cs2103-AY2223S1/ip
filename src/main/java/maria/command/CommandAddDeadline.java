package maria.command;

import java.time.LocalDate;

import maria.Storage;
import maria.Ui;
import maria.task.Task;
import maria.task.TaskList;
import maria.task.TaskNoNameException;
import maria.task.TaskDeadline;

public class CommandAddDeadline extends Command {

    private String name;
    private boolean done;
    private LocalDate deadline;

    public CommandAddDeadline(String name, boolean done, LocalDate deadline) {
        this.name = name;
        this.done = done;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        try {
            Task task = new TaskDeadline(this.name, this.done, this.deadline);
            taskList.add(task);
            ui.showText("I created a Deadline " + task + " for you. You have " + taskList.size() + " task(s) now.");
        } catch (TaskNoNameException e) {
            ui.showText("Error in creating Deadline. " + e.getMessage());
        }

    }
}