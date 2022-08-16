import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static private final String exitCommand = "bye";
    static private final String listCommand = "list";
    static private final String exitMessage = "Goodbye and have a nice day!";

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

        List<String> storage = new ArrayList<>();
        Boolean flag = true;

        while (flag) {
            System.out.println("Enter Command: ");
            userInput = scannerObj.nextLine(); // Read user input

            switch (userInput) {
                case exitCommand:
                    flag = false;
                    break;

                case listCommand:
                    for (int idx = 0; idx < storage.size(); idx++) {
                        System.out.println(idx + 1 + ": " + storage.get(idx));
                    }
                    break;

                default:
                    storage.add(userInput);
                    System.out.println("\n___________________________ \n");
                    System.out.println("added: " + userInput);
                    System.out.println("___________________________ \n");
                    break;
            }
        }

        System.out.println(exitMessage);
        scannerObj.close();
    }
}
