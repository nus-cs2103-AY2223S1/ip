package dukeprogram.commands.task.annotations;

import dukeprogram.Task;
import dukeprogram.UiMessage;
import dukeprogram.commands.Command;
import dukeprogram.facilities.TaskList;
import dukeprogram.ui.UserInterface;
import exceptions.InvalidCommandException;

/**
 * AnnotateTaskCommand is an abstract class that provides functionalities
 * to update the completion status of Tasks without defining what to mark
 * them as.
 */
public abstract class AnnotateTaskCommand extends Command {

    protected boolean annotationState;

    public AnnotateTaskCommand(boolean annotationState) {
        this.annotationState = annotationState;
    }

    @Override
    public boolean execute() {
        return false;
    }

    /**
     * Parses a task number and annotates the task with that index
     * @param commandString the index of the task
     * @return a new Command to annotate the task
     * @throws InvalidCommandException if
     */
    @Override
    public Command parse(String commandString) throws InvalidCommandException {
        String[] fullCommandParameters = commandString.split(" ");
        if (fullCommandParameters.length < 2) {
            throw new InvalidCommandException(
                    new UiMessage("You have to specify which task index you want to mark!")
            );
        }

        return new Command() {
            @Override
            public boolean execute() {
                if (fullCommandParameters[1].equals("all")) {
                    for (Task task : TaskList.current().getAllTasks()) {
                        task.markJobState(annotationState);
                    }
                    UserInterface.printInStyle(
                            String.format("Ok, I've annotated all the items in %s.", TaskList.current().getName())
                    );
                } else {
                    int index = Integer.parseInt(fullCommandParameters[1]) - 1;
                    Task task = TaskList.current().get(index);
                    task.markJobState(annotationState);

                    UserInterface.printInStyle(
                            "Okay, I've annotated this task as requested:",
                            task.toString()
                    );
                }
                return true;
            }
        };
    }
}
