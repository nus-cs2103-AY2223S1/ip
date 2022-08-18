import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> arrayList = new ArrayList<>();
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                int length = arrayList.size();
                for (int i = 0; i < length; i++) {
                    System.out.println((i + 1) + ". " + arrayList.get(i));
                }
            } else {
                System.out.println("added: " + input);
                arrayList.add(input);
            }

            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
