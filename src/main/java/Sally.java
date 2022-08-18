import java.util.ArrayList;
import java.util.Scanner;

public class Sally {
    private static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        border();
        System.out.println("Hello! I'm Sally \uD83D\uDE04");
        System.out.println("What can I do for you?");
        border();
        messaging();
    }

    private static void messaging() {
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();

        if (message.equals("bye")) {
            border();
            System.out.println("Until next time!");
            border();
        } else if (message.equals("list")) {
            border();
            if (list.size() == 0) {
                System.out.println("You don't have any list right now");
            } else {
                System.out.println("Here's your current list:");
                System.out.println(printList());
            }
            border();
            messaging();
        } else if (message.contains("unmark")) {
            int taskNum = Integer.parseInt(message.substring(7)) - 1; // -1 so that index is constant
            if (taskNum >= 0 && taskNum < list.size()) {
                Task task = list.get(taskNum);
                String description = task.toString();
                border();
                if (task.isDone) {
                    task.markAsUndone();
                    String unmarkTask = task.toString();
                    System.out.println("Got it, I've unmarked this task for you!\n" + unmarkTask);
                } else {
                    System.out.printf("You have not marked: \n  " + description + "\n");
                }
                border();
            } else {
                border();
                int taskNumUn = taskNum + 1;
                System.out.println("Sorry :( task number " + taskNumUn + " cannot be found. " +
                        "You might want to check your list again!\n");
                border();
            }
            messaging();
        } else if (!message.contains("unmark") && message.contains("mark")) {
            int taskNum = Integer.parseInt(message.substring(5)) - 1; // -1 so that index is constant
            if (taskNum >= 0 && taskNum < list.size()) {
                Task task = list.get(taskNum);
                String description = task.toString();
                border();
                if (!task.isDone) {
                    task.markAsDone();
                    String markTask = task.toString();
                    System.out.println("Got it, I've marked this task for you!\n" + markTask);
                } else {
                    System.out.printf("You have previously done: \n    " + description + "\n");
                }
                border();
            } else {
                border();
                int taskNumOut = taskNum + 1;
                System.out.println("Sorry :( task number " + taskNumOut + " cannot be found. " +
                        "You might want to check your list again!\n");
                border();
            }
            messaging();
        } else {
            //ToDos
            if (message.startsWith("todo ")) {
                String description = message.substring(5);
                list.add(new ToDo(description));
            }
            //Deadlines
            else if (message.startsWith("deadline ") && message.contains(" /by ")) {
                String description = message.substring(9, message.indexOf("/by") - 1);
                String by = message.substring(message.indexOf("/by") + 4);
                list.add(new Deadline(description, by));
            }
            //Events
            else if (message.startsWith("event ") && message.contains( "/at ")) {
                String description = message.substring(6, message.indexOf("/at") - 1);
                String at = message.substring(message.indexOf("/at") + 4);
                list.add(new Event(description, at));
            }

            //Message printed out
            String newTask = list.get(list.size() - 1).toString();
            int totalTasks = list.size();
            border();
            System.out.println("Got it. I've added this task:\n    " + newTask + "\nto your to-do list!");
            if (totalTasks == 1) {
                System.out.println("Now you have 1 task in the list.\n");
            } else {
                System.out.println("Now you have " + totalTasks + " tasks in the list.\n");
            }
            border();
            messaging();
        }
    }

    private static String printList() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            int number = i + 1;
            output = output + number + ". " + list.get(i).toString() + " \n";
        }
        return output;
    }

    private static void border () {
        System.out.println("-------------------------------------------------------------------------------------");
    }
}