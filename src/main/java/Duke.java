import java.util.ArrayList;
import java.util.Scanner;

/**
 * MakiBot
 */
public class Duke {
    protected ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + "MAKIBOT");
        new Duke().start();
    }

    /**
     * Start a conversation with MakiBot
     */
    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Makibot\n" +
                "What can I do for you?");

        // Event loop
        while (true) {
            String input = sc.nextLine();

            // Handle special commands
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (input.equals("list")) {
                // List
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(i + 1 + ". " + taskList.get(i));
                }
            } else if (input.startsWith("mark ")) {
                try {
                    int taskNum = Integer.parseInt(input.substring(5)) - 1;
                    Task t = taskList.get(taskNum);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this tasks as done:");
                    System.out.println(t);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int taskNum = Integer.parseInt(input.substring(7)) - 1;
                    Task t = taskList.get(taskNum);
                    t.markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(t);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            } else {
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println("added: " + input);
            }

        }
    }
}
