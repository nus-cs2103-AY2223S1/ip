import java.util.Scanner;

public class Duke {
    public static boolean terminate = false;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.greet();

        Scanner in = new Scanner(System.in);
        while (!terminate) {
            String userInput = in.nextLine();
            if (userInput.equals("bye")) {
                Duke.exit();
            } else {
                Duke.echo(userInput);
            }
        }
    }

    public static void greet() {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");
    }

    public static void echo(String echoedMessage) {
        System.out.println("    ____________________________________________________________\n" +
                "     " + echoedMessage + "\n" +
                "    ____________________________________________________________");
    }

    public static void exit() {
        Duke.terminate = true;
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
