import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

    private static void tracker() throws DukeException {
        final String HORIZONTAL_LINE = "  ____________________________________________________________";
        ArrayList<Task> storedTasks = new ArrayList<Task>(100);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(HORIZONTAL_LINE + "\n  Hello! I'm Duke\n  What can I do for you?\n" + HORIZONTAL_LINE);
        Scanner commands = new Scanner(System.in);
        while (true) {
            String command = commands.nextLine();
            if (command.equals("bye")) {
                System.out.println(HORIZONTAL_LINE + "\n  Bye. Hope to see you again soon!\n" + HORIZONTAL_LINE);
                commands.close();
                break;
            } else if (command.equals("list")) {
                System.out.println(HORIZONTAL_LINE);
                for (int i = 0; i < storedTasks.size(); i++) {
                    System.out.println("  " + String.valueOf(i + 1) + ". " + storedTasks.get(i));
                }
                System.out.println(HORIZONTAL_LINE);
            } else if (command.split(" ").length == 2 && command.split(" ")[0].equals("mark")) {
                Integer taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
                storedTasks.get(taskNumber).markAsDone();
                System.out.println(HORIZONTAL_LINE + "\n  Nice! I've marked this task as done:\n"
                        + storedTasks.get(taskNumber) + "\n" + HORIZONTAL_LINE);
            } else if (command.split(" ").length == 2 && command.split(" ")[0].equals("unmark")) {
                Integer taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
                storedTasks.get(taskNumber).markAsUndone();
                System.out.println(HORIZONTAL_LINE + "\n  OK, I've marked this task as not done yet:\n"
                        + storedTasks.get(taskNumber) + "\n" + HORIZONTAL_LINE);
            } else if (command.split(" ").length > 1 && command.split(" ")[0].equals("todo")) {
                ArrayList<String> commandDelimited = new ArrayList<String>(Arrays.asList(command.split(" ")));
                StringBuilder description = new StringBuilder();
                for (int i = 1; i < commandDelimited.size(); i++) {
                    description.append(commandDelimited.get(i));
                    if (i != commandDelimited.size() - 1) {
                        description.append(" ");
                    }
                }
                Task todo = new Todos(description.toString());
                storedTasks.add(todo);
                System.out.println(
                        HORIZONTAL_LINE + "\n  Got it. I've added this task:\n  " + todo + "\n  Now you have "
                                + String.valueOf(storedTasks.size()) + " tasks in the list.\n" + HORIZONTAL_LINE);
            } else if (command.split(" ").length > 3 && command.split(" ")[0].equals("deadline")) {
                ArrayList<String> commandDelimited = new ArrayList<String>(Arrays.asList(command.split(" ")));
                int posOfBy = commandDelimited.indexOf("/by");
                StringBuilder description = new StringBuilder();
                StringBuilder dateTime = new StringBuilder();
                for (int i = 1; i < commandDelimited.size(); i++) {
                    if (i < posOfBy) {
                        description.append(commandDelimited.get(i));
                        if (i != posOfBy - 1) {
                            description.append(" ");
                        }
                    } else if (i > posOfBy) {
                        dateTime.append(commandDelimited.get(i));
                        if (i != commandDelimited.size() - 1) {
                            dateTime.append(" ");
                        }
                    }
                }
                Task deadline = new Deadlines(description.toString(), dateTime.toString());
                storedTasks.add(deadline);
                System.out.println(
                        HORIZONTAL_LINE + "\n  Got it. I've added this task:\n  " + deadline + "\n  Now you have "
                                + String.valueOf(storedTasks.size()) + " tasks in the list.\n" + HORIZONTAL_LINE);
            } else if (command.split(" ").length > 3 && command.split(" ")[0].equals("event")) {
                ArrayList<String> commandDelimited = new ArrayList<String>(Arrays.asList(command.split(" ")));
                int posOfAt = commandDelimited.indexOf("/at");
                StringBuilder description = new StringBuilder();
                StringBuilder specificTime = new StringBuilder();
                for (int i = 1; i < commandDelimited.size(); i++) {
                    if (i < posOfAt) {
                        description.append(commandDelimited.get(i));
                        if (i != posOfAt - 1) {
                            description.append(" ");
                        }
                    } else if (i > posOfAt) {
                        specificTime.append(commandDelimited.get(i));
                        if (i != commandDelimited.size() - 1) {
                            specificTime.append(" ");
                        }
                    }
                }
                Task event = new Events(description.toString(), specificTime.toString());
                storedTasks.add(event);
                System.out.println(
                        HORIZONTAL_LINE + "\n  Got it. I've added this task:\n  " + event + "\n  Now you have "
                                + String.valueOf(storedTasks.size()) + " tasks in the list.\n" + HORIZONTAL_LINE);
            } else {
                commands.close();
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void main(String[] args) {
        try {
            tracker();
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}