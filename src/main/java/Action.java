import java.time.DateTimeException;
import java.time.LocalDate;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public abstract class Action {
    private final String action;

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
                    String status = currentTask.status();
                    System.out.println(taskNumber + "." + status);
                }
            } else {
                System.out.println("Bye bro. See you soon.");
            }
        }
    }

    public static class NonTimedTaskAction extends Action { // todo
        private final String taskDescription;

        public NonTimedTaskAction(String taskDescription) {
            super("todo");
            this.taskDescription = taskDescription;
        }

        @Override
        public void resolve(ArrayList<Task> taskList) {
            Task newTask = new Task.ToDo(taskDescription);
            Task.addTask(newTask, taskList);
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
        }
    }

    public static class TimedTaskAction extends Action { // deadline & event
        private final String taskDescription;
        private final LocalDate localDate;
        private final LocalTime localTime;

        public TimedTaskAction(String action, String taskDescription, LocalDate localDate, LocalTime localTime) {
            super(action);
            this.taskDescription = taskDescription;
            this.localDate = localDate;
            this.localTime = localTime;
        }

        @Override
        public void resolve(ArrayList<Task> taskList) {
            Task newTask;

            if (super.action.equals("deadline")) {
                newTask =  new TimedTask.Deadline(this.taskDescription, this.localDate, this.localTime);
            } else {
                newTask = new TimedTask.Event(this.taskDescription, this.localDate, this.localTime);
            }

            Task.addTask(newTask, taskList);
        }
    }

    public static Action actionParser(String input) throws UnknownActionException, EmptyTaskNameException,
            InvalidFormattingException, EmptyTimingException, NumberFormatException, InvalidDateException,
            InvalidTimeException {
        String[] actionSplit = input.split(" ", 2);

        String action = actionSplit[0];

        switch (action) {
        case "bye":
        case "list":
            if (actionSplit.length == 2) {
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
                String[] taskSplit = actionSplit[1].split("/", 2);

                if (taskSplit.length == 2) {
                    String taskName = taskSplit[0];
                    String timing = taskSplit[1];

                    String[] timingSplit = timing.split(" ", 2);

                    if ((action.equals("deadline") && (timingSplit[0].equals("by"))) ||
                            (action.equals("event") && (timingSplit[0].equals("at")))) {

                        if (timingSplit.length == 2) {
                            String deadlineDateAndTime = timingSplit[1];

                            String[] splitDateAndTime = deadlineDateAndTime.split(" ");
                            LocalDate localDate;
                            LocalTime localTime = null;

                            if (splitDateAndTime.length <= 2) { // Should only have date and time
                                String date = splitDateAndTime[0];
                                String[] splitDayMonthYear = date.split("/");

                                if (splitDayMonthYear.length == 3) {
                                    StringBuilder day = new StringBuilder(splitDayMonthYear[0]);
                                    StringBuilder month = new StringBuilder(splitDayMonthYear[1]);
                                    StringBuilder year = new StringBuilder(splitDayMonthYear[2]);

                                    int dayLength = day.length();
                                    int monthLength = month.length();
                                    int yearLength = year.length();

                                    // Filling the days, months and years to match the format required for LocalDate
                                    for (int i = 0; i < (2 - dayLength); i++) {
                                        day.insert(0, "0");
                                    }

                                    for (int i = 0; i < (2 - monthLength); i++) {
                                        month.insert(0, "0");
                                    }

                                    for (int i = 0; i < (4 - yearLength); i++) {
                                        year.insert(0, "0");
                                    }

                                    String localDateFormat = year + "-" + month + "-" + day;
                                    try {
                                        localDate = LocalDate.parse(localDateFormat);
                                    } catch (DateTimeParseException e) {
                                        throw new InvalidDateException();
                                    }
                                } else {
                                    throw new InvalidDateException();
                                }

                                if (splitDateAndTime.length == 2) {
                                    String time = splitDateAndTime[1];

                                    if (time.length() == 4) {
                                        String hour = time.substring(0, 2);
                                        String minute = time.substring(2);
                                        String localTimeFormat = hour + ":" + minute;
                                        try {
                                            localTime = LocalTime.parse(localTimeFormat);
                                        } catch (DateTimeParseException e) {
                                            throw new InvalidTimeException();
                                        }
                                    }
                                }

                                if (action.equals("deadline")) {
                                    return new TimedTaskAction("deadline", taskName, localDate, localTime);
                                } else {
                                    return new TimedTaskAction("event", taskName, localDate, localTime);
                                }
                            } else {
                                throw new InvalidFormattingException(action);
                            }
                        } else {
                            throw new EmptyTimingException(action);
                        }
                    } else {
                        throw new InvalidFormattingException(action);
                    }
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
