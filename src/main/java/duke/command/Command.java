package duke.command;

import java.time.LocalDateTime;

import duke.util.MessagePrinter;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents a Command to be executed by Duke.
 */
public abstract class Command {
    /**
     * The Action to be done by the Command.
     */
    protected Action action;

    /**
     * Constructor with the Action to be done by the Command.
     * @param action The Action to be done by the Command.
     */
    public Command(Action action) {
        this.action = action;
    }

    /**
     * A public static factory method to return a DoNothingCommand.
     * @return The DoNothingCommand.
     */
    public static DoNothingCommand doNothing() {
        return new DoNothingCommand();
    }

    /**
     * A public static factory method to return a GreetCommand
     * @return The GreetCommand
     */
    public static GreetCommand greet() {
        return new GreetCommand();
    }

    /**
     * A public static factory method to return an ExitCommand
     * @return The ExitCommand
     */
    public static ExitCommand exit() {
        return new ExitCommand();
    }

    /**
     * A public static factory method to return a ListCommand
     * @return The ListCommand
     */
    public static ListCommand list() {
        return new ListCommand();
    }

    /**
     * A public static factory method to return a MarkCommand to mark given Task as done in Tasklist.
     * @param idTask The index of given Task in the TaskList.
     * @return The MarkCommand to mark given Task as done in Tasklist.
     */
    public static MarkCommand mark(int idTask) {
        return new MarkCommand(idTask);
    }

    /**
     * A public static factory method to return a UnmarkCommand
     * to mark given Task as not done in the Tasklist.
     * @param idTask The index of given Task in the TaskList.
     * @return The MarkCommand to mark given Task as not done in the Tasklist.
     */
    public static UnmarkCommand unmark(int idTask) {
        return new UnmarkCommand(idTask);
    }

    /**
     * A public static factory method to return a TodoCommand to create Todo Task.
     * @param msg The information of the Task.
     * @return The TodoCommand.
     */
    public static TodoCommand todo(String msg) {
        return new TodoCommand(msg);
    }

    /**
     * A public static factory method to return an EventCommand to create Event Task.
     * @param msg The information of the Task.
     * @param time The Time of the Task.
     * @return The EventCommand
     */
    public static EventCommand event(String msg, LocalDateTime time) {
        return new EventCommand(msg, time);
    }

    /**
     * A public static factory method to return a DeadlineCommand to create Deadline Task.
     * @param msg The information of the Task.
     * @param time The Time of the Task.
     * @return The DeadlineCommand
     */
    public static DeadlineCommand deadline(String msg, LocalDateTime time) {
        return new DeadlineCommand(msg, time);
    }

    /**
     * A public static factory method to return a DeleteCommand to remove given Task from Tasklist.
     * @param idTask The index of given Task in the TaskList.
     * @return The DeleteCommand to remove given Task from Tasklist.
     */
    public static DeleteCommand delete(int idTask) {
        return new DeleteCommand(idTask);
    }

    /**
     * A public static factory method to return a SaveCommand.
     * @return The SaveCommand.
     */
    public static SaveCommand save() {
        return new SaveCommand();
    }

    /**
     * A public static factory method to return a ReadCommand.
     * @return The ReadCommand
     */
    public static ReadCommand read() {
        return new ReadCommand();
    }

    /**
     * A public static factory method to return a FindCommand.
     * @param target The String of the target.
     * @return The FindCommand
     */
    public static FindCommand find(String target) {
        return new FindCommand(target);
    }

    /**
     * Execute the Command with given Duke Segments.
     * @param taskList TaskList of the Duke.
     * @param messagePrinter MessagePrinter of the Duke.
     * @param storage Storage of the Duke.
     * @return The response of Duke.
     */
    public abstract String execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage);

    /**
     * Returns whether this command terminates Duke.
     * @return The boolean indicating whether this command terminates Duke.
     */
    public abstract boolean isTerminated();

    /**
     * Returns the standard format of the Command.
     * @return String of standard format.
     */
    public abstract String getFormat();

    /**
     * Return the Action to be done by the Command.
     * @return The Action to be done by the Command.
     */
    public Action getAction() {
        return this.action;
    }
}
