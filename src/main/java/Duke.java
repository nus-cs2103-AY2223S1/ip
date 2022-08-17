import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        String logo = "_________                     ___\n"
                + "\\    ___ \\  ___________   ____\\_ |_________  ____\n"
                + "/    \\  \\/_/ __ \\_  __ \\_/ __ \\| __ \\_  __ \\/  _ \\\n"
                + "\\     \\___\\  ___/|  | \\/\\  ___/| \\_\\ \\  | \\(  (_) )\n"
                + " \\________/\\_____>__|    \\_____>_____/__|   \\____/\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        while (true) {
            System.out.print("--> ");
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else {
                tasks.add(input);
                System.out.println("Added: " + input);
            }
        }

        System.out.println("Goodbye! See you soon!");
    }
}
