import java.util.Scanner;

public class Duke {
    private static final String BYE = "bye";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\t-------------------------------");
        System.out.println("\tHello, I'm Duke!");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t-------------------------------");
        while (true) {
            Scanner s = new Scanner(System.in);
            String command = s.nextLine().trim();
            if (command.equals(BYE)) {
                System.out.println("\t-------------------------------");
                System.out.println("\tBye! Hope to see you again");
                System.out.println("\t-------------------------------");
                break;
            } else {
                System.out.println("\t-------------------------------");
                System.out.println("\t" + command);
                System.out.println("\t-------------------------------");
            }
        }
    }
}
