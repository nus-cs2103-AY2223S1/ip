package duke;

import duke.commands.Command;

import java.util.Scanner;

public class Duke {
    private static final String FILE_PATH_DIR =  "data"; //"../../../data";

    public static void main(String[] args) {
        System.out.println("Hello from Duke");
        System.out.println("What can I do for you?");

        Scanner inputScanner = new Scanner(System.in);
        Storage storage = new Storage(FILE_PATH_DIR);
        TaskList tasks;

        tasks = storage.load();

        Parser parser = new Parser(true);


        while (parser.isAcceptingInput()) {
            String input = inputScanner.nextLine();
            try {
                Command command = parser.parseInput(input);
                command.execute(tasks, storage);
            } catch (DukeException e) {
                System.out.println(e);
            } catch (NumberFormatException e) {
                System.out.println("Input String cannot be parsed to Integer.");
            }
        }
    }
}
