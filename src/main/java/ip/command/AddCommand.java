package ip.command;

import java.util.Scanner;

import ip.exception.BadDeadline;
import ip.exception.BadTimespan;
import ip.exception.MissingDescription;
import ip.task.Deadline;
import ip.task.Event;
import ip.task.ToDo;
import ip.utility.TaskList;


/**
 * DukeCommand to add a task to the task list.
 */
public class AddCommand extends DukeCommand {
    /** DukeCommand given by the user */
    private final String taskType;
    /** Options following the command given */
    private final Scanner options;

    /**
     * Constructor for AddCommand.
     *
     * @param taskType Type of task to be added.
     * @param options Options to be used to add a new task.
     */
    public AddCommand(String taskType, Scanner options) {
        this.taskType = taskType;
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
    public String execute(TaskList taskList) throws MissingDescription, BadTimespan, BadDeadline {
        assert !taskType.isEmpty() : "No task type.";
        switch (taskType) {
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
            return "Cannot add task of that type.";
        }
        return "Task successfully added.";
    }
}
