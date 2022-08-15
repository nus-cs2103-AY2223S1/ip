import java.util.Scanner;

public class Duke {

    private void outputMessage(String message) {
        System.out.println("Duke: " + message);
    }

    private boolean parseMessage(String message) {
        if (message.equals("bye")) {
            outputMessage("NOOOOOO... Don't send me back to the void! T_T");
            return false;
        } else {
            outputMessage(message);
        }

        return true;
    }

    private void greet() {
        outputMessage("Hi, I'm Duke.");
        outputMessage("What can I do for you?");
        outputMessage("I'll do my best :)");
    }

    private void askForCommand() {
        System.out.print("You: ");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        duke.greet();
        Scanner scanner = new Scanner(System.in);
        do {
            duke.askForCommand();
        } while(duke.parseMessage(scanner.nextLine()));
    }
}
