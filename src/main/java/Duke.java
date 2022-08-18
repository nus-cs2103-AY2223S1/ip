import java.util.Scanner;

public class Duke {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        boolean terminate = false;
        while (!terminate) {
            String m = sc.nextLine();
            printMessage(m);
            if (m.equals("bye")) {
                terminate = true;
            }
        }
    }

    private static void printMessage(String m) {
        if (m.equals("bye")) {
            m = "Bye. Hope to see you again soon!";
        }
        String message = "    ____________________________________________________________\n    "
                + m
                + "\n    ____________________________________________________________\n";
        System.out.println(message);
    }
}
