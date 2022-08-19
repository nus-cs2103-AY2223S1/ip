import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static boolean running = true;
    static ArrayList<Task> tasks = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public void addTodo(String s) {
        Todo t = new Todo(s);
        tasks.add(t);
        System.out.println("added: " + t);
        System.out.println("You have " + tasks.size() + " tasks in the list now");
    }

    public void addDeadline(String s, String by) {
        Deadline t = new Deadline(s, by);
        tasks.add(t);
        System.out.println("added: " + t);
        System.out.println("You have " + tasks.size() + " tasks in the list now");
    }

    public void addEvent(String s, String at) {
        Event t = new Event(s, at);
        tasks.add(t);
        System.out.println("added: " + t);
        System.out.println("You have " + tasks.size() + " tasks in the list now");
    }

    public void getList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
        System.out.println("There are " + tasks.size() + " tasks in the list.");
    }

    public void mark(Task t) {
        t.mark(t);
    }

    public void unmark(Task t) {
        t.unmark(t);
    }

    public void delete(int i) {
        Task t = tasks.get(i);
        tasks.remove(i);
        System.out.println("I have deleted this task:\n  " + t + "\n"
                + "Now you have " + tasks.size() + " tasks on the list.");
    }
    //@@author chengda300
    //Reused from https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html
    // with minor modifications
    public class Task {
        protected boolean isDone;
        protected String description;

        public Task(String desc) {
            this.description = desc;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return isDone ? "X" : " ";
        }

        public void mark(Task t) {
            t.isDone = true;
            System.out.println("Successfully marked this task as done: " + t);
        }

        public void unmark(Task t) {
            t.isDone = false;
            System.out.println("Successfully marked this task as not done: " + t);
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
    }

    public class Deadline extends Task {

        protected String by;

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

        protected String at;

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

    public class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }

        public class MissingFieldException extends DukeException {
            public MissingFieldException(String message) {
                super(message);
            }
        }
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        String logo = " ____            _\n"
                + "|  _ \\ _   _  __| | ___\n"
                + "| | | | | | |/ _  |/ _ \\\n"
                + "| |_| | |_| | |_| |  __/\n"
                + "|____/ \\__,_|\\__,_|\\___|\n";
        System.out.println("Hello from\n" + logo);
        while (d.running) {
            String input = d.sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Byebye! See you again soon!");
                d.running = false;
            } else if (input.equals("list")) {
                d.getList();
            } else if (input.startsWith("mark")) {
                try {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    if (index >= tasks.size() || index < 0) {
                        System.out.println("I cannot mark a task that does not exist!");
                    } else {
                        Task t = tasks.get(index);
                        d.mark(t);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("I cannot mark a task that does not exist!");
                }
            } else if (input.startsWith("unmark")) {
                try {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index >= tasks.size() || index < 0) {
                        System.out.println("I cannot unmark a task that does not exist!");
                    } else {
                        Task t = tasks.get(index);
                        d.unmark(t);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("I cannot unmark a task that does not exist!");
                }
            } else if (input.startsWith("todo")) {
                if (input.length() < 6) {
                    System.out.println("The task is empty! What do you mean exactly?");
                } else {
                    d.addTodo(input.substring(5));
                }
            } else if (input.startsWith("deadline")) {
                if (! input.contains("/by")) {
                    System.out.println("Specify a deadline type task by typing "
                            + "deadline <task> /by <deadline>");
                } else if (input.indexOf("/by") < 11) {
                    System.out.println("You did not specify a task.");
                } else if (input.indexOf("/by") + 4 >= input.length()) {
                    System.out.println("You did not specify a deadline.");
                } else {
                    d.addDeadline(input.substring(9, input.indexOf("/by") - 1),
                            input.substring(input.indexOf("/by") + 4));
                }
            } else if (input.startsWith("event")) {
                if (! input.contains("/at")) {
                    System.out.println("Specify an event type task by typing "
                            + "event <task> /at <time>");
                } else if (input.indexOf("/at") < 8) {
                    System.out.println("You did not specify a task.");
                } else if (input.indexOf("/at") + 4 >= input.length()) {
                    System.out.println("You did not specify a deadline.");
                } else {
                    d.addEvent(input.substring(6, input.indexOf("/at") - 1),
                            input.substring(input.indexOf("/at") + 4));
                }
            } else if (input.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index >= tasks.size() || index < 0) {
                        System.out.println("I cannot delete a task that does not exist!");
                    } else {
                        d.delete(index);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("I cannot delete a task that does not exist!");
                }
            } else {
                System.out.println("Sorry, I cannot understand what you mean. I can only understand"
                        + "todo, deadline, event, mark, unmark, delete, list, bye");
            }
        }
    }
}