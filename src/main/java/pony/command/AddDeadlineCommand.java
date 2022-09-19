package pony.command;

import pony.*;
import pony.task.Deadline;
import pony.task.Task;
import pony.TaskList;

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
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            String[] taskInfoArr = Parser.parseDeadlineDetails(commandDetails, format);
            String description = taskInfoArr[0];
            String timeInfo = taskInfoArr[1];
            Task newTask = new Deadline(description, timeInfo);
            tasks.addTask(newTask);
            ui.printAddedTask(newTask, tasks);
            storage.updateDisk(tasks);
        } catch (PonyException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Please follow this date format: yyyy-MM-dd HH:mm");
        }
    }
}