import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "     _   _    ______     _____ ____\n"
                + "    | | / \\  |  _ \\ \\   / /_ _/ ___|\n"
                + " _  | |/ _ \\ | |_) \\ \\ / / | |\\___ \\\n"
                + "| |_| / ___ \\|  _ < \\ V /  | | ___) |\n"
                + " \\___/_/   \\_\\_| \\_\\ \\_/  |___|____/\n";
        System.out.println(logo);

        // greeting messages
        say("Hello. I'm Jarvis", true, false);
        say("What can I do for you?", false, true);

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> task = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                // when user enters bye
                // exit programme
                say("Bye. Hope to see you again soon.", true, true);
                break;
            }
            if (userInput.equals("list")) {
                // when user enters list
                // display the current list
            }
            else {
                // add user input to the list
                task.add(userInput);
                say("added: " + userInput, true, true);
            }
        }
    }

    public static void say(String message, boolean firstLine, boolean lastLine) {
        String line = "____________________________________________________________";
        if (firstLine == true) {
            System.out.println(line);
        }
        System.out.println(" " + message);
        if (lastLine == true) {
            System.out.println(line);
        }
    }
}
