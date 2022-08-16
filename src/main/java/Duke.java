import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    static final String exitCommand = "bye";
    static final String exitMessage = "Goodbye and have a nice day!";

    public static void main(String[] args) {
        File file = new File("src/main/assets/logo.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
                // Creating a file object.
            }
            System.out.println("Welcome to Aladdin Services");
        } catch (Exception e) {
            System.out.println("Welcome to Aladdin Services");
        }

        Scanner scannerObj = new Scanner(System.in); // Create a Scanner object
        String userInput = "";

        while (!Objects.equals(userInput, exitCommand)) {
            System.out.println("Enter Command: ");
            userInput = scannerObj.nextLine(); // Read user input
            System.out.println("\n___________________________ \n");
            System.out.println(userInput);
            System.out.println("___________________________ \n");
        }

        System.out.println(exitMessage);
        scannerObj.close();
    }
}
