package duke.command;

import duke.MessagePrinter;
import duke.Storage;
import duke.TaskList;

import java.time.LocalDateTime;

public abstract class Command {
    protected Action action;

    public Command(Action action) {
        this.action = action;
    }

    public static DoNothingCommand doNothing() {
        return new DoNothingCommand();
    }

    public static GreetCommand greet() {
        return new GreetCommand();
    }

    public static ExitCommand exit() {
        return new ExitCommand();
    }

    public static ListCommand list() {
        return new ListCommand();
    }

    public static MarkCommand mark(int idTask) {
        return new MarkCommand(idTask);
    }

    public static UnmarkCommand unmark(int idTask) {
        return new UnmarkCommand(idTask);
    }

    public static TodoCommand todo(String msg) {
        return new TodoCommand(msg);
    }

    public static EventCommand event(String msg, LocalDateTime time) {
        return new EventCommand(msg, time);
    }

    public static DeadlineCommand deadline(String msg, LocalDateTime time) {
        return new DeadlineCommand(msg, time);
    }

    public static DeleteCommand delete(int idTask) {
        return new DeleteCommand(idTask);
    }

    public static SaveCommand save() {
        return new SaveCommand();
    }

    public static ReadCommand read() {
        return new ReadCommand();
    }

    public static FindCommand find(String target) {
        return new FindCommand(target);
    }

    public abstract void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage);

    public abstract boolean isTerminated();

    public abstract String getFormat();

    public Action getAction() {
        return this.action;
    }
}
