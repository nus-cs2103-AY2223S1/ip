import java.util.Scanner;

/**
 * A chatbot named Tako that echos commands
 * entered by the user.
 *
 * @author Alvin Tan Fu Long
 */
public class Tako {
    /**
     * Runs a program that echoes commands entered by the user.
     * The program closes on the command 'bye'.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello! I'm Tako\nWhat do you want?");
        Scanner sc = new Scanner(System.in);
        String EXIT_PROGRAM = "bye";
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals(EXIT_PROGRAM)) {
                System.out.println("Bye, until next time...");
                break;
            } else {
                System.out.println(input);
            }
        }
        sc.close();
    }
}