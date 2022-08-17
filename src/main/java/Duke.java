import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm\n" + logo + "\nWhat can I do for you?");

        Scanner in = new Scanner(System.in);
        String userInput = in.next();

        while (!userInput.equals("bye")) {
            System.out.println(userInput + "\n");
            userInput = in.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
        in.close();
    }
}
