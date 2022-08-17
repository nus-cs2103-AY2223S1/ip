import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from\n" + logo + "\n What can I do for you?");
        while (true) {
            String input = sc.nextLine();
            if (!input.equals("bye")) {
                System.out.println("----\n  " + input + "\n----");
            } else {
                System.out.println("----\n  Goodbye!\n----");
                break;
            }
        }
    }
}
