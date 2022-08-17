package duke.commands;

import duke.core.DukeException;
import duke.task.*;

import java.util.function.Supplier;

public class AddTaskCommand<T extends Task> extends TaskListCommand {

    private final String argumentDelimiter;
    private final Supplier<? extends T> taskFactory;

    private static final String taskAddedText = "Got it. I've added this task:";
    private static final String taskListText = "Now you have %s tasks in the list.";

    public AddTaskCommand(String invoker, TaskList taskList, String argumentDelimiter, Supplier<? extends T> taskFactory) {
        super(invoker, taskList);
        this.argumentDelimiter = argumentDelimiter;
        this.taskFactory = taskFactory;
    }

    @Override
    public String execute(String parameters) throws DukeException {

        if (parameters.equals("")) {
            throw new DukeException("No arguments given!");
        }

        T newTask;
        if (argumentDelimiter != null) {
            String[] splitParameters = parameters.split(argumentDelimiter, 2);
            if (splitParameters.length > 1) {
                newTask = taskFactory.get();
                newTask.setText(splitParameters[0]);
                newTask.setDetails(splitParameters[1]);
                taskList.addTask(newTask);
            } else {
                throw taskFactory.get().invalidParameterError();
            }
        } else {
            newTask = taskFactory.get();
            newTask.setText(parameters);
            taskList.addTask(newTask);
        }
        return generateOutput(newTask);
    }

    public String generateOutput(T t) {
        return taskAddedText + "\n  "
                + t
                + "\n"
                + taskCountText();
    }
}
