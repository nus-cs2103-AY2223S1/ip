package bobthebot.command;

import bobthebot.tasks.ToDoList;

public class DeleteCommand extends Command {
    private int indexToDelete;
    private ToDoList list;

    public DeleteCommand(int indexToDelete, ToDoList list) {
        super("delete");
        this.indexToDelete = indexToDelete;
        this.list = list;
    }

    @Override
    public String execute() {
        return list.deleteTask(indexToDelete - 1);
    }
}
