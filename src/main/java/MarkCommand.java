public class MarkCommand extends Command {

    int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public void execute(ToDoList list, Ui ui) throws InvalidIndex {
        list.mark(this.index);
    }

    public boolean isExit() {
        return false;
    }
}
