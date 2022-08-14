import java.sql.SQLSyntaxErrorException;
import java.util.Scanner;

public class Duke {
    private static Task[] all = new Task[100];
    private static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I am Duke");
        System.out.println("What can I do for you?");

        String s = "";
        while (true) {
            s = sc.nextLine();
            if (s.equals("bye")) {
                bye();
                break;
            } else if (s.equals("list")) {
                displayList();
            } else if (startWith(s, "mark")) {
                int index = Integer.parseInt(extractIndex(s, "mark"));
                //System.out.println(index);
                all[index - 1].markAsDone();
            } else if (startWith(s, "unmark")) {
                int index = Integer.parseInt(extractIndex(s, "unmark"));
                //System.out.println(index);
                all[index - 1].markAsUndone();
            } else {
                store(s);
            }
        }
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static  void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.printf("%s. %s\n", i + 1, all[i].toString());
        }
    }

    private static void store(String s) {
        all[count] = Task.of(s);
        System.out.println("Got it. I've added this task:");
        System.out.printf("  added: %s\n", all[count].toString());
        count += 1;
        System.out.printf("Now you have %s tasks in the list.\n", count);

    }

    private static boolean startWith(String s, String target) {
        for (int i = 0; i < target.length(); i++) {
            if (s.charAt(i) != target.charAt(i)) return false;
        }

        try {
            int val = Integer.parseInt(s.substring(target.length() + 1));
            return true;
        } catch (NumberFormatException e) {
            //System.out.println("Input String cannot be parsed to Integer.");
            return false;
        }

    }

    private static String extractIndex(String s, String target) {
        return s.substring(target.length() + 1);
    }

    public static class Task {
        private static String todo = "todo";
        private static String ddl = "deadline";
        private static String event = "event";
        protected String description;
        protected boolean isDone;

        public static Task of(String s) {
            int i = s.indexOf(" ");
            String identifier = s.substring(0, i);
//            System.out.printf("%s\n", identifier);
//            System.out.printf("%s\n", s.substring(i + 1));
            if (identifier.equals(todo)) {
                return new Todo(s.substring(i + 1));
            } else if (identifier.equals(ddl)) {
                int j = s.indexOf("/by");
                return new Deadline(s.substring(ddl.length() + 1, j - 1), s.substring(j + 4));
            } else if (identifier.equals(event)) {
                int j = s.indexOf("/at");
                return new Event(s.substring(event.length() + 1, j - 1), s.substring(j + 4));
            } else {
                return null;
            }
        }

        private Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        @Override
        public String toString() {
            return String.format("[%s] %s", getStatusIcon(), description);
        }

        public void markAsDone() {
            isDone = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.toString());
        }

        public void markAsUndone() {
            isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this.toString());
        }
    }

    private static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    private static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    private static class Event extends Task {
        private String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }
}
