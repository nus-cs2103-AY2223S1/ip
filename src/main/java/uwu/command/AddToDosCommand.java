package uwu.command;

import uwu.exception.EmptyInputException;
import uwu.exception.UwuException;
import uwu.task.TaskList;
import uwu.task.ToDos;
import uwu.uwu.Storage;
import uwu.uwu.Ui;

/**
 * Adds a task of type ToDos to the task list.
 */
public class AddToDosCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": adds a task of type todo.\n"
            + "(e.g todo return book)";

    public static final String MESSAGE_DETAILED_USAGE = "add a todo task using the following format:\n"
            + "'todo [task description]'\n"
            + "here is an example, 'todo return book' :> \nhave fun~";

    private String userCommand;

    /**
     * Constructs an AddToDosCommand object.
     *
     * @param userCommand The command the user typed.
     */
    public AddToDosCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Executes the AddToDosCommand which adds a task of type ToDos into a stored task list.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out UwuBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     * @throws UwuException If task description is empty.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws UwuException {
        boolean isTaskDescriptionEmpty = userCommand.toLowerCase().replaceFirst(COMMAND_WORD, "").isBlank();
        if (isTaskDescriptionEmpty) {
            throw new EmptyInputException("oops! your task description is empty ><\n"
                    + MESSAGE_DETAILED_USAGE);
        }

        String description = userCommand.substring(COMMAND_WORD.length()).trim();
        ToDos todo = new ToDos(description);
        tasks.add(todo);
        storage.save(tasks.taskListToStorageString());
        return ui.displayAddTask(todo, tasks.size());
    }

    /**
     * Returns whether AddToDosCommand exits the program.
     *
     * @return false as AddToDosCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
