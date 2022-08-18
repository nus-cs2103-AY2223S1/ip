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

    private static void addDeadlineOrEvent(String taskAndDate, String firstWord, ArrayList<Task> taskList) {
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
        }

        System.out.println("Write properly leh. Your format is wrong");
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
                String[] splitString = input.split(" ", 2);

                if (splitString.length == 2) {
                    String firstWord = splitString[0];

                    switch (firstWord) {
                        case "mark":
                        case "unmark":
                            int taskNumber;

                            try {
                                taskNumber = Integer.parseInt(splitString[1]);
                                if (taskNumber > taskList.size() || taskNumber <= 0) {
                                    System.out.println("Wrong number bro.");
                                } else {
                                    Task selectedTask = taskList.get(taskNumber - 1);
                                    if (firstWord.equals("mark")) {
                                        System.out.println("Nice one lah, finally doing your work:");
                                        selectedTask.markAsFinished();
                                    } else {
                                        System.out.println("Bro, what happened:");
                                        selectedTask.markAsUnfinished();
                                    }
                                    System.out.println(selectedTask.status());
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Write properly leh. Your format is wrong");
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
                } else {
                    System.out.println("Write properly leh.");
                }
            }

            input = scanner.nextLine();
        }

        System.out.println("Bye bro. See you soon.");
    }
}
