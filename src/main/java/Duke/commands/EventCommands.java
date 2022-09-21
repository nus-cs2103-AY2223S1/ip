package Duke.commands;

import Duke.*;
import Duke.tasks.Event;
import Duke.tasks.Task;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventCommands extends Executor {

    private final static Pattern eventPattern = Pattern.compile("(?<taskName>.*) /at (?<at>.*)");


    /**
     * Class constructor.
     *
     * @param input The input provided by the user.
     */

    public EventCommands(String input) {
        super(input);
        assert input.startsWith("event");
    }


    /**
     * Executes the command to create a new Event task, saves the new task in the task list and
     * prints the size of the list after the command execution.
     *
     * @param tasks The task list containing all the tasks before the command is executed.
     * @param ui Provides access to the UI of the program.
     * @param storage Provides access to local storage.
     * @throws IOException when there is a problem with the IO.
     * @throws DukeException when there is a wrong input or save file issues.
     */

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Matcher match = eventPattern.matcher(description);
        if (!match.matches()) {
            throw new DukeException("No event time was given, try again");
        }
        String line1 = "I've added this task:";
        Task addedTask = tasks.addTask(new Event(match.group("taskName"), match.group("at")));
        String line2 = addedTask.toString();
        storage.addTask(addedTask);
        String line3 = super.execute(tasks, ui, storage);
        String reply = line1 + "\n" + line2 + "\n" + line3;
        return reply;
    }
}
