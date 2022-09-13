package duke.command;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;


/**
 * Represents a Command class.
 *
 * @author Khor Jun Wei
 * @version CS2103T AY22/23 Sem 1
 */
public abstract class Command {

    /**
     * Represents whether commands can still be made.
     */
    protected boolean isOngoing;

    /**
     * Represents the type of commands the class understands.
     */
    public enum ActionKeywords {
        DEADLINE,
        DELETE,
        EVENT,
        FIND,
        LIST,
        MARK,
        REMIND,
        TODO,
        UNMARK,
    }

    /**
     * Constructs a Command class.
     */
    private Command() {
        this.isOngoing = true;
    }

    /**
     * Creates Command class through a constructor method.
     * @param s String of details.
     * @return Command.
     * @throws DukeException If any is found.
     */
    public static Command of(String s) throws DukeException {
        String[] splitResponse = s.split(" ");
        String keyword = splitResponse[0];
        if (splitResponse.length == 1) {
            return handleSingleWordCommand(keyword);
        } else {
            return handleMultiWordCommand(keyword, s);
        }
    }

    /**
     * Constructs Command object when given a one-word response.
     * @param keyword Command given by user.
     * @return Command.
     * @throws DukeException If there is no valid command.
     */
    public static Command handleSingleWordCommand(String keyword) throws DukeException {
        switch (keyword) {
        case "deadline":
        case "event":
        case "delete":
        case "mark":
        case "unmark":
        case "find":
            throw new DukeException(keyword);
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "reminders":
            return new RemindCommand();
        default:
            throw new DukeException("unknown");
        }
    }

    /**
     * Constructs Command object when given a multi-word response.
     * @param keyword First word given by user.
     * @param s Full command given by user.
     * @return Command.
     * @throws DukeException should there be no valid command.
     */
    public static Command handleMultiWordCommand(String keyword, String s) throws DukeException {
        switch (keyword) {
        case "todo":
            return new AddCommand(Task.TaskType.TODO, s.substring(5), null);
        case "deadline": {
            try {
                String[] tempSplit = s.substring(9).split(" /by ");
                if (tempSplit.length == 1) {
                    throw new DukeException("deadline format");
                } else {
                    return new AddCommand(Task.TaskType.DEADLINE, tempSplit[0], LocalDate.parse(tempSplit[1]));
                }
            } catch (DateTimeParseException e) {
                throw new DukeException("deadline format");
            }
        }
        case "event": {
            try {
                String[] tempSplit = s.substring(6).split(" /at ");
                if (tempSplit.length == 1) {
                    throw new DukeException("event format");
                } else {
                    return new AddCommand(Task.TaskType.EVENT, tempSplit[0], LocalDate.parse(tempSplit[1]));
                }
            } catch (DateTimeParseException e) {
                throw new DukeException("event format");
            }
        }
        case "delete":
            try {
                int location = Integer.parseInt(s.substring(7)) - 1;
                return new DeleteCommand(location);
            } catch (NumberFormatException e) {
                throw new DukeException("non integer input when deleting");
            }
        case "mark":
            try {
                int location = Integer.parseInt(s.substring(5)) - 1;
                return new MarkCommand(true, location);
            } catch (NumberFormatException e) {
                throw new DukeException("non integer input when marking");
            }
        case "unmark":
            try {
                int location = Integer.parseInt(s.substring(7)) - 1;
                return new MarkCommand(false, location);
            } catch (NumberFormatException e) {
                throw new DukeException("non integer input when marking");
            }
        case "find":
            String substring = s.substring(5);
            return new FindCommand(substring);
        default:
            throw new DukeException("unknown");
        }
    }

    /**
     * Represents an Add Command class.
     */
    public static class AddCommand extends Command {

        /**
         * Represents the task type.
         */
        private final Task.TaskType taskType;

        /**
         * Represents what task has to be done.
         */
        private final String todo;

        /**
         * Represents the date of task, if any.
         */
        private final LocalDate by;

        /**
         * Creates Add Command Class through a constructor method.
         * @param type Task type.
         * @param todo Task to be done.
         * @param by Date when task has to be done, if any.
         */
        public AddCommand(Task.TaskType type, String todo, LocalDate by) {
            this.taskType = type;
            this.todo = todo;
            this.by = by;
        }

        /**
         * Executes task.
         * @param tasks List of tasks.
         * @param ui User interface being used.
         * @param storage Storage where text is stored.
         * @return Message.
         */
        @Override
        public String execute(TaskManager tasks, Ui ui, Storage storage) {
            if (taskType == Task.TaskType.TODO) {
                Task task = Task.of(Task.TaskType.TODO, todo);
                tasks.addTask(task);
                return ui.sendAndReturnMessage(ActionKeywords.TODO, task, String.valueOf(tasks.numOfTasks()));
            } else if (taskType == Task.TaskType.DEADLINE) {
                Task task = Task.of(Task.TaskType.DEADLINE, todo + " /by " + by);
                tasks.addTask(task);
                return ui.sendAndReturnMessage(ActionKeywords.DEADLINE, task, String.valueOf(tasks.numOfTasks()));
            } else if (taskType == Task.TaskType.EVENT) {
                Task task = Task.of(Task.TaskType.EVENT, todo + " /at " + by);
                tasks.addTask(task);
                return ui.sendAndReturnMessage(ActionKeywords.EVENT, task, String.valueOf(tasks.numOfTasks()));
            } else {
                return null;
            }
        }
    }

