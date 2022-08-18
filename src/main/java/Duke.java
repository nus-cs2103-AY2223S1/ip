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
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            for (int i = 0; i< 20; i++) {
                System.out.print("-");
            }
            System.out.println();
            if (input.equals("bye")) {
                System.out.println(indent + "Bye. Hope to see you again soon!");
            } else {
                System.out.println(indent + input);
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
