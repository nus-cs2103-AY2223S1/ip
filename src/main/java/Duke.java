import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
    private class Task {
        String name;
        Boolean done = false;

        Task(String name) {
            this.name = name;
        }
        private void changeDone() {
            this.done = !done;
        }
        @Override
        public String toString() {
            String mark = done ? "X" : "";
            return "[" + mark + "] " + name;
        }
    }
    private class Todo extends Task {
        Todo(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }
    private class Deadlines extends Task {
        String byDate;
        Deadlines(String name, String byDate) {
            super(name);
            this.byDate = byDate;
        }
        @Override
        public String toString() {
            return "[D]" + super.toString() + "(by: " + byDate + ")";
        }
    }
    private class Events extends Task {
        String atDate;
        Events(String name, String atDate) {
            super(name);
            this.atDate = atDate;
        }
        @Override
        public String toString() {
            return "[E]" + super.toString() + "(at: " + atDate + ")";
        }
    }


    Duke() {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("")) {
                    continue;
                } else {
                    System.out.println(input);
                }
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            System.out.println("Exiting");
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
    }
}
