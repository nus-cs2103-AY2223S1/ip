import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void displayList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean hasNextInput = true;

        String logo = "_________                     ___\n"
                + "\\    ___ \\  ___________   ____\\_ |_________  ____\n"
                + "/    \\  \\/_/ __ \\_  __ \\_/ __ \\| __ \\_  __ \\/  _ \\\n"
                + "\\     \\___\\  ___/|  | \\/\\  ___/| \\_\\ \\  | \\(  (_) )\n"
                + " \\________/\\_____>__|    \\_____>_____/__|   \\____/\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        while (hasNextInput) {
            System.out.print("--> ");
            String input = scanner.nextLine();

            switch (input.split(" ")[0]) {
                case "bye":
                    hasNextInput = false;
                    break;
                case "list":
                    displayList();
                    break;
                case "mark":
                    tasks.get(Integer.parseInt(input.split(" ")[1]) - 1).markAsDone();
                    break;
                case "unmark":
                    tasks.get(Integer.parseInt(input.split(" ")[1]) - 1).markAsNotDone();
                    break;
                default:
                    tasks.add(new Task(input));
                    System.out.println("Added: " + input);
            }
        }

        System.out.println("Goodbye! See you soon!");
    }
}
