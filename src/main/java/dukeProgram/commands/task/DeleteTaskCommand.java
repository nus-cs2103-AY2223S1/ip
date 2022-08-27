package dukeProgram.commands.task;

import dukeProgram.commands.Command;
import dukeProgram.facilities.TaskList;
import dukeProgram.Task;
import dukeProgram.ui.UserInterface;
import dukeProgram.UiMessage;
import exceptions.InvalidCommandException;

public class DeleteTaskCommand extends Command {

    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public Command parse(String commandString) throws InvalidCommandException {
        String[] fullCommandParameters = commandString.split(" ");
        if (fullCommandParameters.length < 2) {
            throw new InvalidCommandException(
                    new UiMessage(
                            "You have to specify which task index you want to delete!")
            );
        }

        TaskList currentTaskList = TaskList.current();
        int index;
        if (fullCommandParameters[1].equals("all")) {
            index = -1;
        } else {
            try {
                index = Integer.parseInt(fullCommandParameters[1]) - 1;
            } catch (NumberFormatException e) {
                throw new InvalidCommandException(new UiMessage(
                        "I couldn't understand what you wanted to delete."
                ));
            }

            if (index >= currentTaskList.getSize() || index < 0) {
                throw new InvalidCommandException(new UiMessage(
                        "Sorry!",
                        "You've specified a task number that's out of bounds!",
                        currentTaskList.getSize() == 0 ?
                                "You have no tasks in this list." :
                                String.format("Please only choose numbers between 1 and %d",
                                        currentTaskList.getSize())
                ));
            }
        }

        return new Command() {
            @Override
            public boolean execute() {
                if (index == -1) {
                    String response = UserInterface.askForInput("Are you sure you want to delete all tasks?");
                    if (response.equals("yes")) {
                        TaskList.current().clear();
                        UserInterface.printInStyle(
                                String.format("Ok, I've deleted all the items in %s.",
                                        TaskList.current().getName())
                        );
                    } else if (!response.equals("no")) {
                        UserInterface.printInStyle("Please only input \"yes\" or \" no\"");
                    }
                } else {
                    Task task = TaskList.current().get(index);
                    currentTaskList.remove(index);

                    UserInterface.printInStyle(
                            "Okay, I've removed this task as requested:",
                            task.toString()
                    );
                }
                return true;
            }
        };
    }
}
