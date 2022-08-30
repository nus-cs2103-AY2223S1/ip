package ip.command;

import java.util.Scanner;

import ip.TaskList;
import ip.exception.BadDeadline;
import ip.exception.BadTimespan;
import ip.exception.MissingDescription;
import ip.task.Deadline;
import ip.task.Event;
import ip.task.ToDo;


/**
 * Command to add a task to the task list.
 */
public class AddCommand extends Command {
    /** Command given by the user */
    private final String commandGiven;
    /** Options following the command given */
    private final Scanner options;

    /**
     * Constructor for AddCommand.
     *
     * @param commandGiven Type of add command given.
     * @param options Options to be used to add a new task.
     */
    public AddCommand(String commandGiven, Scanner options) {
        this.commandGiven = commandGiven;
        this.options = options;
    }

    /**
     * Creates and adds the task to the task list.
     *
     * @param taskList The task list to add the task to.
     * @throws MissingDescription If there is no task description given.
     * @throws BadTimespan If the event's timespan is missing.
     * @throws BadDeadline If the deadline is incorrectly formatted or missing.
     */
    @Override
    public void execute(TaskList taskList) throws MissingDescription, BadTimespan, BadDeadline {
        switch (commandGiven) {
        case "todo":
            taskList.add(new ToDo(options));
            break;
        case "deadline":
            taskList.add(new Deadline(options));
            break;
        case "event":
            taskList.add(new Event(options));
            break;
        default:
            System.out.println("Add command not recognized.");
        }
    }
}
