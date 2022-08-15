import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();

        while (true) {
            if (text.equals("bye")) {
                break;
            }
            System.out.println("\t" + text);
            scanner = new Scanner(System.in);
            text = scanner.next();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
