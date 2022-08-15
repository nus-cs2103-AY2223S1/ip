import java.util.Scanner;

public class Duke {
    private static final String INDENTATION = "    ";
    private static final String LINE = INDENTATION + "__________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static Task[] tasks = new Task[100];

    private static void printLine() {
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        System.out.println("Hello from\n" + LOGO);

        Scanner sc = new Scanner(System.in);
        printLine();
        System.out.println(INDENTATION + "Hello! I'm Duke\n"
                + INDENTATION + "What can I do for you?");
        printLine();

        String s = sc.nextLine();
        while (!s.equals("bye")) {
            if (s.equals("list")) {
                printLine();
                for (int i = 0; i < Task.getTotal(); i++) {
                    System.out.println(INDENTATION + tasks[i]);
                }
                printLine();
            } else {
                tasks[Task.getTotal()] = new Task(s);
                printLine();
                System.out.println(INDENTATION + "added: " + s);
                printLine();
            }

            s = sc.nextLine();
        }

        printLine();
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        sc.close();
    }
}
