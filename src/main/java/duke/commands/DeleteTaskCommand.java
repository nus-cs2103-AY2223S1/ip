package duke.commands;

import duke.exceptions.TaskNotFoundException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.utils.Storage;

public class DeleteTaskCommand extends Command {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private String index;

    public DeleteTaskCommand(Storage storage, Ui ui, TaskList tasks, String index) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public String execute() {
        try {
            Task deletedTask = tasks.deleteTask(Integer.parseInt(index) - 1);
            storage.saveToFile(tasks.getList());

            String response = String.format(
                    "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list",
                    deletedTask, tasks.getSize()
            );
            return response;
        } catch (TaskNotFoundException e) {
            String response = String.format("Oops! %s", e.getMessage());
            return response;
        } catch (NumberFormatException e) {
            String response = String.format("Oops! I could not recognise this format: %s", index);
            return response;
        }
    }
}
