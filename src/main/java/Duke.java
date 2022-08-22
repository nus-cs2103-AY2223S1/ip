import duke.exceptions.DukeException;
import duke.exceptions.InvalidInput;
import duke.exceptions.UnknownCommand;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    private TaskList data;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public void run() {
        data = new TaskList();
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new Storage();
            storage.loadData(data);
        } catch (DukeException e) {
            ui.printError(e);
            return;
        }
        ui.helloMessage();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            ui.printDivider();
            System.out.println("Duck:");
            String input = sc.nextLine();
            try {
                Command command = parser.parse(input);
                command.execute(data, ui, storage);
                if ("exit".equals(command.getCommand())) {
                    sc.close();
                    return;
                }
            } catch (DukeException e) {
                ui.printError(e);
            }
            ui.printDivider();
        }
    }


    public static void main(String[] args) {
        new Duke().run();
    }
}
