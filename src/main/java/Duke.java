import java.util.Scanner;
import static pnkp.duke.IOFormat.say;
import pnkp.duke.modules.Todos;

public class Duke {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        Todos todos = new Todos();

        // intro string
        say("Hello, Duke here! What can I do for you?");

        boolean fExit = false;
        while (!fExit) {
            String line = stdin.nextLine();
            Scanner scanner = new Scanner(line);
            String command = scanner.hasNext() ? scanner.next() : "";

            switch (command) {
                case "":
                    // say("Sorry, I didn't catch that?")
                    break;
                case "list":
                    todos.cmdList();
                    break;
                case "bye":
                    say("OK. See you next time! *boings away*");
                    fExit = true;
                    break;
                default:
                    todos.cmdAdd(new Scanner(line));
                    break;
            }
        }
    }
}
