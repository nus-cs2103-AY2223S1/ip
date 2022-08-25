package duke;

public class Duke {
    private final Ui ui;
    private final TaskList taskList;
    public static String filePath = "tasks.txt";

    public Duke(String filePath) {
        ui = new Ui();
        Storage storage = new Storage(filePath);
//        try {
            taskList = new TaskList(storage);
//        } catch (CustomMessageException e) {
//            ui.showLoadingError();
//        }
    }

    public void run() {
        ui.showWelcome();
        Parser parser = new Parser(taskList);
        while (true) {
            try {
                String nextCommand = ui.getNextCommand();
                ui.showLine();
                System.out.print(parser.parseUserCommand(nextCommand, parser.breakLoopIndicator));
                if (parser.breakLoopIndicator.getIsExitCommand()) {
                    ui.showExitMessage();
                    break;
                }
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
            } catch (CustomMessageException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(Duke.filePath).run();
    }
}
