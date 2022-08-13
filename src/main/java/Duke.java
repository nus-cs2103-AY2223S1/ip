import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I'm Duke!\n" + logo + "\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                message("Bye! Hope to see you again soon!");
                sc.close();
                break;
            }
            message(command);
        }
    }

    private static void message(String text) {
        System.out.println("Duke: " + text);
    }
}
