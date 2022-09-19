package pony.command;

import pony.*;
import pony.task.Event;
import pony.task.Task;
import pony.TaskList;

import java.time.format.DateTimeParseException;

/**
 * Command for adding Event Task.
 */
public class AddEventCommand extends Command {

    private String commandDetails;
    private String format = "<task> /at <time>";

    /**
     * Constructor for AddEventCommand.
     *
     * @param commandDetails Details for the command.
     */
    public AddEventCommand(String commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * Executes an add Event command.
     *
     * @param tasks TaskList that stores Tasks.
     * @param storage Storage that handles memory files.
     * @param ui Ui that handles interaction with users.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        String message = "";
        try {
            String[] taskInfoArr = Parser.parseEventDetails(commandDetails, format);
            String description = taskInfoArr[0];
            String timeInfo = taskInfoArr[1];
            Task newTask = new Event(description, timeInfo);
            tasks.addTask(newTask);
            message = ui.printAddedTask(newTask, tasks);
            storage.updateDisk(tasks);
        } catch (PonyException e) {
            message = e.getMessage();
        } catch (DateTimeParseException e) {
            message = "Please follow this date format: yyyy-MM-dd HH:mm";
        }
        return message;
    }
}