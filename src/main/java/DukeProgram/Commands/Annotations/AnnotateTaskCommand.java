package DukeProgram.Commands.Annotations;

import DukeProgram.Commands.Command;
import DukeProgram.Facilities.TaskList;
import DukeProgram.Task;
import DukeProgram.UI.UserInterface;
import DukeProgram.User;
import Exceptions.InvalidCommandException;

public abstract class AnnotateTaskCommand extends Command {

    protected String[] fullCommandParameters;
    protected boolean annotationState;

    public AnnotateTaskCommand(String[] fullCommandParameters, boolean annotationState) {
        this.fullCommandParameters = fullCommandParameters;
        this.annotationState = annotationState;
    }

    @Override
    public boolean execute() {
        if (fullCommandParameters.length < 2) {
            UserInterface.printInStyle(
                    "You have to specify which task index you want to mark!");
            return true;
        }

        if (fullCommandParameters[1].equals("all")) {
            for (Task task : TaskList.current()) {
                task.MarkJobState(annotationState);
            }
            UserInterface.printInStyle(
                    String.format("Ok, I've annotated all the items in %s.", TaskList.current().getName())
            );
        } else {
            int index = Integer.parseInt(fullCommandParameters[1]) - 1;
            Task task = TaskList.current().get(index);
            task.MarkJobState(annotationState);

            UserInterface.printInStyle(
                    "Okay, I've annotated this task as requested:",
                    task.toString()
                    );
        }

        return true;
    }

    @Override
    public Command parse(String commandString) throws InvalidCommandException {
        throw new InvalidCommandException();
    }
}
