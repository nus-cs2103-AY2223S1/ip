package drake.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import drake.DrakeException;
import drake.IncompatibleCommandException;
import drake.Storage;
import drake.TaskList;
import drake.Ui;
import drake.tasks.DoWithinPeriod;
import drake.tasks.Task;

/**
 * Represents the within command to create a new DoWithinPeriod task.
 */
public class WithinCommand extends CreateTaskCommand {

    private static final Pattern descriptionPattern =
            Pattern.compile("(?<taskName>.*) /range (?<from>.*) (?<to>.*)");
    /**
     * Constructor.
     *
     * @param fullInput The input given by the user.
     */
    public WithinCommand(String fullInput) {
        super(fullInput);
        assert fullInput.startsWith("within");
    }

    /**
     * Executes the command to create a new deadline task, saving the new task and
     * printing the size of the task list after execution.
     *
     * @param tasks   The task list before the command is executed.
     * @param ui      Gives access to the UI of the program.
     * @param storage Gives access to local storage.
     * @return The list of replies
     * @throws IOException    when there is an issue with the IO.
     * @throws DrakeException when there is inappropriate input or save file issues.
     */
    @Override
    public List<String> execute(TaskList tasks, Ui ui, Storage storage) throws DrakeException, IOException {
        ArrayList<String> reply = new ArrayList<>();
        Matcher match = descriptionPattern.matcher(description);
        if (!match.matches()) {
            throw new IncompatibleCommandException("I need two dates!");
        }
        reply.add("I've added this task:");
        Task addedTask = tasks.addTask(new DoWithinPeriod(match.group("taskName"),
                match.group("from"), match.group("to")));
        reply.add(addedTask.toString());
        storage.addTask(addedTask);
        reply.addAll(super.execute(tasks, ui, storage));
        return reply;
    }
}
