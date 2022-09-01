import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final ArrayList<Task> LIST_OF_THINGS = new ArrayList<>();

    public static boolean isMarkCommand(String str) {
        if (str.length() < 6) {
            return false;
        }
        if (!str.startsWith("mark")) {
            return false;
        }
        try {
            Integer.parseInt(str.substring(5));
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isUnmarkCommand(String str) {
        if (str.length() < 8) {
            return false;
        }
        if (!str.startsWith("unmark")) {
            return false;
        }
        try {
            Integer.parseInt(str.substring(7));
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }


    public static void main(String[] args) throws IOException {
        System.out.println("Hi, I'm Duke. What can I do for you?");
        Scanner keyboard = new Scanner(System.in);
        String message = keyboard.nextLine();
        while (true) {
            if (message.equals("bye")) {
                System.out.println("Bye! See you next time!");
                break;
            } else if (message.equals("list")) {
                StringBuilder output_message = new StringBuilder();
                for (int i = 0; i < LIST_OF_THINGS.size(); i++) {
                    output_message.append(String.format("%d. %s", i + 1, LIST_OF_THINGS.get(i)));
                    output_message.append("\n");
                }
                System.out.println(output_message);
            } else if (isMarkCommand(message)) {
                int index = Integer.parseInt(message.substring(5));
                if (index > LIST_OF_THINGS.size() || index < 1) {
                    System.out.printf("Sorry, task %d does not exist!%n", index);
                } else {
                    Task task = LIST_OF_THINGS.get(index - 1);
                    task.markComplete();
                    System.out.printf("Good Job! This task has been marked as done:%n%s%n", task);
                }
            } else if (isUnmarkCommand(message)) {
                int index = Integer.parseInt(message.substring(7));
                if (index > LIST_OF_THINGS.size() || index < 1) {
                    System.out.printf("Sorry, task %d does not exist!%n", index);
                } else {
                    Task task = LIST_OF_THINGS.get(index - 1);
                    task.markIncomplete();
                    System.out.printf("Get it done! This task has been marked as undone:%n%s%n", task);
                }
            } else if (message.startsWith("deadline")) {
                int slash_char_pos = message.indexOf("/by");
                if (slash_char_pos < 13) {
                    System.out.println("Deadline tasks require a task name and a deadline specified by /by");
                } else {
                    String task_name = message.substring(9, slash_char_pos);
                    String deadline = message.substring(slash_char_pos + 4);
                    Task this_task = new Task.DeadlineTask(task_name, deadline);
                    LIST_OF_THINGS.add(this_task);
                    System.out.printf("I've added this task:%n%s%n", this_task);
                }
            } else if (message.startsWith(("event"))) {
                int slash_char_pos = message.indexOf("/at");
                if (slash_char_pos < 10) {
                    System.out.println("Event tasks require a task name and a datetime specified by /at");
                } else {
                    String task_name = message.substring(6, slash_char_pos);
                    String date = message.substring(slash_char_pos + 4);
                    Task this_task = new Task.EventTask(task_name, date);
                    LIST_OF_THINGS.add(this_task);
                    System.out.printf("I've added this task:%n%s%n", this_task);
                }
            } else if (message.startsWith(("todo"))) {
                String task_name = message.substring(5);
                Task this_task = new Task.TodoTask(task_name);
                LIST_OF_THINGS.add(this_task);
                System.out.printf("I've added this task:%n%s%n", this_task);
            } else if (message.startsWith("delete")) {
                try {
                    int task_index = Integer.parseInt(message.substring(7)) - 1;
                    Task removed = LIST_OF_THINGS.remove(task_index);
                    System.out.printf("Noted. I've removed this task:%n%s%nNow you have %d tasks in the list%n",
                            removed, LIST_OF_THINGS.size());
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please indicate which task you would like to delete");
                } catch (NumberFormatException e) {
                    System.out.println("You need to enter a number!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.printf("You only have %d tasks!%n", LIST_OF_THINGS.size());
                }
            } else {
                System.out.println("Sorry, I don't know what that means :(");
            }
            message = keyboard.nextLine();
        }
    }
}
