import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.Scanner;

public class Candice {

    private static class Task {
        private boolean finished = false;
        private final String task;

        public Task(String task) {
            this.task = task;
        }

        public void markAsFinished() {
            this.finished = true;
        }

        public void markAsUnfinished() {
            this.finished = false;
        }

        public String status() {
            String completed = this.finished ? "[X] " : "[ ] ";
            return completed + this.task;
        }
    }

    private static class ToDo extends Task {
        public ToDo(String task) {
            super(task);
        }

        @Override
        public String status() {
            return "[T]" + super.status();
        }
    }

    private static class Deadline extends Task {
        private final String date;

        public Deadline(String task, String date) {
            super(task);
            this.date = date;
        }

        @Override
        public String status() {
            return "[D]" + super.status() + "(by: " + date + ")";
        }
    }

    private static class Event extends Task {
        private final String time;

        public Event(String task, String time) {
            super(task);
            this.time = time;
        }

        @Override
        public String status() {
            return "[E]" + super.status() + "(at: " + time + ")";
        }
    }

    private static void addTask(Task newTask, ArrayList<Task> taskList) {
        taskList.add(newTask);
        System.out.println("I gotchu fam. Your task has been added:");
        System.out.println("  " + newTask.status());
        System.out.println("You have " + taskList.size() + " task(s). Stop procrastinating bro.");
    }

    private static void addDeadlineOrEvent(String taskAndDate, String firstWord, ArrayList<Task> taskList)
            throws InvalidFormattingException, UnknownActionException, EmptyTimingException {
        if (!firstWord.equals("deadline") && !firstWord.equals("event")) {
            throw new UnknownActionException();
        } else {
            String[] taskSplit = taskAndDate.split("/", 2);

            if (taskSplit.length == 2) {
                String taskName = taskSplit[0];
                String date = taskSplit[1];

                String[] dateSplit = date.split(" ", 2);

                if (firstWord.equals("deadline")) {
                    if (dateSplit[0].equals("by")) {
                        Deadline newDeadline = new Deadline(taskName, dateSplit[1]);
                        addTask(newDeadline, taskList);
                        return;
                    }
                }

                if (firstWord.equals("event")) {
                    if (dateSplit[0].equals("at")) {
                        Event newEvent = new Event(taskName, dateSplit[1]);
                        addTask(newEvent, taskList);
                        return;
                    }
                }

                throw new InvalidFormattingException(firstWord);
            } else {
                throw new EmptyTimingException(firstWord);
            }
        }
    }

    private static String[] taskIdentifier(String str) throws EmptyTaskNameException, UnknownActionException {
        String[] splitStr = str.split(" ", 2);

        String taskType = splitStr[0];
        switch (taskType) {
            case "todo":
            case "deadline":
            case "event":
            case "mark":
            case "unmark":
                if (splitStr.length == 2) {
                    return splitStr;
                } else {
                    throw new EmptyTaskNameException(splitStr[0]);
                }
            default:
                throw new UnknownActionException();
        }
    }

    private static void markOrUnmark(int taskNumber, String firstWord, ArrayList<Task> taskList)
            throws IllegalArgumentException, UnknownActionException {
        int length = taskList.size();

        if (taskNumber <= 0 || taskNumber > length) {
            throw new IllegalArgumentException("Wrong task number lah");
        }

        Task selectedTask = taskList.get(taskNumber - 1);

        if (firstWord.equals("mark")) {
            System.out.println("Nice one lah, finally doing your work.");
            selectedTask.markAsFinished();
        } else if (firstWord.equals("unmark")) {
            System.out.println("What happened bro");
            selectedTask.markAsUnfinished();
        } else {
            throw new UnknownActionException();
        }
    }

    public static void main(String[] args) {
        System.out.println("Sup bro! My name is Candice.");
        System.out.println("I'm here to help you track your tasks!");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("Here are your tasks. You better start now:");
                int length = taskList.size();
                for (int i = 0; i < length; i++) {
                    Task currentTask = taskList.get(i);
                    int taskNumber = i + 1;
                    String status = currentTask.status();
                    System.out.println(taskNumber + "." + status);
                }
            } else {
                try {
                    String[] splitString = taskIdentifier(input);
                    String firstWord = splitString[0];

                    switch (firstWord) {
                        case "mark":
                        case "unmark":
                            int taskNumber;

                            try {
                                taskNumber = Integer.parseInt(splitString[1]);
                                markOrUnmark(taskNumber, firstWord, taskList);

                                System.out.println(taskList.get(taskNumber - 1).status());
                            } catch (NumberFormatException e) {
                                System.out.println("Write properly leh. Your format is wrong");
                            } catch (IllegalArgumentException e) {
                                System.out.println(e);
                            }
                            break;
                        case "todo":
                            ToDo todo = new ToDo(splitString[1]);
                            addTask(todo, taskList);
                            break;
                        case "deadline":
                        case "event":
                            addDeadlineOrEvent(splitString[1], firstWord, taskList);
                    }
                } catch (EmptyTaskNameException | UnknownActionException |
                        InvalidFormattingException | EmptyTimingException e) {
                    System.out.println(e);
                }
            }

            input = scanner.nextLine();
        }

        System.out.println("Bye bro. See you soon.");
    }
}
