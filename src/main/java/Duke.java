import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<String> listOfStrings = new ArrayList<>();

    private void outputMessage(String message) {
        System.out.println("Duke: " + message);
    }

    private boolean parseMessage(String message) {
        switch (message) {
            case "bye":
                outputMessage("NOOOOOO... Don't send me back to the void! T_T");
                return false;
            case "list":
                outputMessage("Here's your list!");
                printList();
                outputMessage("I'm useful right?");
                break;
            default:
                outputMessage("I've added [" + message + "] to the list!");
                listOfStrings.add(message);
        }
        return true;
    }

    private void greet() {
        outputMessage("Hi, I'm Duke.");
        outputMessage("What can I do for you?");
        outputMessage("I'll do my best! :)");
    }

    private void printList() {
        int index = 1;
        for (String message : listOfStrings) {
            outputMessage(Integer.toString(index) + ". " + message);
            index++;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        Duke duke = new Duke();
        duke.greet();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("You: ");
        } while(duke.parseMessage(scanner.nextLine()));
    }
}
