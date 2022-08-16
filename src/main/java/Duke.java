import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static boolean END;
    //storing all user inputs
    private static ArrayList<String> storage;
    /*
    Decides on what the chatbox will response with based on user input.
     */
    public void Response(String input) {
        if (input.equals("bye")) {
            END = true;
            System.out.println("Bye. Hope to see you again soon!");
        } else if (input.equals("list")) {
            String output = "";
            for (int i = 0; i < storage.size(); i ++) {
                output = output + String.valueOf(i + 1) + ". " + storage.get(i) + "\n";
            }
            System.out.println(output);
        } else {
            storage.add(input);
            System.out.println("added: " + input);
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        END = false;
        storage = new ArrayList<String>();
        Scanner myScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (!END) {
            duke.Response(myScanner.nextLine());
        }
        myScanner.close();
    }
}
