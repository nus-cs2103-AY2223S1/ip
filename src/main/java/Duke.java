import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.lang.*;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        List<Task> tasks = new ArrayList<Task>();
        int taskIndex = 0;
        String indent = "    ";
        String line = "-------------------------------------------------";
        System.out.println(line);
        System.out.println(indent + "Hello! I'm Duke");
        System.out.println(indent + "What can I do for you?");
        System.out.println(line);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            System.out.println(line);
            if (input.equals("bye")) {
                System.out.println(indent + "Bye. Hope to see you again soon!");
            } else if (input.equals("list")) {
                for (int j = 0; j < taskIndex; j++) {
                    int taskNum = j + 1;
                    System.out.println(indent + taskNum + ". " + tasks.get(j));
                }
            } else if (input.startsWith("mark")) {
                Task task = tasks.get(Integer.valueOf(input.substring(5)) - 1);
                task.mark();
                System.out.println(indent + "Nice! I've marked this task as done:");
                System.out.println("      " + task);
            } else if (input.startsWith("unmark")) {
                Task task = tasks.get(Integer.valueOf(input.substring(7)) - 1);
                task.unmark();
                System.out.println(indent + "Ok, I've marked this task as not done yet:");
                System.out.println("      " + task);
            } else if (input.startsWith("delete")) {
                int index = Integer.valueOf(input.substring(7)) - 1;
                Task task = tasks.get(index);
                tasks.remove(index);
                taskIndex -= 1;
                System.out.println("Noted. I've removed this task:");
                System.out.println("      " + task);
                System.out.println(indent + "Now you have " + taskIndex + " tasks in the list");
            } else if (input.startsWith("todo")) {
                if (!input.equals("todo")) {
                    tasks.add(new Todo(input));
                    System.out.println(indent + "Got it. I've added this task:");
                    System.out.println("      " + tasks.get(taskIndex));
                    taskIndex += 1;
                    System.out.println(indent + "Now you have " + taskIndex + " tasks in the list");
                } else {
                     throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (input.startsWith("event")) {
                try {
                    String event = input.substring(0, input.indexOf("/"));
                    String[] splitTime = input.substring(input.indexOf("/") + 1).split("\\s+");
                    LocalDate date = LocalDate.parse(splitTime[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    LocalTime time = LocalTime.parse(splitTime[2], DateTimeFormatter.ofPattern("HHmdem"));
                    tasks.add(new Event(event, LocalDateTime.of(date, time)));
                    System.out.println(indent + "Got it. I've added this task:");
                    System.out.println("      " + tasks.get(taskIndex));
                    taskIndex += 1;
                    System.out.println(indent + "Now you have " + taskIndex + " tasks in the list");
                } catch (Exception e) {
                    System.out.println("Date and Time is not of correct format!");
                }
            } else if (input.startsWith("deadline")){
                try {
                    String deadlineTask = input.substring(0, input.indexOf("/"));
                    String[] splitTime = input.substring(input.indexOf("/") + 1).split("\\s+");
                    LocalDate date = LocalDate.parse(splitTime[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    LocalTime time = LocalTime.parse(splitTime[2], DateTimeFormatter.ofPattern("HHmm"));
                    tasks.add(new Deadline(deadlineTask, LocalDateTime.of(date, time)));
                    System.out.println(indent + "Got it. I've added this task:");
                    System.out.println("      " + tasks.get(taskIndex));
                    taskIndex += 1;
                    System.out.println(indent + "Now you have " + taskIndex + " tasks in the list");
                } catch (Exception e) {
                    System.out.println("Date and Time is not of correct format!");
                }
            } else if (input.equals("upcoming tasks")) { // get deadlines/event within 1 week from now
                LocalDateTime dateTime = LocalDateTime.now();
                for (Task task : tasks) {
                    if (task instanceof Deadline) {
                        Deadline deadline = (Deadline) task;
                        if (deadline.getTime().isBefore(dateTime.plusWeeks(1))) {
                            System.out.println(deadline);
                        }
                    } else if (task instanceof Event) {
                        Event event = (Event) task;
                        if (event.getTime().isBefore(dateTime.plusWeeks(1))) {
                            System.out.println(event);
                        }
                    }
                }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.print(line);
            System.out.println();
            if (input.equals("bye")) {
                System.exit(0);
            }
        }
    }
}
