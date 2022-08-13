import java.util.Scanner;

/**
 * Duke is a personal assistant Chat-bot that aims to help users to keep track of various things
 */
public class Duke {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Main method that runs the program, handles the logic of the input
     * @param args Arguments passed to the program
     */
    public static void main(String[] args) {
        boolean endLoop = false;
        StorageList storageList = new StorageList();

        Output.GREETINGS.print();
        while (!endLoop) {
            String command = scanner.nextLine();
            switch (command) {
                case "bye":
                    Output.GOODBYE.print();
                    endLoop = true;
                    break;
                case "list":
                    Output.echo(storageList.toString());
                    break;
                default:
                    // add to list
                    storageList.add(command);
                    Output.echo("added: " + command + "\n");
            }
        }
    }
}
