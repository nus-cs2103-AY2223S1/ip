import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        boolean open = true;
        while (open) {
            if (text.equalsIgnoreCase("bye")) {
                scanner.close();
                open = false;
                System.out.println("Goodbye! Thank you for visiting Duke.");
            } else {
                System.out.println(text);
                text = scanner.next();
            }
        }
    }
}
