import java.util.Scanner;

public class Duke {
    private final static String LINE = "____________________________________________________________";
    private final static String INDENTATION = "   ";

    public static void greeting() {
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "Hello! I'm Duke");
        System.out.println(INDENTATION + "What can I do for you?");
        System.out.println(INDENTATION + LINE);
    }

    public static void exit() {
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(INDENTATION + LINE);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.greeting();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.next();
            if (command.equals("bye")) {
                sc.close();
                Duke.exit();
                break;
            } else {
                System.out.println(INDENTATION + LINE);
                System.out.println(INDENTATION + command);
                System.out.println(INDENTATION + LINE);
            }
        }
    }
}
