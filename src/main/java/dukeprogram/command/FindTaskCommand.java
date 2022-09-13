package dukeprogram.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import dukeprogram.Duke;
import dukeprogram.InternalAction;
import dukeprogram.Task;
import dukeprogram.Widget;
import dukeprogram.facilities.TaskList;

/**
 * Finds a task in the task list
 */
public class FindTaskCommand extends Command {

    /**
     * Creates a FindTaskCommand
     * @param duke the instance of Duke that spawned this command
     */
    public FindTaskCommand(Duke duke) {
        super(duke);

    }

    @Override
    public void parse(Iterator<String> elements) {
        StringBuilder sb = new StringBuilder();

        while (elements.hasNext()) {
            sb.append(elements.next());
            if (elements.hasNext()) {
                sb.append(" ");
            }
        }

        Pattern pattern = Pattern.compile(String.format("(.*)%s(.*)", sb));

        ArrayList<Task> matches = new ArrayList<>();
        Arrays.stream(duke.getTaskList().getAllTasks())
                .filter(task -> pattern.matcher(task.getName()).matches())
                .forEach(matches::add);

        duke.sendMessage("Here are the matches that I've found:",
                new Widget("Matches",
                        matches.stream()
                                .map(Task::toString)
                                .collect(Collectors.joining("\n"))
                )
        );
    }
}
