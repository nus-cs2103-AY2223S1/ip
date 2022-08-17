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
        handleCommands();
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke\n"
        + "What can I do for you?\n");
    }

    private static void handleCommands() {
        Scanner myObj = new Scanner(System.in);
        while (true) {
            String command = myObj.nextLine();
            if (Objects.equals(command, "bye")) {
                System.out.println(Style.INDENTATION + "Bye. Hope to see you again soon!\n");
                break;
            } else if (Objects.equals(command, "list")) {
                System.out.println(Style.INDENTATION + "Here are the tasks in your list:");
                for (int i = 0; i < data.size(); i++) {
                    System.out.println(Style.INDENTATION + (i + 1)  + "." + data.get(i));
                }
                System.out.println("");
            } else if (command.contains("unmark")) {
                String[] temp = command.split(" ");
                int taskIndex = Integer.parseInt(temp[1]) - 1;
                Task task = data.get(taskIndex);
                task.unmark();
                System.out.println(Style.INDENTATION + "OK, I've marked this task as not done yet:");
                System.out.println(Style.INDENTATION + Style.HALF_INDENTATION + task + "\n");
            } else if (command.contains("mark")) {
                String[] temp = command.split(" ");
                int taskIndex = Integer.parseInt(temp[1]) - 1;
                Task task = data.get(taskIndex);
                task.markAsDone();
                System.out.println(Style.INDENTATION + "Nice! I've marked this task as done:");
                System.out.println(Style.INDENTATION + Style.HALF_INDENTATION + task + "\n");
            } else if (command.contains("deadline") || command.contains("event") || command.contains("todo")) {
                String temp = command.split(" ", 2)[1];
                Task task = new Task("null");

                if (command.contains("deadline")) {
                    String[] taskDetails = temp.split(" /by ");
                    task = new Deadline(taskDetails[0], taskDetails[1]);
                } else if (command.contains("event")) {
                    String[] taskDetails = temp.split(" /at ");
                    task = new Event(taskDetails[0], taskDetails[1]);
                } else if (command.contains("todo")) {
                    task = new ToDo(temp);
                }

                data.add(task);
                String taskOrTasks = data.size() == 1 ? "task" : "tasks";
                
                System.out.println(Style.INDENTATION + "Got it. I've added this task:");
                System.out.println(Style.INDENTATION + Style.HALF_INDENTATION + task);
                System.out.println(Style.INDENTATION + "Now you have " + data.size() + " "
                        + taskOrTasks + " in the list.\n");
            } else {
                data.add(new Task(command));
                System.out.println(Style.INDENTATION + "added: " + command + '\n');
            }
        }
    }
}
