package command;

import java.time.LocalDateTime;

import storage.Storage;

import exception.DorisException;

import task.*;

import ui.Ui;

/**
 * Encapsulates a user instruction to create a new task.
 *
 * @author Marcus Low
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs a new command to create a new Todo task.
     *
     * @param description Description of the task
     */
    public AddCommand(String description) {
        this.task = new Todo(description, false);
    }

    /**
     * Constructs a new command to create a new Deadline or Event task.
     *
     * @param type Whether the task is a Deadline or Event task
     * @param description Description of the task
     * @param time When is the task is due or happening
     */
    public AddCommand(String type, String description, LocalDateTime time) {
        switch (type) {
            case "deadline":
                this.task = new Deadline(description, false, time);
                break;
            case "event":
                this.task = new Event(description, false, time);
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            TaskList.add(this.task);
            if (this.task instanceof Todo) {
                ui.showAddTodo(tasks, this.task);
            } else if (this.task instanceof Deadline) {
                ui.showAddDeadline(tasks, this.task);
            } else {
                ui.showAddEvent(tasks, this.task);
            }
            storage.save(tasks);
        } catch (DorisException e) {
            ui.showError(e);
        }
    }
}
