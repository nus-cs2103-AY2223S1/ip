package commands;

import others.DukeException;
import storage.Storage;
import storage.TaskList;
import task.Task;
import ui.Ui;

public class AddCommand extends Command {
    public static final String COMMAND_WORD_DEADLINE = "deadline";
    public static final String COMMAND_WORD_EVENT = "event";
    public static final String COMMAND_WORD_TODO = "todo";
    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:\n ";
    private Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(taskToAdd);
        String successMessage = MESSAGE_SUCCESS + taskToAdd.toString()
                + "\n" + tasks.getCount();
        ui.showSuccessMessage(successMessage);
        storage.appendToFile(taskToAdd.toString(), ui);
    }

    @Override
    public boolean isRunning() {
        return true;
    }

}
