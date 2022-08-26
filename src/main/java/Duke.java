import java.util.Scanner;

public class Duke {

    private UI ui;
    private TaskList taskList;
    private Storage storage;

    public Duke() {
        ui = new UI();
        try {
            storage = new Storage(ui);
        } catch (DukeException e) {
            ui.exceptionMessage(e.getMessage());
        }
        taskList = new TaskList(ui, storage);
    }

    public void start() {
        ui.greeting();
        try {
            storage.loadFile(taskList);
        } catch (DukeException e) {
            ui.exceptionMessage(e.getMessage());
        }
        command();
    }

    private void command() {
        ui.promptUser();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            try {
               Parser.parseInput(input, taskList);
            } catch (DukeException e) {
                ui.exceptionMessage(e.getMessage());
            }
            input = scanner.nextLine();
        }
        ui.bye();
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
