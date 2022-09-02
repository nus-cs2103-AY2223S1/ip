package command;

import task_classes.Deadline;
import task_classes.Event;
import task_classes.Task;
import task_classes.Todo;
import utils.Parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Command {

    public abstract String exec(ArrayList<Task> taskList);

    public static Command getCommand(Parser.ParsedInputArguments inputArgs) {
        switch (inputArgs.keyword) {
            case "Bye":
            case "bye":
                return new ExitCommand();

            case "list":
            case "List":
                return new ListCommand();

            case "mark":
            case "Mark":
                return new MarkCommand(Integer.parseInt(inputArgs.args));

            case "unmark":
            case "Unmark":
                return new UnmarkCommand(Integer.parseInt(inputArgs.args));

            case "delete":
            case "Delete":
            case "remove":
            case "Remove":
                return new DeleteTaskCommand(Integer.parseInt(inputArgs.args));

            case "todo":
            case "Todo":
                return new AddTodoCommand(new Todo(inputArgs.args));

            case "deadline":
            case "Deadline":
                return new AddDeadlineCommand(new Deadline(inputArgs.args, inputArgs.getFlag("/by")));

            case "event":
            case "Event":
                return new AddEventCommand(new Event(inputArgs.args, inputArgs.getFlag("/at")));

            default:
                return new UnrecognizedCommand();
        }
    }

    static class ExitCommand extends Command {
        public ExitCommand() {
        }

        @Override
        public String exec(ArrayList<Task> taskList) {
//            TODO: Do something to close the app here
            return "Bye. Hope to see you again!";
        }
    }

    static class ListCommand extends Command {
        public ListCommand() {

        }

        @Override
        public String exec(ArrayList<Task> taskList) {
            String returnMsg = "";
            int index = 1;

            for (Task t : taskList) {
                returnMsg += index + ". " + t + "\n";
                index++;
            }
            return returnMsg;
        }
    }

    static class MarkCommand extends Command {
        int index;
        public MarkCommand(int index) {
            if (index < 0) {
                throw new IllegalArgumentException();
            }
            this.index = index - 1;
        }

        @Override
        public String exec(ArrayList<Task> taskList) {

            Task task = taskList.get(index);
            task.setDone();

            return "Nice! I've marked this as done: \n" + task;
        }
    }

    static class UnmarkCommand extends Command {
        int index;
        public UnmarkCommand(int index) {
            if (index < 0) {
                throw new IllegalArgumentException();
            }
            this.index = index - 1;
        }

        @Override
        public String exec(ArrayList<Task> taskList) {
            Task task = taskList.get(this.index);
            task.setNotDone();

            return "OK, I've marked this task as not done yet: \n" + task;
        }
    }

    static class AddTodoCommand extends Command {
        Todo todo;
        public AddTodoCommand(Todo todo) {
            if (todo == null) {
                throw new IllegalArgumentException();
            }
            this.todo = todo;
        }

        @Override
        public String exec(ArrayList<Task> taskList) {
            taskList.add(this.todo);
            return "Got it. I've added this task: \n" +
                    this.todo.toString() + "\n" +
                    "Now you have " + taskList.size() + " tasks in the list.";
        }
    }

    static class AddDeadlineCommand extends Command {
        Deadline deadline;
        public AddDeadlineCommand(Deadline deadline) {
            if (deadline == null) {
                throw new IllegalArgumentException();
            }
            this.deadline = deadline;
        }

        @Override
        public String exec(ArrayList<Task> taskList) {

            taskList.add(this.deadline);
            return "Got it. I've added this task: \n" +
                    this.deadline.toString() + "\n" +
                    "Now you have " + taskList.size() + " tasks in the list.";
        }
    }

    static class AddEventCommand extends Command {
        Event event;
        public AddEventCommand(Event event) {
            if (event == null) {
                throw new IllegalArgumentException();
            }
            this.event = event;
        }

        @Override
        public String exec(ArrayList<Task> taskList) {
            taskList.add(this.event);

            return "Got it. I've added this task: \n" +
                    this.event.toString() + "\n" +
                    "Now you have " + taskList.size() + " tasks in the list.";
        }
    }

    static class DeleteTaskCommand extends Command {
        int index;
        public DeleteTaskCommand(int index) {
            if (index <= 0) {
                throw new IllegalArgumentException();
            }
            this.index = index - 1;
        }

        public String exec(ArrayList<Task> taskList) {
            Task task = taskList.get(index);
            taskList.remove(index);
            return "Noted. I've removed this task: \n" +
                    task.toString() + "\n" +
                    "Now you have " + taskList.size() + " tasks in the list.";
        }
    }

    static class UnrecognizedCommand extends Command {
        public UnrecognizedCommand() {
        }

        @Override
        public String exec(ArrayList<Task> taskList) {
            return "Unrecognized command";
        }
    }
}
