
import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("----------------------\nHello! I'm HelperBot\nWhat can I do for you?\n----------------------\n");
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println("----------------------\n" + userInput + "\n----------------------\n");
            userInput = sc.nextLine();
        }
        System.out.println("Thank you and ATB :)");
    }
}
