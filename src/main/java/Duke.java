import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    private static final String DIVIDER = "\t____________________________________________________________";
    private static LinkedList<String> tasks = new LinkedList<>();

    /**
     * Formats Duke's messages by adding horizontal line dividers and indentation.
     * @param str Duke's message to be printed out
     */
    private static void prettyPrint(String str) {
        String[] messages = str.split("\n");
        System.out.println(DIVIDER);
        for (String message : messages) {
            System.out.println("\t  " + message);
        }
        System.out.println(DIVIDER);
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = String.join("\n", logo,
                "Hello! I'm Duke", "What can I do for you?");
        prettyPrint(welcomeMessage);
    }

    private static void goodbye() {
        String farewellMessage = "Bye. Hope to see you again soon!";
        prettyPrint(farewellMessage);
    }

    /**
     * Lists all the tasks entered thus far by the user.
     * Will print 'No tasks' if no tasks are found.
     */
    private static void listTasks() {
        String taskList = "";
        int count = 0;
        for (String task : tasks) {
            count++;
            taskList += String.format("\n%d. %s", count, task);
        }
        prettyPrint(count != 0 ? taskList.substring(1) : "No tasks");
    }

    /**
     * Stores the specified task into the linked list.
     * @param task The task to be recorded
     */
    private static void addTask(String task) {
        tasks.add(task);
        prettyPrint("added: " + task);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cmd;
        greet();
        // Echoes user's input until the user types 'bye', for which the program exits
        while(!(cmd = sc.nextLine()).equals("bye")) {
            switch (cmd) {
                case "list":
                    listTasks();
                    continue;
                default:
                    addTask(cmd);
            }
        }
        goodbye();
    }
}
