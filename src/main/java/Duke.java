import java.util.Scanner;

public class Duke {
    public static void printLine() {
        System.out.println("-".repeat(100));
    }

    public static void repeat(String s) {
        printLine();
        System.out.println(s);
        printLine();
    }

    public static void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        printLine();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String result = sc.nextLine();
            if (result.equals("bye")) {
                printBye();
                break;
            }
            repeat(result);
        }
    }
}
