import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String indent = "       ";
    private static String divider = " ___________________________________________________________________";

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("\n Hello there! \n"
                + "\n My name is Zelk, nice to meet you :D \n"
                + " What can I do for you?\n"
                + divider);

        Scanner s = new Scanner(System.in);
        String input = "";

        while ((input = s.nextLine()) != null) {
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(indent + "These are the tasks in your list so far!");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(indent + (i+1) + ". " + tasks.get(i));
                }
                System.out.println(divider);
            } else if (input.contains("unmark")) {
                Integer taskNo = Integer.valueOf(input.substring(7));
                tasks.get(taskNo - 1).markAsUndone();
                System.out.println(indent + "Okay, I'll mark this task as undone: \n"
                        + indent + " " + tasks.get(taskNo - 1));
                System.out.println(divider);
            } else if (input.contains("mark")) {
                Integer taskNo = Integer.valueOf(input.substring(5));
                tasks.get(taskNo - 1).markAsDone();
                System.out.println(indent + "Alright! I've marked this task as done :) \n"
                        + indent + " " + tasks.get(taskNo - 1));
                System.out.println(divider);
            } else {
                if (input.contains("todo")) {
                    tasks.add(new Todo(input.substring(5)));
                } else if (input.contains("deadline")) {
                    int deadlineChar = input.indexOf("/");
                    tasks.add(new Deadline(input.substring(9, deadlineChar), input.substring(deadlineChar + 4)));
                } else if (input.contains("event")) {
                    int eventChar = input.indexOf("/");
                    tasks.add(new Event(input.substring(6, eventChar), input.substring(eventChar + 4)));
                } else {
                    System.out.println(indent + input);
                    continue;
                }

                System.out.println(indent + "new task added: " + tasks.get(tasks.size() - 1)
                        + "\n" + indent + "You now have " + tasks.size() + " total tasks in your list"
                        + "\n" + divider);
            }
        }

        System.out.println(indent + "Bye! Hope to see you again soon! Thank you for chatting with me :)");
    }
}
