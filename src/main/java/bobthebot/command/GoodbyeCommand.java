package bobthebot.command;

import bobthebot.tasks.ToDoList;
import bobthebot.utils.Ui;

/**
 * Constructs goodbye command.
 */
public class GoodbyeCommand extends Command {
    private ToDoList list;

    /**
     * Constructs goodbye command.
     */
    public GoodbyeCommand(ToDoList list) {
        super("goodbye");
        this.list = list;
    }

    /**
     *  Executes the goodbye command.
     *
     * @returns String representing the goodbye message.
     */
    @Override
    public String execute() {
        return Ui.sayGoodbye(list);
    }
}
