import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        greet();
        String next = sc.nextLine();
        while (!next.equals("bye")) {
            echo(next);
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

    public static void echo(String message) {
        String line = "____________________________________________________________";
        System.out.println("\t" + line);
        System.out.println("\t" + message);
        System.out.println("\t" + line);
    }
}
