package DukeProgram.Commands;

import DukeProgram.Facilities.TaskList;
import DukeProgram.Task;
import DukeProgram.UI.UserInterface;
import Exceptions.InvalidCommandException;

public class DeleteTaskCommand extends Command {

    private final String[] fullCommandParameters;

    public DeleteTaskCommand(String[] fullCommandParameters) {
        this.fullCommandParameters = fullCommandParameters;
    }

    @Override
    public boolean execute() {
        if (fullCommandParameters.length < 2) {
            UserInterface.printInStyle(
                    "You have to specify which task index you want to delete!");
            return true;
        }

        if (fullCommandParameters[1].equals("all")) {
            String response = UserInterface.askForInput("Are you sure you want to delete all tasks?");
            if (response.equals("yes")) {
                TaskList.current().clear();
                UserInterface.printInStyle(
                        String.format("Ok, I've annotated all the items in %s.",
                                TaskList.current().getName())
                );
            } else if (!response.equals("no")) {
                UserInterface.printInStyle("Please only input \"yes\" or \" no\"");
            }
        } else {
            try {
                int index = Integer.parseInt(fullCommandParameters[1]) - 1;
                Task task = TaskList.current().get(index);
                TaskList.current().remove(index);

                UserInterface.printInStyle(
                        "Okay, I've removed this task as requested:",
                        task.toString()
                );
            } catch (NumberFormatException e) {
                UserInterface.printInStyle(
                        "I couldn't understand what you wanted to delete."
                );
            }
        }

        return true;
    }

    @Override
    public Command parse(String commandString) throws InvalidCommandException {
        return null;
    }
}
