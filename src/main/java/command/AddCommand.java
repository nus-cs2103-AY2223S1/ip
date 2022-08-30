package command;

import java.time.LocalDateTime;

import storage.Storage;

import exception.DorisException;

import tasklist.*;

import ui.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(String description) {
        this.task = new Todo(description);
    }

    public AddCommand(String type, String description, LocalDateTime time) {
        switch (type) {
            case "deadline":
                this.task = new Deadline(description, time);
                break;
            case "event":
                this.task = new Event(description, time);
        }
    }

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
