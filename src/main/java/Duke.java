import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Yale\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);

        ArrayList<Task> toDoList = new ArrayList<Task>();

        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else if (input.equals("list")) {

                System.out.println("Here are the tasks in your list:");
                int i = 1;
                for (Task task : toDoList) {
                    System.out.printf("%d.%s\n", i, task);
                    i++;
                }

            } else if (input.contains("unmark")) {
                String[] splitStr = input.trim().split("\\s+");
                int unMarkItem = Integer.parseInt(splitStr[splitStr.length - 1]) - 1;
                toDoList.get(unMarkItem).unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(toDoList.get(unMarkItem));
            } else if (input.contains("mark")) {
                String[] splitStr = input.trim().split("\\s+");
                int markItem = Integer.parseInt(splitStr[splitStr.length - 1]) - 1;
                toDoList.get(markItem).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(toDoList.get(markItem));
            } else if (input.contains("delete")) {
                String[] splitStr = input.trim().split("\\s+");
                int deleteItem = Integer.parseInt(splitStr[splitStr.length - 1]) - 1;
                Task deletedTask = toDoList.remove(deleteItem);
                String message = "Noted. I've removed this task:\n  "
                        + deletedTask
                        + String.format("\nNow you have %s tasks in the list.", toDoList.size());
                System.out.println(message);
            } else {
                Boolean addTask = true;
                Task newTask = null;
                if (input.contains("deadline")) {
                    String[] splitStr = input.trim().split("/");
                    String date = splitStr[1].replace("by", "").trim();
                    newTask = new Deadline(splitStr[0].replace("deadline", "").trim(), date);
                } else if (input.contains("event")) {
                    String[] splitStr = input.trim().split("/");
                    String date = splitStr[1].replace("at", "").trim();
                    newTask = new Event(splitStr[0].replace("event", "").trim(), date);
                } else if (input.contains("todo")) {
                    try {
                        newTask = new Todo(input.replace("todo", "").trim());
                    } catch (DukeException e) {
                        addTask = false;
                        System.out.println(e);
                    }
                } else {
                    addTask = false;
                    try {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    } catch (DukeException e) {
                        System.out.println(e);
                    }
                }

                if (addTask) {
                    toDoList.add(newTask);
                    String message = "\t" + "_".repeat(20) + "\n"
                            + String.format("\tGot it. I've added this task:\n\t  %s\n", newTask)
                            + String.format("\tNow you have %s tasks in the list\n", toDoList.size())
                            + "\t" + "_".repeat(20);
                    System.out.println(message);
                }
            }
        }
    }
}
