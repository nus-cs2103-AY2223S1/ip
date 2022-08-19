import javax.swing.event.TreeWillExpandListener;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String separator = "     ==================================";
        String indent = "      ";

        String welcomeMsg = "Hello! I'm\n" + logo + "\nWhat can I do for you?\n";
        System.out.println(welcomeMsg);

        Scanner sc = new Scanner(System.in);
        String input = "";

        while (true) {
            input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(separator);
                System.out.println(indent + "Bye. Hope to see you again soon!");
                System.out.println(separator);
                break;
            } else {
                System.out.println(separator);
                System.out.println(indent + input);
                System.out.println(separator);
            }
        }
    }
}
