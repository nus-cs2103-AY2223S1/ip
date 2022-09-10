package drake.commands;

import drake.*;
import drake.tasks.Event;
import drake.tasks.Task;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventCommand extends CreateTaskCommand {

    private final static Pattern descriptionPattern = Pattern.compile("(?<taskName>.*) /at (?<at>.*)");

    public EventCommand(String fullInput) {
        super(fullInput);
    }

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
