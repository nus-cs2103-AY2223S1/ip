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
                String description = task.description;
                border();
                if (task.isDone) {
                    System.out.println("Got it, I've unmarked this task for you!");
                    System.out.println("    [ ] " + description);
                    task.markAsUndone();
                } else {
                    System.out.printf("You have not marked: \n  " + description + "\n");
                }
                border();
            } else {
                border();
                System.out.println("Sorry :( task number " + taskNum + 1 + " cannot be found. " +
                        "You might want to check your list again!\n");
                border();
            }
            messaging();
        }
        else if (!message.contains("unmark") && message.contains("mark")) {
            int taskNum = Integer.parseInt(message.substring(5)) - 1; // -1 so that index is constant
            if (taskNum >= 0 && taskNum < list.size()) {
                Task task = list.get(taskNum);
                String description = task.description;
                border();
                if (!task.isDone) {
                    System.out.println("Got it, I've marked this task for you!");
                    System.out.println("    [X] " + description);
                    task.markAsDone();
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
        }
        else {
            border();
            list.add(new Task(message));
            System.out.println("'" + message + "' added to your list!");
            border();
            messaging();
        }
    }

    private static String printList() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            int number = i + 1;
            output = output + number + ". [" + list.get(i).getStatusIcon() + "] " + list.get(i).description + "\n";
        }
        return output;
    }

    private static void border () {
        System.out.println("-------------------------------------------------------------------------------------");
    }
}