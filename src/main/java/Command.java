import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Command {
    // at Level-7
    protected Action action;

    public abstract void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage);

    public abstract boolean isTerminated();

    public abstract String getFormat();

    public Command(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return this.action;
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

    public static class DoNothingCommand extends Command {
        public DoNothingCommand() {
            super(Action.DONOTHING);
        }

        @Override
        public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
//            do nothing
        }

        @Override
        public String getFormat() {
            return "";
        }

        @Override
        public boolean isTerminated() {
            return false;
        }
    }

    public static class GreetCommand extends Command {
        public GreetCommand() {
            super(Action.GREET);
        }

        @Override
        public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
            String LOGO = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            String HELLO_MESSAGE = "Hello! I'm Duke \n" + "What can I do for you?";
            messagePrinter.printMessage("Hello from\n" + LOGO + "\n" + HELLO_MESSAGE);
        }

        @Override
        public String getFormat() {
            return "greet";
        }

        @Override
        public boolean isTerminated() {
            return false;
        }
    }

    public static class ExitCommand extends Command {
        private ExitCommand() {
            super(Action.EXIT);
        }

        @Override
        public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
            String FAREWELL_MESSAGE = "Bye. Hope to see you again soon!";
            messagePrinter.printMessage(FAREWELL_MESSAGE);
        }

        @Override
        public String getFormat() {
            return "bye";
        }

        @Override
        public boolean isTerminated() {
            return true;
        }
    }

    public static class ListCommand extends Command {
        private ListCommand() {
            super(Action.LIST);
        }

        @Override
        public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
            String message = "Here are the tasks in your list:\n";
            if (taskList.size() == 0) {
                message = "Currently no tasks in the list.";
            } else {
                message = message + taskList.toString();
            }
            messagePrinter.printMessage(message);
        }

        @Override
        public String getFormat() {
            return "list";
        }

        @Override
        public boolean isTerminated() {
            return false;
        }
    }

    public static class MarkCommand extends Command {
        int idTask;
        private MarkCommand(int idTask) {
            super(Action.MARK);
            this.idTask = idTask;
        }

        @Override
        public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
            String successMsg = "Nice! I've marked this task as done:";
            Task task = taskList.get(idTask - 1);
            task.markAsDone();
            messagePrinter.printMessage(successMsg + "\n" + task);
        }

        @Override
        public String getFormat() {
            return "mark [ID of task]";
        }

        @Override
        public boolean isTerminated() {
            return false;
        }
    }

    public static class UnmarkCommand extends Command {
        int idTask;
        private UnmarkCommand(int idTask) {
            super(Action.UNMARK);
            this.idTask = idTask;
        }

        @Override
        public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
            String successMsg = "OK, I've marked this task as not done yet:";
            Task task = taskList.get(idTask - 1);
            task.markAsNotDone();
            messagePrinter.printMessage(successMsg + "\n" + task);
        }

        @Override
        public String getFormat() {
            return "unmark [ID of task]";
        }

        @Override
        public boolean isTerminated() {
            return false;
        }
    }

    public static abstract class AddCommand extends Command {
        String msg;

        public AddCommand(Action action, String message) {
            super(action);
            this.msg = message;
        }

        @Override
        public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
            String msg = this.msg;
            String successMsg = "Got it. I've added this Todo:";
            Task todo = Task.todo(msg);
            taskList.add(todo);
            successMsg = successMsg + "\n" + todo + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.";
            messagePrinter.printMessage(successMsg);
        }

        @Override
        public boolean isTerminated() {
            return false;
        }
    }

    public static class TodoCommand extends AddCommand {
        private TodoCommand(String msg) {
            super(Action.TODO, msg);
        }

        @Override
        public String getFormat() {
            return "todo [Name]";
        }
    }

    public static class EventCommand extends AddCommand {
        LocalDateTime time;

        private EventCommand(String msg, LocalDateTime time) {
            super(Action.EVENT, msg);
            this.time = time;
        }

        @Override
        public String getFormat() {
            return "event [Event Name] /at [Event Time(yyyy-MM-dd HH:mm)]";
        }
    }

    public static class DeadlineCommand extends AddCommand {
        String msg;
        LocalDateTime time;

        private DeadlineCommand(String msg, LocalDateTime time) {
            super(Action.DEADLINE, msg);
            this.time = time;
        }

        @Override
        public String getFormat() {
            return "deadline [Deadline Name] /by [Deadline Time(yyyy-MM-dd HH:mm)]";
        }
    }

    public static class DeleteCommand extends Command {
        int idTask;
        private DeleteCommand(int idTask) {
            super(Action.DELETE);
            this.idTask = idTask;
        }

        @Override
        public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
            String successMsg = "Noted. I've removed this task:";
            Task task = taskList.remove(idTask - 1);
            successMsg = successMsg + "\n" + task + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.";
            messagePrinter.printMessage(successMsg);
        }

        @Override
        public String getFormat() {
            return "delete [ID of Task]";
        }

        @Override
        public boolean isTerminated() {
            return false;
        }
    }

    public static class SaveCommand extends Command {
        private SaveCommand() {
            super(Action.SAVE);
        }

        @Override
        public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
            storage.write(taskList.toFormattedString());
            int size = taskList.size();
            String temp = size == 1 ? "task has" : "tasks have";
            messagePrinter.printMessage("Your " + size + " " + temp + " been saved successfully");
        }

        @Override
        public String getFormat() {
            return "save";
        }

        @Override
        public boolean isTerminated() {
            return false;
        }
    }

    public static class ReadCommand extends Command {
        private ReadCommand() {
            super(Action.READ);
        }

        @Override
        public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
            taskList.parse(storage.read());
            int size = taskList.size();
            String temp = size == 1 ? "task has" : "tasks have";
            messagePrinter.printMessage("Your " + size + " " + temp + " been loaded successfully\n"
                    + "Type [list] to view your tasks");
        }

        @Override
        public String getFormat() {
            return "read";
        }

        @Override
        public boolean isTerminated() {
            return false;
        }
    }
}
