import java.util.ArrayList;
import java.util.Scanner;

public class Fred {
    public static void list(ArrayList<String> arrayList) {
        for (String s : arrayList) {
            System.out.println("Fred: " + (arrayList.indexOf(s) + 1) + ". " + s);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<String> storage = new ArrayList<>();

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
                    storage.add(input);
                    System.out.println("Fred: added \"" + input + "\"");
            }
        }

        scanner.close();
    }
}