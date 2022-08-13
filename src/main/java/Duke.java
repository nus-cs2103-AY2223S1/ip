import java.util.Scanner;

/**
 * Duke is a personal assistant Chat-bot that aims to help users to keep track of various things
 */
public class Duke {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Main method that runs the program
     * @param args Arguments passed to the program
     */
    public static void main(String[] args) {
        Output.GREETINGS.print();
        commandParser();
    }

    /**
     * Parses the user input and calls the appropriate method
     */
    private static void commandParser(){
        boolean endLoop = false;
        StorageList storageList = new StorageList();

        while (!endLoop) {
            String input = scanner.nextLine();
            String command = input.split(" ")[0];

            switch (command) {
                case "bye":
                    Output.GOODBYE.print();
                    endLoop = true;
                    break;
                case "list":
                    Output.echo(storageList.toString());
                    break;
                case "mark":
                    //TODO: handle when index is out of range
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    storageList.mark(index);
                    Output.MARK.changeStatus(storageList.get(index));
                    break;
                case "unmark":
                    //TODO: handle when index is out of range
                    index = Integer.parseInt(input.split(" ")[1]) - 1;
                    storageList.unmark(index);
                    Output.UNMARK.changeStatus(storageList.get(index));
                    break;
                default:
                    // add to list
                    storageList.add(new Task(input));
                    Output.echo("added: " + input + "\n");
            }
        }
    }
}
