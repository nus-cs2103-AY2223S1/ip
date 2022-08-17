import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "Botto";
        System.out.println("Hello from " + logo + "\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;

        while(shouldContinue) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                shouldContinue = false;
                continue;
            }
            System.out.println(input);
        }

    }
}
