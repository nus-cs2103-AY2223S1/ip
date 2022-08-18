import java.util.*;
import java.lang.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String[] tasks = new String[100];
        int taskIndex = 0;
        String indent = "    ";
        System.out.println(indent);
        for (int i = 0; i< 20; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println(indent + "Hello! I'm Duke");
        System.out.println();
        System.out.println(indent + "What can I do for you?");
        System.out.println(indent);
        for (int i = 0; i< 20; i++) {
            System.out.print("-");
        }
        System.out.println();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            for (int i = 0; i< 20; i++) {
                System.out.print("-");
            }
            System.out.println();
            if (input.equals("bye")) {
                System.out.println(indent + "Bye. Hope to see you again soon!");
            } else if (input.equals("list")) {
                for (int j = 0; j < taskIndex; j++) {
                    int taskNum = j + 1;
                    System.out.println(indent + taskNum + ". " + tasks[j]);
                }
            } else {
                System.out.println(indent + "added: " + input);
                tasks[taskIndex] = input;
                taskIndex += 1;
            }
            for (int i = 0; i< 20; i++) {
                System.out.print("-");
            }
            System.out.println();
            if (input.equals("bye")) {
                System.exit(0);
            }
        }
    }
}
