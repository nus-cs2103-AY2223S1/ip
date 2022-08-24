package DukeProgram.Commands;

import DukeProgram.Commands.Annotations.MarkTaskCommand;
import DukeProgram.Commands.Annotations.UnmarkTaskCommand;
import DukeProgram.Facilities.TaskList;
import DukeProgram.UI.UserInterface;
import Exceptions.InvalidCommandException;

import java.util.Locale;

public class UseTaskListCommand extends Command {

    @Override
    public boolean execute() {
        UserInterface.advanceLocation("Task Lists");
        UserInterface.printCurrentLocation();

        if (TaskList.current() == null) {
            if (TaskList.getNumberOfTaskLists() < 1) {

                UserInterface.printInStyle("You have no task lists...");

                while (true) {

                    String response = UserInterface.askForInput("Add a new task list?").toLowerCase(Locale.ROOT);

                    if (response.equals("yes")) {
                        TaskList newList = TaskList.addNewTaskList(UserInterface.askForInput("Name:"));
                        accessTaskList(newList.getName());
                        break;

                    } else if (response.equals("no")) {
                        break;
                    }
                }
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
            throw new InvalidCommandException(this, commandString);
        }
    }

    private void accessTaskList(String taskListName) {
        TaskList.changeTaskList(taskListName);

        UserInterface.advanceLocation(taskListName);

        boolean continueLoop = true;
        while (continueLoop) {
            UserInterface.printCurrentLocation();
            try {
                String response = UserInterface.askForInput("What do you want to do in your task list?");
                continueLoop = parse(response).execute();
            } catch (InvalidCommandException e) {
                UserInterface.printInStyle("I didn't understand that.");
            }
        }

        UserInterface.retreatLocation();
    }
}
