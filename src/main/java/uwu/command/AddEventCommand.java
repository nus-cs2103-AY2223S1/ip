package uwu.command;

import uwu.exception.EmptyInputException;
import uwu.exception.IncorrectFormatException;
import uwu.exception.InvalidDateException;
import uwu.exception.UwuException;
import uwu.task.Event;
import uwu.task.TaskList;
import uwu.uwu.Storage;
import uwu.uwu.Ui;

/**
 * Adds a task of type Event to the task list.
 */
public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": adds a task of type event.\n"
            + "(e.g event project meeting /at 2022-09-11 18:00)";

    public static final String MESSAGE_DETAILED_USAGE = "add an event task using the following format:"
            + "\n'event [task description] /at [date and time]'\n"
            + "make sure that your [date and time] is of the format: yyyy-MM-DD HH:mm\n"
            + "if you do not have a time, you can leave it blank~\n"
            + "here is an example, 'event christmas party /at 2022-12-25 18:00' :> \nhave fun~";

    private String userCommand;

    /**
     * Constructs an AddEventCommand object.
     *
     * @param userCommand The command the user typed.
     */
    public AddEventCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Executes the AddEventCommand which adds a task of type Event into a stored task list.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out UwuBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     * @throws UwuException If task description is empty;
     *                      If task does not contain descriptor;
     *                      If date is empty for event task.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws UwuException {
        boolean isTaskDescriptionEmpty = userCommand.toLowerCase().replaceFirst(COMMAND_WORD, "").isBlank();
        if (isTaskDescriptionEmpty) {
            throw new EmptyInputException("oops! your task description is empty ><\n"
                    + MESSAGE_DETAILED_USAGE);
        }

        int commandLength = COMMAND_WORD.length();
        String taskInformation = userCommand.substring(commandLength).trim();
        String descriptor = "/at";
        boolean hasNoDescriptor = !taskInformation.contains(descriptor);
        boolean hasNoDate = taskInformation.trim().endsWith(descriptor);

        if (hasNoDescriptor) {
            throw new IncorrectFormatException("oops! your task description is missing the descriptor: "
                    + descriptor + " ><\n"
                    + MESSAGE_DETAILED_USAGE);
        }

        if (hasNoDate) {
            throw new InvalidDateException("oops! your task description is missing the date ><\n"
                    + MESSAGE_DETAILED_USAGE);
        }

        int eventStartIndex = userCommand.indexOf(descriptor + " ");
        String description = userCommand.substring(commandLength, eventStartIndex).trim();
        boolean hasNoDescription = description.isBlank();

        if (hasNoDescription) {
            throw new EmptyInputException("oops! your task description is empty ><\n"
                    + MESSAGE_DETAILED_USAGE);
        }

        String eventStartTime = userCommand.substring(eventStartIndex + 3).trim();

        Event task = new Event(description, eventStartTime);
        tasks.add(task);
        storage.save(tasks.taskListToStorageString());
        return ui.displayAddTask(task, tasks.size());
    }

    /**
     * Returns whether AddEventCommand exits the program.
     *
     * @return false as AddCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
