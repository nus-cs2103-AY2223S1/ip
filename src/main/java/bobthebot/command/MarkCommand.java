package bobthebot.command;

import bobthebot.tasks.ToDoList;

public class MarkCommand extends Command {
    private int indexToUnmark;
    private ToDoList list;

    public MarkCommand(int indexToUnmark, ToDoList list) {
        super("mark");
        this.indexToUnmark = indexToUnmark;
        this.list = list;
    }

    @Override
    public String execute() {
        return list.markItemDone(this.indexToUnmark);
    }
}
