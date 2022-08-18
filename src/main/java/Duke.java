import java.util.Scanner;
import java.util.*;
public class Duke {
    public static void main(String[] args) throws DukeException {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner input = new Scanner(System.in);
        List<Task> l = new ArrayList<Task>();

        while (true) {
            String line = input.nextLine();
            String command = line.replaceAll("\\s+","");
            System.out.println("____________________________________________________________");

            // throw exception at incomplete commands
            try{
                if (command.equals("todo") || command.equals("deadline") ||command.equals("event")) {
                    throw new DukeException(String.format("☹ OOPS!!! The description of a %s cannot be empty.", command));
                }
                if (command.equals("mark") || command.equals("unmark")) {
                    throw new DukeException(String.format("☹ OOPS!!! The task index to %s cannot be empty.", command));
                }
                if (command.length() >= 4 && "mark".equals(command.substring(0,4)) && !command.matches(".*[0-9]")){
                    throw new DukeException("☹ OOPS!!! There is no task index to mark.");
                }
                if (command.length() >= 6 && "unmark".equals(command.substring(0,6)) && !command.matches(".*[0-9]")) {
                    throw new DukeException("☹ OOPS!!! There is no task index to unmark.");
                }
                if (command.length() >= 5 && "event".equals(command.substring(0,5)) && !command.contains("/")) {
                    throw new DukeException("☹ OOPS!!! There is no start and end time given for a event.");
                }
                if (command.length() >= 8 && "deadline".equals(command.substring(0,8)) && !command.contains("/")) {
                    throw new DukeException("☹ OOPS!!! There is no end time given for a deadline.");
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
                System.out.println("____________________________________________________________");
                continue;
            }

            if ("bye".equals(line)) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            if ("list".equals(line)) {
                int count = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task t : l) {
                    System.out.println(String.format("%d.%s %s", count, t.getStatusIcon(),
                            t.getDescription()));
                    count += 1;
                }
            }

            else if ("mark".equals(line.split(" ")[0])) {
                int number = Integer.parseInt(line.substring(5));
                // throw exception if index out of task list length
                try {
                    if (number > l.size() || number < 1) {
                        throw new DukeException("☹ OOPS!!! There is no such task in the list.");
                    }
                } catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                    System.out.println("____________________________________________________________");
                    continue;
                }
                Task t = l.get(number - 1);
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(String.format("  %s %s", t.getStatusIcon(), t.getDescription()));
            }

            else if ("unmark".equals(line.split(" ")[0])) {
                int number = Integer.parseInt(line.substring(7));
                // throw exception if index out of task list length
                try {
                    if (number > l.size() || number < 1) {
                        throw new DukeException("☹ OOPS!!! There is no such task in the list.");
                    }
                } catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                    System.out.println("____________________________________________________________");
                    continue;
                }
                Task t = l.get(number - 1);
                t.markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(String.format("  %s %s", t.getStatusIcon(), t.getDescription()));
            }

            else if ("todo".equals(line.split(" ")[0])) {
                String task = line.substring(5);
                ToDo t = new ToDo(task);
                l.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(String.format("  %s %s", t.getStatusIcon(), t.getDescription()));
                System.out.println(String.format("Now you have %d tasks in the list.", l.size()));
            }

            else if ("deadline".equals(line.split(" ")[0])) {
                String task = line.substring(9).split("/")[0];
                String end = line.split("/")[1];
                Deadline t = new Deadline(task, end);
                l.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(String.format("  %s %s", t.getStatusIcon(), t.getDescription()));
                System.out.println(String.format("Now you have %d tasks in the list.", l.size()));
            }

            else if ("event".equals(line.split(" ")[0])) {
                String task = line.substring(6).split("/")[0];
                String time = line.split("/")[1];
                Event t = new Event(task, time);
                l.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(String.format("  %s %s", t.getStatusIcon(), t.getDescription()));
                System.out.println(String.format("Now you have %d tasks in the list.", l.size()));
            }
            else {
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                    System.out.println("____________________________________________________________");
                    continue;
                }
            }
            System.out.println("____________________________________________________________");
        }
    }
}
