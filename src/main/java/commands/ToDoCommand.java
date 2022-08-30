package commands;

import data.Task;
import data.TaskList;
import data.ToDo;
import exceptions.DukeException;
import storage.Storage;
import ui.Ui;

public class ToDoCommand extends Command {
    private final String description;

    public ToDoCommand(String description) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.add(new ToDo(description, false));
        ui.printMultiMsg(new String[]{
                "Got it. I've added this task:",
                "  " + task,
                "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list."
        });
        storage.save(tasks);
    }
}
