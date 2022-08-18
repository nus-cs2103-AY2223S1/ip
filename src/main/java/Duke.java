import java.util.Scanner;

public class Duke {
    public static void printLine() {
        System.out.println("-".repeat(100));
    }

    public static void greetings() {
        printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        printLine();
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
        System.out.println("Hello from\n" + logo);

       greetings();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            switch(input) {
                case "bye":
                    printBye();
                    return;
                default:
                    repeat(input);
                    break;
            }
        }
    }
}
