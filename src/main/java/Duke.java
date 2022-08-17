import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        String horizontalLine = new String(new char[50]).replace("\0", "~");
        String greeting = "Hello! I'm Duke\n" +
                "\tWhat can I duke for you?";
        ArrayList<String> list = new ArrayList<String>();

        System.out.println("Hello from\n" + logo);

        System.out.println("\t" + horizontalLine);
        System.out.println("\t" + greeting);
        System.out.println("\t" + horizontalLine);

        String input = sc.nextLine();

        while (!input.toLowerCase().equals("bye")) {
            System.out.println("\t" + horizontalLine);

            if (!input.toLowerCase().equals("list")) {
                list.add(input);
                System.out.println("\tadded: " + input);
            } else {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t" + String.valueOf(i + 1) + ". " + list.get(i));
                }
            }

            System.out.println("\t" + horizontalLine);

            input = sc.nextLine();
        }

        System.out.println("\t" + horizontalLine);
        System.out.println("\t" + "Bye. Hope to not see you again!");
        System.out.println("\t" + "Duke hates you.");
        System.out.println("\t" + horizontalLine);
    }
}
