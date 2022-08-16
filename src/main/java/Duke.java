import java.util.Scanner;

public class Duke {
    static void line() {
        System.out.println("________________________________________");
    }
    static void greet() {
        line();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        line();
    }

    public static void echo(String message) {
        line();
        System.out.println(message);
        line();
    }

    public static void exit() {
        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        boolean isDone = false;
        greet();
        while (!isDone) {
            Scanner scanner = new Scanner(System.in); // creating scanner for user input
            String input = scanner.nextLine();
            switch (input) {
                case ("bye"): {
                    exit();
                    isDone = true;
                    break;
                }
                default: {
                    echo(input);
                    break;
                }
            }
        }
    }
}
