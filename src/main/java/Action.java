import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

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

    public static class DeadlineAction extends Action { // deadline
        private final String taskName;
        private final LocalDate deadlineDate;
        private final LocalTime deadlineTime;

        public DeadlineAction(String taskName, LocalDate localDate, LocalTime localTime) {
            super("deadline");
            this.taskName = taskName;
            this.deadlineDate = localDate;
            this.deadlineTime = localTime;
        }

        @Override
        public void resolve(ArrayList<Task> taskList) {
            Task newDeadline = new TimedTask.Deadline(this.taskName, this.deadlineDate, this.deadlineTime);

            Task.addTask(newDeadline, taskList);

            String taskDescription = newDeadline.showTaskListTextDescription();
            TaskList.addTaskToTaskListText(taskDescription);
        }
    }

    public static class EventAction extends Action { // event
        private final String taskName;
        private final LocalDate eventDate;
        private final LocalTime eventStartTime;
        private final LocalTime eventEndTime;

        public EventAction(String taskName, LocalDate eventDate, LocalTime eventStartTime, LocalTime eventEndTime) {
            super("event");
            this.taskName = taskName;
            this.eventDate = eventDate;
            this.eventStartTime = eventStartTime;
            this.eventEndTime = eventEndTime;
        }

        @Override
        public void resolve(ArrayList<Task> taskList) {
            Task newEvent = new TimedTask.Event(this.taskName, this.eventDate, this.eventStartTime,
                    this.eventEndTime);

            Task.addTask(newEvent, taskList);

            String taskDescription = newEvent.showTaskListTextDescription();
            TaskList.addTaskToTaskListText(taskDescription);
        }
    }

    public static Action actionParser(String input) throws UnknownActionException, EmptyTaskNameException,
            InvalidFormattingException, EmptyTimingException, NumberFormatException, InvalidDateException,
            InvalidTimeException {
        String[] actionSplit = input.split(" ", 2);

        String action = actionSplit[0];

        switch(action) {
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
                String[] taskSplit = actionSplit[1].split(" /", 2);

                if (taskSplit.length == 2) {
                    String taskName = taskSplit[0];
                    String timing = taskSplit[1];

                    String[] timingSplit = timing.split(" ", 2);

                    if ((action.equals("deadline") && (timingSplit[0].equals("by")))
                            || (action.equals("event") && (timingSplit[0].equals("at")))) {

                        if (timingSplit.length == 2) {
                            String dateAndTime = timingSplit[1];
                            String[] splitDateAndTime = dateAndTime.split(" ");

                            if (splitDateAndTime.length > 2) {
                                throw new InvalidFormattingException(action);
                            }

                            LocalDate date;

                            String dateAsString = splitDateAndTime[0];
                            String[] splitDayMonthYear = dateAsString.split("/");

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
                                    date = LocalDate.parse(localDateFormat);
                                } catch (DateTimeParseException e) {
                                    throw new InvalidDateException();
                                }
                            } else {
                                throw new InvalidDateException();
                            }

                            if (splitDateAndTime.length == 2) { // Time was provided
                                String time = splitDateAndTime[1];

                                if (action.equals("deadline") && time.length() == 4) {
                                    String hour = time.substring(0, 2);
                                    String minute = time.substring(2);
                                    String localTimeFormat = hour + ":" + minute;
                                    LocalTime deadlineTime;
                                    try {
                                        deadlineTime = LocalTime.parse(localTimeFormat);
                                    } catch (DateTimeParseException e) {
                                        throw new InvalidTimeException();
                                    }

                                    return new DeadlineAction(taskName, date, deadlineTime);
                                } else if (time.length() == 9) {
                                    String[] splitStartEndTime = time.split("-");
                                    if (splitStartEndTime.length == 2) {
                                        LocalTime eventStartTime;
                                        LocalTime eventEndTime;
                                        for (int i = 0; i < 2; i++) {
                                            String eventTime = splitStartEndTime[i];
                                            String hour = eventTime.substring(0, 2);
                                            String minute = eventTime.substring(2);
                                            String localTimeFormat = hour + ":" + minute;
                                            splitStartEndTime[i] = localTimeFormat;
                                        }

                                        try {
                                            eventStartTime = LocalTime.parse(splitStartEndTime[0]);
                                            eventEndTime = LocalTime.parse(splitStartEndTime[1]);
                                        } catch (DateTimeParseException e) {
                                            throw new InvalidTimeException();
                                        }

                                        return new EventAction(taskName, date, eventStartTime, eventEndTime);
                                    } else {
                                        throw new InvalidTimeException();
                                    }
                                } else {
                                    throw new InvalidTimeException();
                                }
                            } else {
                                if (action.equals("deadline")) {
                                    return new DeadlineAction(taskName, date, null);
                                } else {
                                    return new EventAction(taskName, date, null, null);
                                }
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
