import exceptions.DukeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static void printSeparator() {
        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    
    private static void printMsg(String msg) {
        printSeparator();
        System.out.println("     " + msg);
        printSeparator();
    }
    
    private static void printMultiMsg(String[] msgs) {
        printSeparator();
        for (String msg : msgs) {
            System.out.println("     " + msg);
        }
        printSeparator();
    }

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printMultiMsg(new String[]{"Hello my name is Duke", "What can I do for you?"});

        Scanner sc = new Scanner (System.in);
        String command, description;

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.length() < 1) continue;

            try {
                String[] words = input.split(" ", 2);
                command = words[0];
                if (words.length > 1) description = words[1];
                else description = "";

                if (command.equals("bye")) {
                    printMsg("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    String[] taskStrings = new String[tasks.size()];
                    for (int i = 0; i < tasks.size(); i++) {
                        taskStrings[i] = (i + 1) + ". " + tasks.get(i).toString();
                    }
                    printMultiMsg(taskStrings);
                } else if (command.equals("mark")) {
                    Task task = tasks.get(Integer.parseInt(words[1]) - 1);
                    task.markDone();
                    printMultiMsg(new String[]{"Nice! I've marked this task as done:", task.toString()});
                } else if (command.equals("todo")) {
                    if (description.length() == 0) throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    Task task = new ToDo(description, false);
                    tasks.add(task);
                    printMultiMsg(new String[]{
                            "Got it. I've added this task:",
                            "  " + task,
                            "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list."
                    });
                } else if (command.equals("deadline")) {
                    String[] splitArgs = description.split(" /by ", 2);
                    String title = splitArgs[0];
                    String by = splitArgs[1];
                    Task task = new Deadline(title, false, by);
                    tasks.add(task);
                    printMultiMsg(new String[]{
                            "Got it. I've added this task:",
                            "  " + task,
                            "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list."
                    });
                } else if (command.equals("event")) {
                    String[] splitArgs = description.split(" /at ", 2);
                    String title = splitArgs[0];
                    String at = splitArgs[1];
                    Task task = new Event(title, false, at);
                    tasks.add(task);
                    printMultiMsg(new String[]{
                            "Got it. I've added this task:",
                            "  " + task,
                            "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list."
                    });
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printMsg(e.getMessage());
            }
        }
    }
}
