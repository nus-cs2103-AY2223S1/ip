package jenny.commands;

import jenny.exceptions.JennyException;
import jenny.tasks.AbstractTask;
import jenny.tasks.TodoTask;
import jenny.util.UserInterface;

import java.util.ArrayList;

public class TodoCommand extends AbstractCommand {
    private static final String MESSAGE_SCOPE = TodoCommand.class.getSimpleName();
    public static final String COMMAND = "todo";
    private static final String ADD_TODO_SUCCESS = "Got it. I've added this task:";

    public TodoCommand(String arguments) {
        super(COMMAND);
        super.arguments = arguments;
    }

    @Override
    public void run(ArrayList<AbstractTask> tasks) throws JennyException {
        try {
            TodoTask task = new TodoTask(arguments);
            tasks.add(task);
            UserInterface.print(new String[]{
                    ADD_TODO_SUCCESS,
                    "  " + task,
                    "Now you have " + tasks.size() + " tasks in the list."
            });
        } catch (JennyException e) {
            throw new JennyException(MESSAGE_SCOPE, e.getMessage());
        }
    }
}
