import java.util.ArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class Action {
    private final String action;
    private static final Path TASKLIST_PATH = Paths.get(System.getProperty("user.dir"), "data", "tasks.txt");

    public Action(String action) {
        this.action = action;
    }

    public static class SingleWordAction extends Action { // bye & list
        public SingleWordAction(String action) {
            super(action);
        }

        @Override
        public void resolve(ArrayList<Task> taskList) {
            if (super.action.equals("list")) {
                System.out.println("Here are your tasks. You better start now:");
                int length = taskList.size();
                for (int i = 0; i < length; i++) {
                    Task currentTask = taskList.get(i);
                    int taskNumber = i + 1;
                    String status = currentTask.showStatus();
                    System.out.println(taskNumber + "." + status);
                }
            } else {
                System.out.println("Bye bro. See you soon.");
            }
        }
    }

    public static class NonTimedTaskAction extends Action { // todo
        private final String taskName;

        public NonTimedTaskAction(String taskName) {
            super("todo");
            this.taskName = taskName;
        }

        @Override
        public void resolve(ArrayList<Task> taskList) {
            Task newTask = new Task.ToDo(taskName);
            Task.addTask(newTask, taskList);

            String taskDescription = newTask.showTaskListTextDescription();
            TaskList.addTaskToTaskListText(taskDescription);
        }
    }

    public static class NumberedAction extends Action { // mark, unmark & delete
        private final int taskNumber;

        public NumberedAction(String action, int taskNumber) {
            super(action);
            this.taskNumber = taskNumber;
        }

        @Override
        public void resolve(ArrayList<Task> taskList) throws IllegalArgumentException {
            try {
                if (super.action.equals("delete")) {
                    Task.deleteTask(this.taskNumber, taskList);
                } else if (super.action.equals("mark")) {
                    Task.mark(this.taskNumber, taskList);
                } else {
                    Task.unmark(this.taskNumber, taskList);
                }
            } catch (IllegalArgumentException e) {
                throw e;
            }

            TaskList.updateTaskListText(taskList);
        }
    }

    public static class TimedTaskAction extends Action { // deadline & event
        private final String taskName;
        private final String timing;

        public TimedTaskAction(String action, String taskName, String timing) {
            super(action);
            this.taskName = taskName;
            this.timing = timing;
        }

        @Override
        public void resolve(ArrayList<Task> taskList) {
            Task newTask;
            String taskType;

            if (super.action.equals("deadline")) {
                newTask =  new TimedTask.Deadline(this.taskName, this.timing);
                taskType = "D";
            } else {
                newTask = new TimedTask.Event(this.taskName, this.timing);
                taskType = "E";
            }

            Task.addTask(newTask, taskList);

            String taskDescription = newTask.showTaskListTextDescription();
            TaskList.addTaskToTaskListText(taskDescription);
        }
    }

    public static Action actionParser(String input) throws UnknownActionException, EmptyTaskNameException,
            InvalidFormattingException, EmptyTimingException, NumberFormatException {
        String[] actionSplit = input.split(" ", 2);

        String action = actionSplit[0];

        switch (action) {
            case "bye":
            case "list":
                if (actionSplit.length == 2) { // Should not have more than "bye" or "list"
                    throw new UnknownActionException();
                } else {
                    return new SingleWordAction(action);
                }
            case "todo":
                if (actionSplit.length == 2) {
                    return new NonTimedTaskAction(actionSplit[1]);
                } else {
                    throw new EmptyTaskNameException("todo");
                }
            case "mark":
            case "unmark":
            case "delete":
                if (actionSplit.length == 2) {
                    try {
                        int taskNumber = Integer.parseInt(actionSplit[1]);
                        return new NumberedAction(action, taskNumber);
                    } catch (NumberFormatException e) {
                        throw new NumberFormatException("Write properly leh. Your number format is wrong.");
                    }
                } else {
                    throw new EmptyTaskNameException(action);
                }
            case "deadline":
            case "event":
                if (actionSplit.length == 2) {
                    String[] taskSplit = actionSplit[1].split(" /", 2);

                    if (taskSplit.length == 2) {
                        String taskName = taskSplit[0];
                        String timing = taskSplit[1];

                        String[] timingSplit = timing.split(" ", 2);

                        if (action.equals("deadline") && (timingSplit[0].equals("by"))) {
                            return new TimedTaskAction("deadline", taskName, timingSplit[1]);
                        }

                        if (action.equals("event") && (timingSplit[0].equals("at"))) {
                            return new TimedTaskAction("event", taskName, timingSplit[1]);
                        }

                        throw new InvalidFormattingException(action);
                    } else {
                        throw new EmptyTimingException(action);
                    }
                } else {
                    throw new EmptyTaskNameException(action);
                }
            default:
                throw new UnknownActionException();
        }
    }

    public abstract void resolve(ArrayList<Task> taskList);
}
