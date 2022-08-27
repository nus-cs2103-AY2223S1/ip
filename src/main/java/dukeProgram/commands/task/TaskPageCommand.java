package dukeProgram.commands.task;

import dukeProgram.commands.Command;
import dukeProgram.commands.ExitCommand;
import dukeProgram.commands.task.annotations.MarkTaskCommand;
import dukeProgram.commands.task.annotations.UnmarkTaskCommand;
import dukeProgram.facilities.TaskList;
import dukeProgram.ui.UserInterface;
import dukeProgram.UiMessage;
import exceptions.InvalidCommandException;

import java.util.Arrays;

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
                            String.format("I can't don't know how to %s, " +
                                    "please use the commands from the square brackets!", separatedCommands[0])
                    )
            );
        }
    }
}
