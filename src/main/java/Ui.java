import java.util.Scanner;

public class Ui {
    String logo = "     _   _    ______     _____ ____\n"
            + "    | | / \\  |  _ \\ \\   / /_ _/ ___|\n"
            + " _  | |/ _ \\ | |_) \\ \\ / / | |\\___ \\\n"
            + "| |_| / ___ \\|  _ < \\ V /  | | ___) |\n"
            + " \\___/_/   \\_\\_| \\_\\ \\_/  |___|____/\n";

    public void greet() {
        System.out.println(logo);
        say("Hello. I'm Jarvis", true, false);
        say("What can I do for you?", false, true);
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (scanner.hasNextLine()) {
            command = scanner.nextLine();
        }
        return command;
    }

    public void exit() {
        say("Bye. Hope to see you again soon.", true, true);
    }

    public void say(String message, boolean isFirstLine, boolean isLastLine) {
        String line = "____________________________________________________________";
        if (isFirstLine) {
            System.out.println(line);
        }
        System.out.println(" " + message);
        if (isLastLine) {
            System.out.println(line);
        }
    }
}
