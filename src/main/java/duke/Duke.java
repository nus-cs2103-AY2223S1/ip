package duke;

import duke.command.Command;

public class Duke {
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this.ui = new Ui();
        tasks = new TaskList();
    }

    private void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (IllegalArgumentException e) {
                ui.showInvalidCommandError();
            } catch (IndexOutOfBoundsException e) {
                ui.showOutOfBoundsError();
            } catch (DukeException e) {
                ui.printMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}