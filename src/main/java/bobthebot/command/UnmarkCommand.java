package bobthebot.command;

import bobthebot.tasks.ToDoList;

public class UnmarkCommand extends Command {
    private int indexToUnmark;
    private ToDoList list;

    public UnmarkCommand(int indexToUnmark, ToDoList list) {
        super("unmark");
        this.indexToUnmark = indexToUnmark;
        this.list = list;
    }

    @Override
    public String execute() {
        return list.markItemUndone(this.indexToUnmark);
    }
}
