import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = " _______________________________________ \n";
        System.out.println(line + " I'm Dukie\n" + " What can I do for you?\n" + line);

        boolean noBye = true;
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String userInput = "0";

        while (true) {
            userInput = myObj.nextLine();  // Read user input

            if (userInput.equals("bye")) {
                System.out.println("Goodbaiiiii\n");
                System.exit(0);
            }

            System.out.println(line + " " + userInput + "\n" + line);  // Output user input
        }


    }
}
