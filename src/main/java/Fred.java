import java.util.ArrayList;
import java.util.Scanner;

public class Fred {
    public static void list(ArrayList<Task> arrayList) {
        int counter = 1;
        for (Task t : arrayList) {
            System.out.println("Fred: " + counter++ + "." + t.toString());
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
            } else if (input.equals("list")) {
                System.out.println("Fred: Here are the tasks in your list:");
                Fred.list(storage);
            } else if (input.startsWith("mark")) {
                System.out.println("Fred: Nice! I've marked this task as done:");
                int index = Character.getNumericValue(input.charAt(input.length() - 1));
                storage.get(index - 1).setStatus(true);
                System.out.println("Fred: " + storage.get(index - 1).toString());
            } else if (input.startsWith("unmark")) {
                System.out.println("Fred: OK, I've marked this task as not done yet:");
                int index = Character.getNumericValue(input.charAt(input.length() - 1));
                storage.get(index - 1).setStatus(false);
                System.out.println("Fred: " + storage.get(index - 1).toString());
            } else {
                Task newTask = new Task(input);
                storage.add(newTask);
                System.out.println("Fred: added \"" + input + "\"");
            }
        }

        scanner.close();
    }
}
