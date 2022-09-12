package duke.command;

import duke.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.parser.Parser;

import java.util.Iterator;

/**
 * An abstract Command class for all command classes to inherit from.
 * Use factory method to get an instance.
 */
public abstract class Command {

    /**
     * Executes the given command.
     * This should be overridden by all Command subclasses.
     *
     * @param taskList a TaskList object for command to be executed on
     * @return a string object representing the response given after the command.
     */
    public abstract String exec(TaskList taskList);

    /**
     * Executes the given command.
     * This should be overridden by all Command subclasses.
     *
     * @param mainTaskList   the overall Task List in which the command should have an effect on.
     * @param targetTaskList the target Task List for purposes of indexing.
     * @return a string object representing the response given after the command.
     */
    public abstract String exec(TaskList mainTaskList, TaskList targetTaskList);

    /**
     * Check if the given command is an exit command.
     *
     * @return true only if the Command is an exit command.
     */
    public boolean isExit() {
        return false;
    }

    public boolean returnsTaskList() {
        return false;
    }

    public TaskList getTaskList() {
        return null;
    }

    /**
     * Returns the relevant command object from a Parser.ParsedInputArguments object.
     *
     * @param inputArgs an input argument object representing input
     * @return a Command object
     */
    public static Command getCommand(Parser.ParsedInputArguments inputArgs) {
        switch (inputArgs.keyword) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "mark":
                return new MarkCommand(Integer.parseInt(inputArgs.args));

            case "unmark":
                return new UnmarkCommand(Integer.parseInt(inputArgs.args));

            case "find":
                return new FindCommand(inputArgs.args);

            case "delete":
            case "remove":
                return new DeleteTaskCommand(Integer.parseInt(inputArgs.args));

            case "todo":
                return new AddTodoCommand(new Todo(inputArgs.args));

            case "deadline":
                return new AddDeadlineCommand(new Deadline(inputArgs.args, inputArgs.getFlag("/by")));

            case "event":
                return new AddEventCommand(new Event(inputArgs.args, inputArgs.getFlag("/at")));

            default:
                return new UnrecognizedCommand();
        }
    }

    static class ExitCommand extends Command {
        public ExitCommand() {
        }

        @Override
        public String exec(TaskList taskList) {
            return "Bye. Hope to see you again!";
        }

        @Override
        public String exec(TaskList mainTaskList, TaskList targetTaskList) {
            return "Bye. Hope to see you again!";
        }

        @Override
        public boolean isExit() {
            return true;
        }

    }

    static class ListCommand extends Command {
        private TaskList resultTaskList;
        public ListCommand() {

        }

        @Override
        public String exec(TaskList taskList) {
            this.resultTaskList = taskList;
            return "There are " + this.resultTaskList.size() + " items in your list.";
        }

        @Override
        public String exec(TaskList mainTaskList, TaskList targetTaskList) {
            this.resultTaskList = mainTaskList;
            return "There are " + this.resultTaskList.size() + " items in your list.";
        }

        @Override
        public boolean returnsTaskList() {
            return true;
        }

        @Override
        public TaskList getTaskList() {
            assert (resultTaskList != null);
            return resultTaskList;
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
        public String exec(TaskList taskList) {

            Task task = taskList.get(index);
            task.setDone();

            return "Nice! I've marked this as done: \n" + task;
        }

        @Override
        public String exec(TaskList mainTaskList, TaskList targetTaskList) {
            Task task = targetTaskList.get(index);
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
        public String exec(TaskList taskList) {
            Task task = taskList.get(this.index);
            task.setNotDone();

            return "OK, I've marked this task as not done yet: \n" + task;
        }

        @Override
        public String exec(TaskList mainTaskList, TaskList targetTaskList) {
            Task task = targetTaskList.get(index);
            task.setNotDone();

            return "OK, I've marked this task as not done yet: \n" + task;
        }

    }

    static class FindCommand extends Command {
        String args = "";

        public FindCommand(String args) {
            this.args = args;
        }

        private TaskList taskList;

        @Override
        public String exec(TaskList taskList) {
            this.taskList = new TaskList();
            for (Iterator<Task> i = taskList.getIterator(); i.hasNext(); ) {
                Task t = i.next();

                if (t.getDescription().contains(args)) {
                    this.taskList.add(t);
                }
            }

            if (this.taskList.size() > 0) {
                return "Here are the matching tasks in your list:\n" + this.taskList.toString();
            } else {
                return "There are no matching tasks in your list.";
            }
        }

        @Override
        public String exec(TaskList mainTaskList, TaskList targetTaskList) {
            this.taskList = new TaskList();
            for (Iterator<Task> i = mainTaskList.getIterator(); i.hasNext(); ) {
                Task t = i.next();

                if (t.getDescription().contains(args)) {
                    this.taskList.add(t);
                }
            }

            if (this.taskList.size() > 0) {
                return "Here are the matching tasks in your list:\n" + this.taskList.toString();
            } else {
                return "There are no matching tasks in your list.";
            }
        }

        @Override
        public boolean returnsTaskList() {
            return true;
        }

        @Override
        public TaskList getTaskList() {
            return taskList;
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
        public String exec(TaskList taskList) {
            taskList.add(this.todo);
            return "Got it. I've added this task: \n" +
                    this.todo.toString() + "\n" +
                    "Now you have " + taskList.size() + " tasks in the list.";
        }

        @Override
        public String exec(TaskList mainTaskList, TaskList targetTaskList) {
            mainTaskList.add(this.todo);
            return "Got it. I've added this task: \n" +
                    this.todo.toString() + "\n" +
                    "Now you have " + mainTaskList.size() + " tasks in the list.";
        }

        @Override
        public boolean returnsTaskList() {
            return true;
        }

        @Override
        public TaskList getTaskList() {
            TaskList tl = new TaskList();
            tl.add(todo);
            return tl;
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
        public String exec(TaskList taskList) {

            taskList.add(this.deadline);
            return "Got it. I've added this task: \n" +
                    this.deadline.toString() + "\n" +
                    "Now you have " + taskList.size() + " tasks in the list.";
        }

        @Override
        public String exec(TaskList mainTaskList, TaskList targetTaskList) {
            mainTaskList.add(this.deadline);
            return "Got it. I've added this task: \n" +
                    this.deadline.toString() + "\n" +
                    "Now you have " + mainTaskList.size() + " tasks in the list.";
        }

        @Override
        public boolean returnsTaskList() {
            return true;
        }

        @Override
        public TaskList getTaskList() {
            TaskList tl = new TaskList();
            tl.add(deadline);
            return tl;
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
        public String exec(TaskList taskList) {
            taskList.add(this.event);

            return "Got it. I've added this task: \n" +
                    this.event.toString() + "\n" +
                    "Now you have " + taskList.size() + " tasks in the list.";
        }

        @Override
        public String exec(TaskList mainTaskList, TaskList targetTaskList) {
            mainTaskList.add(this.event);

            return "Got it. I've added this task: \n" +
                    this.event.toString() + "\n" +
                    "Now you have " + mainTaskList.size() + " tasks in the list.";
        }

        @Override
        public boolean returnsTaskList() {
            return true;
        }

        @Override
        public TaskList getTaskList() {
            TaskList tl = new TaskList();
            tl.add(event);
            return tl;
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

        public String exec(TaskList taskList) {
            Task task = taskList.get(index);
            taskList.remove(index);
            return "Noted. I've removed this task: \n" +
                    task.toString() + "\n" +
                    "Now you have " + taskList.size() + " tasks in the list.";
        }

        @Override
        public String exec(TaskList mainTaskList, TaskList targetTaskList) {
            Task task = targetTaskList.get(index);
            mainTaskList.remove(mainTaskList.indexOf(task));
            targetTaskList.remove(mainTaskList.indexOf(task));
            return "Noted. I've removed this task: \n" +
                    task.toString() + "\n" +
                    "Now you have " + mainTaskList.size() + " tasks in the list.";
        }

    }

    static class UnrecognizedCommand extends Command {
        public UnrecognizedCommand() {
        }

        @Override
        public String exec(TaskList taskList) {
            return "Unrecognized command, type 'help' to get help.";
        }

        @Override
        public String exec(TaskList mainTaskList, TaskList targetTaskList) {
            return "Unrecognized command, type 'help' to get help.";
        }
    }
}
