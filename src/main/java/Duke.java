import java.util.Scanner;
import static pnkp.duke.IOFormat.say;

public class Duke {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        // intro string
        say("Hello, Duke here! What can I do for you?");

        boolean fExit = false;
        while (!fExit) {
            String line = stdin.nextLine();
            String command = (new Scanner(line)).next();
            switch (command) {
                case "bye":
                    say("OK. See you next time! *boings away*");
                    fExit = true;
                    break;
                default:
                    say(line);
                    break;
            }
        }
    }
}
