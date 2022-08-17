package main.java;
import java.util.Scanner;

/**
 * Duke, a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author Totsuka Tomofumi
 * @version Level-3
 */
public class Duke {
    /**
     * Duke initialises with his personal logo and a welcome message before prompting for standard input
     * from the command line. Each input is remembered as a task by Duke and a list can be retrieved
     * if the user types "list". Tasks can be marked or unmarked done. If the user types "bye",
     * Duke terminates with a goodbye message.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        //assume no more than 100 tasks
        Task[] tasks = new Task[100];
        int index = 0;
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");

        while (true) {
            String response = scanner.nextLine();
            //bye command
            if (response.equals("bye")) {
                break;
            //list command
            } else if (response.equals("list")) {
                int order = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task task : tasks) {
                    if (task == null) {
                        break;
                    }
                    System.out.println(order++ + "." + task.toString());
                }
            //mark command
            } else if (response.startsWith("mark ")) {
                try {
                    int query = Integer.parseInt(response.substring(4).strip()) - 1;
                    tasks[query].mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[query].toString());
                } catch (NumberFormatException e) {
                    tasks[index++] = new Task(response);
                    System.out.println("added: " + response);
                }
            //unmark command
            } else if (response.startsWith("unmark ")) {
                try {
                    int query = Integer.parseInt(response.substring(6).strip()) - 1;
                    tasks[query].unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks[query].toString());
                } catch (NumberFormatException e) {
                    tasks[index++] = new Task(response);
                    System.out.println("added: " + response);
                }
            } else {
                tasks[index++] = new Task(response);
                System.out.println("added: " + response);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
