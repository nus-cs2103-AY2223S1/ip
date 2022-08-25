import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final ArrayList<String> LIST_OF_THINGS = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hi, I'm Duke. What can I do for you?");
        Scanner keyboard = new Scanner(System.in);
        String message = keyboard.nextLine();
        while (true) {
            if (message.equals("bye")) {
                System.out.println("Bye! See you next time!");
                break;
            }
            else if (message.equals("list")) {
                StringBuilder output_message = new StringBuilder();
                for (int i = 0; i < LIST_OF_THINGS.size(); i++) {
                    output_message.append(String.format("%d. %s", i + 1, LIST_OF_THINGS.get(i)));
                    output_message.append("\n");
                }
                System.out.println(output_message);
            }
            else {
                LIST_OF_THINGS.add(message);
                System.out.printf("Added: %s%n", message);
            }
            message = keyboard.nextLine();
        }
    }
}
