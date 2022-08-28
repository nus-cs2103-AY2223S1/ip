package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.function.Supplier;

import duke.core.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A command that adds a task of a specified type into a given list.
 * @param <T>
 */
public class AddTaskCommand<T extends Task> extends TaskListCommand {

    /**
     * Text to be displayed when this task is successfully executed.
     */
    private static final String taskAddedText = "Got it. I've added this task:";

    /**
     * The string that delimits arguments of this command.
     */
    private final String argumentDelimiter;

    /**
     * The supplier that supplies new tasks whenever this command is executed.
     */
    private final Supplier<? extends T> taskFactory;

    /**
     * Constructor for an AddTaskCommand.
     *
     * @param invoker           The string used to invoke the execution of this command.
     * @param taskList          The TaskList that this command adds a task to.
     * @param argumentDelimiter The string that delimits the arguments of this command.
     * @param taskFactory       The supplier that suppleis new tasks whenever this command is executed.
     */
    public AddTaskCommand(String invoker, TaskList taskList, String argumentDelimiter,
                          Supplier<? extends T> taskFactory) {
        super(invoker, taskList);
        this.argumentDelimiter = argumentDelimiter;
        this.taskFactory = taskFactory;
    }

    /**
     * Adds a task to the given TaskList using the given producer.
     *
     * @param parameters Arguments for the command, used to determine the text and details of the task.
     * @return String output of the task addition.
     * @throws DukeException
     */
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

                LocalDate localDate;

                try {
                    localDate = LocalDate.parse(splitParameters[1]);
                } catch (DateTimeParseException e) {
                    throw new DukeException("Invalid date!");
                }

                newTask.setDetails(localDate);
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

    /**
     * Generates the output of the command.
     *
     * @param t Task whose string representation is to be displayed in the output.
     * @return String output.
     */
    private String generateOutput(T t) {
        return taskAddedText + "\n  "
                + t
                + "\n"
                + taskCountText();
    }
}
