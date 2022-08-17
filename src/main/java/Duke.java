import java.util.Scanner;

public class Duke {
    private static final String DIVIDER = "\t___________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(DIVIDER);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.next();

            if (input != "bye") {
                System.out.println(DIVIDER);
                System.out.printf("\t%s\n", input);
                System.out.println(DIVIDER);
            } else {
                System.out.println(DIVIDER);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(DIVIDER);
                break;
            }
        }
        sc.close();

    }
}
