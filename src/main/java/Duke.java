import java.util.Scanner;

public class Duke {

    private static String divider() {
        return "--------------------";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println(divider());

        while (true) {
            String line = scanner.nextLine();
            System.out.println(divider());
            if (line.equals("bye")) {
                System.out.println("See you again!");
                break;
            }
            System.out.println(line);
        }

    }
}
