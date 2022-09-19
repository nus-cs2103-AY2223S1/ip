package pony.command;

import pony.Parser;
import pony.Ui;
import pony.Storage;
import pony.PonyException;
import pony.task.Deadline;
import pony.task.Task;
import pony.task.TaskList;

import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {

    private String commandDetails;
    private String format = "<task> /by <time>";

    public AddDeadlineCommand(String commandDetails) {
        this.commandDetails = commandDetails;
    }

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