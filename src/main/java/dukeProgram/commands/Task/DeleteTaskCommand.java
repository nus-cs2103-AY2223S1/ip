package dukeprogram.commands.task;

import dukeprogram.commands.Command;
import dukeprogram.facilities.TaskList;
import dukeprogram.Task;
import dukeprogram.ui.UserInterface;

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
            TaskList currentTaskList = TaskList.current();

            try {
                int index = Integer.parseInt(fullCommandParameters[1]) - 1;
                Task task = TaskList.current().get(index);
                currentTaskList.remove(index);

                UserInterface.printInStyle(
                        "Okay, I've removed this task as requested:",
                        task.toString()
                );
            } catch (NumberFormatException e) {
                UserInterface.printInStyle(
                        "I couldn't understand what you wanted to delete."
                );
            } catch (IndexOutOfBoundsException e) {
                UserInterface.printInStyle("Sorry!",
                        "You've specified a task number that's out of bounds!",
                        currentTaskList.getSize() == 0 ? "You have no tasks in this list." :
                                String.format(
                                        "Please only choose numbers between 1 and %d",
                                        currentTaskList.getSize())
                );
            }
        }

        return true;
    }
}
