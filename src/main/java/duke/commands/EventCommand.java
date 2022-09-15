package duke.commands;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Event;
import duke.tasks.Task;

/**
 * Command for adding Event tasks.
 */
public class EventCommand extends TaskCommand {
    private String remainingCommand;

    /**
     * EventCommand constructor.
     *
     * @param remainingCommand Event description and at.
     */
    public EventCommand(String remainingCommand) {
        this.remainingCommand = remainingCommand;
    }

    /**
     * Executes a Event command.
     *
     * @param tasks TaskList that stores Tasks objects.
     * @param ui Ui that handles user interaction.
     * @param storage Storage that handles storing information on memory files.
     * @throws DukeException if instruction format is wrong or if task description exists.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] splitCommand = remainingCommand.split(" /at ");
            if (splitCommand.length != 2) {
                throw new DukeException("OOPS!!! Please enter a valid event format "
                        + "(event <description> /at yyyy-mm-dd)");
            }

            LocalDate d1 = LocalDate.parse(splitCommand[1]);
            String description = splitCommand[0];
            LocalDate date = d1;

            if (taskDescriptionExists(tasks, description)) {
                throw new DukeException("OOPS!!! The task description already exists. Please use a different "
                        + "task description.");
            }

            Task task = new Event(description, date);
            tasks.addTask(task, description);
            storage.addTaskToDisk(task.taskMemo() + System.lineSeparator());

            return ui.printAddTask(task, tasks.getTaskListSize());
        } catch (IOException e) {
            return (e.getMessage());
        } catch (DateTimeParseException e) {
            return ("OOPS!!! Please enter a valid date format (/at yyyy-mm-dd)");
        }
    }

    /**
     * Checks if program should exit.
     *
     * @return false as program should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
