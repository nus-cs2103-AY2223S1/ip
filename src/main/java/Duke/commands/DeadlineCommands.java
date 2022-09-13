package Duke.commands;

import Duke.*;
import Duke.tasks.Deadline;
import Duke.tasks.Task;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommands extends Executor {

    private final static Pattern descriptionPattern = Pattern.compile("(?<taskName>.*) /by (?<by>.*)");


    public DeadlineCommands(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Matcher match = descriptionPattern.matcher(description);
        if (!match.matches()) {
            throw new DukeException("No deadline was given, try again");
        }
        ui.printLine("I've added this task:");
        Task addedTask = tasks.addTask(new Deadline(match.group("taskName"), match.group("by")));
        ui.printLine(addedTask);
        storage.addTask(addedTask);
        super.execute(tasks, ui, storage);
    }
}
