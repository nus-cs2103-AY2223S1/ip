package zeus;

import zeus.command.Command;
import zeus.storage.Storage;
import zeus.task.TaskList;

/**
 * Zeus, the personal chatbot that helps a person keep track of
 * todos, events, deadlines.
 *
 * @author Derrick Khoo
 */
public class Zeus {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs an instance of Zeus.
     *
     * @param filePath the file location for saving data
     */
    public Zeus(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            taskList = new TaskList(storage.loadData());
        } catch (ZeusException e) {
            System.out.println(e);
            taskList = new TaskList();
        }
    }

    public String getGreeting() {
        return ui.greet();
    }

    /**
     * Gets Zeus's response to the user's input.
     *
     * @param input the input from the user
     * @return Zeus's response to the user's input
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.handle(this.storage, this.ui, this.taskList);
        } catch (ZeusException e) {
            return "Zeus says:\n" + ui.formatMessage(e.toString());
        }
    }
}
