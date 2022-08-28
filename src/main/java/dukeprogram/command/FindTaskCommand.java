package dukeprogram.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import dukeprogram.InternalAction;
import dukeprogram.Task;
import dukeprogram.facilities.TaskList;

/**
 * Finds a task in the tasklist
 */
public class FindTaskCommand extends Command {

    @Override
    protected InternalAction onEnter() {
        return new InternalAction("I can help you find tasks!");
    }

    @Override
    protected InternalAction onStay() {
        return new InternalAction("Is there another task you want me to find?");
    }

    @Override
    public InternalAction onParse(String input) {
        String[] separatedCommands = input.split(" ");

        Pattern pattern = Pattern.compile(String.format("(.*)%s(.*)",
                String.join(" ",
                        Arrays.copyOfRange(separatedCommands, 1, separatedCommands.length)))
        );

        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : TaskList.current().getAllTasks()) {
            if (pattern.matcher(task.getName()).matches()) {
                matches.add(task);
            }
        }

        return new InternalAction(
                "Here are the matches that I've found:\n"
                        + matches.stream()
                        .map(Task::toString)
                        .collect(Collectors.joining("\n")));
    }

    @Override
    public Command onExit() {
        return new AccessTasksCommand();
    }
}
