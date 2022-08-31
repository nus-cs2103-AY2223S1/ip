import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Command {
    private final CommandType commandType;

    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    public abstract void resolve(TaskList taskList) throws IllegalArgumentException;

    public static class SingleWordCommand extends Command { // bye & list
        public SingleWordCommand(CommandType commandType) {
            super(commandType);
        }

        @Override
        public void resolve(TaskList taskList) {
            if (super.commandType == CommandType.LIST) {
                Ui.printMessageForList(taskList);
            } else { // Command is "bye"
                System.out.println("Bye bro. See you soon.");
            }
        }
    }

    public static class NonTimedTaskCommand extends Command { // todo
        private final String taskName;

        public NonTimedTaskCommand(String taskName) {
            super(CommandType.TODO);
            this.taskName = taskName;
        }

        @Override
        public void resolve(TaskList taskList) {
            Task newTask = new Task.ToDo(taskName);
            taskList.addTask(newTask);
            Ui.printMessageForAddTask(newTask, taskList);
        }
    }

    public static class NumberedCommand extends Command { // mark, unmark & delete
        private final int taskNumber;

        public NumberedCommand(CommandType commandType, int taskNumber) {
            super(commandType);
            this.taskNumber = taskNumber;
        }

        @Override
        public void resolve(TaskList taskList) throws IllegalArgumentException {
            try {
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
            } catch (IllegalArgumentException e) {
                throw e;
            }
        }
    }

    public abstract static class TimedTaskCommand extends Command {
        String taskName;
        LocalDate taskDate;

        public TimedTaskCommand(CommandType commandType, String taskName, LocalDate date) {
            super(commandType);
            this.taskName = taskName;
            this.taskDate = date;
        }
    }

    public static class DeadlineCommand extends TimedTaskCommand { // deadline
        private final LocalTime deadlineTime;

        public DeadlineCommand(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
            super(CommandType.DEADLINE, taskName, deadlineDate);
            this.deadlineTime = deadlineTime;
        }

        @Override
        public void resolve(TaskList taskList) {
            Task newDeadline = new TimedTask.Deadline(this.taskName, this.taskDate, this.deadlineTime);

            taskList.addTask(newDeadline);
            Ui.printMessageForAddTask(newDeadline, taskList);
        }
    }

    public static class EventCommand extends TimedTaskCommand { // event
        private final LocalTime eventStartTime;
        private final LocalTime eventEndTime;

        public EventCommand(String taskName, LocalDate eventDate, LocalTime eventStartTime, LocalTime eventEndTime) {
            super(CommandType.EVENT, taskName, eventDate);
            this.eventStartTime = eventStartTime;
            this.eventEndTime = eventEndTime;
        }

        @Override
        public void resolve(TaskList taskList) {
            Task newEvent = new TimedTask.Event(this.taskName, this.taskDate, this.eventStartTime,
                    this.eventEndTime);

            taskList.addTask(newEvent);
            Ui.printMessageForAddTask(newEvent, taskList);
        }
    }
}
