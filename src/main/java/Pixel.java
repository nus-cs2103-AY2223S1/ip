import java.util.Scanner;

public class Pixel {

    Scanner myScanner = new Scanner(System.in);  // Create a Scanner object

    void run() {
        String userInput = myScanner.nextLine();  // Read user input
        System.out.println(userInput);  // Output user input

        if (userInput.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else {
            run();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Pixel \r\nWhat can I do for you?");
        Pixel test = new Pixel();
        test.run();
    }
}
