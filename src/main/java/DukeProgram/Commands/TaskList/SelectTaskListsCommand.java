package dukeprogram.commands.tasklist;

import dukeprogram.commands.Command;
import dukeprogram.commands.ExitCommand;
import dukeprogram.ui.UserInterface;
import dukeprogram.UiMessage;
import exceptions.InvalidCommandException;
import java.util.Arrays;

public class SelectTaskListsCommand extends Command {

    @Override
    public boolean execute() {
        UserInterface.advanceLocation("Task Lists");

        boolean continueLoop = true;

        while (continueLoop) {
            UserInterface.printCurrentLocation();

            String response = UserInterface.askForInput("[add | delete | list | load | exit]");

            try {
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
        String[] commandParameters = commandString.split(" ");
        String command = commandParameters[0];

        switch (commandParameters[0]) {
        case "exit":
            return new ExitCommand();

        case "add":
            return new AddNewTaskListCommand().parse(createTaskName(commandParameters));

        case "delete":
            return new RemoveTaskListCommand().parse(createTaskName(commandParameters));

        case "list":
            return new ShowAllTaskListsCommand();

        case "load":
            return new LoadTaskListCommand().parse(createTaskName(commandParameters));

        default:
            throw new InvalidCommandException(
                    new UiMessage(String.format("I don't know how to %s."
                                    + " Please use only commands from within the square brackets!",
                            commandParameters[0])
                    )
            );
        }
    }

    private String createTaskName (String[] commandParameters)
            throws InvalidCommandException {

        if (commandParameters.length < 2) {

            throw new InvalidCommandException(
                    new UiMessage(
                            String.format("Usage: %s tasklist_name\n"
                                            + "\t\tExample: %s MyTaskList",
                                    commandParameters[0], commandParameters[0])
                    )
            );
        }

        return String.join(" ",
                Arrays.copyOfRange(commandParameters,
                        1,
                        commandParameters.length));
    }
}