    /**
     * Represents a Delete Command class.
     */
    public static class DeleteCommand extends Command {

        /**
         * Represents location of task in array of tasks.
         */
        private final int location;

        /**
         * Creates Delete Command through a constructor method.
         * @param location Index where the task is located
         */
        public DeleteCommand(int location) {
            this.location = location;
        }

        /**
         * Executes task.
         * @param tasks List of tasks.
         * @param ui User interface being used.
         * @param storage Storage where text is stored.
         * @return Message.
         * @throws DukeException If it is found.
         */
        @Override
        public String execute(TaskManager tasks, Ui ui, Storage storage) throws DukeException {
            try {
                Task task = tasks.removeTask(location);
                return ui.sendAndReturnMessage(ActionKeywords.DELETE, task, String.valueOf(tasks.numOfTasks()));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("index out of bounds");
            }
        }

    }

    /**
     * Represents an Exit Command class.
     */
    public static class ExitCommand extends Command {

        /**
         * Creates Exit Command through a constructor method.
         */
        public ExitCommand() {
        }

        /**
         * Executes task.
         * @param tasks List of tasks.
         * @param ui User interface being used.
         * @param storage Storage where text is stored.
         * @return Message.
         * @throws IOException If there is such an exception.
         */
        @Override
        public String execute(TaskManager tasks, Ui ui, Storage storage) throws IOException {
            this.isOngoing = false;
            String message = tasks.craftTextMessageForFile();
            storage.editStorage(message);
            return ui.sayBye();
        }
    }

    /**
     * Represents a Final Command class.
     */
    public static class FindCommand extends Command {

        /**
         * Represents string to be used to filter tasks.
         */
        private final String s;

        /**
         * Creates Final Command through a constructor method.
         */
        public FindCommand(String s) {
            this.s = s;
        }

        /**
         * Executes task.
         * @param tasks List of tasks.
         * @param ui User interface being used.
         * @param storage Storage where text is stored.
         * @return Message.
         */
        @Override
        public String execute(TaskManager tasks, Ui ui, Storage storage) {
            String res = tasks.findAndCraftTaskList(this.s);
            return ui.sendAndReturnMessage(ActionKeywords.FIND, null, "0", res);
        }
    }

    /**
     * Represents a Mark Command class.
     */
    public static class MarkCommand extends Command {

        /**
         * Represents whether the task is completed.
         */
        private final boolean isCompleted;

        /**
         * Represents the location of the task.
         */
        private final int location;

        /**
         * Creates a Mark Command class through a constructor method.
         * @param isCompleted Whether task is completed.
         * @param location Index where the task is located.
         */
        public MarkCommand(boolean isCompleted, int location) {
            this.isCompleted = isCompleted;
            this.location = location;
        }

        /**
         * Executes task.
         * @param tasks List of tasks.
         * @param ui User interface being used.
         * @param storage Storage where text is stored.
         * @return Message.
         * @throws DukeException If it is found.
         */
        @Override
        public String execute(TaskManager tasks, Ui ui, Storage storage) throws DukeException {
            try {
                if (isCompleted) {
                    Task task = tasks.markTaskComplete(location);
                    return ui.sendAndReturnMessage(ActionKeywords.MARK, task, String.valueOf(tasks.numOfTasks()));
                } else {
                    Task task = tasks.markTaskIncomplete(location);
                    return ui.sendAndReturnMessage(ActionKeywords.UNMARK, task, String.valueOf(tasks.numOfTasks()));
                }
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("index out of bounds");
            }
        }

    }

    /**
     * Represents a List Command class.
     */
    public static class ListCommand extends Command {

        /**
         * Creates a List Command class through a constructor method.
         */
        public ListCommand() {
        }

        /**
         * Executes task.
         * @param tasks List of tasks.
         * @param ui User interface being used.
         * @param storage Storage where text is stored.
         * @return Message.
         */
        @Override
        public String execute(TaskManager tasks, Ui ui, Storage storage) {
            String message = tasks.craftTaskList();
            return ui.sendAndReturnMessage(ActionKeywords.LIST, null, String.valueOf(tasks.numOfTasks()), message);
        }
    }

    /**
     * Represents a Remind Command class.
     */
    public static class RemindCommand extends Command {

        /**
         * Creates a Remind Command class through a constructor method.
         */
        public RemindCommand() {
        }

        /**
         * Executes task.
         * @param tasks list of tasks.
         * @param ui User interface being used.
         * @param storage Storage where text is stored.
         * @return Message.
         */
        @Override
        public String execute(TaskManager tasks, Ui ui, Storage storage) {
            String message = tasks.craftRemindersList();
            return ui.sendAndReturnMessage(ActionKeywords.REMIND, null,
                    String.valueOf(tasks.numOfTaskType(Task.TaskType.DEADLINE)), message);
        }
    }

    /**
     * Executes task.
     * @param tasks List of tasks.
     * @param ui User interface being used.
     * @param storage Storage where text is stored.
     * @return Message.
     * @throws DukeException If it is found.
     * @throws IOException If there is such an exception.
     */
    public abstract String execute(TaskManager tasks, Ui ui, Storage storage) throws DukeException, IOException;

    /**
     * Checks if one can still give more commands.
     * @return boolean.
     */
    public boolean isExit() {
        return !isOngoing;
    }
}
