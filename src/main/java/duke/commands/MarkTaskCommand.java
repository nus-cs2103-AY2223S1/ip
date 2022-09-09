package duke.commands;

import duke.exceptions.TaskNotFoundException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.ui.Ui;

public class MarkTaskCommand extends Command {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    private String index;

    public MarkTaskCommand(Storage storage, Ui ui, TaskList tasks, String index) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public String execute() {
        try {
            Task markedTask = tasks.markTask(Integer.parseInt(index) - 1);
            storage.saveToFile(tasks.getList());

            String response = String.format(
                    "Nice! I've marked this task as done:\n  %s",
                    markedTask);
            return response;
        } catch (TaskNotFoundException e) {
            String response = String.format("Oops! %s", e.getMessage());
            return response;
        } catch (NumberFormatException e) {
            String response = String.format("Oops! I could not recognise this format: %s\n", index);
            return response;
        }
    }

}
