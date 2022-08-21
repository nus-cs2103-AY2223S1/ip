package maria.command;

import java.time.LocalDate;

import maria.Storage;
import maria.Ui;
import maria.task.Task;
import maria.task.TaskList;
import maria.task.TaskNoNameException;
import maria.task.TaskEvent;

public class CommandAddEvent extends Command {

    private String name;
    private boolean done;
    private LocalDate start;
    private LocalDate end;

    public CommandAddEvent(String name, boolean done, LocalDate start, LocalDate end) {
        this.name = name;
        this.done = done;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        try {
            Task task = new TaskEvent(this.name, this.done, this.start, this.end);
            taskList.add(task);
            ui.showText("I created a Event " + task + " for you. You have " + taskList.size() + " task(s) now.");
        } catch (TaskNoNameException e) {
            ui.showText("Error in creating Event. " + e.getMessage());
        }

    }
}