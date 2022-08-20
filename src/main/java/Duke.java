import java.time.format.DateTimeParseException;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadTaskFile());
        ui = new Ui();
    }

    public void run() {
        ui.welcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = ui.isExit();
            } catch (InvalidInputException | InvalidIndexException | InvalidDescriptionException | InvalidTimeException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                Ui.separationLine();
                System.out.println("Enter a valid date and time! (Eg. 2020-12-25 1330)");
                Ui.separationLine();
            }
        }
        ui.bye();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke("data/TaskFile.txt").run();
    }


}
