import java.util.Scanner;

public class Pixel {

    private static int count = 0;
    private Scanner myScanner = new Scanner(System.in);  // Create a Scanner object
    private String[] inputMemory = new String[100];

    private void run() {
        String userInput = myScanner.nextLine();  // Read user input

        if (userInput.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (userInput.equals("list")) {
            // System.out.println(inputMemory.length);
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ". " + inputMemory[i]);
            }
            run();
        } else {
            inputMemory[count] = userInput; // Stores user input
            System.out.println(userInput);  // Output user input
            count += 1;
            run();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        System.out.println("Hello from\n");
        System.out.println("Hello! I'm Pixel \r\nWhat can I do for you?");
        Pixel test = new Pixel();
        test.run();
    }
}
