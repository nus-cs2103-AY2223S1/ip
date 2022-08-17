import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String horizontalLine = new String(new char[50]).replace("\0", "~");
        String greeting = "Hello! I'm Duke\n" +
                "\tWhat can I duke for you?";

        System.out.println("\t" + horizontalLine);
        System.out.println("\t" + greeting);
        System.out.println("\t" + horizontalLine);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.toLowerCase().equals("bye")) {
            System.out.println("\t" + horizontalLine);
            System.out.println("\t" + input);
            System.out.println("\t" + horizontalLine);

            input = sc.nextLine();
        }

        System.out.println("\t" + horizontalLine);
        System.out.println("\t" + "Bye. Hope to not see you again!");
        System.out.println("\t" + "Duke hates you.");
        System.out.println("\t" + horizontalLine);
    }
}
