
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Arrays;
import java.util.ArrayList;

import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;


public class Duke {

    public static class UnknownCommandException extends Exception {
        public UnknownCommandException() {
            super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static class EmptyDescriptionException extends RuntimeException {
        public EmptyDescriptionException(String message) {
            super("☹ OOPS!!! The description of a " + message + " cannot be empty.");
        }
    }

    public static abstract class Task {
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

        public String getDescription() {
            return this.description;
        }

        public abstract String getLetterTag();

        public abstract String getAdditionalInfo();

    }

    public static class Todo extends Task {

        public Todo(String description) {
            super(description);
        }

        @Override
        public String getLetterTag() {
            return "T";
        }

        @Override
        public String getAdditionalInfo() {
            return "";
        }


        @Override
        public String toString() {
            return "[" + this.getLetterTag() + "][" + this.getStatusIcon() + "] " + this.description;
        }

    }

    public static class Deadline extends Task {

        protected String deadline;

        public Deadline(String description, String deadline) {
            super(description);
            this.deadline = deadline;
        }

        @Override
        public String getLetterTag() {
            return "D";
        }

        @Override
        public String getAdditionalInfo() {
            return this.deadline;
        }

        @Override
        public String toString() {
            return "[" + this.getLetterTag() + "][" + this.getStatusIcon() + "] "
                    + this.description + " (by: " + this.deadline + ")";
        }

    }

    public static class Event extends Task {

        protected String time;

        public Event(String description, String time) {
            super(description);
            this.time = time;
        }

        @Override
        public String getLetterTag() {
            return "E";
        }

        @Override
        public String getAdditionalInfo() {
            return this.time;
        }

        @Override
        public String toString() {
            return "[" + this.getLetterTag() + "][" + this.getStatusIcon() + "] "
                    + this.description + " (at: " + this.time + ")";
        }

    }

    public static void main(String[] args) throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File("tasks.txt");
        file.createNewFile();
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNext()) {
            String tempTask = fileScanner.nextLine();
            String[] loadedTask = tempTask.split("~~");
            switch (loadedTask[0]) {
                case "T":
                    tasks.add(new Todo(loadedTask[2]));
                    break;
                case "D":
                    tasks.add(new Deadline(loadedTask[2], loadedTask[3]));
                    break;
                case "E":
                    tasks.add(new Event(loadedTask[2], loadedTask[3]));
                    break;
            }
            if (loadedTask[1].equals("X")) {
                tasks.get(tasks.size() - 1).mark();
            }
        }

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
                    for (int i = 0; i < commands.length; i++) {
                        if (commands[i].equals("/at")) {
                            atMarker = i;
                        }
                    }
                    String[] newCommands = Arrays.copyOfRange(commands, 1, atMarker);
                    String newCommand = String.join(" ", newCommands);
                    String[] time = Arrays.copyOfRange(commands, atMarker + 1, commands.length);
                    String timeString = String.join(" ", time);
                    try {
                        LocalDate timeStringParsed = LocalDate.parse(timeString);
                        tasks.add(new Event(newCommand, timeStringParsed.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } catch (DateTimeParseException error) {
                        System.out.println("We do not recognise that date time format! Please enter in yyyy-mm-dd format");
                    }
                } else if (commands[0].equals("deadline")) {
                    if (commands.length == 1) {
                        throw new EmptyDescriptionException("deadline");
                    }
                    int atMarker = 1;

                    for (int i = 0; i < commands.length; i++) {
                        if (commands[i].equals("/by")) {
                            atMarker = i;
                        }
                    }
                    String[] newCommands = Arrays.copyOfRange(commands, 1, atMarker);
                    String newCommand = String.join(" ", newCommands);
                    String[] deadline = Arrays.copyOfRange(commands, atMarker + 1, commands.length);
                    String deadlineString = String.join(" ", deadline);
                    try {
                        LocalDate deadlineParsed = LocalDate.parse(deadlineString);
                        tasks.add(new Deadline(newCommand, deadlineParsed.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } catch (DateTimeParseException error) {
                        System.out.println("We do not recognise that date time format! Please enter in yyyy-mm-dd format");
                    }
                } else if (commands[0].equals("delete")) {
                    if (commands.length == 1) {
                        throw new EmptyDescriptionException("delete");
                    }
                    System.out.println("Noted. I've removed this task:");
                    int index = Integer.parseInt(commands[1]) - 1;
                    System.out.println(tasks.get(index));
                    tasks.remove(index);
                } else {
                    throw new UnknownCommandException();
                }
            } catch (UnknownCommandException | EmptyDescriptionException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("____________________________________________________________");

            new FileWriter("tasks.txt").close();
            FileWriter fileWriter = new FileWriter("tasks.txt");
            for (Task task : tasks) {
                fileWriter.write(task.getLetterTag() + "~~" + task.getStatusIcon() + "~~" +
                        task.getDescription() + "~~" + task.getAdditionalInfo() + System.lineSeparator());
            }
            fileWriter.close();
        }
    }
}
