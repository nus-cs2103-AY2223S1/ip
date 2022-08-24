public class UnmarkCommand extends Commands {
    private int index;
    public UnmarkCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList<Todo> list, Storage storage) {
        Todo item = list.get(index);
        item.unCompleteTask();
        UI.unmarkItemMessage(item);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
