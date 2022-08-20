import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsUndone() {
            this.isDone = false;
        }

        public String toString() {
            return "[" + getStatusIcon() + "] " + description;
        }

    }

    public class ToDo extends Task {

        public ToDo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public class Event extends Task {

        protected String time;

        public Event(String description, String time) {
            super(description);
            this.time = time;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (" + time + ")";
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
            return "[D]" + super.toString() + " ("+ by + ")";
        }
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I am a ToDos, Events, Deadlines and Talk Bot, otherwise known as TEDTalk\n" +
                "What can I do for you today?");

        ArrayList<Task> store = new ArrayList<>();

        while (true) {

            if (sc.hasNext("mark")) {
                sc.next();
                int num = Integer.parseInt(sc.next());
                store.get(num - 1).markAsDone();
                System.out.println("Task successfully completed!\n" + store.get(num - 1));
                sc.nextLine();
            } else if (sc.hasNext("unmark")) {
                sc.next();
                int num = Integer.parseInt(sc.next());
                store.get(num - 1).markAsUndone();
                System.out.println("Task incomplete.\n" + store.get(num - 1));
                sc.nextLine();
            } else if (sc.hasNext("bye")) {
                System.out.println("Bye bye! Hope to see you soon!");
                break;
            } else if (sc.hasNext("list")) {
                sc.nextLine();
                System.out.println("Here are your tasks:/n");
                for (int i = 0; i < store.size(); i++) {
                    System.out.println(i + 1 + ". " + store.get(i));
                }
            } else if (sc.hasNext("todo")) {
                sc.next();
                String next = sc.nextLine();
                Task newTask = new ToDo(next);
                store.add(newTask);
                System.out.println("Successfully added new task:\n" + newTask +
                        "\nYou have " + store.size() + " tasks in the list.");
            } else if (sc.hasNext("event")) {
                sc.next();
                String next = sc.nextLine();
                String[] split = next.split("/");
                Task newTask = new Event(split[0], split[1]);
                store.add(newTask);
                System.out.println("Successfully added new task:\n" + newTask +
                        "\nYou have " + store.size() + " tasks in the list.");
            } else if (sc.hasNext("deadline")) {
                sc.next();
                String next = sc.nextLine();
                String[] split = next.split("/");
                Task newTask = new Deadline(split[0], split[1]);
                store.add(newTask);
                System.out.println("Successfully added new task:\n" + newTask +
                        "\nYou have " + store.size() + " tasks in the list.");
            }
            else {
                String next = sc.nextLine();
                Task newTask = new Task(next);
                store.add(newTask);
                System.out.println("Successfully added new task:\n" + newTask +
                        "\nYou have " + store.size() + " tasks in the list.");
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
