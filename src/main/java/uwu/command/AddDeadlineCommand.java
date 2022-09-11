package uwu.command;

import uwu.exception.EmptyInputException;
import uwu.exception.IncorrectFormatException;
import uwu.exception.InvalidDateException;
import uwu.exception.UwuException;
import uwu.task.Deadline;
import uwu.task.TaskList;
import uwu.uwu.Storage;
import uwu.uwu.Ui;

/**
 * Adds a task of type Deadline to the task list.
 */
public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": adds a task of type deadline.\n"
            + "(e.g deadline lab assignment /by 2022-09-25 23:59)";

    public static final String MESSAGE_DETAILED_USAGE = "add a deadline task using the following format:\n"
            + "'deadline [task description] /by [date and time]'\n"
            + "make sure that your [date and time] is of the format: yyyy-MM-DD HH:mm\n"
            + "if you do not have a time, you can leave it blank~\n"
            + "here is an example, 'deadline project /by 2022-09-20 23:59' :> \nhave fun~";

    private String userCommand;

    /**
     * Constructs an AddDeadlineCommand object.
     *
     * @param userCommand The command the user typed.
     */
    public AddDeadlineCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Executes the AddDeadlineCommand which adds a task of type Deadline into a stored task list.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out UwuBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     * @throws UwuException If task description is empty;
     *                      If task does not contain descriptor;
     *                      If date is empty for deadline task.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws UwuException {
        boolean isTaskDescriptionEmpty = userCommand.toLowerCase().replaceFirst(COMMAND_WORD, "").isBlank();
        if (isTaskDescriptionEmpty) {
            throw new EmptyInputException("oops! your task description is empty ><\n"
                    + MESSAGE_DETAILED_USAGE);
        }

        int commandLength = COMMAND_WORD.length();
        String taskInformation = userCommand.substring(commandLength).trim();
        String descriptor = "/by";
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

        int deadlineIndex = userCommand.indexOf(descriptor + " ");
        String description = userCommand.substring(commandLength, deadlineIndex).trim();
        boolean hasNoDescription = description.isBlank();

        if (hasNoDescription) {
            throw new EmptyInputException("oops! your task description is empty ><\n"
                    + MESSAGE_DETAILED_USAGE);
        }

        String deadline = userCommand.substring(deadlineIndex + 3).trim();

        Deadline task = new Deadline(description, deadline);
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
