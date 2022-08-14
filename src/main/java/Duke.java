import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] itemArray = new String[100];
        int currIndex = 0;

        // greet the user
        System.out.println("Hello! I'm Pip :)\nWhat can I do for you?");

        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine().trim();

            if (input.equals("bye")) {
                // exit
                in.close();
                System.out.println("Goodbye and see you again soon!");
                break;
            } else if (input.equals("list")) {
                if (currIndex == 0) {
                    // no items stored
                    System.out.println("Your list is currently empty!");
                } else {
                    // display items stored
                    for (int i = 0; i < currIndex; i++) {
                        int itemNumber = i + 1;
                        System.out.println(itemNumber + ". " + itemArray[i]);
                    }
                }
            } else {
                // store commands entered by the user
                itemArray[currIndex++] = input;
                System.out.println("Added: " + input);
            }
        }
    }
}
