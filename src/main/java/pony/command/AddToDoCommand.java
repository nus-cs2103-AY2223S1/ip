package pony.command;

import pony.*;
import pony.task.Task;
import pony.TaskList;
import pony.task.ToDo;

/**
 * Command for adding Event task.
 */
public class AddToDoCommand extends Command {

    private String commandDetails;

    /**
     * Constructor for AddToDoCommand.
     *
     * @param commandDetails Details for the command.
     */
    public AddToDoCommand(String commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * Executes an add ToDo command.
     *
     * @param tasks TaskList that stores Tasks.
     * @param storage Storage that handles memory files.
     * @param ui Ui that handles interaction with users.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        String message = "";
        try {
            String description = Parser.parseTodoDetails(commandDetails);
            Task newTask = new ToDo(description);
            tasks.addTask(newTask);
            message = ui.printAddedTask(newTask, tasks);
            storage.updateDisk(tasks);
        } catch (PonyException e) {
            message = e.getMessage();
        }
        return message;
    }
}

