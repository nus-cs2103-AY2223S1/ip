package bobthebot.command;

import bobthebot.tasks.ToDoList;
import bobthebot.utils.Ui;

public class GoodbyeCommand extends Command {
    private ToDoList list;

    public GoodbyeCommand(ToDoList list) {
        super("goodbye");
        this.list = list;
    }
    @Override
    public String execute() {
        return Ui.sayGoodbye(list);
    }
}
