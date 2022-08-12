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

        List<Task> list = new ArrayList<Task>();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.split(" ")[0].equals("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                list.set(index, list.get(index).setStatus(true));
                System.out.println("Task marked as done!");
                System.out.println(list.get(index).toString());
            } else if (input.split(" ")[0].equals("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                list.set(index, list.get(index).setStatus(false));
                System.out.println("Task marked as not done!");
                System.out.println(list.get(index).toString());
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ") " + list.get(i).toString());
                }
            } else {
                System.out.println("Added: " + input);
                list.add(new Task(input, false));
            }
        }
    }
}
