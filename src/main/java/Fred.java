import java.util.ArrayList;
import java.util.Scanner;

public class Fred {
    public static void list(ArrayList<Task> arrayList) {
        for (Task t : arrayList) {
            System.out.println("Fred: " + (arrayList.indexOf(t) + 1) + ". " + t.getName());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<Task> storage = new ArrayList<>();

        System.out.println("Fred: Hello! I'm Fred!");
        System.out.println("Fred: What can I do for you?");

        while (true) {
            System.out.print("Player: ");
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Fred: Bye. Hope to see you again soon!");
                break;
            }

            switch (input) {
                case "list":
                    Fred.list(storage);
                    break;
                default:
                    Task newTask = new Task(input);
                    storage.add(newTask);
                    System.out.println("Fred: added \"" + input + "\"");
            }
        }

        scanner.close();
    }
}
