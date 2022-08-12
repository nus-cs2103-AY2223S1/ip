import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    public abstract class Task {
        protected String description;
        protected boolean done;

        public Task(String description) {
            this.description = description;
            this.done = false;
        }

        public String mark() {
            this.done = true;
            return this.toString();
        }

        public String unmark() {
            this.done = false;
            return this.toString();
        }

        @Override
        public String toString() {
            return String.format("[%s] %s", (this.done ? "X" : " "), this.description);
        }
    }

    public class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return String.format("[T]%s", super.toString());
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
            return String.format("[D]%s (by: %s)", super.toString(), this.by);
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
            return String.format("[E]%s (at: %s)", super.toString(), this.at);
        }
    }

    private LinkedList<Task> list;
    private static final String LINE = "--------------------------------------------------";

    private void run() {
        list = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        System.out.printf("%s%n%s%n%s%n%s%n", LINE, "Hello! I'm Cortana", "What can I do for you?", LINE);

        while (true) {
            String input = sc.nextLine().trim();
            String[] inputArr = input.split(" ", 2);
            System.out.println(LINE);

            if (input.equals("bye")) {
                System.out.printf("%s%n%s%n","Bye. Hope to see you again soon!", LINE);
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.printf("%d.%s%n", i, list.get(i - 1));
                }
            } else if (inputArr[0].equals("mark")) {
                int idx = Integer.parseInt(inputArr[1]);
                System.out.printf("Nice! I've marked this task as done:%n%s%n", list.get(idx - 1).mark());
            } else if (inputArr[0].equals("unmark")) {
                int idx = Integer.parseInt(inputArr[1]);
                System.out.printf("OK, I've marked this task as not done yet:%n%s%n", list.get(idx - 1).unmark());
            } else if (inputArr[0].equals("todo")) {
                list.add(new Todo(inputArr[1]));
                System.out.printf("Got it. I've added this task: %n%s%nNow you have %d tasks in the list.%n", list.getLast(), list.size());
            } else if (inputArr[0].equals("deadline")) {
                String[] split = inputArr[1].split(" /by ");
                list.add(new Deadline(split[0], split[1]));
                System.out.printf("Got it. I've added this task: %n%s%nNow you have %d tasks in the list.%n", list.getLast(), list.size());
            } else if (inputArr[0].equals("event")) {
                String[] split = inputArr[1].split(" /at ");
                list.add(new Event(split[0], split[1]));
                System.out.printf("Got it. I've added this task: %n%s%nNow you have %d tasks in the list.%n", list.getLast(), list.size());
            } else {
                System.out.println("Unknown command");
            }

            System.out.println(LINE);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
