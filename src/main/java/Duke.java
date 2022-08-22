public class Duke {
    private Ui ui;
    private TaskList tasks;

    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException de) {
                ui.showError(de);
            } finally {
                ui.showLine();
            }
        }

    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
