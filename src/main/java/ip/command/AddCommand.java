package ip.command;

import java.util.Scanner;

import ip.exception.BadDeadline;
import ip.exception.BadTimespan;
import ip.exception.MissingDescription;
import ip.task.Deadline;
import ip.task.Event;
import ip.task.ToDo;
import ip.utility.Storage;
import ip.utility.TaskList;

/**
 * DukeCommand to add a task to the task list.
 */
public class AddCommand extends DukeCommand {
    /** DukeCommand given by the user */
    private final String taskType;
    /** Options following the command given */
    private final Scanner taskMetadata;

    /**
     * Constructor for AddCommand.
     *
     * @param taskType Type of task to be added.
     * @param taskMetadata Data to be used to add a new task.
     */
    public AddCommand(String taskType, Scanner taskMetadata) {
        this.taskType = taskType;
        this.taskMetadata = taskMetadata;
    }

    /**
     * Creates and adds the task to the task list.
     *
     * @param taskList The task list to add the task to.
     * @param storage Storage that this command is being executed in.
     * @throws MissingDescription If there is no task description given.
     * @throws BadTimespan        If the event's time span is missing.
     * @throws BadDeadline        If the deadline is incorrectly formatted or missing.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws MissingDescription, BadTimespan, BadDeadline {
        assert !taskType.isEmpty() : "No task type.";
        storage.saveToBackup(taskList);
        switch (taskType) {
        case "todo":
            taskList.add(new ToDo(taskMetadata));
            break;
        case "deadline":
            taskList.add(new Deadline(taskMetadata));
            break;
        case "event":
            taskList.add(new Event(taskMetadata));
            break;
        default:
            return "Cannot add task of that type.";
        }
        storage.saveToLatest(taskList);
        return "Added new task! Get started now!!!";
    }
}
