import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "  _______________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(HORIZONTAL_LINE + "\n  Hello! I'm Duke\n  What can I do for you?\n" + HORIZONTAL_LINE);
        Scanner commands = new Scanner(System.in);
        while (true) {
            String command = commands.nextLine();
            if (command.equals("bye")) {
                System.out.println(HORIZONTAL_LINE + "\n Bye. Hope to see you again soon!\n  " + HORIZONTAL_LINE);
                commands.close();
                break;
            } else {
                System.out.println(HORIZONTAL_LINE + "\n  " + command + "\n" + HORIZONTAL_LINE);
            }
        }
    }
}
