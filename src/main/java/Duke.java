import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String logo  = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static ArrayList<String> list;

    public static void main(String[] args) {
        list = new ArrayList<>();

        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        boolean open = true;
        while (open) {
            // Bye
            if (text.equalsIgnoreCase("bye")) {
                scanner.close();
                open = false;
                System.out.println("Goodbye! Thank you for visiting Duke.");
            // List
            } else if (text.equalsIgnoreCase("list")) {
                if (list.isEmpty()) {
                    System.out.println("There is nothing in the list yet!");

                } else {
                    for (int i = 1; i <= list.size(); i++) {
                        System.out.println(i + ". " + list.get(i - 1));
                    }
                }
                text = scanner.next();
            } else {
                list.add(text);
                System.out.println("Added:" + text);
                text = scanner.next();
            }
        }
    }
}
