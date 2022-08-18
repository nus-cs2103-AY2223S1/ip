import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        entryStatement();
        ArrayList<String> inputs = new ArrayList<String>();
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("What can I do for you?");
        while (true) {
            String userIn = myObj.nextLine();  // Read user input
            if (userIn.equals("bye")) {
                byeStatement();
                break;
            }
            else if (userIn.equals("list")) {
                for (int i = 1; i < inputs.size()+1; i++) {
                    System.out.println(i+": " + inputs.get(i - 1));
                }
            }
            else {
                inputs.add(userIn);
                System.out.println("added: " + userIn);  // Output user input
            }
        }

    }
    public static void entryStatement() {
        System.out.println(" /\\_/\\");
        System.out.println("/ o o \\");
        System.out.println("/ ^  ^\\");
        System.out.println("Hello from Chan-bot!");
    }

    public static void byeStatement() {
        System.out.println("Bye bye!");
        System.out.println(" /\\_/\\");
        System.out.println("/ o o \\");
        System.out.println("/    ^\\");
    }
}
