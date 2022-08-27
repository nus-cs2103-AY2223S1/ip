package dukeprogram.commands.task;

import java.util.Arrays;

import dukeprogram.UiMessage;
import dukeprogram.commands.Command;
import dukeprogram.commands.ExitCommand;
import dukeprogram.commands.task.annotations.MarkTaskCommand;
import dukeprogram.commands.task.annotations.UnmarkTaskCommand;
import dukeprogram.facilities.TaskList;
import dukeprogram.ui.UserInterface;
import exceptions.InvalidCommandException;

/**
 * TaskPageCommand is a page directory, which is able to parse various commands
 * related to a single task list. It can execute additional task related actions,
 * like adding new tasks, annotating tasks and deleting tasks.
 */
public class TaskPageCommand extends Command {

    @Override
    public boolean execute() {
        UserInterface.advanceLocation(TaskList.current().getName());

        boolean continueLoop = true;
        while (continueLoop) {
            UserInterface.printCurrentLocation();
            try {
                String response = UserInterface.askForInput(
                        "[add | delete | list | find | mark | unmark | back]");
                continueLoop = parse(response).execute();
            } catch (InvalidCommandException e) {
                UserInterface.printInStyle(e.getUiMessage().toString());
            }
        }

        UserInterface.retreatLocation();
        return true;
    }

    @Override
    public Command parse(String commandString) throws InvalidCommandException {
        String[] separatedCommands = commandString.split(" ");
        switch (separatedCommands[0]) {
        case "list":
            return new ListTasksCommand(separatedCommands);

        case "find":
            return new FindTaskCommand().parse(String.join(" ",
                    Arrays.copyOfRange(separatedCommands, 1, separatedCommands.length))
            );

        case "add":
            return new AddTaskCommand().parse(commandString);

        case "mark":
            return new MarkTaskCommand().parse(commandString);

        case "unmark":
            return new UnmarkTaskCommand().parse(commandString);

        case "delete":
            return new DeleteTaskCommand().parse(commandString);

        case "back":
            return new ExitCommand();

        default:
            throw new InvalidCommandException(this, commandString,
                    new UiMessage(
                            String.format("I can't don't know how to %s, "
                                    + "please use the commands from the square brackets!",
                                    separatedCommands[0])
                    )
            );
        }
    }
}
