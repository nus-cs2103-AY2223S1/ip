import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();

        while (true) {
            String input = getInput(sc);
            if (processInput(input) == 0) {
                break;
            }
        }
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static String getInput(Scanner sc) {
        System.out.println("What can I do for you?");
        return sc.nextLine();
    }

    public static int processInput(String s) {
        if (s.equals("bye")) {
            System.out.println("Bye. Hope to see you again!");
            return 0;
        } else {
            System.out.println(s);
            return 1;
        }
    }
}
