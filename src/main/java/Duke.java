import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        ArrayList<String> allMessages = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        greet();
        String next = sc.nextLine();
        while (!next.equals("bye")) {
            if (!next.equals("list")) {
                allMessages.add(next);
                echo("added:" + next);
            } else {
                echo(allMessages);
            }
            next = sc.nextLine();
        }
        exitMessage();
    }

    public static void greet() {
        echo("Hello! I'm Duke\n" + "\tWhat can I do for you?");
    }

    public static void exitMessage() {
        echo("Bye. Hope to see you again soon!");
    }

    public static void echo(ArrayList<String> ls) {
        String line = "____________________________________________________________";
        System.out.println("\t" + line);
        for (int i = 0; i < ls.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + ls.get(i));
        }
        System.out.println("\t" + line);
    }

    public static void echo(String message) {
        String line = "____________________________________________________________";
        System.out.println("\t" + line);
        System.out.println("\t" + message);
        System.out.println("\t" + line);
    }
}
