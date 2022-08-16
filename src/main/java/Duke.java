import java.util.Locale;
import java.util.Scanner;
public class Duke {
    static final String CHATBOX_NAME = "Ado";
    static final String PARTITION = "<><><><><><><><><><><><><><><><>\n";

    public static void main(String[] args) {
        String input = "";
        Scanner sc = new Scanner(System.in);

        String startMessage = "Yo! I'm " + CHATBOX_NAME + "!\nWhat can I do for you? :)\n";
        printMessage(startMessage);
        while(!input.equals("bye")) {
            input = sc.next();
            input = input.toLowerCase();
            switch (input) {
                case "list":
                    printMessage("list\n");
                    break;
                case "blah":
                    printMessage("blah\n");
                    break;
                case "bye":
                    printMessage("Gone so soon? :( Bye\n");
                    break;
                default:
                    printMessage("Invalid input. Try again!\n");
            }
        }
    }

     static void printMessage(String message){
        String print = PARTITION + message + PARTITION;
        System.out.println(print);
    }


}
