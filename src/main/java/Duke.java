import java.rmi.activation.UnknownObjectException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static class UnknownCommandException extends RuntimeException {
        public UnknownCommandException() {
            super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static class EmptyDescriptionException extends RuntimeException {
        public EmptyDescriptionException(String message) {
            super("☹ OOPS!!! The description of a " + message + " cannot be empty.");
        }
    }

    public static class Task {
        protected boolean isDone;
        protected String description;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void mark() {
            this.isDone = true;
        }

        public void unmark() {
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

    }

    public static class Todo extends Task{

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T][" + this.getStatusIcon() + "] " + this.description;
        }

    }

    public static class Deadline extends Task{

        protected String deadline;

        public Deadline(String description, String deadline) {
            super(description);
            this.deadline = deadline;
        }


        @Override
        public String toString() {
            return "[D][" + this.getStatusIcon() + "] " + this.description + " (by: " + this.deadline + ")";
        }

    }

    public static class Event extends Task{

        protected String time;

        public Event(String description, String time) {
            super(description);
            this.time = time;
        }

        @Override
        public String toString() {
            return "[E][" + this.getStatusIcon() + "] " + this.description + " (at: " + this.time + ")";
        }

    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Jett");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            System.out.println("____________________________________________________________");
            String[] commands = command.split(" ");
            try {
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (command.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + "." + tasks.get(i));
                    }
                } else if (commands[0].equals("mark")) {
                    if (commands.length == 1) {
                        throw new EmptyDescriptionException("mark");
                    }
                    System.out.println("Nice! I've marked this task as done:");
                    int index = Integer.parseInt(commands[1]) - 1;
                    tasks.get(index).mark();
                    System.out.println(tasks.get(index));
                } else if (commands[0].equals("unmark")) {
                    if (commands.length == 1) {
                        throw new EmptyDescriptionException("unmark");
                    }
                    System.out.println("OK, I've marked this task as not done yet:");
                    int index = Integer.parseInt(commands[1]) - 1;
                    tasks.get(index).unmark();
                    System.out.println(tasks.get(index));
                } else if (commands[0].equals("todo")) {
                    if (commands.length == 1) {
                        throw new EmptyDescriptionException("todo");
                    }
                    System.out.println("Got it. I've added this task:");
                    String[] newCommands = Arrays.copyOfRange(commands, 1, commands.length);
                    String newCommand = String.join(" ", newCommands);
                    tasks.add(new Todo(newCommand));
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (commands[0].equals("event")) {
                    if (commands.length == 1) {
                        throw new EmptyDescriptionException("event");
                    }
                    int atMarker = 1;
                    System.out.println("Got it. I've added this task:");
                    for (int i = 0; i < commands.length; i++) {
                        if (commands[i].equals("/at")) {
                            atMarker = i;
                        }
                    }
                    String[] newCommands = Arrays.copyOfRange(commands, 1, atMarker);
                    String newCommand = String.join(" ", newCommands);
                    String[] time = Arrays.copyOfRange(commands, atMarker + 1, commands.length);
                    String timeString = String.join(" ", time);
                    tasks.add(new Event(newCommand, timeString));
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (commands[0].equals("deadline")) {
                    if (commands.length == 1) {
                        throw new EmptyDescriptionException("deadline");
                    }
                    int atMarker = 1;
                    System.out.println("Got it. I've added this task:");
                    for (int i = 0; i < commands.length; i++) {
                        if (commands[i].equals("/by")) {
                            atMarker = i;
                        }
                    }
                    String[] newCommands = Arrays.copyOfRange(commands, 1, atMarker);
                    String newCommand = String.join(" ", newCommands);
                    String[] deadline = Arrays.copyOfRange(commands, atMarker + 1, commands.length);
                    String deadlineString = String.join(" ", deadline);
                    tasks.add(new Deadline(newCommand, deadlineString));
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    throw new UnknownCommandException();
                }
            } catch (UnknownCommandException | EmptyDescriptionException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("____________________________________________________________");
        }
    }
}
