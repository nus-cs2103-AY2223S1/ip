import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("\tHello I'm Duke\n \tWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String ss = scanner.nextLine();

            if (ss.equals("bye")) {
                System.out.println("\t\tBye. Hope to see you again soon!");
                break;
            } else {
                System.out.printf("\t\t%s\n",ss);
            }
        }
    }
}
