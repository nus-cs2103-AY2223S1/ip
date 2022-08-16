import java.util.Scanner; //import Scanner class
public class Duke {
    private static boolean END;
    /*
    Decides on what the chatbox will response with based on user input.
     */
    public void Response(String input) {
        if (input.equals("bye")) {
            END = true;
            System.out.println("Bye. Hope to see you again soon!");
        } else {
            System.out.println(input);
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        END = false;
        Scanner myScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (!END) {
            duke.Response(myScanner.nextLine());
        }
        myScanner.close();
    }
}
