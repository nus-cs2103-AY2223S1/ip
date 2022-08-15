import java.util.Scanner;

public class Duke {
    private static final String INDENTATION = "    ";
    private static final String LINE = INDENTATION + "__________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        System.out.println("Hello from\n" + LOGO);

        Scanner sc = new Scanner(System.in);
        System.out.println(LINE + "\n" + INDENTATION + "Hello! I'm Duke\n"
                + INDENTATION + "What can I do for you?\n" + LINE);

        String s = sc.nextLine();
        while (!s.equals("bye")) {
            System.out.println(LINE + "\n" + INDENTATION + s + "\n" + LINE);
            s = sc.nextLine();
        }

        System.out.println(LINE + "\n" + INDENTATION + "Bye. Hope to see you again soon!");
        sc.close();
    }
}
