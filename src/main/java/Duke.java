import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static boolean isRunning = true;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    
    private String logo = " ____            _\n"
            + "|  _ \\ _   _  __| | ___\n"
            + "| | | | | | |/ _  |/ _ \\\n"
            + "| |_| | |_| | |_| |  __/\n"
            + "|____/ \\__,_|\\__,_|\\___|\n";
    
    private String bye = "bye";
    private String list = "list";
    private String mark = "mark";
    private String unmark = "unmark";
    private String todo = "todo";
    private String deadline = "deadline";
    private String deadlineBy = "/by";
    private String event = "event";
    private String eventAt = "/at";
    private String delete = "delete";
    private String space = " ";
    
    public void addTodo(String s) {
        if (s.isBlank()) {
            System.out.println("The task is empty, what do you really mean?");
        } else {
            Todo t = new Todo(s);
            tasks.add(t);
            System.out.println("Successfully added: " + t);
            System.out.println("You have " + tasks.size() + " tasks in the list now");
        }
    }

    public void addDeadline(String s) {
        if (!s.contains(space + deadlineBy + space)) {
            if (s.startsWith(deadlineBy) || s.endsWith(deadlineBy)) {
                System.out.println("The task is empty, what do you really mean?");
            } else {
                System.out.println("The deadline is empty, do you mean it has no deadline?");
                System.out.println("If it is, please add it as a todo instead.");
            }
        } else {
            String task = s.substring(0, s.indexOf(deadlineBy) - space.length());
            String deadline = s.substring(s.indexOf(deadlineBy) + deadlineBy.length()
                    + space.length());
            if (task.isBlank()) {
                System.out.println("The task is empty, what do you really mean?");
            } else if (deadline.isBlank()) {
                System.out.println("The deadline is empty, do you mean it has no deadline?");
                System.out.println("If it is, please add it as a todo instead.");
            } else {
                Deadline t = new Deadline(task, deadline);
                tasks.add(t);
                System.out.println("Successfully added: " + t);
                System.out.println("You have " + tasks.size() + " tasks in the list now");
            }
        }
    }

    public void addEvent(String s) {
        if (!s.contains(space + eventAt + space)) {
            if (s.startsWith(eventAt) || s.endsWith(eventAt)) {
                System.out.println("The event is empty, what do you really mean?");
            } else {
                System.out.println("The time is empty, do you mean it never starts?");
            }
        } else {
            String event = s.substring(0, s.indexOf(eventAt) - space.length());
            String time = s.substring(s.indexOf(eventAt) + eventAt.length()
                    + space.length());
            if (event.isBlank()) {
                System.out.println("The event is empty, what do you really mean?");
            } else if (time.isBlank()) {
                System.out.println("The time is empty, do you mean it never starts?");
            } else {
                Event e = new Event(event, time);
                tasks.add(e);
                System.out.println("Successfully added: " + e);
                System.out.println("You have " + tasks.size() + " tasks in the list now");
            }
        }
    }

    public void getList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println("There are " + tasks.size() + " tasks in the list.");
    }

    public void markTask(String input) {
        try {
            int index = Integer.parseInt(input);
            if (index >= tasks.size() || index < 0) {
                System.out.println("I cannot mark a task that does not exist!");
            } else {
                Task t = tasks.get(index);
                t.markTask();
            }
        } catch (NumberFormatException e) {
            System.out.println("I cannot mark a task that does not exist!");
        }
    }

    public void unmarkTask(String input) {
        try {
            int index = Integer.parseInt(input);
            if (index >= tasks.size() || index < 0) {
                System.out.println("I cannot unmark a task that does not exist!");
            } else {
                Task t = tasks.get(index);
                t.unmarkTask();
            }
        } catch (NumberFormatException e) {
            System.out.println("I cannot unmark a task that does not exist!");
        }
    }

    public void deleteTask(String input) {
        try {
            int index = Integer.parseInt(input);
            if (index >= tasks.size() || index < 0) {
                System.out.println("I cannot delete a task that does not exist!");
            } else {
                Task t = tasks.get(index);
                tasks.remove(t);
                System.out.println("Successfully deleted: " + t);
                System.out.println("You have " + tasks.size() + " tasks in the list now");
            }
        } catch (NumberFormatException e) {
            System.out.println("I cannot delete a task that does not exist!");
        }
    }
    
    //@@author chengda300
    //Reused from https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html
    // with minor modifications
    public class Task {
        private boolean isDone;
        private String description;

        public Task(String desc) {
            this.description = desc;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return this.isDone ? "X" : " ";
        }

        public void markTask() {
            this.isDone = true;
            System.out.println("Successfully marked this task as done: " + this);
        }

        public void unmarkTask() {
            this.isDone = false;
            System.out.println("Successfully marked this task as not done: " + this);
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
    }

    public class Deadline extends Task {
        private String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + this.by + ")";
        }
    }

    public class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public class Event extends Task {
        private String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + this.at + ")";
        }
    }
    //@@author

    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println("Hello from dude\n" + duke.logo);
        while (duke.isRunning) {
            String input = duke.sc.nextLine();
            if (input.equals(duke.bye)) {
                System.out.println("Byebye! See you again soon!");
                duke.isRunning = false;
            } else if (input.equals(duke.list)) {
                duke.getList();
            } else if (input.startsWith(duke.mark + duke.space)) {
                String parameter = input.substring((duke.mark + duke.space).length());
                duke.markTask(parameter);
            } else if (input.startsWith(duke.unmark + duke.space)) {
                String parameter = input.substring((duke.unmark + duke.space).length());
                duke.unmarkTask(parameter);
            } else if (input.startsWith(duke.todo + duke.space)) {
                String parameter = input.substring((duke.todo + duke.space).length());
                duke.addTodo(parameter);
            } else if (input.startsWith(duke.deadline + duke.space)) {
                String parameter = input.substring((duke.deadline + duke.space).length());
                duke.addDeadline(parameter);
            } else if (input.startsWith(duke.event + duke.space)) {
                String parameter = input.substring((duke.event + duke.space).length());
                duke.addEvent(parameter);
            } else if (input.startsWith(duke.delete + duke.space)) {
                String parameter = input.substring((duke.delete + duke.space).length());
                duke.deleteTask(parameter);
            } else {
                System.out.println("Sorry, I cannot understand what you exactly mean.");
                System.out.println("Certain commands require input parameters.");
            }
        }
    }
}