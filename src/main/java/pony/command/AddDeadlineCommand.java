package pony.command;

import pony.Parser;
import pony.PonyException;
import pony.Storage;
import pony.TaskList;
import pony.Ui;

import pony.task.Deadline;
import pony.task.Task;


import java.time.format.DateTimeParseException;

/**
 * Command for adding Deadline Task.
 */
public class AddDeadlineCommand extends Command {

    private String commandDetails;
    private String format = "<task> /by <time>";

    /**
     * Constructor for AddDeadlineCommand.
     *
     * @param commandDetails Details for the command.
     */
    public AddDeadlineCommand(String commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * Executes an add Deadline command.
     *
     * @param tasks TaskList that stores Tasks.
     * @param storage Storage that handles memory files.
     * @param ui Ui that handles interaction with users.
     * @return A command message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        String message = "";
        try {
            String[] taskInfoArr = Parser.parseDeadlineDetails(commandDetails, format);
            String description = taskInfoArr[0];
            String timeInfo = taskInfoArr[1];
            Task newTask = new Deadline(description, timeInfo);
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