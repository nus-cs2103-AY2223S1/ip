public class UnmarkCommand extends Command {

    int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public void execute(ToDoList list, Ui ui) throws InvalidIndex {
        list.unMark(this.index);
    }

    public boolean isExit() {
        return false;
    }
}
