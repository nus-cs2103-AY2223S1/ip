import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String input = "";
        Scanner scan = new Scanner(System.in);
        String[] list = new String[100];
        int count = 0;

        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");

        while (!input.equals("bye")) {
            input = scan.nextLine(); // Reads user input
            if (input.equals("list")) { // Prints out items in list
                for (int i = 0; i < count; i++) {
                    System.out.println(i+1 + ". " + list[i]);
                }
            }
            else if (!input.equals("bye")) { // Only adds input to list if it is not "bye"
                list[count] = input;
                count += 1;
                System.out.println("\tadded: " + input); // Prints user input
            }
        }

        System.out.println("Bye. Hope to see you again soon!"); // Exits when user types "bye"
    }
}
