package dukeprogram.commands.task;

import java.util.ArrayList;
import java.util.regex.Pattern;

import dukeprogram.Task;
import dukeprogram.commands.Command;
import dukeprogram.facilities.TaskList;
import dukeprogram.ui.UserInterface;
import exceptions.InvalidCommandException;

/**
 * FindTaskCommand is capable of finding a task by querying substrings of the task name.
 * As a standalone command, this will print all the task lists. Otherwise, if a command
 * string is passed, FindTaskCommand will find all tasks that contain the string as a
 * substring of its task name.
 */
public class FindTaskCommand extends Command {
    @Override
    public boolean execute() {
        UserInterface.printInStyle(TaskList.current().getAllTasks());

        return true;
    }

    @Override
    public Command parse(String commandString) throws InvalidCommandException {
        Pattern pattern = Pattern.compile(String.format("(.*)%s(.*)", commandString));

        return new Command() {

            @Override
            public boolean execute() {
                ArrayList<Task> matches = new ArrayList<Task>();
                for (Task task : TaskList.current().getAllTasks()) {
                    if (pattern.matcher(task.getName()).matches()) {
                        matches.add(task);
                    }
                }

                UserInterface.printInStyle(matches);

                return true;
            }
        };
    }
}
