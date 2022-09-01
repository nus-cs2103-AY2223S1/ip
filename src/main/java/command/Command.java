package command;

import task_classes.Deadline;
import task_classes.Event;
import task_classes.Task;
import task_classes.Todo;

import java.time.LocalDate;
import java.util.HashMap;

public abstract class Command {

    public Command getCommand(String keyword) {
        return _getCommand(keyword, -1, null);
    }
    public Command getCommand(String keyword, int index) {
        return _getCommand(keyword, index, null);
    }
    public Command getCommand(String keyword, Task task) {
        return _getCommand(keyword, -1, task);
    }

    private Command _getCommand(String keyword, int index, Task task) {
        switch (keyword) {
            case "Bye":
            case "bye":
                return new ExitCommand();

            case "list":
            case "List":
                return new ListCommand();

            case "mark":
            case "Mark":
                return new MarkCommand(index);

            case "unmark":
            case "Unmark":
                return new UnmarkCommand(index);

            case "todo":
            case "Todo":
                return new AddTodoCommand((Todo) task);

            case "deadline":
            case "Deadline":
                return new AddDeadlineCommand((Deadline) task);

            case "event":
            case "Event":
                return new AddEventCommand((Event) task);

            case "delete":
            case "Delete":
            case "remove":
            case "Remove":
                return new DeleteTaskCommand(index);

            default:
                return new UnrecognizedCommand();
        }
    }

    static class ExitCommand extends Command {
        public ExitCommand() {
        }
    }

    static class ListCommand extends Command {
        public ListCommand() {

        }
    }

    static class MarkCommand extends Command {
        int index;
        public MarkCommand(int index) {
            this.index = index;
        }
    }

    static class UnmarkCommand extends Command {
        int index;
        public UnmarkCommand(int index) {
            this.index = index;
        }
    }

    static class AddTodoCommand extends Command {
        Todo todo;
        public AddTodoCommand(Todo todo) {
            this.todo = todo;
        }
    }

    static class AddDeadlineCommand extends Command {
        Deadline deadline;
        public AddDeadlineCommand(Deadline deadline) {
            this.deadline = deadline;
        }
    }

    static class AddEventCommand extends Command {
        Event event;
        public AddEventCommand(Event event) {
            this.event = event;
        }
    }

    static class DeleteTaskCommand extends Command {
        int index;
        public DeleteTaskCommand(int index) {
            this.index = index;
        }
    }

    static class UnrecognizedCommand extends Command {
        public UnrecognizedCommand() {
        }
    }
}
