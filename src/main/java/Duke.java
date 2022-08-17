import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm nothing but an echo bot for now!\nWhat can I do for you, my highness?");
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Your highness:");
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("Echoing: Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println("Echoing: " + input);
            }
        }
    }
}
