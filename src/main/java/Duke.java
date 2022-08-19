import java.util.Scanner;

public class Duke {
    static boolean running = true;
    static Task[] tasks = new Task[100];
    static int count = 0;
    static Scanner sc = new Scanner(System.in);
    public void addTodo(String s) {
        Todo t = new Todo(s);
        tasks[count] = t;
        count++;
        System.out.println("added: " + t);
        System.out.println("You have " + count + " tasks in the list now");
    }

    public void addDeadline(String s, String by) {
        Deadline t = new Deadline(s, by);
        tasks[count] = t;
        count++;
        System.out.println("added: " + t);
        System.out.println("You have " + count + " tasks in the list now");
    }

    public void addEvent(String s, String at) {
        Event t = new Event(s, at);
        tasks[count] = t;
        count++;
        System.out.println("added: " + t);
        System.out.println("You have " + count + " tasks in the list now");
    }

    public void getList() {
        for (int i = 0; i < count; i++) {
            System.out.println(i + 1 + "." + tasks[i]);
        }
    }

    public void mark(Task t) {
        t.mark(t);
    }

    public void unmark(Task t) {
        t.unmark(t);
    }
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
            return "[E]" + super.toString() + " (by: " + this.at + ")";
        }
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        String logo = " ____            _    \n"
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
                Task t = tasks[Integer.parseInt(input.substring(5)) - 1];
                d.mark(t);
            } else if (input.startsWith("unmark")) {
                Task t = tasks[Integer.parseInt(input.substring(7)) - 1];
                d.unmark(t);
            } else {
                if (input.startsWith("todo")) {
                    d.addTodo(input.substring(5));
                } else if (input.startsWith("deadline")) {
                    d.addDeadline(input.substring(9, input.indexOf("/by") - 1),
                            input.substring(input.indexOf("/by") + 4));
                } else if (input.startsWith("event")) {
                    d.addEvent(input.substring(6, input.indexOf("/at") - 1),
                            input.substring(input.indexOf("/at") + 4));
                }
            }
        }
    }
}