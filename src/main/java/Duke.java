import java.util.Scanner;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke() {
        storage = new Storage("data/duke.txt");
        ui = new Ui();
        taskList = new TaskList();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(storage, ui, taskList);

        ui.greet();
        storage.loadTasks(taskList);
        Command command = null;
        do {
            ui.showInputLine();
            String input = scanner.nextLine();

            try {
                command = parser.parse(input);
                command.execute();
            } catch (NumberFormatException e) {
                ui.showError("Please Enter a valid task number!");
            } catch (IllegalArgumentException e) {
                ui.showError("I'm sorry but I don't know what that means.");
            } catch (DukeException e) {
                ui.showError(e);
            }
        } while (command == null || !command.isExit());
        scanner.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
