import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        final String LOGO = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        final String LINE = "\t____________________________________________________________";

        Scanner sc = new Scanner(System.in);
        String command = "";

        System.out.println(LOGO
                + LINE
                + "\n"
                + "\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + LINE);

        while (true) {
            command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!\n"
                        + LINE);
                break;
            }
            System.out.println(LINE
                    + "\n\t"
                    + command
                    + "\n"
                    + LINE);
        }
    }
}
