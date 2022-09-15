package candice.command;

import candice.Ui;
import candice.task.Task;
import candice.task.TaskList;
import candice.task.TimedTask;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Encapsulates all the relevant info of the command inputted after parsing it.
 * Command objects can be resolved to execute the respective commands inputted and return the respective messages.
 */
public abstract class Command {
    /** The type of this Command object */
    final CommandType commandType;

    /**
     * Constructor for Command objects to contain the type of the command inputted.
     *
     * @param commandType The type of the command inputted.
     */
    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    public abstract String resolve(TaskList taskList) throws IllegalArgumentException;

    /**
     * Encapsulates single word commands, specifically "bye" and "list".
     */
    public static class SingleWordCommand extends Command { // bye & list
        /**
         * Constructor for SingleWordCommand objects to contain the type of the command inputted, specifically bye or
         * list.
         *
         * @param commandType The type of the command inputted.
         */
        public SingleWordCommand(CommandType commandType) {
            super(commandType);
        }

        /**
         * Returns the current tasks in the task list if the command inputted was "list" and the exit message otherwise.
         *
         * @param taskList The task list associated with this instance of Candice.
         * @return The task list if the command type is list or exit message if the command type is bye.
         */
        @Override
        public String resolve(TaskList taskList) {
            if (this.commandType == CommandType.LIST) {
                return Ui.getMessageForList(taskList);
            } else { // command type is bye
                return Ui.getMessageForShuttingDown();
            }
        }
    }

    /**
     * Encapsulates commands that do not have a time associated to it, specifically todo and find.
     */
    public static class NonTimedCommand extends Command { // todo & find
        /** The description of the inputted command */
        private final String commandDescription;

        /**
         * Constructor for a command that does not have a time associated with it, specifically commands to create a
         * todo task or finding tasks that have a certain keyword.
         *
         * @param commandType The type of the command inputted.
         * @param commandDescription The description of the command, specifically the name of the task or the keyword
         * used to find tasks.
         */
        public NonTimedCommand(CommandType commandType, String commandDescription) {
            super(commandType);
            this.commandDescription = commandDescription;
        }

        /**
         * Creates a new todo task and adds it to the task list if the type of command inputted was "todo" or finds
         * tasks with names that include the keyword inputted if the type of command inputted was "find".
         *
         * @param taskList The task list associated with this instance of Candice.
         * @return A message reflecting that a todo task was added or the tasks that have a task name that includes the
         * keyword inputted.
         */
        @Override
        public String resolve(TaskList taskList) {
            if (this.commandType == CommandType.TODO) {
                Task newTask = new Task.ToDo(commandDescription);
                taskList.addTask(newTask);
                return Ui.getMessageForAddTask(newTask, taskList);
            } else { // Command type is find
                return Ui.getMessageForFind(taskList, commandDescription);
            }
        }
    }

    /**
     * Encapsulates commands to edit or delete tasks in the task list.
     */
    public static class NumberedCommand extends Command { // mark, unmark & delete
        /** The number of the task that the inputted command wants to edit or delete */
        private final int taskNumber;

        /**
         * Constructor for a command to edit or delete a task in the task list.
         *
         * @param commandType The type of the command inputted.
         * @param taskNumber The number of the task to be edited or deleted.
         */
        public NumberedCommand(CommandType commandType, int taskNumber) {
            super(commandType);
            this.taskNumber = taskNumber;
        }

        /**
         * Deletes the respective task if the type of command inputted was "delete", sets a task as finished if the
         * type of command inputted was "mark" and sets a task as unfinished if the type of command inputted was
         * "unmark".
         *
         * @param taskList The task list associated with this instance of Candice.
         * @return A message reflecting that a task was deleted, marked as finished or marked as unfinished if the
         * command type was delete, mark and unmark respectively.
         * @throws IllegalArgumentException If the task number inputted was larger than the size of the task list or
         * zero and below.
         */
        @Override
        public String resolve(TaskList taskList) throws IllegalArgumentException {
            if (this.commandType == CommandType.DELETE) {
                Task deletedTask = taskList.deleteTask(this.taskNumber);
                return Ui.getMessageForDeleteTask(deletedTask, taskList);
            } else if (this.commandType == CommandType.MARK) {
                Task selectedTask = taskList.markTask(this.taskNumber);
                return Ui.getMessageForMarkTask(selectedTask);
            } else { // commandType == UNMARK
                Task selectedTask = taskList.unmarkTask(this.taskNumber);
                return Ui.getMessageForUnmarkTask(selectedTask);
            }
        }
    }

    /**
     * Encapsulates commands to add tasks, with a date and potentially a timing associated to it, to the task list.
     */
    public abstract static class TimedTaskCommand extends Command {
        /** The name of the task in the inputted command */
        String taskName;
        /** The date associated to the task in the inputted command */
        LocalDate taskDate;
        /** The time associated to the task in the inputted command */
        LocalTime taskTime;

        /**
         * Constructor for a command to add a task with a date associated to it.
         *
         * @param commandType The type of the command inputted.
         * @param taskName The name of the task in the inputted command.
         * @param date The date attached to the task.
         * @param time The time attached to the task.
         */
        public TimedTaskCommand(CommandType commandType, String taskName, LocalDate date, LocalTime time) {
            super(commandType);
            this.taskName = taskName;
            this.taskDate = date;
            this.taskTime = time;
        }
    }

    /**
     * Encapsulates a command to add a deadline task to the task list.
     */
    public static class DeadlineCommand extends TimedTaskCommand { // deadline
        /**
         * Constructor for a command to add a task with a deadline to the task list.
         *
         * @param taskName The name of the deadline task.
         * @param deadlineDate The deadline date of the task.
         * @param deadlineTime The deadline time of the task.
         */
        public DeadlineCommand(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
            super(CommandType.DEADLINE, taskName, deadlineDate, deadlineTime);
        }

        /**
         * Creates a new deadline task and adds it to the task list.
         *
         * @param taskList The task list associated with this instance of Candice.
         * @return A message reflecting that a deadline task was added to the task list.
         */
        @Override
        public String resolve(TaskList taskList) {
            Task newDeadline = new TimedTask.Deadline(this.taskName, this.taskDate, this.taskTime);

            taskList.addTask(newDeadline);
            return Ui.getMessageForAddTask(newDeadline, taskList);
        }
    }

    /**
     * Encapsulates a command to add an event to the task list.
     */
    public static class EventCommand extends TimedTaskCommand { // event
        /** The end time of the event to be created and added */
        private final LocalTime eventEndTime;

        /**
         * Constructor for a command to add an event to the task list.
         *
         * @param taskName The name of the event.
         * @param eventDate The date of the event.
         * @param eventStartTime The starting time of the event.
         * @param eventEndTime The ending time of the event.
         */
        public EventCommand(String taskName, LocalDate eventDate, LocalTime eventStartTime, LocalTime eventEndTime) {
            super(CommandType.EVENT, taskName, eventDate, eventStartTime);
            this.eventEndTime = eventEndTime;
        }

        /**
         * Creates a new event and adds it to the task list.
         *
         * @param taskList The task list associated with this instance of Candice.
         * @return A message reflecting that an event was added to the task list.
         */
        @Override
        public String resolve(TaskList taskList) {
            Task newEvent = new TimedTask.Event(this.taskName, this.taskDate, this.taskTime, this.eventEndTime);

            taskList.addTask(newEvent);
            return Ui.getMessageForAddTask(newEvent, taskList);
        }
    }
}