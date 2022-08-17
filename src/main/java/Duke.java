import java.util.Arrays;
import java.util.Scanner;
import java.util.function.UnaryOperator;

public class Duke {

    private static String divider() {
        return "--------------------";
    }

    private static void setup() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println(divider());
    }

    private static String checkbox(Boolean mark) {
        return mark ? "[x]" : "[ ]";
    }

    static class EmptyInputException extends RuntimeException {
        public EmptyInputException() {
            super("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    static class InvalidInputException extends RuntimeException {
        public InvalidInputException() {
            super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void main(String[] args) {
        class Todo {
            class Task {
                protected String description;
                protected boolean isDone;

                public Task(String description) {
                    this.description = description;
                    this.isDone = false;
                }

                public boolean getIsDone() {
                    return isDone;
                }

                public String getDescription() {
                    return description;
                }

                public void mark() {
                    this.isDone = true;
                }

                public void unmark() {
                    this.isDone = false;
                }

                @Override
                public String toString() {
                    return String.format("[T] %s %s", checkbox(this.getIsDone()), this.getDescription());
                }
            }

            class Deadline extends Task {
                String deadline;

                public Deadline(String description, String deadline) {
                    super(description);
                    this.deadline = deadline;
                }

                @Override
                public String toString() {
                    return String.format("[D] %s %s (by: %s)", checkbox(this.getIsDone()), this.getDescription(), this.deadline);
                }
            }

            class Event extends Task {
                String time;

                public Event(String description, String time) {
                    super(description);
                    this.time = time;
                }

                @Override
                public String toString() {
                    return String.format("[E] %s %s (at: %s)", checkbox(this.getIsDone()), this.getDescription(), this.time);
                }
            }

            int pointer = 0;
            protected Task[] list;
            protected int length;

            public Todo(int length) {
                this.list = new Task[length];
                this.length = length;
            }

            public void addTask(String description) {
                this.list[pointer] = new Task(description);
                pointer++;
            }

            public void addDeadline(String desc, String deadline) {
                this.list[pointer] = new Deadline(desc, deadline);
                pointer++;
            }

            public void addEvent(String desc, String time) {
                this.list[pointer] = new Event(desc, time);
                pointer++;
            }

            public int getLength() {
                return this.length;
            }

            protected Task get(int index) {
                return list[index];
            }

            public void mark(int index) {
                this.get(index).mark();
            }

            public void unmark(int index) {
                this.get(index).unmark();
            }

            private void printData() {
                for (int i = 0; i < this.length; i++) {
                    if (this.get(i) == null) {
                        return;
                    }
                    Task task = this.get(i);
                    // Could use format strings for this
                    System.out.println((i + 1) + ". " + task);
                }
            }
        }
        Scanner scanner = new Scanner(System.in);
        Todo todolist = new Todo(100);
        setup();

        while (true) {
            try {
                String line = scanner.nextLine();
                System.out.println(divider());

                switch (line) {
                    case "":
                        throw new EmptyInputException();

                    case "bye":
                        System.out.println("See you again!");
                        return;

                    case "list":
                        todolist.printData();
                        break;

                    default:
//                  We can't use switch statements since we want regex matching
                        if (line.matches("mark \\d+")) {
                            int index = Integer.parseInt(line.split(" ")[1]);
                            if (index > 100) {
                                break;
                            }

                            todolist.mark(index - 1);
                            todolist.printData();
                            break;
                        }

                        if (line.matches("unmark \\d+")) {
                            int index = Integer.parseInt(line.split(" ")[1]);
                            if (index > 100) {
                                break;
                            }

                            todolist.unmark(index - 1);
                            todolist.printData();
                            break;
                        }

                        if (line.matches("deadline .* by .*")) {
                            String[] res = line.substring(9).split(" by ");
                            String desc = res[0];
                            String deadline = res[1];

                            todolist.addDeadline(desc,deadline);
                            System.out.println(String.format("Added: %s (by: %s)", desc, deadline));
                            break;
                        }

                        if (line.matches("event .* at .*")) {
                            String[] res = line.substring(6).split(" at ");
                            String desc = res[0];
                            String time = res[1];

                            todolist.addEvent(desc, time);
                            System.out.println(String.format("Added: %s (at: %s)", desc, time));
                            break;
                        }

                        if (line.matches("todo .*")) {
                            line = line.substring(5);
                            todolist.addTask(line);
                            System.out.println("Added: " + line);
                            break;
                        }

                        throw new InvalidInputException();
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
