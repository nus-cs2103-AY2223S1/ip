import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = "______       _     \n" +
            "| ___ \\     | |    \n" +
            "| |_/ / ___ | |__  \n" +
            "| ___ \\/ _ \\| '_ \\ \n" +
            "| |_/ / (_) | |_) |\n" +
            "\\____/ \\___/|_.__/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        List < String > list = new ArrayList < String > ();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ") " + list.get(i));
                }
            } else {
                System.out.println("Added: " + input);
                list.add(input);
            }
        }
    }
}
