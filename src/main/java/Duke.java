import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<String> userInputHistory = new ArrayList();
    private static void greetUser() {
        String logo = "_______     _\n" +
                "|  ___|    | |\n" +
                "|  |_  ____| |_____ ____  _  __\n" +
                "|   _|/ _  \\ | ___|/  _ \\| |/  \\\n"+
                "|  | | |_| | | |___| |_| |  / \\ |\n" +
                "|__|  \\__|_|_|____|\\____/|_|  |_|\n";
        System.out.println("Hello from\n" + logo);
        // prompt user
        System.out.println("Where would you like to go next?");
    }

    private static void handleInput(String userInput) {
        if (userInput.equals("bye")) {
            //exit
            System.out.println("Thank you for swinging by :)");
            System.exit(0);
        } else {
            //echo request
            System.out.println(userInput);
        }
    }

    private static void storeInput(String s) {
    }

    public static void main(String[] args) {
        greetUser();
        Scanner in = new Scanner(System.in);
        String s;
        while(true) {
            s = in.nextLine();
            handleInput(s);
        }
    }
}
