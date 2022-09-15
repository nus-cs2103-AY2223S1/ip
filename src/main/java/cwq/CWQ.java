package cwq;

import cwq.cliui.Ui;
import cwq.parser.Parser;
import cwq.storage.Storage;
import cwq.task.TasksController;

/**
 * Duke App main class
 */
public class CWQ {
    private final Storage storage = new Storage("data/user.dat");
    private final TasksController controller = new TasksController(storage.load());
    private final Ui ui = new Ui();
    private final Parser parser = new Parser();

    private final String LOGO = "Hi, I'm cwq. What can I do for you?";
    private void launch() {
        System.out.println(LOGO);
        ui.startGreeting();
        while (true) {
            String inputText = ui.inputCommand();
            String response = parser.parse(inputText, controller, storage);
            ui.showSplitLine();
            ui.respond(response);
            if (response.equals("Bye. Hope to see you soon!")) {
                System.exit(0);
            }
            ui.continueChat();
            ui.showSplitLine();
        }
    }

    public String getResponse(String input) {
        if (input.toLowerCase().equals("hi")) {
            return LOGO;
        }
        return parser.parse(input, controller, storage);
    }

    public static void main(String[] args) {
        CWQ CWQ = new CWQ();
        CWQ.launch();
    }
}
