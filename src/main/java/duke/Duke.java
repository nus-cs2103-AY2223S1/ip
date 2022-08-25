package duke;

/**
 * Duke implements the To-do List bot, and allows users
 * to input commands to create a To-do List.
 *
 * @author Alvin Jiang Min Jun
 * @version v0.1
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke constructor which creates an instance of the bot.
     *
     * @param filePath The path of the file to be read from and written into.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * A method to start the bot by accepting user input.
     */
    public void run() {
        this.ui.showWelcome();
        String command = this.ui.nextCommand();
        try {
            Parser p = new Parser(this.tasks);
            while (!p.isBye(p.parseCommand(command))) {
                this.tasks = p.executeCommand(p.parseCommand(command), command);
                storage.writeFile(this.tasks.getList());
                command = this.ui.nextCommand();
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } finally {
            this.ui.showBye();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }


}
