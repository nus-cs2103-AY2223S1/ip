package drake.commands;

import drake.*;
import drake.tasks.Event;
import drake.tasks.Task;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a command given by the user to create a new event task.
 */
public class EventCommand extends CreateTaskCommand {

    private final static Pattern descriptionPattern = Pattern.compile("(?<taskName>.*) /at (?<at>.*)");

    /**
     * Constructor.
     *
     * @param fullInput The user input.
     */
    public EventCommand(String fullInput) {
        super(fullInput);
    }

    /**
     * Executes the command to create a new event task, saving the new task and
     * printing the size of the task list after execution.
     *
     * @param tasks The task list before the command is executed.
     * @param ui Gives access to the UI of the program.
     * @param storage Gives access to local storage.
     * @throws IOException when there is an issue with the IO.
     * @throws DrakeException when there is inappropriate input or save file issues.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DrakeException, IOException {
        Matcher match = descriptionPattern.matcher(description);
        if (!match.matches()) {
            throw new IncompatibleCommandException("An event task without an event time?");
        }
        ui.printLine("I've added this task:");
        Task addedTask = tasks.addTask(new Event(match.group("taskName"), match.group("at")));
        ui.printLine(addedTask);
        storage.addTask(addedTask);
        super.execute(tasks, ui, storage);
    }
}
