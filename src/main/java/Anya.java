import java.util.Scanner;

public class Anya {
    public static void main(String[] args) {
        // Initialising variables
        Scanner sc = new Scanner(System.in);
        String command;
        String breakLine = "\n---------------------------------------------------------------------";

        // Greet
        System.out.println("Hello! Anya is happy to meet you.\nHow can Anya help?" + breakLine);

        // Get command
        command = sc.nextLine();

        while (!command.equals("bye")) {
            System.out.println("Anya says " + command + breakLine);
            command = sc.nextLine();
        }

        // Exit
        System.out.println("Anya is sad to see you leave. Please be back soon." + breakLine);
    }
}
