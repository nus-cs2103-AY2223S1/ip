import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        List<Task> list = new ArrayList<Task>();

        System.out.println(reply("Hello! I'm Duke. What can I do for you?"));
        Scanner userInput = new Scanner(System.in);
        while (true) {
            Task newTask = null;
            String userText = userInput.nextLine();
            boolean understood = true;

            String commandWord = userText.split(" ", 2)[0];

            if (commandWord.equals("bye")) {
                System.out.println(reply("Bye. Hope to see you again soon!"));
                break;
            } else if (commandWord.equals("list")) {
                System.out.println(reply(getList(list)));
            } else if (commandWord.equals("mark")) {
                try {
                    Task selectedTask = list.get(Integer.valueOf(userText.substring(5)) - 1);
                    selectedTask.markAsDone();
                    System.out.println(reply("Nice! I've marked this task as done:\n" + selectedTask.getStatus()));
                } catch (Exception e) {
                    System.out.println(reply("Please input a valid number"));
                }
            } else if (commandWord.equals("unmark")) {
                try {
                    Task selectedTask = list.get(Integer.valueOf(userText.substring(7)) - 1);
                    selectedTask.markAsUndone();
                    System.out.println(reply("OK, I've marked this task as not done yet:\n" + selectedTask.getStatus()));
                } catch (Exception e) {
                    System.out.println(reply("Please input a valid number"));
                }
            } else if (commandWord.equals("todo")) {
                newTask = new ToDo();
            } else if (commandWord.equals("deadline")) {
                newTask = new DeadLine();
            } else if (commandWord.equals("event")) {
                newTask = new Event();
            } else if (commandWord.equals("delete")) {
                try {
                    Task selectedTask = list.remove(Integer.valueOf(userText.substring(7)) - 1);
                    System.out.println(reply("Noted. I've removed this task:\n" + selectedTask.getStatus()
                            + String.format("\nNow you have %d tasks in the list.", list.size())));
                } catch (Exception e) {
                    System.out.println(reply("Please input a valid number"));
                }
            } else {
                understood = false;
            }

            if (understood == false) {
                System.out.println(reply("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
            }

            if (newTask != null) {
                try {
                    newTask.addName(userText);
                    list.add(newTask);
                    System.out.println(reply("Got it. I've added this task:\n" + newTask.getStatus() +
                        String.format("\nNow you have %d tasks in the list.", list.size())));
                } catch (Exception e) {
                    System.out.println(reply(e.getMessage()));
                }
            }
        }
    }

    public static String getList(List<Task> list) {
        String listString = "";
        for (int i = 0; i < list.size(); i++) {
            listString += String.format("%d. %s", i + 1, list.get(i).getStatus());
            if (i != list.size() - 1) {
                listString += "\n";
            }
        }
        return listString;
    }

    public static String reply(String s) {
        String horizontalLine = "____________________________________________________________\n";
        return horizontalLine + s +'\n' + horizontalLine;
    }
}
