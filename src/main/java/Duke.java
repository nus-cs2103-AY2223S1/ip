import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Yale\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);

        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("    Bye. Hope to see you again soon!\n");
                break;
            }
            String message = "\t" + "_".repeat(20) + "\n"
                    + String.format("\t  %s\n", input)
                    + "\t" + "_".repeat(20) + "\n";
            System.out.println(message);
        }
    }
}
