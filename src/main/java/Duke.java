import java.util.Scanner;

public class Duke {

    private static String getOutput(String input) {
        if (input.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else {
            return input;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo + "\nHello! I'm Duke\n" + "What can I do for you?");
        String command = scanner.nextLine();
        while (true) {
            String output = getOutput(command);
            System.out.println(output);
            if (command.equals("bye")) {
                break;
            } else {
                command = scanner.nextLine();
            }
        }
    }
}
