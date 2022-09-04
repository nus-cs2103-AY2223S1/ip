package candice.command;

import candice.Ui;
import candice.task.Task;
import candice.task.TaskList;
import candice.task.TimedTask;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Encapsulates all the relevant info of the command inputted after parsing it.
 * Command objects can be resolved to execute the respective commands inputted.
 */
public abstract class Command {
    /** The type of this Command object */
    private final CommandType commandType;

    /**
     * Constructor for Command objects to contain the type of the command inputted.
     *
     * @param commandType The type of the command inputted.
     */
    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    public abstract void resolve(TaskList taskList) throws IllegalArgumentException;

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
         * Prints out the current tasks in the task list if the command inputted was "list" and nothing otherwise.
         *
         * @param taskList The task list associated with this instance of Candice.
         */
        @Override
        public void resolve(TaskList taskList) {
            if (super.commandType == CommandType.LIST) {
                Ui.printMessageForList(taskList);
            } else { // Command is "bye"
                // Does nothing
            }
        }
    }

    /**
     * Encapsulates commands to add tasks with no date or time attached to it, specifically todo tasks.
     */
    public static class NonTimedTaskCommand extends Command { // todo
        /** The name of the task in the inputted command */
        private final String taskName;

        /**
         * Constructor for a command to add a task with no date or time, specifically todo tasks.
         *
         * @param taskName The name of the todo task.
         */
        public NonTimedTaskCommand(String taskName) {
            super(CommandType.TODO);
            this.taskName = taskName;
        }

        /**
         * Creates a new todo task and adds it to the task list.
         *
         * @param taskList The task list associated with this instance of Candice.
         */
        @Override
        public void resolve(TaskList taskList) {
            Task newTask = new Task.ToDo(taskName);
            taskList.addTask(newTask);
            Ui.printMessageForAddTask(newTask, taskList);
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
         * type of command inputted was "mark" and sets a task as unfinished if the command inputted was "unmark".
         *
         * @param taskList The task list associated with this instance of Candice.
         * @throws IllegalArgumentException If the task number inputted was larger than the size of the task list or
         * zero and below.
         */
        @Override
        public void resolve(TaskList taskList) throws IllegalArgumentException {
            if (super.commandType == CommandType.DELETE) {
                Task deletedTask = taskList.deleteTask(this.taskNumber);
                Ui.printMessageForDeleteTask(deletedTask, taskList);
            } else if (super.commandType == CommandType.MARK) {
                Task selectedTask = taskList.markTask(this.taskNumber);
                Ui.printMessageForMarkTask(selectedTask);
            } else { // commandType == UNMARK
                Task selectedTask = taskList.unmarkTask(this.taskNumber);
                Ui.printMessageForUnmarkTask(selectedTask);
            }
        }
    }

    /**
     * Encapsulates commands to add tasks, with a date and potentially a timing associated to it, to the task list.
     */
    public abstract static class TimedTaskCommand extends Command {
        /** The name of the task in the inputted command */
        String taskName;
        /** The dated associated to the task in the inputted command */
        LocalDate taskDate;

        /**
         * Constructor for a command to add a task with a date associated to it.
         *
         * @param commandType The type of the command inputted.
         * @param taskName The name of the task in the inputted command.
         * @param date The date attached to the task.
         */
        public TimedTaskCommand(CommandType commandType, String taskName, LocalDate date) {
            super(commandType);
            this.taskName = taskName;
            this.taskDate = date;
        }
    }

    /**
     * Encapsulates a command to add a deadline task to the task list.
     */
    public static class DeadlineCommand extends TimedTaskCommand { // deadline
        /** The deadline time of the task to be created and added */
        private final LocalTime deadlineTime;

        /**
         * Constructor for a command to add a task with a deadline to the task list.
         *
         * @param taskName The name of the deadline task.
         * @param deadlineDate The deadline date of the task.
         * @param deadlineTime The deadline time of the task.
         */
        public DeadlineCommand(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
            super(CommandType.DEADLINE, taskName, deadlineDate);
            this.deadlineTime = deadlineTime;
        }

        /**
         * Creates a new deadline task and adds it to the task list.
         *
         * @param taskList The task list associated with this instance of Candice.
         */
        @Override
        public void resolve(TaskList taskList) {
            Task newDeadline = new TimedTask.Deadline(this.taskName, this.taskDate, this.deadlineTime);

            taskList.addTask(newDeadline);
            Ui.printMessageForAddTask(newDeadline, taskList);
        }
    }

    /**
     * Encapsulates a command to add an event to the task list.
     */
    public static class EventCommand extends TimedTaskCommand { // event
        /** The start time of the event to be created and added */
        private final LocalTime eventStartTime;
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
            super(CommandType.EVENT, taskName, eventDate);
            this.eventStartTime = eventStartTime;
            this.eventEndTime = eventEndTime;
        }

        /**
         * Creates a new event and adds it to the task list.
         *
         * @param taskList The task list associated with this instance of Candice.
         */
        @Override
        public void resolve(TaskList taskList) {
            Task newEvent = new TimedTask.Event(this.taskName, this.taskDate, this.eventStartTime,
                    this.eventEndTime);

            taskList.addTask(newEvent);
            Ui.printMessageForAddTask(newEvent, taskList);
        }
    }
}