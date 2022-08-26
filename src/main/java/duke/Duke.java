package duke;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private static ArrayList<Task> listOfTask = new ArrayList<>();
    private final static String DIRECTORY = "./DATA";
    private final static String FILENAME = "duke.txt";
    private final static String PATHNAME = String.valueOf(Paths.get(DIRECTORY, FILENAME));

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.greetUi();
        Storage storage = new Storage(PATHNAME, listOfTask, DIRECTORY);
        storage.getData();
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            try {
                String fullCommand = input.nextLine();
                Parser parser = new Parser(fullCommand);
                if (parser.isSubStringForExitCommand()){
                    ExitCommand exitCommand = new ExitCommand();
                    exitCommand.execute(fullCommand,listOfTask,ui,storage);
                    break;
                }
                Command c = parser.parse();
                c.execute(fullCommand, listOfTask, ui, storage);
            } catch (DukeException e) {
                System.out.println(e.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}




