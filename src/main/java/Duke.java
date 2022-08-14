import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String greeting = "Hello from\n"  + logo + "\nHow can I help you ?\n" ;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(greeting);
        String userInput = scanner.nextLine();
        if (userInput.equals("bye")){
            System.out.println("Bye. Hope to see you again soon!");
        } else {
            System.out.println(userInput);
        }
        scanner.close();
    }
}
