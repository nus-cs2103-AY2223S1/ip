import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String indent = "     ";
        String divider = "  ____________________________________________________________\n";
        String logo = indent + "____        _        \n"
                    + indent + "|  _ \\ _   _| | _____ \n"
                    + indent + "| | | | | | | |/ / _ \\\n"
                    + indent + "| |_| | |_| |   <  __/\n"
                    + indent + "|____/ \\__,_|_|\\_\\___|\n\n";
        String openingStatement = indent + "Hello! I'm Duke.\n"
                                + indent + "What can I do for you?\n";
        System.out.println(divider + logo + openingStatement + divider);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(divider + indent + input + "\n" + divider);
        }

        String exitStatement = "Bye. Hope to see you again soon! :)";
        System.out.println(divider + indent + exitStatement + "\n" + divider);
        scanner.close();
    }
}
