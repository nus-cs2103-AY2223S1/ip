import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! I'm Duke \n" + "What can I do for you?");
        String command = scan.nextLine();

        while (!command.equals("bye")) {
            System.out.println(
                    "____________________________________________________________ \n"
                    + command + "\n"
                    + "____________________________________________________________");
            command = scan.nextLine();
        }

        System.out.println(
                "____________________________________________________________ \n"
                + "Bye. Hope to see you again soon! \n"
                + "____________________________________________________________");
    }
}
