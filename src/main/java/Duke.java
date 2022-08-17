import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> data = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        try {
            handleCommands();
        } catch (DukeException e) {
            System.out.println(Style.INDENTATION + e);
        }
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke\n"
        + "What can I do for you?\n");
    }

    private static void handleCommands() throws DukeException {
        Scanner myObj = new Scanner(System.in);
        while (true) {
            String commandLineArgument = myObj.nextLine();
            String[] commands = commandLineArgument.split(" ", 2);

            if (Objects.equals(commands[0], "bye")) {
                System.out.println(Style.INDENTATION + "Bye. Hope to see you again soon!\n");
                break;
            } else if (Objects.equals(commands[0], "list")) {
                System.out.println(Style.INDENTATION + "Here are the tasks in your list:");
                for (int i = 0; i < data.size(); i++) {
                    System.out.println(Style.INDENTATION + (i + 1)  + "." + data.get(i));
                }
                System.out.println("");
            } else if (Objects.equals(commands[0], "unmark") || Objects.equals(commands[0], "mark")) {
                int taskIndex = Integer.parseInt(commands[1]) - 1;
                Task task = data.get(taskIndex);
                if (Objects.equals(commands[0], "unmark")) {
                    task.unmark();
                    System.out.println(Style.INDENTATION + "OK, I've marked this task as not done yet:");
                } else if (Objects.equals(commands[0], "mark")) {
                    task.markAsDone();
                    System.out.println(Style.INDENTATION + "Nice! I've marked this task as done:");
                }
                System.out.println(Style.INDENTATION + Style.HALF_INDENTATION + task + "\n");
            } else if (Objects.equals(commands[0], "deadline") || Objects.equals(commands[0], "event")
                        || Objects.equals(commands[0], "todo")) {
                Task task = new Task("null");

                if (Objects.equals(commands[0], "deadline")) {
                    try {
                        String[] taskDetails = commands[1].split(" /by ");
                        task = new Deadline(taskDetails[0], taskDetails[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("The description or date time of a deadline cannot be empty.");
                    }
                } else if (Objects.equals(commands[0], "event")) {
                    try {
                        String[] taskDetails = commands[1].split(" /at ");
                        task = new Event(taskDetails[0], taskDetails[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("The description or date time of an event cannot be empty.");
                    }
                } else if (Objects.equals(commands[0], "todo")) {
                    try {
                        task = new ToDo(commands[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                }

                data.add(task);
                String taskOrTasks = data.size() == 1 ? "task" : "tasks";
                System.out.println(Style.INDENTATION + "Got it. I've added this task:");
                System.out.println(Style.INDENTATION + Style.HALF_INDENTATION + task);
                System.out.println(Style.INDENTATION + "Now you have " + data.size() + " "
                        + taskOrTasks + " in the list.\n");
            } else if (Objects.equals(commands[0], "delete")) {
                int taskIndex = Integer.parseInt(commands[1]) - 1;
                Task task = data.get(taskIndex);
                data.remove(taskIndex);

                String taskOrTasks = data.size() == 1 ? "task" : "tasks";
                System.out.println(Style.INDENTATION + "Noted. I've removed this task:");
                System.out.println(Style.INDENTATION + Style.HALF_INDENTATION + task);
                System.out.println(Style.INDENTATION + "Now you have " + data.size() + " "
                        + taskOrTasks + " in the list.\n");
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
