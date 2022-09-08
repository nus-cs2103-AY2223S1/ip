package uwu.command;

import uwu.exception.EmptyInputException;
import uwu.exception.IncorrectFormatException;
import uwu.exception.InvalidDateException;
import uwu.exception.NullTaskException;
import uwu.exception.UwuException;
import uwu.task.Deadline;
import uwu.task.Event;
import uwu.task.Task;
import uwu.task.TaskList;
import uwu.task.ToDos;
import uwu.uwu.Storage;
import uwu.uwu.Ui;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {
    /** The description of the task. */
    private String description;

    /** The type of task to be added. */
    private String taskType;

    /** The user command parsed into AddCommand. */
    private String userCommand;

    /**
     * Constructor for an AddCommand object.
     *
     * @param userCommand The command the user typed.
     */
    public AddCommand(String userCommand) {
        this.userCommand = userCommand;
        String[] taskData = userCommand.split(" ", 2);
        this.taskType = taskData[0];
        this.description = taskData[1];
    }

    /**
     * Executes the AddCommand which adds a task of taskType into a stored task list.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out UwuBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     * @throws UwuException If task description is empty;
     *                      If task does not contain keyword;
     *                      If date is empty for deadline and event tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws UwuException {
        if (userCommand.replaceFirst(taskType, "").isBlank()) {
            throw new EmptyInputException("your task description is empty TT\n"
                    + "feed me a task description to get started! <:");
        }

        String response = "oops looks like something went wrong while adding your task TT";

        switch (taskType) {
        case "todo":
            ToDos todo = new ToDos(description);
            tasks.add(todo);
            storage.save(tasks.taskListToStorageString());
            response = ui.displayAddTask(todo, tasks.size());
            break;
        case "deadline":
        // Fallthrough.
        case "event":
            String descriptor = taskType.equals("deadline") ? "/by" : "/at";

            if (!description.contains(descriptor)) {
                throw new IncorrectFormatException("please make sure your task contains the keyword "
                        + descriptor + " ><!");
            }

            if (description.trim().endsWith(descriptor)) {
                throw new InvalidDateException("uwu it looks like the date is missing~"
                        + "\nplease enter a date after the " + descriptor
                        + " in this format:" + "\n\tyyyy-mm-dd HH:mm"
                        + "\nthankiew <:");
            }

            int startIndex = userCommand.indexOf(descriptor + " ");
            int userCmdLen = userCommand.length();
            String description = userCommand.substring(taskType.length(), startIndex).trim();

            if (description.isBlank()) {
                throw new EmptyInputException("your task description is empty TT\n"
                        + "feed me a task description to get started! <:");
            }

            String start = userCommand.substring(startIndex + 3, userCmdLen).trim();

            Task task = taskType.equals("deadline") ? new Deadline(description, start) : new Event(description, start);
            tasks.add(task);
            storage.save(tasks.taskListToStorageString());
            response = ui.displayAddTask(task, tasks.size());
            break;
        default:
            throw new NullTaskException("\tsomething went wrong while adding your task..."
                    + "\n\tmake sure your task is of type todo, deadline or event~!");
        }

        return response;
    }

    /**
     * Returns whether AddCommand exits the program.
     *
     * @return false as AddCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
