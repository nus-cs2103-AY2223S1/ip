import java.util.Scanner;

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

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}
