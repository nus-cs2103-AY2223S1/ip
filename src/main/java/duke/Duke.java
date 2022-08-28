package duke;

public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Initializes the Duke chatbot.
     * @param filePath Specifies the path to save the content.
     */

    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath);
    }

    /**
     * start the program
     */
    public void run() {
        ui.greetings();
        while (true) {
            String input = ui.readCommand();
            String command = input.split(" ")[0];
            switch (command) {
                case "bye":
                    ui.printBye();
                    return;
                case "list":
                    System.out.println(ui.listAllItems(tasks.getTasks()));
                    break;
                case "mark":
                    int num1 = Integer.parseInt(input.split(" ")[1]);
                    tasks.mark(num1, ui);
                    storage.update(ui.listAllItems(tasks.getTasks()));
                    break;
                case "unmark":
                    int num2 = Integer.parseInt(input.split(" ")[1]);
                    tasks.unmark(num2, ui);
                    storage.update(ui.listAllItems(tasks.getTasks()));
                    break;
                case "delete":
                    int num3 = Integer.parseInt(input.split(" ")[1]);
                    tasks.delete(num3, ui);
                    storage.update(ui.listAllItems(tasks.getTasks()));
                    break;
                default:
                    try {
                        tasks.addATask(input);
                        storage.update(ui.listAllItems(tasks.getTasks()));
                    } catch (DukeException e) {
                        ui.printMessage(e.getMessage());
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new Duke("src/main/java/duke.txt").run();
    }

}
