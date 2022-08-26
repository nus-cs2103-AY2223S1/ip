package DukeProgram.Commands.Task;

import DukeProgram.Commands.Command;
import DukeProgram.Commands.ExitCommand;
import DukeProgram.Commands.Task.Annotations.MarkTaskCommand;
import DukeProgram.Commands.Task.Annotations.UnmarkTaskCommand;
import DukeProgram.Facilities.TaskList;
import DukeProgram.Task;
import DukeProgram.UI.UserInterface;
import DukeProgram.UiMessage;
import Exceptions.InvalidCommandException;

public class TaskPageCommand extends Command {

    @Override
    public boolean execute() {
        UserInterface.advanceLocation(TaskList.current().getName());

        boolean continueLoop = true;
        while (continueLoop) {
            UserInterface.printCurrentLocation();
            try {
                String response = UserInterface.askForInput(
                        "[add | delete | list | mark | unmark | back]");
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

        case "add":
            return new AddTaskCommand(separatedCommands);

        case "mark":
            return new MarkTaskCommand(separatedCommands);

        case "unmark":
            return new UnmarkTaskCommand(separatedCommands);

        case "delete":
            return new DeleteTaskCommand(separatedCommands);

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
