import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm your mom.\n What can I do for you?\n");
        while(true) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            }
            else {
                System.out.println(input);
            }
        }
    }
}
