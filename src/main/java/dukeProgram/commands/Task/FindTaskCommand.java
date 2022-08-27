package dukeProgram.commands.Task;

import dukeProgram.commands.Command;
import dukeProgram.facilities.TaskList;
import dukeProgram.Task;
import dukeProgram.UI.UserInterface;
import Exceptions.InvalidCommandException;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class FindTaskCommand extends Command {
    @Override
    public boolean execute() {
        return false;
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
