package bobthebot.command;

import bobthebot.utils.Ui;
import bobthebot.tasks.ToDoList;

public class ListCommand extends Command {
    private ToDoList list;

    public ListCommand(ToDoList list) {
        super("list");
        this.list = list;
    }

    @Override
    public String execute() {
        return Ui.listMessage(list);
    }
}
